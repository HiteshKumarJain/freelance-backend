package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.User;

import java.time.LocalDateTime;

public class UserRegisterResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime createdAt;

    public UserRegisterResponseDTO() {
    }

    public UserRegisterResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole() !=null ? user.getRole().toString() : "USER";
        this.createdAt = user.getCreatedAt();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
