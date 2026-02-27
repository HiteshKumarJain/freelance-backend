package com.myapp.freelance_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);

    @Query("SELECT c FROM Category c WHERE SIZE(c.posts)>0 ")
    List<Category> findByExistingPosts();

    boolean existsByName(String name);
}
