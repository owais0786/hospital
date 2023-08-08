package com.astrotalk.hospital.service;

import com.astrotalk.hospital.dao.HospitalStaffMSTRepository;
import com.astrotalk.hospital.dao.PatientDoctorRelationshipMSTRepository;
import com.astrotalk.hospital.dao.PatientMSTRepository;
import com.astrotalk.hospital.enums.AdmitStatus;
import com.astrotalk.hospital.model.HospitalStaffMST;
import com.astrotalk.hospital.model.PatientDoctorRelationshipMST;

import com.astrotalk.hospital.model.PatientMST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDoctorRelationshipMSTService {

    @Autowired
    private PatientDoctorRelationshipMSTRepository repository;
    @Autowired
    private PatientMSTRepository patientMSTRepository;
    @Autowired
    private HospitalStaffMSTRepository hospitalStaffMSTRepository;

    public PatientDoctorRelationshipMST create(PatientDoctorRelationshipMST patientDoctorRelationshipMST) throws Exception {
        HospitalStaffMST hospitalStaffMST=hospitalStaffMSTRepository.findById(patientDoctorRelationshipMST.getHospitalStaffMSTId()).orElseThrow(()->new Exception("Hospital Staff not found"));
      patientDoctorRelationshipMST.setHospitalStaffMST(hospitalStaffMST);

        PatientMST patientMST=patientMSTRepository.findById(patientDoctorRelationshipMST.getPatientMSTId()).orElseThrow(()->new Exception("Patient not found"));
        patientDoctorRelationshipMST.setPatientMST(patientMST);
        repository.findFirstByPatientMSTIdOrderByIdDesc(patientDoctorRelationshipMST.getPatientMSTId()).ifPresent(x->{
            if(x.getReleaseDate()==null)
            {
                throw new RuntimeException("Patient Still Admitted!");
            }
        });
        return repository.save(patientDoctorRelationshipMST);
    }

    public PatientDoctorRelationshipMST update(PatientDoctorRelationshipMST patientDoctorRelationshipMST) throws Exception {
        repository.findById(patientDoctorRelationshipMST.getId()).orElseThrow(()->new Exception("Patient is not Admitted!"));

        hospitalStaffMSTRepository.findById(patientDoctorRelationshipMST.getHospitalStaffMSTId()).orElseThrow(()->new Exception("Hospital Staff not found"));
        patientMSTRepository.findById(patientDoctorRelationshipMST.getPatientMSTId()).orElseThrow(()->new Exception("Patient not found"));

        return repository.save(patientDoctorRelationshipMST);
    }

    public PatientDoctorRelationshipMST getPatientLatestAdmitDetail(Long patientId) throws Exception {
       return repository.findFirstByPatientMSTIdOrderByIdDesc(patientId).orElseThrow(()->new Exception("No Patient Admitted!"));
    }

    public Page<PatientDoctorRelationshipMST> getAllPatientByStatus(AdmitStatus admitStatus, int page, int size)
    {
        Pageable pageable= PageRequest.of(page,size);
        return repository.findPatientByStatus(admitStatus,pageable);
    }

    public PatientDoctorRelationshipMST updateStatus(Long patientId,AdmitStatus admitStatus) throws Exception {
       PatientDoctorRelationshipMST patientDoctorRelationshipMST= repository.findFirstByPatientMSTIdOrderByIdDesc(patientId).orElseThrow(()->new Exception("Patient Not Found"));
       patientDoctorRelationshipMST.setAdmitStatus(admitStatus);
       return repository.save(patientDoctorRelationshipMST);
    }



}
