package com.astrotalk.hospital.model.dto;

import com.astrotalk.hospital.model.SystemMST;
import com.astrotalk.hospital.model.UserMST;

import java.io.Serializable;
import java.util.List;

public class AuthorisationDTO implements Serializable {
    private UserMST userMST;
    private List<SystemMST> systemMSTList;
    private boolean isAuthorised = false;

    public UserMST getUserMST() {
        return userMST;
    }

    public void setUserMST(UserMST userMST) {
        this.userMST = userMST;
    }

    public List<SystemMST> getSystemMSTList() {
        return systemMSTList;
    }

    public void setSystemMSTList(List<SystemMST> systemMSTList) {
        this.systemMSTList = systemMSTList;
    }

    public boolean isAuthorised() {
        return isAuthorised;
    }

    public void setAuthorised(boolean authorised) {
        isAuthorised = authorised;
    }

}
