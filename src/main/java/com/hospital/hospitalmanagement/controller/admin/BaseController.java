package com.hospital.hospitalmanagement.controller.admin;


import com.hospital.hospitalmanagement.entity.EmployeeEntity;
import com.hospital.hospitalmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("employee")
    public EmployeeEntity addEmployeeToModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's username

        return employeeService.findByUserUsername(username).orElse(null);  // Retrieve the Employee entity
    }
}