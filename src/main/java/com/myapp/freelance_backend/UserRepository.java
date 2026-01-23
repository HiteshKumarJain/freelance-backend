package com.myapp.freelance_backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String name);
    List<User> findByEmailEndingWith(String domain);
    boolean existsByEmail(String email);
    long countByName(String name);

}
