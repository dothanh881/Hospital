package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.MedicationEntity;
import com.hospital.hospitalmanagement.entity.TreatmentEntity;
import com.hospital.hospitalmanagement.entity.TreatmentMedicationEntity;
import com.hospital.hospitalmanagement.models.dto.TreatmentDTO;
import com.hospital.hospitalmanagement.models.dto.TreatmentMedicationDTO;
import com.hospital.hospitalmanagement.repository.TreatmentMedicationRepository;
import com.hospital.hospitalmanagement.repository.TreatmentRepository;
import com.hospital.hospitalmanagement.service.TreatmentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
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

        Integer treatmentId = treatmentRepository.addTreatment(admissionId, startDate, endDate, medications, statusId);
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
}
