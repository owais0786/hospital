package com.astrotalk.hospital.config;

import com.astrotalk.hospital.util.Crypto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final long serialVersionUID = -2550185165626007488L;
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    Crypto crypto;

    public Claims getAllClaimsFromToken(String token) {
        return getAllClaimsFromToken(token, true);
    }

    public Claims getAllClaimsFromToken(String token, boolean isEncrypted) {
        if (isEncrypted) {
            token = decryptToken(token);
        }
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(Claims claims) {
        return !isTokenExpired(claims);
    }

    private Boolean isTokenExpired(Claims claims) {
        final Date expiration = claims.getExpiration();
        if(expiration==null)
            return false;
        return expiration.before(new Date());
    }



    public String generateToken(Long id, UserDetails userDetails, String system) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("system",system);
        claims.put("access", getAuthoritiesFromUserDetails(userDetails));
        return doGenerateToken(claims, id.toString());

    }

    public String doGenerateToken(Map<String, Object> claims, String subject) throws Exception {
        String token = encryptToken(Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(null)
                .signWith(SignatureAlgorithm.HS512, secret).compact());

        return token;
    }


    public String encryptToken(String token) {
        String encrypt = crypto.encrypt(token);
        return encrypt;
    }

    public String getAuthoritiesFromUserDetails(UserDetails userDetails) {
        try {
            return userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        } catch (Exception e) {
            return "";
        }
    }
    public Collection<? extends GrantedAuthority> getAuthoritiesFromToken(Claims claims) {
        try{
            String[] access = claims.get("access").toString().split(",");
            return AuthorityUtils.createAuthorityList(access);
        }catch (Exception e){
            e.printStackTrace();
            return AuthorityUtils.createAuthorityList();
        }
    }

    public String decryptToken(String token) {
        String decrypt = crypto.decrypt(token);
        return decrypt;

    }
}
