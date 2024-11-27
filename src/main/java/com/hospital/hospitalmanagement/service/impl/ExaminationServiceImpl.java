package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import com.hospital.hospitalmanagement.models.dto.ExaminationMedicationDTO;
import com.hospital.hospitalmanagement.models.dto.MedicationDTO;
import com.hospital.hospitalmanagement.repository.DoctorRepository;
import com.hospital.hospitalmanagement.repository.ExaminationMedicationRepository;
import com.hospital.hospitalmanagement.repository.ExaminationRepository;
import com.hospital.hospitalmanagement.repository.MedicationRepository;
import com.hospital.hospitalmanagement.repository.custom.ExaminationRepositoryCustom;
import com.hospital.hospitalmanagement.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private ExaminationRepositoryCustom examinationRepositoryCustom;
    @Override
    public void addExamination(ExaminationDTO examinationDTO) {
        // Prepare parameters for stored procedure
        Integer outpatientId = examinationDTO.getOutPatientId(); // Ensure this is not null
        if (outpatientId == null) {
            throw new IllegalArgumentException("Outpatient ID cannot be null");
        }
        if (examinationDTO.getNextExaminationDate() != null &&
                examinationDTO.getExaminationDate().after(examinationDTO.getNextExaminationDate())) {
            throw new IllegalArgumentException("Ngày khám phải trước ngày tái khám!.");
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

            if (examinationDTO.getNextExaminationDate() != null &&
                    examinationDTO.getExaminationDate().after(examinationDTO.getNextExaminationDate())) {
                throw new IllegalArgumentException("Ngày khám phải trước ngày tái khám!.");
            }
            examination.setExaminationDate(examinationDTO.getExaminationDate());
            examination.setNextExaminationDate(examinationDTO.getNextExaminationDate());
            examination.setDiagnosis(examinationDTO.getDiagnosis());
            examination.setFee(examinationDTO.getFee());
            examination.setMedications(examinationDTO.getMedications());


            // Step 1: Retrieve the existing medications for this examination
            List<ExaminationMedicationEntity> existingMedications = examinationMedicationRepository
                    .findByExamination_ID(examinationDTO.getId());

            // Step 2: Compare existing medications with the new medications
            boolean medicationsChanged = false;
            List<ExaminationMedicationDTO> newMedicationDTOs = examinationDTO.getExaminationMedications();

            // Check if the existing medications differ from the new ones
            if (existingMedications.size() != newMedicationDTOs.size()) {
                medicationsChanged = true;  // If the sizes don't match, they have changed
            } else {
                // If the sizes match, compare individual medications
                for (int i = 0; i < existingMedications.size(); i++) {
                    ExaminationMedicationEntity existingMedication = existingMedications.get(i);
                    ExaminationMedicationDTO newMedicationDTO = newMedicationDTOs.get(i);

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
                examinationMedicationRepository.deleteMedicationsByExaminationId(examinationDTO.getId());

                // Insert new medications
                List<ExaminationMedicationEntity> newMedications = new ArrayList<>();
                for (ExaminationMedicationDTO medicationDTO : newMedicationDTOs) {
                    ExaminationMedicationEntity examinationMedication = new ExaminationMedicationEntity();
                    MedicationEntity medicationEntity = new MedicationEntity();
                    medicationEntity.setID(medicationDTO.getMedicationId());
                    examinationMedication.setMedication(medicationEntity); // Set medication entity
                    examinationMedication.setPrice(medicationDTO.getPrice()); // Ensure correct type
                    examinationMedication.setQuantity(medicationDTO.getQuantity());
                    examinationMedication.setExamination(examination); // Associate with the examination

                    newMedications.add(examinationMedication);
                }

                // Step 4: Save the new medications in the examinationMedication table
                examinationMedicationRepository.saveAll(newMedications);
            } else {
                // If medications haven't changed, just update the examination record as needed
                // You can update the existing medications here if needed (e.g., update price or quantity)
                for (int i = 0; i < existingMedications.size(); i++) {
                    ExaminationMedicationEntity existingMedication = existingMedications.get(i);
                    ExaminationMedicationDTO newMedicationDTO = newMedicationDTOs.get(i);

                    // If there's any need to update the existing medication (e.g., price or quantity change)
                    existingMedication.setPrice(newMedicationDTO.getPrice());
                    existingMedication.setQuantity(newMedicationDTO.getQuantity());
                    examinationMedicationRepository.save(existingMedication);
                }
            }

            // Step 5: Save the updated examination entity (optional if medications are saved separately)
            examinationRepository.save(examination);

            // Construct successful response
            response.put("status", "success");
            response.put("message", "Cập nhật thành công");
            response.put("examinationId", examination.getID()); // Optionally return the updated examination ID

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
            response.put("message", "Cập nhật không thành công: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    // Method to get the count of examinations per month for a given year
    public Map<Integer, Long> getExaminationCountsByMonth(int year) {
        List<Object[]> results = examinationRepository.countExaminationsByMonth(year);
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
    public Page<ExaminationEntity> pageExaminations(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        Page<ExaminationEntity> pageExaminations = examinationRepository.findExamination_ByActivePage(pageable);
        return pageExaminations;
    }

    @Override
    public Page<ExaminationEntity> searchExamination(Map<String, Object> param, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo,10);
        // Extract parameters from the map, providing null if missing

        // Call the repository method with dynamic criteria
        return examinationRepositoryCustom.searchExamination(param, pageable);
    }


}
