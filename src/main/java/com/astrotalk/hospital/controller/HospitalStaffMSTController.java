package com.astrotalk.hospital.controller;

import com.astrotalk.hospital.enums.StaffDesignation;
import com.astrotalk.hospital.service.HospitalStaffMSTService;
import com.astrotalk.hospital.util.Constants;
import com.astrotalk.hospital.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.HOSPITAL_STAFF_MST)
public class HospitalStaffMSTController {

    @Autowired
    private HospitalStaffMSTService service;

    @GetMapping(value = Constants.BY_STAFF_DESIGNATION+"{staffDesignation}/{page}/{size}")
    public GenericResponse byStaffDesignation(@PathVariable(value = "staffDesignation") StaffDesignation staffDesignation,@PathVariable(value = "page")int page,@PathVariable(value = "size")int size){
        GenericResponse genericResponse = new GenericResponse();
        try {

            genericResponse.setData(service.getByDesignation(staffDesignation,page,size));
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

}
