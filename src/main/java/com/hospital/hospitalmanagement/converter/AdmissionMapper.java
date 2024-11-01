package com.hospital.hospitalmanagement.converter;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.AdmissionDTO;

public class AdmissionMapper {
    public static AdmissionEntity toEntity(AdmissionDTO dto, InPatientEntity inPatient, DoctorEntity doctor, NurseEntity nurse, RoomEntity room) {
        AdmissionEntity entity = new AdmissionEntity();

        // Map properties from DTO to Entity
        entity.setID(dto.getId()); // Optional: if you allow setting ID manually
        entity.setInPatient(inPatient);
        entity.setDoctor(doctor);
        entity.setNurse(nurse);
        entity.setDateAdmission(dto.getDateAdmission());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setRoom(room);
        entity.setDateOfDischarge(dto.getDateOfDischarge());
        entity.setFee(dto.getFee());

        return entity;
    }
}
