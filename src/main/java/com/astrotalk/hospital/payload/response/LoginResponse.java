package com.astrotalk.hospital.payload.response;

import com.astrotalk.hospital.model.UserMST;

import java.util.UUID;

public class LoginResponse {
    private Long id;
    private String userName;
    private String jwtToken;
    private UserMST userMST;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserMST getUserMST() {
        return userMST;
    }

    public void setUserMST(UserMST userMST) {
        this.userMST = userMST;
    }

}
