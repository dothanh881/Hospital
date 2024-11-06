package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.Cities;
import com.hospital.hospitalmanagement.entity.Districts;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.entity.Wards;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;

import com.hospital.hospitalmanagement.repository.PatientRepository;
import com.hospital.hospitalmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PatientAPI {
//    Noi tao api

    @Autowired
    PatientService patientService;
//    @PostMapping("/upsert")
//    public ResponseEntity<String> upsertPatient(@RequestBody PatientEntity patientEntity) {
//        patientService.upsertPatient(patientEntity);
//        return ResponseEntity.ok("Patient record processed successfully.");
//    }
    @Autowired
    PatientRepository patientRepository;



//    @GetMapping("/patients")
//    public ResponseEntity<List<PatientEntity>> getAllPatient(){
//        List<PatientEntity> patients = patientRepository.findAll();
//        return new ResponseEntity<>(patients, HttpStatus.OK);
//    }
//     add patient
@PostMapping("/patient/add")
public ResponseEntity<Map<String, Object>> addPatient(@RequestBody PatientDTO patientDTO) {
    Map<String, Object> response = new HashMap<>();

    try {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName(patientDTO.getFirstName());
        patientEntity.setLastName(patientDTO.getLastName());
        patientEntity.setGender(patientDTO.getGender());
        patientEntity.setDateOfBirth(patientDTO.getDob());
        patientEntity.setStreet(patientDTO.getStreet());
        patientEntity.setPhoneNumber(patientDTO.getPhoneNumber());

        // Setting city, district, and ward based on IDs
        Cities city = new Cities();
        city.setCityId(patientDTO.getCityId());
        patientEntity.setCity(city);

        Districts district = new Districts();
        district.setDistrictId(patientDTO.getDistrictId());
        patientEntity.setDistrict(district);

        Wards ward = new Wards();
        ward.setWardId(patientDTO.getWardId());
        patientEntity.setWard(ward);

        // Luu benh nhan
        PatientEntity result = patientRepository.save(patientEntity);


        response.put("message", "Patient added successfully!");
        response.put("patient", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // HTTP 201 for created resource
    } catch (Exception e) {
        response.put("message", "Failed to add patient: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500 for server error
    }
}
    // Get view
    @GetMapping("patient/{id}")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable Integer id) {
        Optional<PatientEntity> patient = patientRepository.findById(id);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a patient by ID
    @PutMapping("patient/edit/{id}")
    public ResponseEntity<PatientEntity> updatePatient(@PathVariable Integer id, @RequestBody PatientDTO patientDetails) {
        Optional<PatientEntity> existingPatient = patientRepository.findById(id);

        if (existingPatient.isPresent()) {


            PatientEntity patient = existingPatient.get();

            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setGender(patientDetails.getGender());
            patient.setDateOfBirth(patientDetails.getDob());
            patient.setStreet(patientDetails.getStreet());
            patient.setPhoneNumber(patientDetails.getPhoneNumber());
            Cities city = new Cities();
            city.setCityId(patientDetails.getCityId());
            patient.setCity(city);


            Districts district = new Districts();
            district.setDistrictId(patientDetails.getDistrictId());
            patient.setDistrict(district);


            Wards ward = new Wards();
            ward.setWardId(patientDetails.getWardId());
            patient.setWard(ward);

            PatientEntity updatedPatient = patientRepository.save(patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a patient by ID
//    @DeleteMapping("patient/delete/{id}")
//    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
//        if (patientRepository.existsById(id)) {
//            patientRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}

