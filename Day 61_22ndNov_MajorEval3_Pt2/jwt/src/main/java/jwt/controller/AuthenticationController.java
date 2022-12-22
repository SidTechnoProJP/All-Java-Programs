package jwt.controller;

//<dependency>
//        <groupId>com.twilio.sdk</groupId>
//        <artifactId>twilio</artifactId>
//        <version>7.34.0</version>
//        </dependency>

import com.foodapp.auth.service.EmailService;
import com.foodapp.auth.service.IAuthService;
import com.foodapp.auth.service.IauthenticationService;
import jwt.MyUserDetails;
import jwt.jwtutils.JwtUserDetailsService;
import jwt.jwtutils.TokenManager;
import jwt.models.JwtRequestModel;
import jwt.models.JwtResponseModel;
import jwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
@RestController
public class AuthenticationController {
    @Autowired
    IauthenticationService authenticationService;
    @Autowired
    IAuthService authService;
@Autowired
EmailService emailService;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user){
        try {

            return ResponseEntity.ok(authService.register(user));
        }catch (ConstraintViolationException e){
            return ResponseEntity.badRequest().body("give proper values");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel
                                                 request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }
    @PostMapping("/check")
    public  String check(){
        return  "hi";
    }


    @PatchMapping("/phoneNumber")
    ResponseEntity<?> updatePhoneNumber(@RequestParam String phoneNumber){
        try {


            return ResponseEntity.ok(authenticationService.updatePhoneNumber(phoneNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }
    @PostMapping("/forgetPassword")
    ResponseEntity<?> email(@RequestParam String email){
        try {

            emailService.sendSimpleMessage(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }



    @PostMapping("/validation")
    ResponseEntity<?> update(@RequestParam String otp ){
        try {
            MyUserDetails userDetails= (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(authenticationService.validateUpdate(userDetails.getUser().getUser_id(),otp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Authentication failed");
        }
    }

}
