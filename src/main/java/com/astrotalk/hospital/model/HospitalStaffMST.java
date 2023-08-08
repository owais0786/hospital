package com.astrotalk.hospital.model;

import com.astrotalk.hospital.enums.StaffDesignation;

import javax.persistence.*;

@Entity
@Table(name = "hospital_staff_mst")
public class HospitalStaffMST extends BaseModel{

    @Column(name ="staff_name")
    private String staffName;


    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "staff_designation")
    @Enumerated(EnumType.STRING)
    private StaffDesignation staffDesignation;

    @Column(name = "address")
    private String address;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public StaffDesignation getStaffDesignation() {
        return staffDesignation;
    }

    public void setStaffDesignation(StaffDesignation staffDesignation) {
        this.staffDesignation = staffDesignation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
