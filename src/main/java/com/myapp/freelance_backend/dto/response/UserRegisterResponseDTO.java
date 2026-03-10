package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.Post;
import com.myapp.freelance_backend.Role;
import com.myapp.freelance_backend.RoleName;
import com.myapp.freelance_backend.User;

import java.time.LocalDateTime;
import java.util.*;


//This class contains nested dto to display the posts with required fields
public class UserRegisterResponseDTO {
    private Long id;
    private String name;
    private String email;
//    private String role;
    private LocalDateTime createdAt;
    private List<PostSummary> posts = new ArrayList<>();
    private Set<RoleSummary> roles = new HashSet<>();

    public static class PostSummary {
        private Long id;
        private String title;
        private LocalDateTime createdAt;
        public PostSummary (){

        }
        public PostSummary (Post post){
            this.id = post.getId();
            this.title = post.getTitle();
            this.createdAt = post.getCreatedAt();
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
    }

    public static class RoleSummary {
        private Long id;
        private RoleName name;

        public RoleSummary(){

        }
        public RoleSummary(Role role){
            this.id = role.getId();
            this.name = role.getRoleName();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public RoleName getName() {
            return name;
        }

        public void setName(RoleName name) {
            this.name = name;
        }
    }
    public UserRegisterResponseDTO() {
    }

    public UserRegisterResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
//        this.role = user.getRole() !=null ? user.getRole().toString() : "USER";
        this.createdAt = user.getCreatedAt();
        for(Post p : user.getPosts()) {
            this.posts.add(new PostSummary(p));
        }
        for(Role r : user.getRoles()){
            this.roles.add(new RoleSummary(r));
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public List<PostSummary> getPosts() {
        return posts;
    }

    public void setPosts(List<PostSummary> posts) {
        this.posts = posts;
    }

    public Set<RoleSummary> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleSummary> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisterResponseDTO that = (UserRegisterResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
