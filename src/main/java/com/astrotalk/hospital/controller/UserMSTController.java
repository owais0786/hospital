package com.astrotalk.hospital.controller;

import com.astrotalk.hospital.payload.request.LoginRequest;
import com.astrotalk.hospital.payload.request.SignUpRequest;
import com.astrotalk.hospital.service.UserMSTService;
import com.astrotalk.hospital.util.Constants;
import com.astrotalk.hospital.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Constants.USER_MST)
public class UserMSTController {

    @Autowired
    private UserMSTService service;

    @PostMapping(value = Constants.SIGN_UP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse singUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse.setData(service.signUp(signUpRequest));
            genericResponse.setMessage("SuccessFul");
            genericResponse.setStatus(true);
            return genericResponse;
        } catch (Exception e) {
            e.printStackTrace();
            genericResponse.setData(null);
            genericResponse.setMessage(e.getMessage());
            genericResponse.setStatus(false);
            return genericResponse;
        }
    }


    @PostMapping(value = "login/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@Valid @RequestBody LoginRequest loginMST) {
        return service.login(loginMST);
    }

}
