package foodapp.controller.user;

import foodapp.customexceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import foodapp.customexceptions.*;
import foodapp.entity.Users;
import foodapp.service.system.SystemInterface;
import foodapp.service.user.LoginInterface;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private LoginInterface loginInterface;

    @Autowired
    private SystemInterface systemInterface;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> userLogin(@RequestHeader String username, @RequestHeader String password) throws LoginFailedException, SessionIdExpiredException {
        if (username != null && (password != null))
            return ResponseEntity.of(Optional.of(loginInterface.signIn(username, password)));
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<String> newRegistration(@ModelAttribute @Valid Users user, @RequestPart MultipartFile profilePhotos) throws SignupException, SessionIdExpiredException, UsernameNotFoundException, IOException {
        if (user != null)
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.of(Optional.of(loginInterface.signUp(user, profilePhotos)));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logOut() throws SignOutException {
        return ResponseEntity.of(Optional.of(loginInterface.signOut()));
    }

    @PatchMapping("/user/changePassword")
    public ResponseEntity<String> changePassword(@RequestHeader String newPassword, @RequestHeader String confirmPassword) throws Exception {
        return ResponseEntity.of(Optional.of(loginInterface.changePassword(newPassword, confirmPassword)));
    }

    @PatchMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestHeader String email) throws SessionIdExpiredException, UsernameNotFoundException {
        return ResponseEntity.of(Optional.of(loginInterface.forgotPassword(email)));
    }

    @PatchMapping("/resetPassword/token={token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token, @RequestHeader String newPassword,
                                                @RequestHeader String confirmPassword) throws TokenExpiredException, SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(loginInterface.resetPassword(token, newPassword, confirmPassword)));
    }

    @PostMapping("/sendOTP")
    public ResponseEntity<String> sendOtp(@RequestHeader String email) throws Exception {
        return ResponseEntity.of(Optional.of(systemInterface.sendOTP(email)));
    }

    @PatchMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestParam int otp, @RequestHeader String email) throws Exception {
        return ResponseEntity.of(Optional.of(systemInterface.verifyEmail(otp, email)));
    }

    @PatchMapping("/user/changeProfilePhoto")
    public ResponseEntity<String> changeProfilePhoto(@RequestParam MultipartFile profilePhoto) throws IOException, UpdateFailedException {
        return ResponseEntity.of(Optional.of(loginInterface.changeProfilePhoto(profilePhoto)));
    }

    @GetMapping("/user/showProfilePhoto")
    public ResponseEntity<?> viewProfilePhoto() throws IOException {
        byte[] image = loginInterface.viewProfilePhoto();
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(image);
    }

    @DeleteMapping("/user/deleteProfilePhoto")
    public ResponseEntity<String> removeProfilePhoto() throws IOException {
        return ResponseEntity.of(Optional.of(loginInterface.deleteOldProfilePhoto()));
    }

}
