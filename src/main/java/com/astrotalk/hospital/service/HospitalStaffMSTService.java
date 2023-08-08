package com.astrotalk.hospital.service;

import com.astrotalk.hospital.dao.HospitalStaffMSTRepository;
import com.astrotalk.hospital.enums.StaffDesignation;
import com.astrotalk.hospital.model.HospitalStaffMST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalStaffMSTService {

    @Autowired
    private HospitalStaffMSTRepository repository;

    public HospitalStaffMST create(HospitalStaffMST hospitalStaffMST)
    {
        repository.findByContactNumber(hospitalStaffMST.getContactNumber()).ifPresent(x->{
            throw new RuntimeException("Hospital Staff Already Exists!");
        });

        return repository.save(hospitalStaffMST);
    }

    public HospitalStaffMST update(HospitalStaffMST hospitalStaffMST) throws Exception {
        repository.findById(hospitalStaffMST.getId()).orElseThrow(()->new Exception("Hospital Staff not found!"));
        repository.findByContactNumber(hospitalStaffMST.getContactNumber()).ifPresent(x->{
            if(!x.getId().equals(hospitalStaffMST.getId()))
            throw new RuntimeException("Hospital Staff Already Exists!");
        });
        return repository.save(hospitalStaffMST);
    }

    public HospitalStaffMST getById(Long id) throws Exception {
        return  repository.findById(id).orElseThrow(()->new Exception("Hospital Staff Not Found!"));
    }


    public List<HospitalStaffMST> getAll(){
        return repository.findAll();
    }
    public Page<HospitalStaffMST> getByDesignation(StaffDesignation staffDesignation,int page,int size)
    {
        Pageable pageable=PageRequest.of(page,size);
        return  repository.findByStaffDesignation(staffDesignation,pageable);
    }
}
