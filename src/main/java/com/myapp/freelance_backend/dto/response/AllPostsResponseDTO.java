package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.Post;

import java.time.LocalDateTime;

public class AllPostsResponseDTO {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String author;

    public AllPostsResponseDTO() {
    }

    public AllPostsResponseDTO(Post post) {
     this.id = post.getId();
     this.title = post.getTitle();
     this.createdAt = post.getCreatedAt();
     this.author = post.getAuthor() != null  ? post.getAuthor().getName() : "Unknown";
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
