    package com.hospital.hospitalmanagement.api.admin;

    import com.hospital.hospitalmanagement.entity.*;
    import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
    import com.hospital.hospitalmanagement.models.dto.PatientDTO;
    import com.hospital.hospitalmanagement.repository.ExaminationRepository;
    import com.hospital.hospitalmanagement.repository.OutPatientRepository;
    import com.hospital.hospitalmanagement.repository.PatientRepository;
    import com.hospital.hospitalmanagement.service.ExaminationService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.web.bind.annotation.*;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.Optional;

    @RestController
    @RequestMapping("/examination/")
    public class ExaminationAPI {

        @Autowired
        private ExaminationService examinationService;
        @Autowired
        private OutPatientRepository outPatientRepository;
        @Autowired
        private PatientRepository patientRepository;
        @Autowired
        private ExaminationRepository examinationRepository;

        @PostMapping("/add")
        public ResponseEntity<Map<String, Object>> addExamination(@RequestBody ExaminationDTO examinationDTO) {
            Map<String, Object> response = new HashMap<>();


            try {

                PatientEntity patient = patientRepository.findById(examinationDTO.getOutPatientId())
                        .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + examinationDTO.getOutPatientId()));


                System.out.println("Patient found: " + patient);


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


                System.out.println("Examination DTO before saving: " + examinationDTO);


                examinationService.addExamination(examinationDTO);

                response.put("message", "Thêm thành công !");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace for better debugging
                response.put("message", "lỗi, thêm không thành công " + e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }










        @PostMapping("/edit")
        @Transactional
        public ResponseEntity<Map<String, Object>> editExamination(@RequestParam("id") Integer examinationId, @RequestBody ExaminationDTO examinationDTO) {
            Map<String, Object> response = new HashMap<>();

            // Ensure the received DTO has the correct ID
            examinationDTO.setId(examinationId);

            try {
                // Call the service layer to update the examination
                examinationService.updateExamination(examinationDTO);

                // Construct success response
                response.put("status", "success");
                response.put("message", "Examination updated successfully!");
                response.put("examinationId", examinationId); // Optionally return the examination ID

                return ResponseEntity.ok(response);
            } catch (Exception e) {
                // Handle any errors
                response.put("status", "error");
                response.put("message", "An error occurred while updating the examination: " + e.getMessage());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Map<String, String>> examination_del(@PathVariable Integer id) {
            Map<String, String> response = new HashMap<>();

            try {
                int updated = examinationRepository.examination_del(id);

                if (updated > 0) {
                    response.put("message", "Xóa thành công");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.put("message", "Đợt khám không tồn tại");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                response.put("message", "Không thành công: " + e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }




        // Endpoint to get examination statistics for a particular year
        @GetMapping("/stats")
        public ResponseEntity<Map<Integer, Long>> getExaminationStats(@RequestParam int year) {
            Map<Integer, Long> stats = examinationService.getExaminationCountsByMonth(year);
            return ResponseEntity.ok(stats);
        }
    }
