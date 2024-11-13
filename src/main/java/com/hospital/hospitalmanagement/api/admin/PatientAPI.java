package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.Cities;
import com.hospital.hospitalmanagement.entity.Districts;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.entity.Wards;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;

import com.hospital.hospitalmanagement.repository.CityRepository;
import com.hospital.hospitalmanagement.repository.DistrictRepository;
import com.hospital.hospitalmanagement.repository.PatientRepository;
import com.hospital.hospitalmanagement.repository.WardRepository;
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
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    WardRepository wardRepository;






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
    public ResponseEntity<Map<String,String>> updatePatient(@PathVariable Integer id, @RequestBody PatientDTO patientDetails) {


        try {
            Optional<PatientEntity> existingPatient = patientRepository.findById(id);



            PatientEntity patient = existingPatient.get();

            // Update patient fields
            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setDateOfBirth(patientDetails.getDob());
            patient.setStreet(patientDetails.getStreet());
            patient.setPhoneNumber(patientDetails.getPhoneNumber());

            // Set City, District, and Ward (check if they exist)
            Optional<Cities> city = cityRepository.findById(patientDetails.getCityId());
            if (city.isPresent()) {
                patient.setCity(city.get());
            } else {
                return new ResponseEntity<>(Map.of("message", "City not found"), HttpStatus.BAD_REQUEST);
            }

            Optional<Districts> district = districtRepository.findById(patientDetails.getDistrictId());
            if (district.isPresent()) {
                patient.setDistrict(district.get());
            } else {
                return new ResponseEntity<>(Map.of("message", "District not found"), HttpStatus.BAD_REQUEST);
            }

            Optional<Wards> ward = wardRepository.findById(patientDetails.getWardId());
            if (ward.isPresent()) {
                patient.setWard(ward.get());
            } else {
                return new ResponseEntity<>(Map.of("message", "Ward not found"), HttpStatus.BAD_REQUEST);
            }


            patientRepository.save(patient);

            // Return success response
            return new ResponseEntity<>(Map.of("message", "Patient updated successfully"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("patient/delete/{id}")
    public ResponseEntity<Map<String, String>> deletePatient(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();

        try {
            int updated = patientRepository.softDeletePatient(id);

            if (updated > 0) {
                response.put("message", "Xóa thành công");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "Bệnh nhân không tồn tại.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("message", "Không thành công: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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

