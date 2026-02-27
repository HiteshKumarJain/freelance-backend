package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.Category;
import com.myapp.freelance_backend.Post;

import java.time.LocalDateTime;

public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private UserPostResponseDTO author;
    private CategorySummary category;

    public static class CategorySummary {
        private Long id;
        private String name;

        public CategorySummary() {

        }
        public CategorySummary(Category category) {
            this.id = category.getId();
            this.name = category.getName();
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
    }

    public PostResponseDTO() {

    }
    public PostResponseDTO(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = new UserPostResponseDTO(post.getAuthor());
        this.createdAt = post.getCreatedAt();
        this.category = new CategorySummary(post.getCategory());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserPostResponseDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserPostResponseDTO author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CategorySummary getCategory() {
        return category;
    }

    public void setCategory(CategorySummary category) {
        this.category = category;
    }
}
