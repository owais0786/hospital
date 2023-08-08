package com.astrotalk.hospital.dao;

import com.astrotalk.hospital.model.UserRoleMST;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleMSTRepository extends JpaRepository<UserRoleMST, Long> {
    Optional<UserRoleMST> findByRoleCodeAndRoleNameAndIsActiveTrue(String roleCode, String roleName);
    Optional<UserRoleMST> findByIsActiveTrueAndId(Long userRoleMSTId);
    List<UserRoleMST> findByIsActiveTrue();
    List<UserRoleMST> findByIsActiveTrueAndIdIn(List<Long> userRoleMSTId);

    Optional<UserRoleMST> findFirstByRoleCodeAndIsActiveTrue(String code);
}
