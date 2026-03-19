package com.myapp.freelance_backend.service;

import com.myapp.freelance_backend.dto.request.PostCreateRequestDTO;
import com.myapp.freelance_backend.dto.response.AllPostsResponseDTO;
import com.myapp.freelance_backend.dto.response.PostResponseDTO;
import com.myapp.freelance_backend.entity.Category;
import com.myapp.freelance_backend.entity.Post;
import com.myapp.freelance_backend.entity.User;
import com.myapp.freelance_backend.exception.ResourceNotFoundException;
import com.myapp.freelance_backend.repository.CategoryRepository;
import com.myapp.freelance_backend.repository.PostRepository;
import com.myapp.freelance_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public PostResponseDTO createPost(Long userId,PostCreateRequestDTO requestDTO) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Category category = categoryRepository.findById(requestDTO.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category not found"));
        Post post = new Post();
        post.setTitle(requestDTO.getTitle());
        post.setContent(requestDTO.getContent());
        user.addPost(post);
        category.addPost(post);
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    public List<AllPostsResponseDTO> getAllPosts(){
        List<Post> allPosts = postRepository.findAll();
        return convertToAllPostDTOList(allPosts);
    }

    public List<PostResponseDTO> fetchUserPost(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Post> postsList = user.getPosts();
        return convertToDTOList(postsList);
    }

    //    Private methods in service
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
