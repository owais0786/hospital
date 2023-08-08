package com.astrotalk.hospital.dao;

import com.astrotalk.hospital.enums.StaffDesignation;
import com.astrotalk.hospital.model.HospitalStaffMST;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalStaffMSTRepository extends JpaRepository<HospitalStaffMST,Long> {
    Optional<HospitalStaffMST> findByContactNumber(String contactNumber);

    Page<HospitalStaffMST> findByStaffDesignation(StaffDesignation staffDesignation, Pageable pageable);
}
