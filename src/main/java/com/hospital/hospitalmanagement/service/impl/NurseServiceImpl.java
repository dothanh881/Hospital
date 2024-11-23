package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.NurseEntity;
import com.hospital.hospitalmanagement.repository.NurseRepository;
import com.hospital.hospitalmanagement.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired
    private NurseRepository nurseRepository;
    @Override
    public Page<NurseEntity> pageNurse(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        Page<NurseEntity> pageNurse = nurseRepository.findNurse_ByActivePage(pageable);
        return pageNurse;
    }

    @Override
    public Page<NurseEntity> searchNurse(Map<String, Object> param, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo,10);
        // Extract parameters from the map, providing null if missing
        String fullName = (String) param.getOrDefault("fullName", null);
        String phoneNumber = (String) param.getOrDefault("phoneNumberSearch", null);
        if (fullName != null && !fullName.isEmpty()) {
            fullName = "%" + fullName + "%"; // Wildcards for LIKE
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            phoneNumber = "%" + phoneNumber + "%"; // Wildcards for LIKE
        }


        // Call the repository method with dynamic criteria
        return nurseRepository.searchNurse(fullName,phoneNumber, pageable);
    }
}
