    package com.hospital.hospitalmanagement.api.admin;

    import com.hospital.hospitalmanagement.entity.*;
    import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
    import com.hospital.hospitalmanagement.models.dto.PatientDTO;
    import com.hospital.hospitalmanagement.repository.OutPatientRepository;
    import com.hospital.hospitalmanagement.repository.PatientRepository;
    import com.hospital.hospitalmanagement.service.ExaminationService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.Optional;

    @RestController
    @RequestMapping("examination/")
    public class ExaminationAPI {

        @Autowired
        private ExaminationService examinationService; // Ensure you have the correct service injected
        @Autowired
        private OutPatientRepository outPatientRepository;
        @Autowired
        private PatientRepository patientRepository;

        @PostMapping("/add")
        public ResponseEntity<Map<String, Object>> addExamination(@RequestBody ExaminationDTO examinationDTO) {
            Map<String, Object> response = new HashMap<>();


            try {
                // Step 1: Check if Patient exists
                PatientEntity patient = patientRepository.findById(examinationDTO.getOutPatientId())
                        .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + examinationDTO.getOutPatientId()));

                // Log the patient for debugging
                System.out.println("Patient found: " + patient);

                // Step 2: Check if OutPatientEntity exists
                Optional<OutPatientEntity> optionalOutPatient = outPatientRepository.findByPatient_ID(patient.getID());
                OutPatientEntity outPatient;

                if (optionalOutPatient.isPresent()) {
                    outPatient = optionalOutPatient.get();
                } else {
                    outPatient = new OutPatientEntity();
                    outPatient.setPatient(patient);

                    String generatedCode = String.format("OP%09d", patient.getID());
                    outPatient.setCode(generatedCode);
                    outPatientRepository.save(outPatient);
                }

                // Log the examination DTO
                System.out.println("Examination DTO before saving: " + examinationDTO);

                // Call the service to add the examination
                examinationService.addExamination(examinationDTO);

                response.put("message", "Examination added successfully!");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace for better debugging
                response.put("message", "Failed to add examination: " + e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    }
