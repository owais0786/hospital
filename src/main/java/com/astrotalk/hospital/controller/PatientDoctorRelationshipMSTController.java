package com.astrotalk.hospital.controller;

import com.astrotalk.hospital.enums.AdmitStatus;
import com.astrotalk.hospital.model.PatientDoctorRelationshipMST;
import com.astrotalk.hospital.payload.request.SignUpRequest;
import com.astrotalk.hospital.service.PatientDoctorRelationshipMSTService;
import com.astrotalk.hospital.util.Constants;
import com.astrotalk.hospital.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Constants.PATIENT_DOCTOR_RELATIONSHIP_MST)
public class PatientDoctorRelationshipMSTController {

    @Autowired
    private PatientDoctorRelationshipMSTService service;

    @PostMapping(value = Constants.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse create(@Valid @RequestBody PatientDoctorRelationshipMST patientDoctorRelationshipMST) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse.setData(service.create(patientDoctorRelationshipMST));
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

    @GetMapping(value = Constants.PATIENT_LATEST_ADMIT_DETAIL+"{id}")
    public GenericResponse byId(@PathVariable(value = "id") Long id){
        GenericResponse genericResponse = new GenericResponse();
        try {

            genericResponse.setData(service.getPatientLatestAdmitDetail(id));
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

    @GetMapping(value = Constants.ALL_PATIENT_BY_STATUS+"{status}/{page}/{size}")
    public GenericResponse byId(@PathVariable(value = "status") AdmitStatus status, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size){
        GenericResponse genericResponse = new GenericResponse();
        try {

            genericResponse.setData(service.getAllPatientByStatus(status,page,size));
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

    @GetMapping(value = Constants.UPDATE_PATIENT_STATUS+"{patientId}/{status}")
    public GenericResponse byId(@PathVariable(value = "status") AdmitStatus status, @PathVariable(value = "patientId") Long patientId){
        GenericResponse genericResponse = new GenericResponse();
        try {

            genericResponse.setData(service.updateStatus(patientId,status));
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
