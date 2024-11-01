package com.hospital.hospitalmanagement.repository.custom.impl;

import com.hospital.hospitalmanagement.repository.custom.ExaminationRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public class ExaminationRepositoryImpl implements ExaminationRepositoryCustom {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    @Transactional // Make sure to annotate with @Transactional
//    public void addExamination(Integer outpatientId, Integer doctorId, Date examinationDate, Date nextExaminationDate, String diagnosis, BigDecimal fee, String medicationIds) {
//        // Create a StoredProcedureQuery for your stored procedure
//        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("AddExamination");
//
//        // Register parameters based on your stored procedure definition
//        query.registerStoredProcedureParameter("outpatientId", Integer.class);
//        query.registerStoredProcedureParameter("doctorId", Integer.class);
//        query.registerStoredProcedureParameter("examinationDate", Date.class);
//        query.registerStoredProcedureParameter("nextExaminationDate", Date.class);
//        query.registerStoredProcedureParameter("diagnosis", String.class);
//        query.registerStoredProcedureParameter("fee", BigDecimal.class);
//        query.registerStoredProcedureParameter("medicationIds", String.class);
//
//        // Set the parameters for the stored procedure
//        query.setParameter("outpatientId", outpatientId);
//        query.setParameter("doctorId", doctorId);
//        query.setParameter("examinationDate", examinationDate);
//        query.setParameter("nextExaminationDate", nextExaminationDate);
//        query.setParameter("diagnosis", diagnosis);
//        query.setParameter("fee", fee);
//        query.setParameter("medicationIds", medicationIds);
//
//        // Execute the stored procedure
//        query.execute();
//    }
}
