package com.hospital.hospitalmanagement.controller.admin;


import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "admin/login"; // This corresponds to login.html in your resources/templates folder
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Hủy session hiện tại
        session.invalidate();
        // Chuyển hướng về trang chủ hoặc trang đăng nhập
        return "redirect:/";
    }
}
