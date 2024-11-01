package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import com.hospital.hospitalmanagement.repository.ExaminationRepository;
import com.hospital.hospitalmanagement.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationRepository examinationRepository;

    @Override
    public void addExamination(ExaminationDTO examinationDTO) {
        // Prepare parameters for stored procedure
        Integer outpatientId = examinationDTO.getOutPatientId(); // Ensure this is not null
        if (outpatientId == null) {
            throw new IllegalArgumentException("Outpatient ID cannot be null");
        }
        Integer doctorId = examinationDTO.getDoctorId();
        Date examinationDate = new Date(); // Assuming current date
        Date nextExaminationDate = examinationDTO.getNextExaminationDate();
        String diagnosis = examinationDTO.getDiagnosis();
        BigDecimal fee = examinationDTO.getFee();


        // Create comma-separated strings for medication details
        String medicationIds = examinationDTO.getMedications().stream()
                .map(medication -> medication.getMedicationId().toString())
                .collect(Collectors.joining(","));

        String prices = examinationDTO.getMedications().stream()
                .map(medication -> medication.getPrice().toString())
                .collect(Collectors.joining(","));

        String quantities = examinationDTO.getMedications().stream()
                .map(medication -> medication.getQuantity().toString())
                .collect(Collectors.joining(","));

        // Call stored procedure via repository
        examinationRepository.addExamination(outpatientId, doctorId, examinationDate, nextExaminationDate, diagnosis, fee, medicationIds, prices,quantities);
    }
}
