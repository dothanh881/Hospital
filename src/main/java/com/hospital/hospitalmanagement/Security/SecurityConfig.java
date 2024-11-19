package com.hospital.hospitalmanagement.Security;

import com.hospital.hospitalmanagement.Security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;

    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/admin/assets/js/**", "/admin/assets/css/**").permitAll()  // Trang login và public không yêu cầu xác thực
                        .anyRequest().authenticated()                         // Các trang khác cần xác thực
                )
                .formLogin(form -> form
                        .loginPage("/login")                                  // Định nghĩa trang login tùy chỉnh
                        .permitAll()
                        .defaultSuccessUrl("/home", true)
                        .loginProcessingUrl("/j_spring_security_check")
                )
                .csrf(csrf -> csrf.disable());                            // Tắt CSRF nếu không cần

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}




