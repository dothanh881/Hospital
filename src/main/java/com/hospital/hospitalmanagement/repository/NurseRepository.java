package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NurseRepository extends JpaRepository<NurseEntity,Integer> {
    @Query(value= "select * from nurse n  left join employee e on e.id = n.id where e.is_active = 1 and e.is_deleted = 0", nativeQuery =true )
    List<NurseEntity> getAllNurse();
}
