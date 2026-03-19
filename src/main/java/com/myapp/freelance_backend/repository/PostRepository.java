package com.myapp.freelance_backend.repository;

import com.myapp.freelance_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
