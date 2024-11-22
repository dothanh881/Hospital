package com.hospital.hospitalmanagement.entity.IdClass;

import java.io.Serializable;
import java.util.Objects;

public class InPatientId implements Serializable {

    private Integer inpatientId;

    public InPatientId() {}

    public InPatientId(Integer inpatientId) {
        this.inpatientId = inpatientId;
    }

    public Integer getPatient() {
        return inpatientId;
    }

    public void setPatient(Integer inpatientId) {
        this.inpatientId = inpatientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InPatientId that = (InPatientId) o;
        return Objects.equals(inpatientId, that.inpatientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inpatientId);
    }
}

