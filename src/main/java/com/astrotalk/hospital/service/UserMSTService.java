package com.astrotalk.hospital.service;

import com.astrotalk.hospital.config.JwtTokenUtil;
import com.astrotalk.hospital.dao.SystemMSTRepository;
import com.astrotalk.hospital.dao.UserMSTRepository;
import com.astrotalk.hospital.dao.UserRoleMSTRepository;
import com.astrotalk.hospital.model.HospitalStaffMST;
import com.astrotalk.hospital.model.SystemMST;
import com.astrotalk.hospital.model.UserMST;
import com.astrotalk.hospital.model.UserRoleMST;
import com.astrotalk.hospital.payload.request.LoginRequest;
import com.astrotalk.hospital.payload.request.SignUpRequest;
import com.astrotalk.hospital.payload.response.LoginResponse;
import com.astrotalk.hospital.util.GenericResponse;
import com.astrotalk.hospital.util.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMSTService {

    @Autowired
    private UserMSTRepository repository;
    @Autowired
    private SystemMSTRepository systemMSTRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailService jwtUserDetailsService;

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserRoleMSTRepository userRoleMSTRepository;
    @Autowired
    private HospitalStaffMSTService hospitalStaffMSTService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public String signUp(SignUpRequest signUpRequest) throws Exception {
        repository.findByUserName(signUpRequest.getUserName()).ifPresent(x->{
            throw new RuntimeException("User Name Already Exist!");
        });
        signUpUser(signUpRequest);
        createHospitalStaff(signUpRequest);
        return "Signed Up Succesfully!";
    }

    private void signUpUser(SignUpRequest signUpRequest) throws Exception {
        UserMST userMST=new UserMST();
        userMST.setUserName(signUpRequest.getUserName());
        userMST.setPassword(bcryptEncoder.encode(signUpRequest.getPassword()));
        UserRoleMST userRoleMST = userRoleMSTRepository.findById(signUpRequest.getUserRoleMSTId()).orElseThrow(() -> new Exception("User Role not found!"));
        userMST.setUserRoleMST(userRoleMST);
        userMST.setUserRoleMSTId(userRoleMST.getId());
        repository.save(userMST);
    }

    private void createHospitalStaff(SignUpRequest signUpRequest) {
        HospitalStaffMST hospitalStaffMST=new HospitalStaffMST();
        hospitalStaffMST.setContactNumber(signUpRequest.getContactNumber());
        hospitalStaffMST.setStaffName(signUpRequest.getStaffName());
        hospitalStaffMST.setStaffDesignation(signUpRequest.getStaffDesignation());
        hospitalStaffMSTService.create(hospitalStaffMST);
    }

    public ResponseEntity login(LoginRequest loginMST) {
        GenericResponse genericResponse = new GenericResponse();


        try {

            UserMST existingUser = repository.findFirstByUserNameIgnoreCaseAndIsActiveTrue(loginMST.getUserName());

            if (existingUser != null && !existingUser.getId().toString().isEmpty()) {

                List<SystemMST> systemMSTList = systemMSTRepository.findByIsActiveTrueAndSystemCode(loginMST.getSystem());
                if (systemMSTList == null || systemMSTList.size() != 1) {
                    throw new Exception("SystemMST Not Found For Code :" + loginMST.getSystem());
                }

                if (loginMST.getPassword() == null || loginMST.getPassword().isEmpty()) {
                    genericResponse.setData(null);
                    genericResponse.setMessage("Password cannot be null or empty!!");
                    genericResponse.setStatus(false);
                    return new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
                }
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginMST.getUserName(), loginMST.getPassword()));
                    final UserDetails userDetails = jwtUserDetailsService
                            .loadUserByUsername(loginMST.getUserName());

                    try {
                        if (!authorizationService.setSystemMST(systemMSTList.get(0)).setUserMST(existingUser).check().getAuthorization().isAuthorised()) {
                            throw new Exception("Not Authorised");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        genericResponse.setData(null);
                        genericResponse.setMessage(e.getMessage());
                        genericResponse.setStatus(false);
                        return new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
                    }

                    final String token = jwtTokenUtil.generateToken(existingUser.getId(), userDetails, loginMST.getSystem());
                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setId(existingUser.getId());
                    loginResponse.setUserName(existingUser.getUserName());
                    loginResponse.setJwtToken(token);
                    loginResponse.setUserMST(existingUser);
                    genericResponse.setData(loginResponse);
                    genericResponse.setStatus(true);
                    genericResponse.setMessage("Success");
                    return new ResponseEntity(genericResponse, HttpStatus.OK);
                } catch (DisabledException e) {

                    genericResponse.setData(null);
                    genericResponse.setMessage("USER_DISABLED");
                    genericResponse.setStatus(false);
                    return new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
                } catch (BadCredentialsException e) {
                    genericResponse.setData(null);
                    genericResponse.setMessage("INVALID_CREDENTIALS");
                    genericResponse.setStatus(false);
                    e.printStackTrace();
                    return new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            genericResponse.setMessage("Something went wrong! please try again!");
            genericResponse.setStatus(false);
            return new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
        }

        genericResponse.setMessage("Something went wrong! please try again!");
        genericResponse.setStatus(false);
        return new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
    }



}
