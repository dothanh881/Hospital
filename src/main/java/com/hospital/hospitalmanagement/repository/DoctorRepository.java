package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.EmployeeEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.print.Doc;
import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {
    @Query(value= "select  * from doctor d left join employee e ON e.id = d.id where e.is_active = 1 and e.is_deleted = 0", nativeQuery =true )
    List<DoctorEntity> getAllDoctor();
    @Query( value = "SELECT COUNT(*) FROM doctor d left join employee e ON d.id = e.id  where e.is_active = 1 and e.is_deleted = 0 ", nativeQuery = true)
    long countDoctor();

    @Query(value = "select e.* from doctor d left join employee e ON e.id = d.id  where e.is_active = 1 and e.is_deleted = 0", nativeQuery = true)
    Page<DoctorEntity> findDoctor_ByActivePage(Pageable pageable);

    @Query(value = "SELECT * FROM doctor p left join employee e ON e.id = p.id WHERE "
            + "e.is_active = 1 AND e.is_deleted = 0 AND "
            + "(:fullName IS NULL OR :fullName = '' OR CONCAT(e.last_name, ' ', e.first_name) LIKE :fullName) AND "
            + "(:phoneNumber IS NULL OR :phoneNumber = '' OR e.phone_number LIKE :phoneNumber)",
            countQuery = "SELECT COUNT(*) FROM doctor p left join employee e ON e.id = p.id WHERE "
                    + "e.is_active = 1 AND e.is_deleted = 0 AND "
                    + "(:fullName IS NULL OR :fullName = '' OR CONCAT(e.last_name, ' ', e.first_name) LIKE :fullName) AND "
                    + "(:phoneNumber IS NULL OR :phoneNumber = '' OR e.phone_number LIKE :phoneNumber)",
            nativeQuery = true)
    Page<DoctorEntity> searchDoctor(@Param("fullName") String fullName,
                                         @Param("phoneNumber") String phoneNumber,
                                         Pageable pageable);
}
