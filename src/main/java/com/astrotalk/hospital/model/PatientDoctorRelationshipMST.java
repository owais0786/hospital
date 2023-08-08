package com.astrotalk.hospital.model;

import com.astrotalk.hospital.enums.AdmitStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "patient_doctor_relationship_mst")
public class PatientDoctorRelationshipMST  extends BaseModel{

    @Column(name = "room_no")
    private String roomNo;

    @NotNull(message = "hospital staff Required")
    @Column(name = "hospital_staff_mst_id", insertable = false, updatable = false)
    private Long hospitalStaffMSTId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_staff_mst_id")
    private HospitalStaffMST hospitalStaffMST;

    @NotNull(message = "patient  Required")
    @Column(name = "patient_mst_id", insertable = false, updatable = false)
    private Long patientMSTId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_mst_id")
    private PatientMST patientMST;

    @Column(name = "admit_status")
    @Enumerated(EnumType.STRING)
    private AdmitStatus admitStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "admit_date")
    @NotNull(message = "Admit Date Required!")
    private LocalDate admitDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "expensed")
    private Double expenses;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Long getHospitalStaffMSTId() {
        return hospitalStaffMSTId;
    }

    public void setHospitalStaffMSTId(Long hospitalStaffMSTId) {
        this.hospitalStaffMSTId = hospitalStaffMSTId;
    }

    @JsonIgnore
    public HospitalStaffMST getHospitalStaffMST() {
        return hospitalStaffMST;
    }

    public void setHospitalStaffMST(HospitalStaffMST hospitalStaffMST) {
        this.hospitalStaffMST = hospitalStaffMST;
    }

    public Long getPatientMSTId() {
        return patientMSTId;
    }

    public void setPatientMSTId(Long patientMSTId) {
        this.patientMSTId = patientMSTId;
    }

    @JsonIgnore
    public PatientMST getPatientMST() {
        return patientMST;
    }

    public void setPatientMST(PatientMST patientMST) {
        this.patientMST = patientMST;
    }

    public AdmitStatus getAdmitStatus() {
        return admitStatus;
    }

    public void setAdmitStatus(AdmitStatus admitStatus) {
        this.admitStatus = admitStatus;
    }

    public LocalDate getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(LocalDate admitDate) {
        this.admitDate = admitDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }
}
