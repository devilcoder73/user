package com.example.user.repository;

import com.example.user.enums.Tenant;
import com.example.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByTenant(Tenant tenant);
    Optional<User> findByUserId(String userId);
}
