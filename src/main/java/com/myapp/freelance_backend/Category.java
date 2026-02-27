package com.myapp.freelance_backend;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter category name")
    @Size(min=3, max=50,message = "Category Name should be more than 3 characters")
    @Column(unique = true)
    private String name;

    private String description;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.setCategory(this);
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


    public List<Post> getPosts() {
        return posts;
    }

    public void setPost(List<Post> posts) {
        this.posts = posts;
    }
}
