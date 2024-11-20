package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
//    @EntityGraph(attributePaths = {"department"})
//    Optional<EmployeeEntity> findByUserUsername(String username);
@Query("SELECT e FROM EmployeeEntity e LEFT JOIN FETCH e.department WHERE e.user.username = :username")
Optional<EmployeeEntity> findByUserUsername(String username);
}
