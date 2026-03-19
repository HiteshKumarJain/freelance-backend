package com.myapp.freelance_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCreateRequestDTO {
    @NotBlank(message = "Please enter category name")
    @Size(min=3, max=50,message = "Category Name should be more than 3 characters")
    private String name;

    private String description;

}
