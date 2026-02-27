package com.myapp.freelance_backend;

import com.myapp.freelance_backend.dto.request.PostCreateRequestDTO;
import com.myapp.freelance_backend.dto.response.AllPostsResponseDTO;
import com.myapp.freelance_backend.dto.response.PostResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostController(PostRepository postRepository,UserRepository userRepository,CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/user/{userId}/post")
    public ResponseEntity<ApiResponse> createPost(@PathVariable Long userId, @Valid @RequestBody PostCreateRequestDTO postCreateRequestDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Category> categoryOptional = categoryRepository.findById(postCreateRequestDTO.getCategoryId());
        if(userOptional.isEmpty()) return ResponseEntity.status(404).body(new ApiResponse("error","User not found",null));
        if(categoryOptional.isEmpty()) return ResponseEntity.status(404).body(new ApiResponse("error","Category not found",null));
        User user = userOptional.get();
        Category category = categoryOptional.get();
        Post post = new Post();
        post.setTitle(postCreateRequestDTO.getTitle());
        post.setContent(postCreateRequestDTO.getContent());
        user.addPost(post);
        category.addPost(post);
        Post savedPost = postRepository.save(post);
        PostResponseDTO savedPostDTO = convertToDTO(savedPost);
        return ResponseEntity.status(201).body(new ApiResponse("success","post created successfully",savedPostDTO));
    }

    @GetMapping("/user/allPosts")
    public ResponseEntity<ApiResponse> getAllPosts(){
        List<Post> allPosts = postRepository.findAll();
        List<AllPostsResponseDTO> AllPostDtoList = convertToAllPostDTOList(allPosts);
        return ResponseEntity.status(200).body(new ApiResponse("success","All posts found",AllPostDtoList));
    }

    @GetMapping("/user/getPosts/{userId}")
    public ResponseEntity<ApiResponse> fetchUserPost(@PathVariable Long userId) {
        if(!userRepository.existsById(userId)) return ResponseEntity.status(404).body(new ApiResponse("error","User not found",null));
        List<Post> postsList = userRepository.findById(userId).get().getPosts();
        List<PostResponseDTO> dtoList = convertToDTOList(postsList);
        return ResponseEntity.status(200).body(new ApiResponse("success","Posts of the user id : "+userId,dtoList));
    }

    private PostResponseDTO convertToDTO(Post post) {
        return new PostResponseDTO(post);
    }
    private List<PostResponseDTO> convertToDTOList(List<Post> posts) {
      List<PostResponseDTO>  postsDtoList = new ArrayList<>();
      for(Post p : posts) {
          postsDtoList.add(new PostResponseDTO(p));
      }
      return postsDtoList;
    }
    private List<AllPostsResponseDTO> convertToAllPostDTOList(List<Post> posts) {
        List<AllPostsResponseDTO>  postsDtoList = new ArrayList<>();
        for(Post p : posts) {
            postsDtoList.add(new AllPostsResponseDTO(p));
        }
        return postsDtoList;
    }
}
