package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.Cities;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PatientRepository  extends JpaRepository<PatientEntity,Integer> {

    @Procedure(procedureName = "Patient_ups")
    void callUpsertPatientProcedure(
            Integer Id,
            String p_firstName,
            String p_lastName,
            Date p_dateOfBirth,
            String p_gender,
            String p_street,
            String p_phoneNumber,
            Integer p_cityId,
            Integer p_districtId,
            Integer p_wardId
    );


//    @Query(value= "select  * from patient p where p.is_active = 1 and p.is_deleted = 0", nativeQuery =true )
//    List<PatientEntity> findPatient_ByActive();


    // Updated query to include pagination
    @Query(value = "select * from patient p where p.is_active = 1 and p.is_deleted = 0", nativeQuery = true)
    Page<PatientEntity> findPatient_ByActivePage(Pageable pageable);

    @Query(value = "select * from patient p where p.is_active = 1 and p.is_deleted = 0", nativeQuery = true)
    List<PatientEntity> findPatient_ByActive();
    @Query(value= "select  * from patient p where p.id = :id and p.is_active = 1 and p.is_deleted = 0", nativeQuery =true )
    Optional<PatientEntity> findPatient_Id(@Param(value = "id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE patient SET is_active = 0, is_deleted = 1 WHERE id = :id", nativeQuery = true)
    int softDeletePatient(@Param("id") Integer id);
    @Query("SELECT p.city FROM PatientEntity p WHERE p.ID = :patientId")
    Cities findCityByPatientId(@Param("patientId") int patientId);


    @Query(value = "SELECT * FROM patient p WHERE "
            + "p.is_active = 1 AND p.is_deleted = 0 AND "
            + "(:fullName IS NULL OR :fullName = '' OR CONCAT(p.last_name, ' ', p.first_name) LIKE :fullName) AND "
            + "(:phoneNumber IS NULL OR :phoneNumber = '' OR p.phone_number LIKE :phoneNumber)",
            countQuery = "SELECT COUNT(*) FROM patient p WHERE "
                    + "p.is_active = 1 AND p.is_deleted = 0 AND "
                    + "(:fullName IS NULL OR :fullName = '' OR CONCAT(p.last_name, ' ', p.first_name) LIKE :fullName) AND "
                    + "(:phoneNumber IS NULL OR :phoneNumber = '' OR p.phone_number LIKE :phoneNumber)",
            nativeQuery = true)
    Page<PatientEntity> searchPatients(@Param("fullName") String fullName,
                                       @Param("phoneNumber") String phoneNumber,
                                       Pageable pageable);
}
