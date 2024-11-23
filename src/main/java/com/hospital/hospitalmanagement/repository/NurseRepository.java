package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.NurseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NurseRepository extends JpaRepository<NurseEntity,Integer> {
    @Query(value= "select * from nurse n  left join employee e on e.id = n.id where e.is_active = 1 and e.is_deleted = 0", nativeQuery =true )
    List<NurseEntity> getAllNurse();

    @Query( value = "SELECT COUNT(*) FROM nurse n left join employee e ON n.id = e.id  where e.is_active = 1 and e.is_deleted = 0 ", nativeQuery = true)
    long countNurse();

    @Query(value = "select e.* from nurse n left join employee e ON e.id = n.id  where e.is_active = 1 and e.is_deleted = 0", nativeQuery = true)
    Page<NurseEntity> findNurse_ByActivePage(Pageable pageable);

    @Query(value = "SELECT * FROM nurse n left join employee e ON e.id = n.id WHERE "
            + "e.is_active = 1 AND e.is_deleted = 0 AND "
            + "(:fullName IS NULL OR :fullName = '' OR CONCAT(e.last_name, ' ', e.first_name) LIKE :fullName) AND "
            + "(:phoneNumber IS NULL OR :phoneNumber = '' OR e.phone_number LIKE :phoneNumber)",
            countQuery = "SELECT COUNT(*) FROM nurse n left join employee e ON e.id = n.id WHERE "
                    + "e.is_active = 1 AND e.is_deleted = 0 AND "
                    + "(:fullName IS NULL OR :fullName = '' OR CONCAT(e.last_name, ' ', e.first_name) LIKE :fullName) AND "
                    + "(:phoneNumber IS NULL OR :phoneNumber = '' OR e.phone_number LIKE :phoneNumber)",
            nativeQuery = true)
    Page<NurseEntity> searchNurse(@Param("fullName") String fullName,
                                    @Param("phoneNumber") String phoneNumber,
                                    Pageable pageable);
}
