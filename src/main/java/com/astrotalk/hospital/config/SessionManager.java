package com.astrotalk.hospital.config;

import com.astrotalk.hospital.dao.SystemMSTRepository;
import com.astrotalk.hospital.dao.UserMSTRepository;
import com.astrotalk.hospital.dao.UserRoleMSTRepository;
import com.astrotalk.hospital.model.SystemMST;
import com.astrotalk.hospital.model.UserMST;
import com.astrotalk.hospital.model.UserRoleMST;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class SessionManager {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SystemMSTRepository systemMSTRepository;

    @Autowired
    private UserMSTRepository userMSTRepository;

    @Autowired
    private UserRoleMSTRepository userRoleMSTRepository;
;

    public String getToken() {
        String header = request.getHeader("Authorization");
        if (header == null) return null;
        return header.split(" ")[1];
    }

    public Object getClaim(String claim) {
        if (getToken() == null) {
            return null;
        }

        return jwtTokenUtil.getAllClaimsFromToken(getToken()).get(claim);
    }


    public SystemMST getCurrentSystem(){
        Object system = getClaim("system");
        if (system == null) {
            return null;
        }
        return systemMSTRepository.findFirstByIsActiveTrueAndSystemCode((String) system).orElse(null);
    }

    public UserRoleMST getCurrentUserRole(){
        Object roleId = getClaim("role");
        if (roleId == null) {
            return null;
        }
        return userRoleMSTRepository.findById((Long)roleId).orElse(null);
    }

    public UserMST getCurrentUser() throws Exception {
        Object username = getClaim("sub");
        return userMSTRepository.findByIdAndIsActiveTrue((Long) username).orElseThrow(()->new Exception("User not found"));

    }



}

