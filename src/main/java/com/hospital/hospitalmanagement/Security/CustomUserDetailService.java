package com.hospital.hospitalmanagement.Security;

import com.hospital.hospitalmanagement.entity.RoleEntity;
import com.hospital.hospitalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.hospital.hospitalmanagement.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.hospital.hospitalmanagement.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Khong tìm thấy username"));

        // Using Spring Security's User class, which takes (username, password, authorities)
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<RoleEntity> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
