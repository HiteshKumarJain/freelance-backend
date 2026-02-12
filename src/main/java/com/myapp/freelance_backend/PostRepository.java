package com.myapp.freelance_backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PostRepository extends JpaRepository<Post,Long> {
}
