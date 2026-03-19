package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserPostResponseDTO {
    private Long id;
    private String name;
    private String email;

    public UserPostResponseDTO(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.name= user.getName();
    }

}
