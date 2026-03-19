package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.entity.Role;
import com.myapp.freelance_backend.entity.RoleName;
import com.myapp.freelance_backend.entity.User;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class RoleResponseDTO {
    private Long id;
    private RoleName roleName;
    private List<UserSummary> users = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UserSummary{
        private Long id;
        private String name;
        private String email;

        public UserSummary(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
        }

    }

    public RoleResponseDTO(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
        for(User user : role.getUsers()) {
            this.users.add(new UserSummary(user));
        }
    }


}
