package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.EmployeeDTO;
import com.hospital.hospitalmanagement.repository.CityRepository;
import com.hospital.hospitalmanagement.repository.DistrictRepository;
import com.hospital.hospitalmanagement.repository.EmployeeRepository;
import com.hospital.hospitalmanagement.repository.WardRepository;
import com.hospital.hospitalmanagement.repository.DepartmentRepository;  // Import DepartmentRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeAPI {
    @Autowired
    EmployeeRepository employeeRepository;

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

}
