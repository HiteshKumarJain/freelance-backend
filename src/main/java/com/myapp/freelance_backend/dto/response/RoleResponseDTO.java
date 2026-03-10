package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.Role;
import com.myapp.freelance_backend.RoleName;
import com.myapp.freelance_backend.User;

import java.util.ArrayList;
import java.util.List;


public class RoleResponseDTO {
    private Long id;
    private RoleName roleName;
    private List<UserSummary> users = new ArrayList<>();

    public static class UserSummary{
        private Long id;
        private String name;
        private String email;

        public UserSummary() {
        }
        public UserSummary(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
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
    public RoleResponseDTO() {
    }
    public RoleResponseDTO(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
        for(User user : role.getUsers()) {
            this.users.add(new UserSummary(user));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public List<UserSummary> getUsers() {
        return users;
    }

    public void setUsers(List<UserSummary> users) {
        this.users = users;
    }
}
