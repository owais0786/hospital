package com.astrotalk.hospital.exception;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException exc) throws IOException, ServletException {

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpStatus.FORBIDDEN.value());
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("timestamp" , LocalDateTime.now().toString());
        hashMap.put("status" , HttpStatus.FORBIDDEN.value());
        hashMap.put("message", "Access Denied");

        res.getWriter().write(new Gson().toJson(hashMap));
    }
}
