package com.hospital.hospitalmanagement.repository.custom.impl;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.repository.custom.AdmisisonRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
public class AdmissionRepositoryCustomImpl implements AdmisisonRepositoryCustom {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<AdmissionEntity> searchAdmission(Map<String, Object> params, Pageable pageable) {
        // Base SQL query for Admissions
        return null;
//        String baseQuery = "SELECT a.*,i.code FROM admission a left join inpatient i ON i.inpatient_id =a.inpatient_id WHERE 1=1";
//        String countQuery = "SELECT COUNT(*) , i.code FROM admission a inner join inpatient i ON i.inpatient_id =a.inpatient_id WHERE 1=1";
//        StringBuilder queryBuilder = new StringBuilder(baseQuery);
//        StringBuilder countBuilder = new StringBuilder(countQuery);
//
//        Map<String, Object> queryParameters = new HashMap<>();
//
//        // Handle date parameters (if present)
//        // Example: fromDateAdmission, toDateAdmission, fromDateChargeOf, toDateChargeOf
//        // Check for fromDateAdmission
//        if (params.containsKey("fromDateAdmission") && params.get("fromDateAdmission") != null && !params.get("fromDateAdmission").toString().isEmpty()) {
//            queryBuilder.append(" AND a.date_admission >= :fromDateAdmission");
//            countBuilder.append(" AND a.date_admission >= :fromDateAdmission");
//            queryParameters.put("fromDateAdmission", params.get("fromDateAdmission"));
//        }
//
//// Check for toDateAdmission
//        if (params.containsKey("toDateAdmission") && params.get("toDateAdmission") != null && !params.get("toDateAdmission").toString().isEmpty()) {
//            queryBuilder.append(" AND a.date_admission <= :toDateAdmission");
//            countBuilder.append(" AND a.date_admission <= :toDateAdmission");
//            queryParameters.put("toDateAdmission", params.get("toDateAdmission"));
//        }
//
//// Check for fromDateChargeOf
//        if (params.containsKey("fromDateChargeOf") && params.get("fromDateChargeOf") != null && !params.get("fromDateChargeOf").toString().isEmpty()) {
//            queryBuilder.append(" AND a.date_of_discharge >= :fromDateChargeOf");
//            countBuilder.append(" AND a.date_of_discharge >= :fromDateChargeOf");
//            queryParameters.put("fromDateChargeOf", params.get("fromDateChargeOf"));
//        }
//
//// Check for toDateChargeOf
//        if (params.containsKey("toDateChargeOf") && params.get("toDateChargeOf") != null && !params.get("toDateChargeOf").toString().isEmpty()) {
//            queryBuilder.append(" AND a.date_of_discharge <= :toDateChargeOf");
//            countBuilder.append(" AND a.date_of_discharge <= :toDateChargeOf");
//            queryParameters.put("toDateChargeOf", params.get("toDateChargeOf"));
//        }
//
//// Check for doctorId
//        if (params.containsKey("doctorId") && params.get("doctorId") != null && !params.get("doctorId").toString().isEmpty()) {
//            queryBuilder.append(" AND a.doctor_id = :doctorId");
//            countBuilder.append(" AND a.doctor_id = :doctorId");
//            queryParameters.put("doctorId", params.get("doctorId"));
//        }
//
//// Check for nurseId
//        if (params.containsKey("nurseId") && params.get("nurseId") != null && !params.get("nurseId").toString().isEmpty()) {
//            queryBuilder.append(" AND a.nurse_id = :nurseId");
//            countBuilder.append(" AND a.nurse_id = :nurseId");
//            queryParameters.put("nurseId", params.get("nurseId"));
//        }
//
//// Check for patientCode
//        if (params.containsKey("patientCode") && params.get("patientCode") != null && !params.get("patientCode").toString().isEmpty()) {
//            queryBuilder.append(" AND i.code LIKE :patientCode");
//            countBuilder.append(" AND i.code LIKE :patientCode");
//            queryParameters.put("patientCode", "%" + params.get("patientCode") + "%");
//        }
//        // Sort and pagination
//        if (pageable.getSort().isSorted()) {
//            String orderBy = pageable.getSort().stream()
//                    .map(order -> "a." + order.getProperty() + " " + order.getDirection().name())
//                    .collect(Collectors.joining(", "));
//            queryBuilder.append(" ORDER BY ").append(orderBy);
//        }
//
//        // Execute the native query
//        Query query = entityManager.createNativeQuery(queryBuilder.toString(), AdmissionEntity.class);
//        Query countQueryObj = entityManager.createNativeQuery(countBuilder.toString());
//
//        // Set parameters
//        queryParameters.forEach((key, value) -> {
//            query.setParameter(key, value);
//            countQueryObj.setParameter(key, value);
//        });
//
//        // Apply pagination
//        query.setFirstResult((int) pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//
//        // Execute queries
//        List<AdmissionEntity> resultList = query.getResultList();
//        long total = ((Number) countQueryObj.getSingleResult()).longValue();
//
//        return new PageImpl<>(resultList, pageable, total);
    }

}
