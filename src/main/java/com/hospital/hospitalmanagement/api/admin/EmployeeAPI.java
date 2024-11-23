package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.EmployeeDTO;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.*;
import jakarta.transaction.Transactional;
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


    @Transactional
    @PostMapping("/doctor/add")
    public ResponseEntity<Map<String, Object>> addDoctor(@RequestBody EmployeeDTO employeeDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Create DoctorEntity directly
            DoctorEntity doctor = new DoctorEntity();
            doctor.setFirstName(employeeDTO.getFirstName());
            doctor.setLastName(employeeDTO.getLastName());
            doctor.setGender(employeeDTO.getGender());
            doctor.setDob(employeeDTO.getDob());
            doctor.setStreet(employeeDTO.getStreet());
            doctor.setPhoneNumber(employeeDTO.getPhoneNumber());

            // Set additional fields
            Cities city = new Cities();
            city.setCityId(employeeDTO.getCityId());
            doctor.setCity(city);

            Districts district = new Districts();
            district.setDistrictId(employeeDTO.getDistrictId());
            doctor.setDistrict(district);

            Wards ward = new Wards();
            ward.setWardId(employeeDTO.getWardId());
            doctor.setWard(ward);

            DepartmentEntity department = new DepartmentEntity();
            department.setId(employeeDTO.getDepartmentId());
            doctor.setDepartment(department);

            doctor.setSpecialty(employeeDTO.getSpecialty());
            doctor.setDegreeYear(employeeDTO.getDegreeYear());
            doctor.setStartDate(employeeDTO.getStartDate());

            // Generate and set code
            String generatedCode = String.format("D%03d", new Random().nextInt(1000));
            doctor.setCode(generatedCode);

            // Save the DoctorEntity (polymorphic save)
            DoctorEntity savedDoctor = doctorRepository.save(doctor);

            response.put("message", "Thêm mới bác sĩ thành công!");
            response.put("doctor", savedDoctor);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("message", "Thêm mới bác sĩ không thành công: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/doctor/delete/{id}")
    public ResponseEntity<Map<String, String>> doctor_del(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();

        try {
            int updated = employeeRepository.softDeleteEmployee(id);

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

    @DeleteMapping("/nurse/delete/{id}")
    public ResponseEntity<Map<String, String>> nurse_del(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();

        try {
            int updated = employeeRepository.softDeleteEmployee(id);

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

    @Autowired
    private NurseRepository nurseRepository;


    @Transactional
    @PostMapping("/nurse/add")
    public ResponseEntity<Map<String, Object>> addNurse(@RequestBody EmployeeDTO employeeDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Create DoctorEntity directly
            NurseEntity nurse = new NurseEntity();
            nurse.setFirstName(employeeDTO.getFirstName());
            nurse.setLastName(employeeDTO.getLastName());
            nurse.setGender(employeeDTO.getGender());
            nurse.setDob(employeeDTO.getDob());
            nurse.setStreet(employeeDTO.getStreet());
            nurse.setPhoneNumber(employeeDTO.getPhoneNumber());

            // Set additional fields
            Cities city = new Cities();
            city.setCityId(employeeDTO.getCityId());
            nurse.setCity(city);

            Districts district = new Districts();
            district.setDistrictId(employeeDTO.getDistrictId());
            nurse.setDistrict(district);

            Wards ward = new Wards();
            ward.setWardId(employeeDTO.getWardId());
            nurse.setWard(ward);

            DepartmentEntity department = new DepartmentEntity();
            department.setId(employeeDTO.getDepartmentId());
            nurse.setDepartment(department);

            nurse.setSpecialty(employeeDTO.getSpecialty());
            nurse.setDegreeYear(employeeDTO.getDegreeYear());
            nurse.setStartDate(employeeDTO.getStartDate());

            // Generate and set code
            String generatedCode = String.format("N%03d", new Random().nextInt(1000));
            nurse.setCode(generatedCode);

            // Save the DoctorEntity (polymorphic save)
            NurseEntity saveNurse = nurseRepository.save(nurse);

            response.put("message", "Thêm mới y tá thành công!");
            response.put("doctor", saveNurse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("message", "Thêm mới y tá không thành công: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
