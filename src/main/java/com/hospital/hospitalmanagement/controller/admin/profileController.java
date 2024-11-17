package com.hospital.hospitalmanagement.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class profileController {
    @GetMapping("/profile")
    public String showProfilePage() {
        return "admin/profile"; // This corresponds to login.html in your resources/templates folder
    }
}
