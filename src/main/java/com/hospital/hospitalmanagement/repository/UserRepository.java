package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
