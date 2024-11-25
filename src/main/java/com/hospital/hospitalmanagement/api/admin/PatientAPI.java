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
import com.hospital.hospitalmanagement.service.IPatientService;
import com.hospital.hospitalmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    @Autowired
    IPatientService iPatientService;
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
        if (patientRepository.existsByPhoneNumber(patientDTO.getPhoneNumber())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại. Vui lòng nhập số khác.");
        }
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


        response.put("message", "Thêm mới thành công!");
        response.put("patient", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // HTTP 201 for created resource
    } catch (IllegalArgumentException e) {
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // HTTP 400 for bad request
    }
    catch (Exception e) {
        response.put("message", "Thêm mới thất bại: " + e.getMessage());
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
    public ResponseEntity<Map<String, String>> updatePatient(@PathVariable Integer id, @RequestBody PatientDTO patientDetails) {

        Map<String, String> response = new HashMap<>();
        try {
            // Retrieve the existing patient entity
            Optional<PatientEntity> existingPatient = patientRepository.findById(id);

            // Check if patient exists
            if (!existingPatient.isPresent()) {
                response.put("message", "Patient not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            PatientEntity patient = existingPatient.get();

            // Update patient fields
            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setDateOfBirth(patientDetails.getDob());
            patient.setStreet(patientDetails.getStreet());

            // Check if phone number has changed and is unique
            if (!patientDetails.getPhoneNumber().equals(patient.getPhoneNumber())) {
                if (patientRepository.existsByPhoneNumber(patientDetails.getPhoneNumber())) {
                    response.put("message", "Số điện thoại đã tồn tại. Vui lòng nhập số khác.");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
            patient.setPhoneNumber(patientDetails.getPhoneNumber());

            // Set City, District, and Ward (check if they exist)
            Optional<Cities> city = cityRepository.findById(patientDetails.getCityId());
            if (!city.isPresent()) {
                response.put("message", "City not found");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            patient.setCity(city.get());

            Optional<Districts> district = districtRepository.findById(patientDetails.getDistrictId());
            if (!district.isPresent()) {
                response.put("message", "District not found");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            patient.setDistrict(district.get());

            Optional<Wards> ward = wardRepository.findById(patientDetails.getWardId());
            if (!ward.isPresent()) {
                response.put("message", "Ward not found");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            patient.setWard(ward.get());

            // Save the updated patient entity
            patientRepository.save(patient);

            // Return success response
            response.put("message", "Chỉnh sửa thành công");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            // Handle specific validation error
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle unexpected errors
            response.put("message", "Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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

//    @GetMapping("patient/search/{pageNo}")
//    public ResponseEntity<Page<PatientEntity>> searchPatients(
//            @RequestParam Map<String, Object> searchParams,
//            @RequestParam Optional<Integer> page,
//            @RequestParam Optional<Integer> size) {
//
//        // Default pagination values
//        int pageNumber = page.orElse(0);
//        int pageSize = size.orElse(10);
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//
//        // Pass the search parameters to the service layer
//        Page<PatientEntity> patients = iPatientService.searchPatients(searchParams, pageable);
//
//        return ResponseEntity.ok(patients);
//    }

//@GetMapping("patient/search/")
//public  ResponseEntity<String> searchPatient(
//        @RequestParam Map<String, Object> searchParams) {
//
//    // Fetch patients data based on the search parameters and page number
//    return patientService.searchPatientsUnsafe(searchParams);
//
//    // Prepare the response data
//
//
//}
}

