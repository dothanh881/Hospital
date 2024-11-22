package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.EmployeeDTO;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/employee")
public class EmployeeAPI {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    DepartmentRepository departmentRepository;  // Autowired DepartmentRepository

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {
        try {
            // Retrieve the existing employee entity
            Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(id);

            if (!existingEmployee.isPresent()) {
                return new ResponseEntity<>(Map.of("message", "Employee not found"), HttpStatus.NOT_FOUND);
            }

            EmployeeEntity employee = existingEmployee.get();

            // Update basic fields from DTO
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setDob(employeeDTO.getDob());
            employee.setGender(employeeDTO.getGender());

            // Handle city mapping
            Optional<Cities> city = cityRepository.findById(employeeDTO.getCityId());
            if (city.isPresent()) {
                employee.setCity(city.get());
            } else {
                return new ResponseEntity<>(Map.of("message", "City not found"), HttpStatus.BAD_REQUEST);
            }

            // Handle district mapping
            Optional<Districts> district = districtRepository.findById(employeeDTO.getDistrictId());
            if (district.isPresent()) {
                employee.setDistrict(district.get());
            } else {
                return new ResponseEntity<>(Map.of("message", "District not found"), HttpStatus.BAD_REQUEST);
            }

            // Handle ward mapping
            Optional<Wards> ward = wardRepository.findById(employeeDTO.getWardId());
            if (ward.isPresent()) {
                employee.setWard(ward.get());
            } else {
                return new ResponseEntity<>(Map.of("message", "Ward not found"), HttpStatus.BAD_REQUEST);
            }

            // Handle department mapping
            Optional<DepartmentEntity> department = departmentRepository.findById(employeeDTO.getDepartmentId());
            if (department.isPresent()) {
                employee.setDepartment(department.get());
            } else {
                return new ResponseEntity<>(Map.of("message", "Department not found"), HttpStatus.BAD_REQUEST);
            }

            // Set additional fields
            employee.setStreet(employeeDTO.getStreet());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setSpecialty(employeeDTO.getSpecialty());
            employee.setDegreeYear(employeeDTO.getDegreeYear());
            employee.setStartDate(employeeDTO.getStartDate());

            // Save the updated employee entity
            employeeRepository.save(employee);

            return new ResponseEntity<>(Map.of("message", "Employee updated successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Error updating employee: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/doctor/add")
    public ResponseEntity<Map<String, Object>> addDoctor(@RequestBody EmployeeDTO employeeDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Create EmployeeEntity
            EmployeeEntity employee = new EmployeeEntity();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setGender(employeeDTO.getGender());
            employee.setDob(employeeDTO.getDob());
            employee.setStreet(employeeDTO.getStreet());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());

            // Setting city, district, and ward based on IDs
            Cities city = new Cities();
            city.setCityId(employeeDTO.getCityId());
            employee.setCity(city);

            Districts district = new Districts();
            district.setDistrictId(employeeDTO.getDistrictId());
            employee.setDistrict(district);

            Wards ward = new Wards();
            ward.setWardId(employeeDTO.getWardId());
            employee.setWard(ward);

            employee.setSpecialty(employeeDTO.getSpecialty());
            employee.setDegreeYear(employeeDTO.getDegreeYear());
            employee.setStartDate(employeeDTO.getStartDate());

            DepartmentEntity department = new DepartmentEntity();
            department.setId(employeeDTO.getDepartmentId());
            employee.setDepartment(department);

            String generatedCode = generateRandomCode("D", 3); // Prefix "D" and 6 random digits
            employee.setCode(generatedCode);


            // Save EmployeeEntity to generate ID
            EmployeeEntity savedEmployee = employeeRepository.save(employee);

            // Generate the code using the generated ID


            // Update the EmployeeEntity with the generated code
            employeeRepository.save(savedEmployee);

            // Create DoctorEntity and associate with EmployeeEntity
            DoctorEntity doctor = new DoctorEntity();
            doctor.setID(savedEmployee.getID()); // Use the ID from EmployeeEntity

            // Save the DoctorEntity
            doctorRepository.save(doctor);

            response.put("message", "Thêm mới bác sĩ thành công!");
            response.put("employee", savedEmployee);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // HTTP 201 for created resource
        } catch (Exception e) {
            response.put("message", "Thêm mới bác sĩ không thành công: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500 for server error
        }
    }
    private String generateRandomCode(String prefix, int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder(prefix);
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        return code.toString();
    }
}
