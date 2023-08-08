package com.astrotalk.hospital.service;

import com.astrotalk.hospital.dao.PatientDoctorRelationshipMSTRepository;
import com.astrotalk.hospital.dao.PatientMSTRepository;
import com.astrotalk.hospital.model.PatientMST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientMSTService {

    @Autowired
    private PatientMSTRepository repository;

    @Autowired
    private PatientDoctorRelationshipMSTRepository patientDoctorRelationshipMSTRepository;

    public PatientMST create(PatientMST patientMST)
    {
        repository.findByPhoneNumber(patientMST.getPhoneNumber()).ifPresent(x->{
            throw new RuntimeException("Patient Already Exist");
        });
        return repository.save(patientMST);
    }

    public PatientMST update(PatientMST patientMST)
    {
        repository.findById(patientMST.getId()).orElseThrow(()->new RuntimeException("Patient not found!"));
        repository.findByPhoneNumber(patientMST.getPhoneNumber()).ifPresent(x->{
            if(x.getId().equals(patientMST.getId()))
                throw new RuntimeException("Patient Phone Number Already Exist");
        });
        return repository.save(patientMST);
    }

    public PatientMST getById(Long id) throws Exception {
        return  repository.findById(id).orElseThrow(()->new Exception("Patient not found!"));
    }

    public PatientMST getByPhoneNumber(String phoneNumber) throws Exception {
        return repository.findByPhoneNumber(phoneNumber).orElseThrow(()->new Exception("Patient Not Found"));
    }
}
