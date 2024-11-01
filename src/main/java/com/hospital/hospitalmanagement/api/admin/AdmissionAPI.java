package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.AdmissionDTO;
import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import com.hospital.hospitalmanagement.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@RestController
public class AdmissionAPI {


    @Autowired
    private InPatientRepository inPatientRepository ;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private DoctorRepository doctorRepository; // New repository for Doctor
    @Autowired
    private NurseRepository nurseRepository; // New repository for Nurse
    @Autowired
    private RoomRepository roomRepository; // New repository for Room
    @PostMapping("admission/add")
    public ResponseEntity<Map<String, Object>> addAdmission(@RequestBody AdmissionDTO admissionDTO) {

        Map<String, Object> response = new HashMap<>();

        try {
            // Step 1: Check if Patient exists
            PatientEntity patient = patientRepository.findById(admissionDTO.getInpatientId())
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + admissionDTO.getInpatientId()));

            // Log the patient for debugging
            System.out.println("Patient found: " + patient);

            // Step 2: Check if OutPatientEntity exists
            Optional<InPatientEntity> optionalInPatient = inPatientRepository.findByPatient_ID(patient.getID());
            InPatientEntity inPatient;

            if (optionalInPatient.isPresent()) {
                inPatient = optionalInPatient.get();
            } else {
                inPatient = new InPatientEntity();
                inPatient.setPatient(patient);

                String generatedCode = String.format("IP%09d", patient.getID());
                inPatient.setCode(generatedCode);
                inPatientRepository.save(inPatient);
            }

            // Log the examination DTO
            System.out.println("Examination DTO before saving: " + admissionDTO);

            // Call the service to add the examination
            // Step 3: Fetch related entities (Doctor, Nurse, Room)
            DoctorEntity doctor = doctorRepository.findById(admissionDTO.getDoctorId())
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found with ID: " + admissionDTO.getDoctorId()));
            NurseEntity nurse = nurseRepository.findById(admissionDTO.getNurseId())
                    .orElseThrow(() -> new IllegalArgumentException("Nurse not found with ID: " + admissionDTO.getNurseId()));
            RoomEntity room = roomRepository.findById(admissionDTO.getSickroom())
                    .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + admissionDTO.getSickroom()));

            AdmissionEntity admissionEntity = new AdmissionEntity();
            admissionEntity.setInPatient(inPatient); // Ensure the inPatient entity is set correctly
            admissionEntity.setDoctor(doctor); // Set the doctor entity
            admissionEntity.setNurse(nurse); // Set the nurse entity
            admissionEntity.setRoom(room); // Set the room entity
            admissionEntity.setDateAdmission(admissionDTO.getDateAdmission());
            admissionEntity.setDiagnosis(admissionDTO.getDiagnosis());
            admissionEntity.setDateOfDischarge(admissionDTO.getDateOfDischarge());
            admissionEntity.setFee(admissionDTO.getFee());
            admissionRepository.save(admissionEntity);


            response.put("message", "Admission added successfully!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for better debugging
            response.put("message", "Failed to add Admission: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }




    }
}
