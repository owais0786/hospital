package com.astrotalk.hospital.payload.request;

import com.astrotalk.hospital.enums.StaffDesignation;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SignUpRequest implements Serializable {
    @NotNull(message = "username required!")
    String userName;
    @NotNull(message = "password required")
    String password;
    @Enumerated(EnumType.STRING)
    StaffDesignation staffDesignation;

    String staffName;
    @NotNull(message = "contact number required!")
    String contactNumber;

    Long userRoleMSTId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StaffDesignation getStaffDesignation() {
        return staffDesignation;
    }

    public void setStaffDesignation(StaffDesignation staffDesignation) {
        this.staffDesignation = staffDesignation;
    }

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

    public Long getUserRoleMSTId() {
        return userRoleMSTId;
    }

    public void setUserRoleMSTId(Long userRoleMSTId) {
        this.userRoleMSTId = userRoleMSTId;
    }
}
