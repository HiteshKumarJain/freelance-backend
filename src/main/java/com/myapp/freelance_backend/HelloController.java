package com.myapp.freelance_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Day 1 Complete! My first API!";
    }

    @GetMapping("/goodbye")
    public String goodbye(){
        return "That's it for Today,GOOD BYE..!!!";
    }
}