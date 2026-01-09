package com.myapp.freelance_backend;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody User user){

        if(user.getName() ==  null || user.getName().isEmpty()) {
           return  new ApiResponse("error","Name is required",null);
        }
        if(user.getEmail() ==  null || user.getEmail().isEmpty()) {
           return  new ApiResponse("error","Email is required",null);
        }
        return  new ApiResponse("Success","User created successfully",user);


    }


}