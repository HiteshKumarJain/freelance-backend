package com.myapp.freelance_backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserPatchRequestDTO {
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 18,message = "Age should be above or equal to 18")
    @Max(value = 100,message = "Age cannot exceed 100")
    private Integer age;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
    private String phone;

    @Size(max = 200 , message = "The address should be less than 200 characters")
    private String address;

    @Size(min = 8,max=100,message = "Password must be 8-100 characters")
    private String password;
}
