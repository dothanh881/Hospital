package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomNo;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false)
    private int capacity;

    // Many Rooms belong to one Department
    @ManyToOne
    @JoinColumn(name = "depart_id", nullable = false)
    private DepartmentEntity department;

    // Constructors, Getters, Setters
    public RoomEntity() {}


    // Getters and setters
    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    @OneToMany(mappedBy = "room")
    List<AdmissionEntity> admissions;

    public List<AdmissionEntity> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(List<AdmissionEntity> admissions) {
        this.admissions = admissions;
    }

    public RoomEntity(int roomNo, String roomName, int capacity, DepartmentEntity department, List<AdmissionEntity> admissions) {
        this.roomNo = roomNo;
        this.roomName = roomName;
        this.capacity = capacity;
        this.department = department;
        this.admissions = admissions;
    }
}
