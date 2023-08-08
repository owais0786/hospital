package com.astrotalk.hospital.model;

import com.astrotalk.hospital.enums.AdmitStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_mst")
public class PatientMST extends BaseModel{

    @NotNull(message = "patient name required")
    @Column(name = "patient_name")
    private String patientName;


    @Column(name = "address")
    private String address;

    @NotNull(message = "patient number required")
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob")
    private LocalDate dob;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
