package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.Cities;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PatientService implements IPatientService {

    @Autowired
    PatientRepository patientRepository;

    public void upsertPatient(PatientEntity patientEntity) {

        Integer cityId = (patientEntity.getCity() != null) ? patientEntity.getCity().getCityId() : null;
        Integer districtId = (patientEntity.getDistrict() != null) ? patientEntity.getDistrict().getDistrictId() : null;
        Integer wardId = (patientEntity.getWard() != null) ? patientEntity.getWard().getWardId() : null;

        patientRepository.callUpsertPatientProcedure(
                patientEntity.getID(),
                patientEntity.getFirstName(),
                patientEntity.getLastName(),
                patientEntity.getDateOfBirth(),
                patientEntity.getGender(),
                patientEntity.getStreet(),
                patientEntity.getPhoneNumber(),
                cityId,
                districtId,
                wardId
        );
    }


    @Override
    public Page<PatientEntity> searchPatients(Map<String, Object> param, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo,10);
        // Extract parameters from the map, providing null if missing
        String fullName = (String) param.getOrDefault("fullName", null);
        String phoneNumber = (String) param.getOrDefault("phoneNumber", null);
        if (fullName != null && !fullName.isEmpty()) {
            fullName = "%" + fullName + "%"; // Add wildcards for partial matching
        }

        // Call the repository method with dynamic criteria
        return patientRepository.searchPatients(fullName,phoneNumber, pageable);
    }

    @Override
    public Page<PatientEntity> pagePatients(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        Page<PatientEntity> pagePatients = patientRepository.findPatient_ByActivePage(pageable);
        return pagePatients;
    }


}