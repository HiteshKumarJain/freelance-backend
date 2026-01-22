package com.myapp.freelance_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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
        User savedUser = userRepository.save(user);
        return  ResponseEntity.status(201).body(new ApiResponse("Success","User created successfully",savedUser));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUsers(@RequestParam(required = false) String emailEnd) {
        List<User> users = userRepository.findAll();
        if(emailEnd != null) {
            users = users.stream().filter(u ->u.getEmail().contains(emailEnd)).collect(Collectors.toList());
            if(users.isEmpty()){
                return ResponseEntity.status(404).body(new ApiResponse("error","No user found with email end : " + emailEnd,null));
            }
            return ResponseEntity.status(200).body(new ApiResponse("success","Users with email end : " + emailEnd,users));
        }
        return ResponseEntity.status(200).body(new ApiResponse("success","All users",users));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
           User user = userOptional.get();
        return ResponseEntity.status(200).body(new ApiResponse("success","User found",user));
        }
        return ResponseEntity.status(404).body(new ApiResponse("error","User not found",null));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
        if(!userRepository.existsById(id)) {
            return ResponseEntity.status(404).body(new ApiResponse("error","User not found, cannot delete",null));
        }
        userRepository.deleteById(id);
        return ResponseEntity.status(200).body(new ApiResponse("success","User deleted successfully",null));

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable int id,@RequestBody User user) {
        if(!userRepository.existsById(id)) {
            return ResponseEntity.status(404).body(new ApiResponse("error","User not found, cannot update",null));
        } else if(user.getName() == null || user.getName().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Name is required",null));
        } else if(user.getEmail() == null || user.getEmail().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Email is required",null));
        }
            user.setId(id);
            User updatedUser = userRepository.save(user);
            return ResponseEntity.status(200).body(new ApiResponse("success","User updated successfully",updatedUser));
    }
    @PatchMapping("/user/{id}")
    public ResponseEntity<ApiResponse> partialUpdateUser(@PathVariable int id , @RequestBody User user) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if(!existingUserOptional.isPresent()) {
            return ResponseEntity.status(404).body(new ApiResponse("error","User not found, cannot update",null));
        }
        User existingUser = existingUserOptional.get();
        boolean updated = false;
        if(user.getName() != null && !user.getName().isEmpty()) {
            existingUser.setName(user.getName());
            updated = true;
        }
        if(user.getEmail() != null && !user.getEmail().isEmpty()) {
            existingUser.setEmail(user.getEmail());
            updated = true;
        }
        if(!updated) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Atleast one field must be provided",null));
        }
        User savedUser = userRepository.save(existingUser);
        return ResponseEntity.status(200).body(new ApiResponse("success","User updated partially",savedUser));
    }

}