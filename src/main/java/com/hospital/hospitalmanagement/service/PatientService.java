package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.Cities;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

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
}