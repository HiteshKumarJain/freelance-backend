package com.myapp.freelance_backend.dto.response;

import com.myapp.freelance_backend.entity.Category;
import com.myapp.freelance_backend.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private UserPostResponseDTO author;
    private CategorySummary category;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CategorySummary {
        private Long id;
        private String name;

        public CategorySummary(Category category) {
            this.id = category.getId();
            this.name = category.getName();
        }

    }


    public PostResponseDTO(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = new UserPostResponseDTO(post.getAuthor());
        this.createdAt = post.getCreatedAt();
        this.category = new CategorySummary(post.getCategory());
    }

}
