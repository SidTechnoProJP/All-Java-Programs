package example.loginpage.controller;

import example.loginpage.coustomexception.LoginFailedException;
import example.loginpage.coustomexception.SignOutException;
import example.loginpage.coustomexception.SignupException;
import example.loginpage.model.User;
import example.loginpage.service.LoginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginPageController {

    @Autowired
    private LoginInterface loginInterface;

    @PostMapping("/sign-in")
    public ResponseEntity<String> userLogin(@RequestParam String username, @RequestParam String password) throws LoginFailedException {
        if (username != null && (password != null))
            return ResponseEntity.of(Optional.of(loginInterface.signIn(username, password)));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization failed.");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> newRegistration(@RequestBody User user) throws SignupException {
        if (user != null)
            return ResponseEntity.of(Optional.of(loginInterface.signUp(user)));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide valid details for registration");
    }

    @DeleteMapping("/sign-out")
    public ResponseEntity<String> logOut() throws SignOutException {
        return ResponseEntity.of(Optional.of(loginInterface.signOut()));
    }
}
