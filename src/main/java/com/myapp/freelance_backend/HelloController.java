package com.myapp.freelance_backend;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user){

        if(user.getName() ==  null || user.getName().isEmpty()) {
           return  ResponseEntity.status(400).body(new ApiResponse("error","Name is required",null));
        }
        if(user.getEmail() ==  null || user.getEmail().isEmpty()) {
           return  ResponseEntity.badRequest().body(new ApiResponse("error","Email is required",null));
//           both of the return statements are same only in name if condition and email if condition
//            but the return statement used in the if condition of mail is a shortcut one
        }
        return  ResponseEntity.status(201).body(new ApiResponse("Success","User created successfully",user));


    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable int id) {
        if(id==1) {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Ravi");
        user1.setEmail("ravi@example.com");

        return ResponseEntity.status(200).body(new ApiResponse("success","User found",user1));
        }
        return ResponseEntity.status(404).body(new ApiResponse("error","User not found",null));
    }

}