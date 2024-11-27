package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.ExaminationMedicationDTO;
import com.hospital.hospitalmanagement.models.dto.TreatmentDTO;
import com.hospital.hospitalmanagement.models.dto.TreatmentMedicationDTO;
import com.hospital.hospitalmanagement.repository.TreatmentMedicationRepository;
import com.hospital.hospitalmanagement.repository.TreatmentRepository;
import com.hospital.hospitalmanagement.repository.TreatmentStatusRepository;
import com.hospital.hospitalmanagement.service.TreatmentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private TreatmentMedicationRepository treatmentMedicationRepository;

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> addTreatment(TreatmentDTO treatmentDTO) {
        Map<String, Object> response = new HashMap<>();

        Integer admissionId = treatmentDTO.getAdmissionId();
        Date startDate = treatmentDTO.getStartDate();
        Date endDate = treatmentDTO.getEndDate();
        String medications = treatmentDTO.getMedications();
        Integer statusId = treatmentDTO.getStatusId();
        String description = treatmentDTO.getDescription();
        Integer treatmentId = treatmentRepository.addTreatment(admissionId, startDate, endDate, medications, statusId,description);
        if (treatmentId != null) {
            // Process and set TreatmentMedication entities
            List<TreatmentMedicationEntity> newMedications = new ArrayList<>();
            for (TreatmentMedicationDTO medicationDTO : treatmentDTO.getTreatmentMedications()) {
                TreatmentMedicationEntity treatmentMedication = new TreatmentMedicationEntity();
                MedicationEntity medicationEntity = new MedicationEntity();
                medicationEntity.setID(medicationDTO.getMedicationId());
                treatmentMedication.setMedication(medicationEntity);
                treatmentMedication.setPrice(medicationDTO.getPrice());
                treatmentMedication.setQuantity(medicationDTO.getQuantity());
                treatmentMedication.setTreatment(new TreatmentEntity(treatmentId));

                newMedications.add(treatmentMedication);
            }


            for (TreatmentMedicationEntity treatmentMedication : newMedications) {
                treatmentMedicationRepository.save(treatmentMedication);
            }


            response.put("message", "Thêm mới thành công");
            response.put("treatmentId", treatmentId);

            return ResponseEntity.ok(response);
        } else {

            response.put("message", "Failed to add treatment");
            return ResponseEntity.status(500).body(response);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> updateTreatment(TreatmentDTO treatmentDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Retrieve the examination from the database
            TreatmentEntity treatment = treatmentRepository.findById(treatmentDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Treatment not found"));

            // Update examination fields
            if (treatmentDTO.getEndDate() != null &&
                    treatmentDTO.getStartDate().after(treatmentDTO.getEndDate())) {
                throw new IllegalArgumentException("Ngày bắt đầu phải trước ngày kết thúc điều trị!.");
            }
            treatment.setStartDate(treatmentDTO.getStartDate());
            treatment.setEndDate(treatmentDTO.getEndDate());
            TreatmentStatusEntity treatmentStatus = new TreatmentStatusEntity();
            treatmentStatus.setId(treatmentDTO.getStatusId());
            treatment.setTreatmentStatusEntity(treatmentStatus);
            treatment.setMedications(treatmentDTO.getMedications());
            treatment.setDescription(treatmentDTO.getDescription());


            treatmentRepository.save(treatment);

            // Step 1: Retrieve the existing medications for this examination
            List<TreatmentMedicationEntity> existingMedications = treatmentMedicationRepository
                    .findByTreatment_ID(treatmentDTO.getId());

            // Step 2: Compare existing medications with the new medications
            boolean medicationsChanged = false;
            List<TreatmentMedicationDTO> newMedicationDTOs = treatmentDTO.getTreatmentMedications();

            // Check if the existing medications differ from the new ones
            if (existingMedications.size() != newMedicationDTOs.size()) {
                medicationsChanged = true;  // If the sizes don't match, they have changed
            } else {
                // If the sizes match, compare individual medications
                for (int i = 0; i < existingMedications.size(); i++) {
                    TreatmentMedicationEntity existingMedication = existingMedications.get(i);
                    TreatmentMedicationDTO newMedicationDTO = newMedicationDTOs.get(i);

                    // Compare medication ID, price, and quantity
                    if (!existingMedication.getMedication().getID().equals(newMedicationDTO.getMedicationId()) ||
                            !existingMedication.getPrice().equals(newMedicationDTO.getPrice()) ||
                            existingMedication.getQuantity()!=(newMedicationDTO.getQuantity())) {
                        medicationsChanged = true;  // Found a difference, so medications have changed
                        break;
                    }
                }
            }

            // Step 3: If medications have changed, delete old ones and insert new ones
            if (medicationsChanged) {
                // Delete existing medications for this examination
                treatmentMedicationRepository.deleteMedicationsByTreatmentId(treatmentDTO.getId());

                // Insert new medications
                List<TreatmentMedicationEntity> newMedications = new ArrayList<>();
                for (TreatmentMedicationDTO medicationDTO : newMedicationDTOs) {
                    TreatmentMedicationEntity treatmentMedication = new TreatmentMedicationEntity();
                    MedicationEntity medicationEntity = new MedicationEntity();
                    medicationEntity.setID(medicationDTO.getMedicationId());
                    treatmentMedication.setMedication(medicationEntity); // Set medication entity
                    treatmentMedication.setPrice(medicationDTO.getPrice()); // Ensure correct type
                    treatmentMedication.setQuantity(medicationDTO.getQuantity());
                    treatmentMedication.setTreatment(treatment); // Associate with the examination

                    newMedications.add(treatmentMedication);
                }

                // Step 4: Save the new medications in the examinationMedication table
                treatmentMedicationRepository.saveAll(newMedications);
            } else {
                // If medications haven't changed, just update the examination record as needed
                // You can update the existing medications here if needed (e.g., update price or quantity)
                for (int i = 0; i < existingMedications.size(); i++) {
                    TreatmentMedicationEntity existingMedication = existingMedications.get(i);
                    TreatmentMedicationDTO newMedicationDTO = newMedicationDTOs.get(i);

                    // If there's any need to update the existing medication (e.g., price or quantity change)
                    existingMedication.setPrice(newMedicationDTO.getPrice());
                    existingMedication.setQuantity(newMedicationDTO.getQuantity());
                    treatmentMedicationRepository.save(existingMedication);
                }
            }

            // Step 5: Save the updated examination entity (optional if medications are saved separately)


            // Construct successful response
            response.put("status", "success");
            response.put("message", "Treatment updated successfully");
            response.put("examinationId", treatment.getID()); // Optionally return the updated examination ID

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch (RuntimeException e) {
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
