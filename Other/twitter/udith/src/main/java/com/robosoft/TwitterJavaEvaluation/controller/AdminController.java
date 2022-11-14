package com.robosoft.TwitterJavaEvaluation.controller;

import com.robosoft.TwitterJavaEvaluation.model.Admin;
import com.robosoft.TwitterJavaEvaluation.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;
    //Admin sign in
    @GetMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody Admin admin) {
        String sId = adminService.signIn(admin);
        if(sId==null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sId);
    }
    //Admin verifying users
    @PutMapping("/verify/{sId}/{userId}/{status}")
    public ResponseEntity<String> followers(@PathVariable int sId, @PathVariable String userId,@PathVariable Boolean status) {
        if(adminService.verifyUsers(userId, sId, status)) {
            return ResponseEntity.status(HttpStatus.OK).body("Updated.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("Denied");
    }
}
