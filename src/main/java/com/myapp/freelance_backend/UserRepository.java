package com.myapp.freelance_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String name);
    List<User> findByEmailEndingWith(String domain);
    boolean existsByEmail(String email);
    long countByName(String name);

    @Query("SELECT u FROM User u WHERE u.email=:search OR u.name=:search")
    List<User> findByNameOrEmail(@Param("search") String search);
    @Query("SELECT u.email FROM User u")
    List<String> getAllEmails();

}
