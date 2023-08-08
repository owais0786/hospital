package com.astrotalk.hospital.dao;

import com.astrotalk.hospital.model.PatientMST;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientMSTRepository extends JpaRepository<PatientMST,Long> {
    Optional<PatientMST> findByPhoneNumber(String phoneNumber);
}
