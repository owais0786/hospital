package com.astrotalk.hospital.service;


import com.astrotalk.hospital.dao.UserRoleMSTRepository;
import com.astrotalk.hospital.model.UserRoleMST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRoleMSTService {
    @Autowired
    private UserRoleMSTRepository repository;

    public UserRoleMST create(UserRoleMST userRoleMST)throws  Exception {
        repository.findByRoleCodeAndRoleNameAndIsActiveTrue(userRoleMST.getRoleCode(), userRoleMST.getRoleName()).ifPresent(x -> {
            throw new RuntimeException("UserRole Already Exists");
        });
        return repository.save(userRoleMST);

    }

    public UserRoleMST update(UserRoleMST userRoleMST) throws Exception {
        repository.findById(userRoleMST.getId()).orElseThrow(() -> new Exception("userRoleMST Not Found"));
        repository.findByRoleCodeAndRoleNameAndIsActiveTrue(userRoleMST.getRoleCode(), userRoleMST.getRoleName()).ifPresent(x -> {
            if(!Objects.equals(x.getId(), userRoleMST.getId()))
                throw new RuntimeException("UserRole Already Exists");
        });
        return repository.save(userRoleMST);
    }

    public UserRoleMST getById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("userRoleMST Not Found"));
    }

    public UserRoleMST getByCodeOrCreate(String code) {
        Optional<UserRoleMST> existing = repository.findFirstByRoleCodeAndIsActiveTrue(code);
        if (existing.isPresent()) {
            return existing.get();
        }
        UserRoleMST userRoleMST = new UserRoleMST();
        userRoleMST.setRoleCode(code);
        userRoleMST.setRoleName(code);
        return repository.save(userRoleMST);
    }
}
