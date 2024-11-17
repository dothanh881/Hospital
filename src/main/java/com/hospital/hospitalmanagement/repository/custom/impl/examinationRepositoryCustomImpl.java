package com.hospital.hospitalmanagement.repository.custom.impl;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import com.hospital.hospitalmanagement.repository.custom.ExaminationRepositoryCustom;
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

@Repository
public class examinationRepositoryCustomImpl implements ExaminationRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Page<ExaminationEntity> searchExamination(Map<String, Object> params, Pageable pageable) {

        String baseQuery = "SELECT e.* FROM examination e left join outpatient o ON o.outpatient_id =e.outpatient_id WHERE 1=1 and e.is_active = 1 and e.is_deleted = 0";
        String countQuery = "SELECT COUNT(*) FROM examination e inner join outpatient o ON o.outpatient_id =e.outpatient_id WHERE 1=1 and e.is_active = 1 and e.is_deleted = 0";
        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        StringBuilder countBuilder = new StringBuilder(countQuery);

        Map<String, Object> queryParameters = new HashMap<>();

        if (params.containsKey("fromDateExamination") && params.get("fromDateExamination") != null && !params.get("fromDateExamination").toString().isEmpty()) {
            queryBuilder.append(" AND e.examination_date >= :fromDateExamination");
            countBuilder.append(" AND e.examination_date >= :fromDateExamination");
            queryParameters.put("fromDateExamination", params.get("fromDateExamination"));
        }

// Check for toDateAdmission
        if (params.containsKey("toDateExamination") && params.get("toDateExamination") != null && !params.get("toDateExamination").toString().isEmpty()) {
            queryBuilder.append(" AND e.examination_date <= :toDateExamination");
            countBuilder.append(" AND e.examination_date <= :toDateExamination");
            queryParameters.put("toDateExamination", params.get("toDateExamination"));
        }

// Check for fromDateChargeOf
        if (params.containsKey("fromNextDateExamination") && params.get("fromNextDateExamination") != null && !params.get("fromNextDateExamination").toString().isEmpty()) {
            queryBuilder.append(" AND e.next_examination_date >= :fromNextDateExamination");
            countBuilder.append(" AND e.next_examination_date >= :fromNextDateExamination");
            queryParameters.put("fromNextDateExamination", params.get("fromNextDateExamination"));
        }

// Check for toDateChargeOf
        if (params.containsKey("toNextDateExamination") && params.get("toNextDateExamination") != null && !params.get("toNextDateExamination").toString().isEmpty()) {
            queryBuilder.append(" AND e.next_examination_date <= :toNextDateExamination");
            countBuilder.append(" AND e.next_examination_date <= :toNextDateExamination");
            queryParameters.put("toNextDateExamination", params.get("toNextDateExamination"));
        }

// Check for doctorId
        if (params.containsKey("doctorId") && params.get("doctorId") != null && !params.get("doctorId").toString().isEmpty()) {
            queryBuilder.append(" AND e.doctor_id = :doctorId");
            countBuilder.append(" AND e.doctor_id = :doctorId");
            queryParameters.put("doctorId", params.get("doctorId"));
        }



// Check for patientCode
        if (params.containsKey("patientCodeSearch") && params.get("patientCodeSearch") != null && !params.get("patientCodeSearch").toString().isEmpty()) {
            queryBuilder.append(" AND o.code LIKE :patientCodeSearch");
            countBuilder.append(" AND o.code LIKE :patientCodeSearch");
            queryParameters.put("patientCodeSearch", "%" + params.get("patientCodeSearch") + "%");
        }
        // Sort and pagination
        if (pageable.getSort().isSorted()) {
            String orderBy = pageable.getSort().stream()
                    .map(order -> "e." + order.getProperty() + " " + order.getDirection().name())
                    .collect(Collectors.joining(", "));
            queryBuilder.append(" ORDER BY ").append(orderBy);
        }

        // Execute the native query
        Query query = entityManager.createNativeQuery(queryBuilder.toString(), ExaminationEntity.class);
        Query countQueryObj = entityManager.createNativeQuery(countBuilder.toString());

        // Set parameters
        queryParameters.forEach((key, value) -> {
            query.setParameter(key, value);
            countQueryObj.setParameter(key, value);
        });

        // Apply pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Execute queries
        List<ExaminationEntity> resultList = query.getResultList();
        long total = ((Number) countQueryObj.getSingleResult()).longValue();

        return new PageImpl<>(resultList, pageable, total);

        }
}
