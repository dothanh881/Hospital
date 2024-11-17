package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ExaminationRepository extends JpaRepository<ExaminationEntity, Integer> {

    @Procedure(name = "AddExamination")
    void addExamination(Integer outpatientId, Integer doctorId, Date examinationDate, Date nextExaminationDate, String diagnosis, BigDecimal fee, String medicationIds, String prices, String quantities);

    List<ExaminationEntity> findByOutPatient_Patient_ID(Integer patientId);

    @Query(value= "select  * from examination e where e.outpatient_id = :outpatientId and e.is_active = 1 and e.is_deleted = 0", nativeQuery =true )
    List<ExaminationEntity> getAllExaminationByPatient(@Param(value ="outpatientId") Integer outpatientId );

    @Modifying
    @Transactional
    @Query(value = "UPDATE examination SET is_active = 0, is_deleted = 1 WHERE id = :id", nativeQuery = true)
    int examination_del(@Param("id") Integer id);

    @Query(value = "SELECT MONTH(e.examination_date) AS month, COUNT(*) AS count " +
            "FROM examination e " +
            "WHERE YEAR(e.examination_date) = :year and e.is_active = 1 and e.is_deleted = 0 " +
            "GROUP BY MONTH(e.examination_date) " +
            "ORDER BY month", nativeQuery = true)
    List<Object[]> countExaminationsByMonth(@Param("year") int year);
    @Query(value = "select * from examination e  where e.is_active = 1 and e.is_deleted = 0", nativeQuery = true)
    Page<ExaminationEntity> findExamination_ByActivePage(Pageable pageable);

}
