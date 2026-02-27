package com.myapp.freelance_backend.dto.request;

import com.myapp.freelance_backend.Category;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryCreateRequestDTO {
    @NotBlank(message = "Please enter category name")
    @Size(min=3, max=50,message = "Category Name should be more than 3 characters")
    private String name;

    private String description;

    public CategoryCreateRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
