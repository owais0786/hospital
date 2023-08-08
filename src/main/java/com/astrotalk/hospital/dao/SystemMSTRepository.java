package com.astrotalk.hospital.dao;

import com.astrotalk.hospital.model.SystemMST;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SystemMSTRepository extends JpaRepository<SystemMST, Long> {
    Optional<SystemMST> findFirstByIsActiveTrueAndSystemCode(String system);
    Optional<SystemMST> findBySystemNameAndSystemCodeAndIsActiveTrue(String systemName,String systemCode);
    List<SystemMST> findByIsActiveTrueAndSystemCode(String system);
    List<SystemMST> findByIsActiveTrue();
    Optional<SystemMST> findByIsActiveTrueAndId(Long systemMSTId);
}
