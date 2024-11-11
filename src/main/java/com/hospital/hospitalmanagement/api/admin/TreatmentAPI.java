package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.OutPatientEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.entity.TreatmentEntity;
import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import com.hospital.hospitalmanagement.models.dto.TreatmentDTO;
import com.hospital.hospitalmanagement.repository.TreatmentRepository;
import com.hospital.hospitalmanagement.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/treatment/")
public class TreatmentAPI {
    @Autowired
    private TreatmentService treatmentService;
    @Autowired
    private TreatmentRepository treatmentRepository;

//    @PostMapping("/add")
//    public ResponseEntity<Map<String, Object>> addTreatment(@RequestBody TreatmentDTO treatmentDTO) {
//        Map<String, Object> response = new HashMap<>();
//
//
//        try {
//
//            PatientEntity patient = patientRepository.findById(examinationDTO.getOutPatientId())
//                    .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + examinationDTO.getOutPatientId()));
//
//
//            System.out.println("Patient found: " + patient);
//
//
//            Optional<OutPatientEntity> optionalOutPatient = outPatientRepository.findByPatient_ID(patient.getID());
//            OutPatientEntity outPatient;
//
//            if (optionalOutPatient.isPresent()) {
//                outPatient = optionalOutPatient.get();
//            } else {
//                outPatient = new OutPatientEntity();
//                outPatient.setPatient(patient);
//
//                String generatedCode = String.format("OP%09d", patient.getID());
//                outPatient.setCode(generatedCode);
//                outPatientRepository.save(outPatient);
//            }
//
//
//            System.out.println("Examination DTO before saving: " + examinationDTO);
//
//
//            examinationService.addExamination(examinationDTO);
//
//            response.put("message", "Thêm thành công !");
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace(); // Print stack trace for better debugging
//            response.put("message", "lỗi, thêm không thành công " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addTreatment(@RequestBody TreatmentDTO treatmentDTO) {
        Map<String, Object> response = new HashMap<>();



        try {
            // Call the service layer to update the examination
            treatmentService.addTreatment(treatmentDTO);

            // Construct success response
            response.put("status", "success");
            response.put("message", "Thành công!");


            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle any errors
            response.put("status", "error");
            response.put("message", "lỗi: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping("/edit")
    @Transactional
    public ResponseEntity<Map<String, Object>> editTreatment(@RequestParam("id") Integer treatmentId, @RequestBody TreatmentDTO treatmentDTO) {
        Map<String, Object> response = new HashMap<>();


        treatmentDTO.setId(treatmentId);

        try {

            treatmentService.updateTreatment(treatmentDTO);

            // Construct success response
            response.put("status", "success");
            response.put("message", "Cập nhật thành công!");
            response.put("treatmentId", treatmentId); // Optionally return the examination ID

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle any errors
            response.put("status", "error");
            response.put("message", "Lỗi " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }






    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> treatment_del(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();

        try {
            int updated = treatmentRepository.treatment_del(id);

            if (updated > 0) {
                response.put("message", "Xóa thành công");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "Quá trình điều trị không tồn tại");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("message", "Không thành công: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    }

