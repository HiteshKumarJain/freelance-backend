package com.myapp.freelance_backend;

import com.myapp.freelance_backend.dto.request.CategoryCreateRequestDTO;
import com.myapp.freelance_backend.dto.response.CategoryResponseDTO;
import com.myapp.freelance_backend.dto.response.PostResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

@PostMapping("/categories")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody CategoryCreateRequestDTO requestDTO) {
        if(categoryRepository.existsByName(requestDTO.getName())) return ResponseEntity.status(409).body(new ApiResponse("error","category already exists",null));
        Category entityCategory = new Category();
        entityCategory.setName(requestDTO.getName());
        entityCategory.setDescription(requestDTO.getDescription());
        Category savedCategory = categoryRepository.save(entityCategory);
    CategoryResponseDTO responseDTO = convertToDTO(savedCategory);
    return ResponseEntity.status(201).body(new ApiResponse("Success","Category created successfully",responseDTO));

 }

 @GetMapping("/categories")
 public ResponseEntity<ApiResponse> fetchAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDTO> responseDTOList = convertToDTOList(categoryList);
        return ResponseEntity.status(200).body(new ApiResponse("Success","All categories Found",responseDTOList));
 }

 @GetMapping("/categories/{id}")
 public ResponseEntity<ApiResponse> fetchCategoryById(@PathVariable Long id) {
     Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()) return ResponseEntity.status(404).body(new ApiResponse("error","Category not found",null));
        Category category = categoryOptional.get();
        CategoryResponseDTO responseDTO = convertToDTO(category);
        return ResponseEntity.status(200).body(new ApiResponse("Success","Category found",responseDTO));
 }

    @GetMapping("/categories/{id}/posts")
    public ResponseEntity<ApiResponse> fetchCategoryPostsById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()) return ResponseEntity.status(404).body(new ApiResponse("error","Category not found",null));
        Category category = categoryOptional.get();
        List<Post> categoryPosts = category.getPosts();
        List<PostResponseDTO> responseDTOS = new ArrayList<>();
        for(Post post : categoryPosts) {
            responseDTOS.add(new PostResponseDTO(post));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Success","Category found",responseDTOS));
    }

 public static CategoryResponseDTO convertToDTO(Category category) {
        return new CategoryResponseDTO(category);
 }

 public static List<CategoryResponseDTO> convertToDTOList(List<Category> categoryList) {
        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryResponseDTOList.add(new CategoryResponseDTO(category));
        }
        return categoryResponseDTOList;
    }
}
