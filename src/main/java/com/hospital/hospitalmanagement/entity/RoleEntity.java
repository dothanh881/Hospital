package com.hospital.hospitalmanagement.entity;

import com.hospital.hospitalmanagement.entity.enums.EnumRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;


    @Column(length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
