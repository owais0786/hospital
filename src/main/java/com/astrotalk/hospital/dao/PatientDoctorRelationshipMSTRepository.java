package com.astrotalk.hospital.dao;

import com.astrotalk.hospital.enums.AdmitStatus;
import com.astrotalk.hospital.model.PatientDoctorRelationshipMST;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientDoctorRelationshipMSTRepository extends JpaRepository<PatientDoctorRelationshipMST,Long> {

    List<PatientDoctorRelationshipMST> findByPatientMSTId(Long patientMSTId);

    Optional<PatientDoctorRelationshipMST> findFirstByPatientMSTIdOrderByIdDesc(Long patientMSTId);

    @Query( "Select m from PatientDoctorRelationshipMST m where m.admitStatus=:admitStatus")
    Page<PatientDoctorRelationshipMST> findPatientByStatus(@Param("admitStatus") AdmitStatus admitStatus, Pageable pageable);
}
