package com.astrotalk.hospital.controller;

import com.astrotalk.hospital.model.PatientMST;
import com.astrotalk.hospital.payload.request.SignUpRequest;
import com.astrotalk.hospital.service.PatientMSTService;
import com.astrotalk.hospital.util.Constants;
import com.astrotalk.hospital.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value =Constants.PATIENT_MST)
public class PatientMSTController {

    @Autowired
    private PatientMSTService service;

    @PostMapping(value = Constants.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse create(@Valid @RequestBody PatientMST patientMST) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse.setData(service.create(patientMST));
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


    @GetMapping(value = Constants.BY_ID+"{id}")
    public GenericResponse byId(@PathVariable(value = "id") Long id){
        GenericResponse genericResponse = new GenericResponse();
        try {

            genericResponse.setData(service.getById(id));
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


    @GetMapping(value = Constants.BY_PHONENUMBER+"{phoneNumber}")
    public GenericResponse byPatientPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber){
        GenericResponse genericResponse = new GenericResponse();
        try {

            genericResponse.setData(service.getByPhoneNumber(phoneNumber));
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

}
