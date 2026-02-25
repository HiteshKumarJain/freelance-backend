package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.User;

public class UserPostResponseDTO {
    private Long id;
    private String name;
    private String email;

    public UserPostResponseDTO() {

    }
    public UserPostResponseDTO(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.name= user.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
