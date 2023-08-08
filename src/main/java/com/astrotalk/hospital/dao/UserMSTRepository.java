package com.astrotalk.hospital.dao;

import com.astrotalk.hospital.model.UserMST;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserMSTRepository extends JpaRepository<UserMST, Long>{
    Optional<UserMST> findByIdAndIsActiveTrue(Long parseLong);

    UserMST findFirstByUserNameIgnoreCaseAndIsActiveTrue(String username);

    Optional<UserMST> findByUserName(String userName);

    Optional<UserMST> findByIsActiveTrueAndId(Long id);

    List<UserMST> findByIsActiveTrue();
}
