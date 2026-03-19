package com.myapp.freelance_backend.controller;

import com.myapp.freelance_backend.service.PostService;
import com.myapp.freelance_backend.util.ApiResponse;
import com.myapp.freelance_backend.dto.request.PostCreateRequestDTO;
import com.myapp.freelance_backend.dto.response.AllPostsResponseDTO;
import com.myapp.freelance_backend.dto.response.PostResponseDTO;
import com.myapp.freelance_backend.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<ApiResponse> createPost(@PathVariable Long userId, @Valid @RequestBody PostCreateRequestDTO requestDTO) {
        PostResponseDTO savedPostDTO = postService.createPost(userId,requestDTO);
        return ResponseUtil.success(201,"Post created successfully",savedPostDTO);
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse> getAllPosts(){
        List<AllPostsResponseDTO> AllPostDtoList = postService.getAllPosts();
        return ResponseUtil.success(200,"All posts found",AllPostDtoList);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<ApiResponse> fetchUserPost(@PathVariable Long userId) {
        List<PostResponseDTO> dtoList = postService.fetchUserPost(userId);
        return ResponseUtil.success(200,"Posts of the user id : "+userId,dtoList);
    }

}
