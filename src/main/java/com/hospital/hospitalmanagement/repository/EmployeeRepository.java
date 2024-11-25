package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
//    @EntityGraph(attributePaths = {"department"})
//    Optional<EmployeeEntity> findByUserUsername(String username);
@Query("SELECT e FROM EmployeeEntity e LEFT JOIN FETCH e.department WHERE e.user.username = :username")
Optional<EmployeeEntity> findByUserUsername(String username);

    boolean existsByCode(String code); // This will check the uniqueness of the code
    boolean existsByPhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employee SET is_active = 0, is_deleted = 1 WHERE id = :id", nativeQuery = true)
    int softDeleteEmployee(@Param("id") Integer id);
}


