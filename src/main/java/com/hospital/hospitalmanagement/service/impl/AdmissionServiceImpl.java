package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.repository.AdmissionRepository;
import com.hospital.hospitalmanagement.repository.custom.AdmisisonRepositoryCustom;
import com.hospital.hospitalmanagement.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    @Autowired
    AdmissionRepository admissionRepository;
    @Autowired
    AdmisisonRepositoryCustom admisisonRepositoryCustom;
    @Override
    public Map<Integer, Long> getAdmissionCountsByMonth(int year) {
        List<Object[]> results = admissionRepository.countAdmissionsByMonth(year);
        Map<Integer, Long> monthCounts = new HashMap<>();

        // Initialize map with all months (to handle months with no data)
        for (int i = 1; i <= 12; i++) {
            monthCounts.put(i, 0L);
        }

        // Populate with actual data
        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            Long count = (Long) result[1];
            monthCounts.put(month, count);
        }

        return monthCounts;
    }
    @Override
    public Page<AdmissionEntity> pageAdmissions(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        Page<AdmissionEntity> pageAdmissions = admissionRepository.findAdmission_ByActivePage(pageable);
        return pageAdmissions;
    }

    @Override
    public Page<AdmissionEntity> searchAdmission(Map<String, Object> param, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo,10);
        // Extract parameters from the map, providing null if missing

        // Call the repository method with dynamic criteria
        return admisisonRepositoryCustom.searchAdmission(param, pageable);

    }
}
