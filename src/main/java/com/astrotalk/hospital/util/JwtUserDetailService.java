package com.astrotalk.hospital.util;

import com.astrotalk.hospital.dao.UserMSTRepository;
import com.astrotalk.hospital.model.UserMST;
import com.astrotalk.hospital.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JwtUserDetailService implements UserDetailsService {
    @Autowired
    private UserMSTRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMST user = userDao.findFirstByUserNameIgnoreCaseAndIsActiveTrue(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),getAuthorities(user));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(UserMST userMST) {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authorizationService.getUserAccess(userMST));
        return authorities;
    }

    public UserMST save(UserMST user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    public UserMST getLoggedInUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() == null) return null;
        Object principal = securityContext.getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserMST user = userDao.findFirstByUserNameIgnoreCaseAndIsActiveTrue(username);
        return user;
    }
}
