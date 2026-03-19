package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.entity.Post;
import com.myapp.freelance_backend.entity.Role;
import com.myapp.freelance_backend.entity.RoleName;
import com.myapp.freelance_backend.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;


//This class contains nested dto to display the posts with required fields
@Getter
@Setter
@NoArgsConstructor
public class UserRegisterResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private List<PostSummary> posts = new ArrayList<>();
    private Set<RoleSummary> roles = new HashSet<>();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostSummary {
        private Long id;
        private String title;
        private LocalDateTime createdAt;

        public PostSummary (Post post){
            this.id = post.getId();
            this.title = post.getTitle();
            this.createdAt = post.getCreatedAt();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class RoleSummary {
        private Long id;
        private RoleName name;

        public RoleSummary(Role role){
            this.id = role.getId();
            this.name = role.getRoleName();
        }

    }


    public UserRegisterResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        for(Post p : user.getPosts()) {
            this.posts.add(new PostSummary(p));
        }
        for(Role r : user.getRoles()){
            this.roles.add(new RoleSummary(r));
        }
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
