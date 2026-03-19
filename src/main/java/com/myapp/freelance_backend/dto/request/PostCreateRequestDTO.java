package com.myapp.freelance_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateRequestDTO {
    @NotBlank
    @Size(min=3, max=100)
    private String title;

    @NotBlank
    @Size(min = 10, max = 5000, message = "Content must be 10-5000 characters")
    private String content;

    @NotNull(message = "Please enter the category id")
    private Long categoryId;

}
