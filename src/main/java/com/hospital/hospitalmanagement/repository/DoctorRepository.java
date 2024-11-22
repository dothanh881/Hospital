package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {
    @Query(value= "select  * from doctor d left join employee e ON e.id = d.id where e.is_active = 1 and e.is_deleted = 0", nativeQuery =true )
    List<DoctorEntity> getAllDoctor();
    @Query( value = "SELECT COUNT(*) FROM doctor d left join employee e ON d.id = e.id  where e.is_active = 1 and e.is_deleted = 0 ", nativeQuery = true)
    long countDoctor();

    @Query(value = "select e.* from doctor d left join employee e ON e.id = d.id  where e.is_active = 1 and e.is_deleted = 0", nativeQuery = true)
    Page<DoctorEntity> findDoctor_ByActivePage(Pageable pageable);

}
