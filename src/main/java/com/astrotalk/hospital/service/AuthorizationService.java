package com.astrotalk.hospital.service;


import com.astrotalk.hospital.dao.UserAuthorisationMSTRepository;
import com.astrotalk.hospital.dao.UserMSTRepository;
import com.astrotalk.hospital.model.SystemMST;
import com.astrotalk.hospital.model.UserAuthorisationMST;
import com.astrotalk.hospital.model.UserMST;
import com.astrotalk.hospital.model.UserRoleMST;
import com.astrotalk.hospital.model.dto.AuthorisationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorizationService {

    @Autowired
    UserMSTRepository userMSTRepository;

    @Autowired
    UserAuthorisationMSTRepository userAuthorizationMSTRepository;

    private AuthorisationDTO authorization = new AuthorisationDTO();

    private UserMST userMST;
    private SystemMST systemMST;

    public AuthorizationService() {
    }

    public AuthorizationService(UserMST userMST) {
        this.userMST = userMST;
    }


    public String[] getUserAccess(UserMST userMST) {
        try {
            UserRoleMST userRoleMSTS = userMST.getUserRoleMST();
            List<UserAuthorisationMST> userAuthorizationMSTList = new ArrayList<>();
            userAuthorizationMSTList.addAll(userAuthorizationMSTRepository.findByUserRoleMSTIdAndIsActiveTrue(userRoleMSTS.getId()));
            if (userAuthorizationMSTList.size() == 0) {
                throw new Exception("User Authorization Not Found For Your Role");
            }
            String[] access = userAuthorizationMSTList.stream().map(x -> x.getUserRoleMST().getRoleName()).distinct().toArray(String[]::new);
            return access;

        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    public AuthorizationService setUserMST(UserMST userMST) throws Exception {
        this.userMST = userMSTRepository.findByIdAndIsActiveTrue(userMST.getId()).orElseThrow(() -> new Exception("user not found"));
        return this;

    }

    public AuthorizationService setSystemMST(SystemMST systemMST) {
        this.systemMST = systemMST;
        return this;
    }

    public AuthorisationDTO getAuthorization() {
        return authorization;
    }


    public AuthorizationService check() {
        try {
            if (userMST == null) {
                throw new Exception("UserMST Is Null");
            }

            this.authorization = new AuthorisationDTO();

            UserRoleMST userRoleMSTS = userMST.getUserRoleMST();

            UserAuthorisationMST userAuthorizationMST = null;

            userAuthorizationMST = userAuthorizationMSTRepository.findByUserRoleMSTIdAndSystemMSTIdAndIsActiveTrue(userRoleMSTS.getId(), systemMST.getId()).orElseThrow(() -> new Exception("User role not found"));


            if (!userAuthorizationMST.getSystemMST().getId().equals(this.systemMST.getId())) {
                throw new Exception("User Not Authorised For This System");
            }


            if (this.systemMST != null) {
                this.authorization.setSystemMSTList(Collections.singletonList(systemMST));
            }

            this.authorization.setUserMST(userMST);
            this.authorization.setAuthorised(true);
            return this;
        } catch (Exception e) {
            this.authorization = new AuthorisationDTO();
            e.printStackTrace();
            return this;
        }
    }

}
