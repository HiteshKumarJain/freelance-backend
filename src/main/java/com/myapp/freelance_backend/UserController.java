package com.myapp.freelance_backend;

import com.myapp.freelance_backend.dto.request.UserRegisterRequestDTO;
import com.myapp.freelance_backend.dto.response.UserRegisterResponseDTO;
import jakarta.validation.Valid;
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

//POST end points...
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserRegisterRequestDTO requestDTO){

        if(userRepository.existsByEmail(requestDTO.getEmail())) {
            return ResponseEntity.status(409).body(new ApiResponse("error","Email id already registered",null));
        }
        User entityUser = new User();
        entityUser.setName(requestDTO.getName());
        entityUser.setEmail(requestDTO.getEmail());
        entityUser.setAge(requestDTO.getAge());
        entityUser.setAddress(requestDTO.getAddress());
        entityUser.setPassword(requestDTO.getPassword());
        entityUser.setPhone(requestDTO.getPhone());
        User savedUser = userRepository.save(entityUser);
        UserRegisterResponseDTO responseDTO = convertToDTO(savedUser);
        return  ResponseEntity.status(201).body(new ApiResponse("Success","User created successfully",responseDTO));
    }

//GET end points..
    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getUsers(@RequestParam(required = false) String emailEnd) {
        List<User> users = userRepository.findAll();
        List<UserRegisterResponseDTO> dtoList;
        if(emailEnd != null) {
            users = users.stream().filter(u ->u.getEmail().contains(emailEnd)).collect(Collectors.toList());
            if(users.isEmpty()){
                return ResponseEntity.status(404).body(new ApiResponse("error","No user found with email end : " + emailEnd,null));
            }
            dtoList = convertToDTOList(users);
            return ResponseEntity.status(200).body(new ApiResponse("success","Users with email end : " + emailEnd,dtoList));
        }
        dtoList = convertToDTOList(users);
        return ResponseEntity.status(200).body(new ApiResponse("success","All users",dtoList));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
           User user = userOptional.get();
           UserRegisterResponseDTO responseDTO = convertToDTO(user);
        return ResponseEntity.status(200).body(new ApiResponse("success","User found",responseDTO));
        }
        return ResponseEntity.status(404).body(new ApiResponse("error","User not found",null));
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<ApiResponse> findUserByEmail(@PathVariable String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            UserRegisterResponseDTO responseDTO = convertToDTO(user);
            return ResponseEntity.status(200).body(new ApiResponse("success","User found",responseDTO));
        }
        return ResponseEntity.status(404).body(new ApiResponse("error","User not found",null));
    }

    @GetMapping("/user/search")
    public ResponseEntity<ApiResponse> searchUser(@RequestParam String name) {
        List<User> usersByName = userRepository.findByNameContaining(name);
        if(usersByName.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("error","User not found",null));
        }
        List<UserRegisterResponseDTO> requestDTOList = convertToDTOList(usersByName);

        return ResponseEntity.status(200).body(new ApiResponse("success","Users found with name : " + name,requestDTOList));
    }

    @GetMapping("/user/domain/{domain}")
    public ResponseEntity<ApiResponse> findUserByEmailDomain(@PathVariable String domain) {
        List<User> usersWithDomain = userRepository.findByEmailEndingWith("@"+domain);
        if(usersWithDomain.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("error","User not found",null));
        }
        List<UserRegisterResponseDTO> requestDTOList = convertToDTOList(usersWithDomain);
        return ResponseEntity.status(200).body(new ApiResponse("success","Users found with domain : @" + domain,requestDTOList));
    }

    @GetMapping("/user/exists/email/{email}")
    public ResponseEntity<ApiResponse> userEmailExists(@PathVariable String email) {

        if(userRepository.existsByEmail(email)) {
            return ResponseEntity.status(200).body(new ApiResponse("success","User found with email : " + email,true));
        }
        return ResponseEntity.status(200).body(new ApiResponse("success","User not found with email : " + email,false));
    }

    @GetMapping("/user/count/{name}")
    public ResponseEntity<ApiResponse> getUserNameCount(@PathVariable String name) {
        long userCount=userRepository.countByName(name);
        return ResponseEntity.status(200).body(new ApiResponse("success","Count of name",userCount));
    }

    @GetMapping("/user/find")
    public ResponseEntity<ApiResponse> searchByNameOrEmail(@RequestParam String searchTerm) {
        List<User> usersByNameOrEmail = userRepository.findByNameOrEmail(searchTerm);
        if(usersByNameOrEmail.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("error","Users not found " ,null));
        }
        List<UserRegisterResponseDTO> requestDTOList = convertToDTOList(usersByNameOrEmail);
        return ResponseEntity.status(200).body(new ApiResponse("success","User found ",requestDTOList));
    }

    @GetMapping("/user/emails")
    public ResponseEntity<ApiResponse> getUserEmails() {
        List<String> userEmails = userRepository.getAllEmails();
        if(userEmails.isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("success","User emails not found " ,null));
        }
        return ResponseEntity.status(200).body(new ApiResponse("success","User emails found " ,userEmails));

    }

//    PUT end points..
    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse> updateUser( @PathVariable Long id, @RequestBody User user) {
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
//    PATCH end points
    @PatchMapping("/user/{id}")
    public ResponseEntity<ApiResponse> partialUpdateUser(@PathVariable Long id , @RequestBody User user) {
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
            return ResponseEntity.status(400).body(new ApiResponse("error","At least one field must be provided",null));
        }
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.status(200).body(new ApiResponse("success","User updated partially",updatedUser));
    }

//DELETE end points..
    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        if(!userRepository.existsById(id)) {
            return ResponseEntity.status(404).body(new ApiResponse("error","User not found, cannot delete",null));
        }
        userRepository.deleteById(id);
        return ResponseEntity.status(200).body(new ApiResponse("success","User deleted successfully",null));

    }

    //  private methods in controller
    private UserRegisterResponseDTO convertToDTO(User user) {
        return new UserRegisterResponseDTO(user);
    }

    private List<UserRegisterResponseDTO> convertToDTOList(List<User> users) {
        List<UserRegisterResponseDTO> dtoList = new ArrayList<>();
        for(User u : users) {
            dtoList.add(new UserRegisterResponseDTO(u));
        }
        return dtoList;
    }
}