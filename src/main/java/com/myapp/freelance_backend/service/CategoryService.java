package com.myapp.freelance_backend.service;

import com.myapp.freelance_backend.dto.request.CategoryCreateRequestDTO;
import com.myapp.freelance_backend.dto.response.CategoryResponseDTO;
import com.myapp.freelance_backend.dto.response.PostResponseDTO;
import com.myapp.freelance_backend.entity.Category;
import com.myapp.freelance_backend.entity.Post;
import com.myapp.freelance_backend.exception.ResourceAlreadyExistsException;
import com.myapp.freelance_backend.exception.ResourceNotFoundException;
import com.myapp.freelance_backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO createCategory( CategoryCreateRequestDTO requestDTO) {
        if(categoryRepository.existsByName(requestDTO.getName())) throw new ResourceAlreadyExistsException("Category already exists");
        Category entityCategory = new Category();
        entityCategory.setName(requestDTO.getName());
        entityCategory.setDescription(requestDTO.getDescription());
        Category savedCategory = categoryRepository.save(entityCategory);
        return convertToDTO(savedCategory);
    }

    public List<CategoryResponseDTO> fetchAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return convertToDTOList(categoryList);
    }

    public CategoryResponseDTO fetchCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found"));
        return convertToDTO(category);
    }

    public  List<PostResponseDTO> fetchCategoryPostsById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found"));
        List<Post> categoryPosts = category.getPosts();
        List<PostResponseDTO> responseDTOS = new ArrayList<>();
        for(Post post : categoryPosts) {
            responseDTOS.add(new PostResponseDTO(post));
        }
        return responseDTOS;
    }

    private CategoryResponseDTO convertToDTO(Category category) {
        return new CategoryResponseDTO(category);
    }

    private List<CategoryResponseDTO> convertToDTOList(List<Category> categoryList) {
        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryResponseDTOList.add(new CategoryResponseDTO(category));
        }
        return categoryResponseDTOList;
    }

}
