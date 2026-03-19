package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AllPostsResponseDTO {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String author;


    public AllPostsResponseDTO(Post post) {
     this.id = post.getId();
     this.title = post.getTitle();
     this.createdAt = post.getCreatedAt();
     this.author = post.getAuthor() != null  ? post.getAuthor().getName() : "Unknown";
    }

}
