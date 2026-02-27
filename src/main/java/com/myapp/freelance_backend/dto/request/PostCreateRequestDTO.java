package com.myapp.freelance_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostCreateRequestDTO {
    @NotBlank
    @Size(min=3, max=100)
    private String title;

    @NotBlank
    @Size(min = 10, max = 5000, message = "Content must be 10-5000 characters")
    private String content;

    @NotNull(message = "Please enter the category id")
    private Long categoryId;

    public PostCreateRequestDTO() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
