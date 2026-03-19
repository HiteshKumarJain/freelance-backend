package com.myapp.freelance_backend.controller;

import com.myapp.freelance_backend.service.CategoryService;
import com.myapp.freelance_backend.util.ApiResponse;
import com.myapp.freelance_backend.dto.request.CategoryCreateRequestDTO;
import com.myapp.freelance_backend.dto.response.CategoryResponseDTO;
import com.myapp.freelance_backend.dto.response.PostResponseDTO;
import com.myapp.freelance_backend.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

@PostMapping("/categories")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody CategoryCreateRequestDTO requestDTO) {
    CategoryResponseDTO responseDTO = categoryService.createCategory(requestDTO);
    return ResponseUtil.success(201,"Category created successfully",responseDTO);
 }

 @GetMapping("/categories")
 public ResponseEntity<ApiResponse> fetchAllCategories() {
        List<CategoryResponseDTO> responseDTOList = categoryService.fetchAllCategories();
     return ResponseUtil.success(200,"All categories found",responseDTOList);
 }

 @GetMapping("/categories/{categoryId}")
 public ResponseEntity<ApiResponse> fetchCategoryById(@PathVariable Long categoryId) {
        CategoryResponseDTO responseDTO = categoryService.fetchCategoryById(categoryId);
     return ResponseUtil.success(200,"Category found",responseDTO);
 }

    @GetMapping("/categories/{categoryId}/posts")
    public  ResponseEntity<ApiResponse> fetchCategoryPostsById(@PathVariable Long categoryId) {
        List<PostResponseDTO> responseDTOS = categoryService.fetchCategoryPostsById(categoryId);
        return ResponseUtil.success(200,"Category found",responseDTOS);
  }

}
