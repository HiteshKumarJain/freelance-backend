package com.myapp.freelance_backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RoleRequestDTO {
    @NotBlank(message = "Please enter the role name")
    private String roleName;

    public RoleRequestDTO() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
