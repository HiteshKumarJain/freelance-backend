package com.myapp.freelance_backend.controller;

import com.myapp.freelance_backend.dto.request.UserPatchRequestDTO;
import com.myapp.freelance_backend.service.UserService;
import com.myapp.freelance_backend.util.ApiResponse;
import com.myapp.freelance_backend.dto.request.UserRegisterRequestDTO;
import com.myapp.freelance_backend.dto.response.UserRegisterResponseDTO;
import com.myapp.freelance_backend.entity.User;
import com.myapp.freelance_backend.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

//POST end points...
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserRegisterRequestDTO requestDTO){
        UserRegisterResponseDTO responseDTO = userService.registerUser(requestDTO);
        return ResponseUtil.success(201,"User created successfully",responseDTO);
    }

    @PostMapping("/users/{userId}/roles/{roleId}")
    public ResponseEntity<ApiResponse> assignRole(@PathVariable Long userId,@PathVariable Long roleId){
        UserRegisterResponseDTO responseDTO = userService.assignRole(userId,roleId);
        return ResponseUtil.success(200,"Role assigned successfully",responseDTO);

    }
//GET end points..
    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getUsers(@RequestParam(required = false) String emailEnd) {
        List<UserRegisterResponseDTO> dtoList = userService.getAllUsers(emailEnd);
        String message = emailEnd !=null ? "Users with Email End : " + emailEnd : "All Users" ;
        return ResponseUtil.success(200,message,dtoList);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable Long id) {
        UserRegisterResponseDTO responseDTO = userService.fetchUserById(id);
        return ResponseUtil.success(200,"User found",responseDTO);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<ApiResponse> findUserByEmail(@PathVariable String email) {
        UserRegisterResponseDTO responseDTO = userService.fetchUserByEmail(email);
        return ResponseUtil.success(200,"User found",responseDTO);    }

    @GetMapping("/user/search")
    public ResponseEntity<ApiResponse> searchUser(@RequestParam String name) {
        List<UserRegisterResponseDTO> responseDTOList = userService.searchUser(name);
        return ResponseUtil.success(200,"User found",responseDTOList);
    }

    @GetMapping("/user/domain/{domain}")
    public ResponseEntity<ApiResponse> findUserByEmailDomain(@PathVariable String domain) {
        List<UserRegisterResponseDTO> requestDTOList = userService.fetchUserByEmailDomain(domain);
        return ResponseUtil.success(200,"Users found with domain : @" + domain,requestDTOList);
    }

    @GetMapping("/user/exists/email/{email}")
    public ResponseEntity<ApiResponse> userEmailExists(@PathVariable String email) {

        if(userService.userEmailExists(email)) {
            return ResponseUtil.success(200,"User found with email : " + email,true);
        }
        return ResponseUtil.success(200,"User not found with email : " + email,false);
    }

    @GetMapping("/user/count/{name}")
    public ResponseEntity<ApiResponse> getUserNameCount(@PathVariable String name) {
        long userCount=userService.getUserNameCount(name);
        return ResponseUtil.success(200,"Count of name : " +name,userCount);
    }

    @GetMapping("/user/find")
    public ResponseEntity<ApiResponse> searchByNameOrEmail(@RequestParam String searchTerm) {
        List<UserRegisterResponseDTO> responseDTOList = userService.searchByNameOrEmail(searchTerm);
        return ResponseUtil.success(200,"User found",responseDTOList);
    }

    @GetMapping("/user/emails")
    public ResponseEntity<ApiResponse> getUserEmails() {
        List<String> userEmails = userService.getAllUserEmails();
        if(userEmails.isEmpty()){
            return ResponseUtil.success(200,"User emails not found " ,null);
        }
        return ResponseUtil.success(200,"User emails found " ,userEmails);
    }

//    PUT end points..
    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse> updateUser( @PathVariable Long id, @Valid@RequestBody UserRegisterRequestDTO requestDto) {
        UserRegisterResponseDTO responseDTO = userService.updateUser(id,requestDto);
        return ResponseUtil.success(200,"User updated successfully",responseDTO);
    }

//    PATCH end points
    @PatchMapping("/user/{id}")
    public ResponseEntity<ApiResponse> partialUpdateUser(@PathVariable Long id , @Valid@RequestBody UserPatchRequestDTO patchRequestDTO) {
       UserRegisterResponseDTO responseDTO = userService.partialUpdateUser(id,patchRequestDTO);
        return ResponseUtil.success(200,"User updated partially",responseDTO);
    }

//DELETE end points..
    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseUtil.success(200,"User deleted successfully",null);

    }
    @DeleteMapping("/users/{userId}/roles/{roleId}")
    public ResponseEntity<ApiResponse> removeUserRole(@PathVariable Long userId,@PathVariable Long roleId) {
        UserRegisterResponseDTO responseDTO = userService.removeUserRole(userId,roleId);
        return ResponseUtil.success(200,"Role removed successfully",responseDTO);
    }
}