package com.astrotalk.hospital.dao;

import com.astrotalk.hospital.model.UserAuthorisationMST;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAuthorisationMSTRepository extends JpaRepository<UserAuthorisationMST, Long> {
    Optional<UserAuthorisationMST> findByUserRoleMSTIdAndSystemMSTIdAndIsActiveTrue(Long userRoleId, Long systemMStTId);
    List<UserAuthorisationMST> findByUserRoleMSTIdAndIsActiveTrue(Long id);
    List<UserAuthorisationMST> findByIsActiveTrue();
}
