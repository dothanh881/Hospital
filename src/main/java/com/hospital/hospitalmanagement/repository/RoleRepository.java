package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    Optional<RoleEntity> findByName(String name);
}
