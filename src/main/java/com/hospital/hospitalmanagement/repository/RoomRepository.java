package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
}
