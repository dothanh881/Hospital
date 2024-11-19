package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.User;
import com.hospital.hospitalmanagement.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginAPI
{
    @Autowired
    private UserRepository userRepository;
//    @PostMapping("/login")
//    @ResponseBody
//    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
//        User user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            // Lưu userId vào session
//            session.setAttribute("userId", user.getId());
//            return ResponseEntity.ok("success");
//        }
//        return ResponseEntity.status(401).body("Invalid credentials");
//    }


}
