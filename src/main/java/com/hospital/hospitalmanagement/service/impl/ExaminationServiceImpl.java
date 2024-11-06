package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import com.hospital.hospitalmanagement.entity.ExaminationMedicationEntity;
import com.hospital.hospitalmanagement.entity.MedicationEntity;
import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import com.hospital.hospitalmanagement.models.dto.ExaminationMedicationDTO;
import com.hospital.hospitalmanagement.models.dto.MedicationDTO;
import com.hospital.hospitalmanagement.repository.DoctorRepository;
import com.hospital.hospitalmanagement.repository.ExaminationMedicationRepository;
import com.hospital.hospitalmanagement.repository.ExaminationRepository;
import com.hospital.hospitalmanagement.repository.MedicationRepository;
import com.hospital.hospitalmanagement.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationRepository examinationRepository;
    // Assuming you have repositories for Examination and Medication
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ExaminationMedicationRepository examinationMedicationRepository;
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
        String medicationIds = examinationDTO.getExaminationMedications().stream()
                .map(medication -> medication.getMedicationId().toString())
                .collect(Collectors.joining(","));

        String prices = examinationDTO.getExaminationMedications().stream()
                .map(medication -> medication.getPrice().toString())
                .collect(Collectors.joining(","));

        String quantities = examinationDTO.getExaminationMedications().stream()
                .map(medication -> medication.getQuantity().toString())
                .collect(Collectors.joining(","));

        // Call stored procedure via repository
        examinationRepository.addExamination(outpatientId, doctorId, examinationDate, nextExaminationDate, diagnosis, fee, medicationIds, prices,quantities);
    }



    @Transactional
    public ResponseEntity<Map<String, Object>> updateExamination(ExaminationDTO examinationDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Retrieve the examination from the database
            ExaminationEntity examination = examinationRepository.findById(examinationDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Examination not found"));

            // Update examination fields
            DoctorEntity doctor = new DoctorEntity();
            doctor.setID(examinationDTO.getDoctorId()); // Set the doctor ID
            examination.setDoctor(doctor); // Set the doctor on the examination

            examination.setExaminationDate(examinationDTO.getExaminationDate());
            examination.setDiagnosis(examinationDTO.getDiagnosis());
            examination.setNextExaminationDate(examinationDTO.getNextExaminationDate());
            examination.setFee(examinationDTO.getFee());
            examination.setMedications(examinationDTO.getMedications());

            // Step 3: Delete existing medications for this examination
            examinationMedicationRepository.deleteMedicationsByExaminationId(examinationDTO.getId());

            // Step 4: Insert new medications
            List<ExaminationMedicationEntity> newMedications = new ArrayList<>();
            for (ExaminationMedicationDTO medicationDTO : examinationDTO.getExaminationMedications()) {
                ExaminationMedicationEntity examinationMedication = new ExaminationMedicationEntity();
                MedicationEntity medicationEntity = new MedicationEntity();
                medicationEntity.setID(medicationDTO.getMedicationId());
                examinationMedication.setMedication(medicationEntity); // Set medication entity
                examinationMedication.setPrice(medicationDTO.getPrice()); // Ensure correct type
                examinationMedication.setQuantity(medicationDTO.getQuantity());
                examinationMedication.setExamination(examination); // Associate with the examination

                newMedications.add(examinationMedication);
            }

            // Step 5: Save the new medications in the examinationMedication table
            examinationMedicationRepository.saveAll(newMedications);

            // Step 6: Save the updated examination entity (optional if medications are saved separately)
            examinationRepository.save(examination);

            // Construct successful response
            response.put("status", "success");
            response.put("message", "Examination updated successfully");
            response.put("examinationId", examination.getID()); // Optionally return the updated examination ID

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            // Construct error response
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            response.put("status", "error");
            response.put("message", "An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

















}
