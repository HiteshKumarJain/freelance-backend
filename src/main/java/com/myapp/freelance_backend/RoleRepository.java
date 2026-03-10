package com.myapp.freelance_backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);
    boolean existsByRoleName(RoleName roleName);
}
