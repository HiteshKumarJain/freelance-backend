package com.myapp.freelance_backend.service;

import com.myapp.freelance_backend.dto.request.UserPatchRequestDTO;
import com.myapp.freelance_backend.dto.request.UserRegisterRequestDTO;
import com.myapp.freelance_backend.dto.response.UserRegisterResponseDTO;
import com.myapp.freelance_backend.entity.Role;
import com.myapp.freelance_backend.entity.RoleName;
import com.myapp.freelance_backend.entity.User;
import com.myapp.freelance_backend.exception.ResourceAlreadyExistsException;
import com.myapp.freelance_backend.exception.ResourceNotFoundException;
import com.myapp.freelance_backend.repository.RoleRepository;
import com.myapp.freelance_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,RoleRepository roleRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTO requestDTO) {
        if(userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already registered");
        }
        Optional<Role> foundRoleOptional = roleRepository.findByRoleName(RoleName.ROLE_USER);
        if(foundRoleOptional.isEmpty()) {
            throw new ResourceNotFoundException("No Default_Role found please create role");
        }
        User entityUser = new User();
        entityUser.setName(requestDTO.getName());
        entityUser.setEmail(requestDTO.getEmail());
        entityUser.setAge(requestDTO.getAge());
        entityUser.setAddress(requestDTO.getAddress());
        entityUser.setPassword(requestDTO.getPassword());
        entityUser.setPhone(requestDTO.getPhone());
        Role foundRole = foundRoleOptional.get();
        entityUser.addRole(foundRole);
        User savedUser = userRepository.save(entityUser);
        return convertToDto(savedUser);
    }

    public UserRegisterResponseDTO assignRole(Long userId, Long roleId) {
        User entityUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Role entityRole = roleRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role not found"));
        if(entityUser.getRoles().contains(entityRole)) throw new ResourceAlreadyExistsException("User already exists with this role");
        entityUser.addRole(entityRole);
        User savedUser = userRepository.save(entityUser);
        return convertToDto(savedUser);
    }

    public List<UserRegisterResponseDTO> getAllUsers(String emailEnd) {
        List<User> userList;
        if(emailEnd != null && !emailEnd.isBlank()) {
            userList = userRepository.findByEmailEndingWith("@"+emailEnd);
        } else{
            userList = userRepository.findAll();
        }
        return convertToDtoList(userList);
    }

    public UserRegisterResponseDTO fetchUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with this Id"));
        return convertToDto(user);
    }

    public UserRegisterResponseDTO fetchUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found with this email"));
        return convertToDto(user);
    }

    public List<UserRegisterResponseDTO> searchUser(String name) {
        List<User> userListByName = userRepository.findByNameContaining(name);
        if(userListByName.isEmpty()) throw new ResourceNotFoundException("User not found");
        return convertToDtoList(userListByName);
    }

    public List<UserRegisterResponseDTO> fetchUserByEmailDomain(String domain){
        List<User> userList = userRepository.findByEmailEndingWith("@"+domain);
        if(userList.isEmpty()) throw new ResourceNotFoundException("Users not found with domain : @" + domain);
        return convertToDtoList(userList);
    }

    public boolean userEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    public long getUserNameCount(String name){
        return userRepository.countByName(name);
    }

    public List<UserRegisterResponseDTO> searchByNameOrEmail(String searchTerm){
        List<User> userList = userRepository.findByNameOrEmail(searchTerm);
        if(userList.isEmpty()) throw new ResourceNotFoundException("Users not found");
        return convertToDtoList(userList);
    }

    public List<String> getAllUserEmails(){
         return userRepository.getAllEmails();
    }

    public UserRegisterResponseDTO updateUser(Long id, UserRegisterRequestDTO requestDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found, cannot update"));
        existingUser.setId(id);
        existingUser.setName(requestDTO.getName());
        existingUser.setEmail(requestDTO.getEmail());
        existingUser.setAge(requestDTO.getAge());
        existingUser.setPhone(requestDTO.getPhone());
        existingUser.setAddress(requestDTO.getAddress());
        existingUser.setPassword(requestDTO.getPassword());
        User updatedUser = userRepository.save(existingUser);
        return convertToDto(updatedUser);
    }

    public UserRegisterResponseDTO partialUpdateUser(Long id , UserPatchRequestDTO user) {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found, cannot update"));
        boolean updated = false;
        if(user.getName() != null && !user.getName().isBlank()) {
            existingUser.setName(user.getName());
            updated = true;
        }
        if(user.getEmail() != null && !user.getEmail().isBlank()) {
            if(userRepository.existsByEmail(user.getEmail())) throw new ResourceAlreadyExistsException("Email already exists");
            existingUser.setEmail(user.getEmail());
            updated = true;
        }
        if(user.getAge() != null) {
            existingUser.setAge(user.getAge());
            updated = true;
        }
        if(user.getPhone() != null && !user.getPhone().isEmpty()){
            existingUser.setPhone(user.getPhone());
            updated=true;
        }
        if(user.getAddress()!=null && !user.getAddress().isEmpty()){
            existingUser.setAddress(user.getAddress());
            updated = true;
        }
        if(user.getPassword() !=null && !user.getPassword().isEmpty()){
            existingUser.setPassword(user.getPassword());
            updated = true;
        }
        if(!updated) {
            throw new IllegalArgumentException("At least one field must be provided");
        }
        User updatedUser = userRepository.save(existingUser);
        return convertToDto(updatedUser);
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found, cannot delete");
        }
        userRepository.deleteById(id);
    }

    public UserRegisterResponseDTO removeUserRole(Long userId, Long roleId) {

        User entityUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Role entityRole = roleRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role not found"));
        if(!entityUser.getRoles().contains(entityRole)) throw new ResourceNotFoundException("User doesn't have this role");
        entityUser.removeRole(entityRole);
        User savedUser = userRepository.save(entityUser);
        return convertToDto(savedUser);

    }

//    Private methods in service
    private UserRegisterResponseDTO convertToDto(User user) {
        return new UserRegisterResponseDTO(user);
    }
    private List<UserRegisterResponseDTO> convertToDtoList(List<User> userList) {
        List<UserRegisterResponseDTO> dtoList = new ArrayList<>();
        for(User user : userList) {
            dtoList.add(new UserRegisterResponseDTO(user));
        }
        return dtoList;
    }
}
