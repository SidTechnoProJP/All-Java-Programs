package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllers {
    @GetMapping("/")
    String return1() {
        return "Hello World";
    }
}
