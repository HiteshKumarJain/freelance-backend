package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.Category;

import java.time.LocalDateTime;

public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Integer postCount;

    public CategoryResponseDTO() {

    }
    public CategoryResponseDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.createdAt = category.getCreatedAt();
        this.postCount = category.getPosts().size();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }
}
