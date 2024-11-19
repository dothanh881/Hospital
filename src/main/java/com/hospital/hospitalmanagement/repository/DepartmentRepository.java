package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
    @Query(value = "select * from department d  where d.is_active = 1 and d.is_deleted = 0", nativeQuery = true)
    List<DepartmentEntity> findDepartments_ByActivePage();
}
