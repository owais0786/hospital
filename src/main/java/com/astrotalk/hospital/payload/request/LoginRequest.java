package com.astrotalk.hospital.payload.request;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull(message = "username required")
    private String userName;
    @NotNull(message = "password required")
    private String password;
    @NotNull(message = "system required")
    private String system;


    public LoginRequest() {
    }

    public LoginRequest(String userName, String password, String system) {
        this.userName = userName;
        this.password = password;
        this.system = system;

    }



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

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}