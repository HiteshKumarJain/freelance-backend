package com.myapp.freelance_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Day 1 Complete! My first API!";
    }

//    The below is the code for @RequestParam
    @GetMapping("/hello_name")
    public String helloName(@RequestParam String yourname) {
        return "Hello, " + yourname + " !";
    }

//    The below is the code for @PathVariable
    @GetMapping("/welcome/{name}")
    public String welcome(@PathVariable String name){
        return "Welcome Home, " + name;
    }
    @GetMapping("/goodbye")
    public String goodbye(){
        return "That's it for Today,GOOD BYE..!!!";
    }


}