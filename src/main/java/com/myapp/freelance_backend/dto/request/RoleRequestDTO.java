package com.myapp.freelance_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleRequestDTO {
    @NotBlank(message = "Please enter the role name")
    private String roleName;

}
