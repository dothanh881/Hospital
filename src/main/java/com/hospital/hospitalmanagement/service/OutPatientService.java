package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.OutPatientEntity;
import com.hospital.hospitalmanagement.models.dto.OutPatientDTO;
import com.hospital.hospitalmanagement.models.dto.OutPatientWithExaminationDTO;
import com.hospital.hospitalmanagement.repository.OutPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutPatientService {

    @Autowired
    private OutPatientRepository outPatientRepository;

//    public OutPatientEntity addNewOutPatient(OutPatientWithExaminationDTO dto) {
//
//        OutPatientEntity outPatient = new OutPatientEntity();
//        outPatient.setFirstName(dto.getFirstName());
//        outPatient.setLastName(dto.getLastName());
//        outPatient.setGender(dto.getGender());
//        outPatient.setAddress(dto.getAddress());
//        outPatient.setPhoneNumber(dto.getPhoneNumber());
//        outPatient.setVisitReason(dto.getVisitReason());
//
//
//        return outPatientRepository.save(outPatient);
//    }
//
//    // Convert OutPatient entity to DTO (if needed)
//    public OutPatientDTO convertToOutPatientDTO(OutPatientEntity outPatient) {
//        OutPatientDTO dto = new OutPatientDTO();
//        dto.setId(outPatient.getId());
//        dto.setFirstName(outPatient.getFirstName());
//        dto.setLastName(outPatient.getLastName());
//        dto.setGender(outPatient.getGender());
//        dto.setVisitReason(outPatient.getVisitReason());
//        return dto;
//    }
}