package com.myapp.freelance_backend;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter name")
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters")
    private String name;

    @NotBlank(message = "Please enter email")
    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 18,message = "Age should be above or equal to 18")
    @Max(value = 100,message = "Age cannot exceed 100")
    @NotNull(message = "Please enter age")
    private Integer age;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
    private String phone;

    @Size(max = 200 , message = "The address should be less than 200 characters")
    private String address;

    @NotBlank(message = "Please enter password")
    @Size(min = 8,max=100,message = "Password must be 8-100 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean active;

    @Column(updatable=false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PrePersist
    protected void onCreate(){
        if (this.role == null) {
            this.role = Role.USER;  // Default
        }

        if (this.active == null) {
            this.active = true;  // Default
        }
        this.createdAt = LocalDateTime.now();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void addPost(Post post) {
        this.posts.add(post);
        post.setAuthor(this);
    }
    public void removePost(Post post) {
        this.posts.remove(post);
        post.setAuthor(null);
    }
}
