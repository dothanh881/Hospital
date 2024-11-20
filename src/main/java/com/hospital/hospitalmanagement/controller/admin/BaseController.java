package com.hospital.hospitalmanagement.controller.admin;


import com.hospital.hospitalmanagement.entity.DepartmentEntity;
import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.EmployeeEntity;
import com.hospital.hospitalmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Controller
public class BaseController {

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("employee")
    public EmployeeEntity addEmployeeToModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's username
        Optional<EmployeeEntity> optionalEmployee = employeeService.findByUserUsername(username);

        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();  // Unwrap the Optional

            // Log employee details including department information
            System.out.println("Employee Found: " + employee.getFirstName() + ", Department: " +
                    (employee.getDepartment() != null ? employee.getDepartment().getTitle() : "No Department"));

            // Check if the employee is a doctor and handle department differently
            if (employee instanceof DoctorEntity) {
                DoctorEntity doctor = (DoctorEntity) employee;
                // Log doctor's department if it exists
                String departmentTitle = (doctor.getDepartment() != null)
                        ? doctor.getDepartment().getTitle()
                        : "No Department Assigned for Doctor";
                System.out.println("Doctor's Department: " + departmentTitle);
            }

            return employee;
        } else {
            System.out.println("No Employee Found for Username: " + username);
            return null;
        }
    }

}