package com.medical.hospital.diagnostics.user.services;

import com.medical.hospital.diagnostics.exceptions.AuthTokenException;
import com.medical.hospital.diagnostics.jwt.JwtTokenUtil;
import com.medical.hospital.diagnostics.user.dto.StartDTO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import static com.medical.hospital.diagnostics.interfaces.StartConstants.*;

@Service
@Transactional
public class AuthService {
    @Autowired
    private JwtTokenUtil jwtUtil;

    private String getUserToken(HttpServletRequest request) {
        try {
            String header = request.getHeader("Authorization");
            return header.split(" ")[1].trim();
        } catch (Exception e) {
            throw new AuthTokenException("Authorization token invalid.");
        }
    }

    private String[] getUserRoles(String token) {
        String[] rolesArray = {NO_ROLES};
        try {
            Claims claims = jwtUtil.parseClaims(token);
            String roles = (String) claims.get("roles");
            System.out.println("!!! roles: " + roles);

            roles = roles.replace("[", "").replace("]", "");
            rolesArray = roles.split(",");
            return rolesArray;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return rolesArray;
    }
   public StartDTO chooseUserLinks(HttpServletRequest request) {
        String token = getUserToken(request);
        String[] userRoles = getUserRoles(token);
        StartDTO startDTO = new StartDTO();

       System.out.println("!!! userRoles[0] = " + userRoles[0]);

       if (ROLE_DOCTOR.equals(userRoles[0])) {
           startDTO.setTitle(TITLE_RESOURCE_DOCTOR);
           startDTO.setLink_resource_one(URL_STOCK);
           startDTO.setLink_resource_two(URL_PATIENTS);
           return startDTO;
       }

       if (ROLE_MANAGER.equals(userRoles[0])) {
           startDTO.setTitle(TITLE_RESOURCE_MANAGER);
           startDTO.setLink_resource_one(URL_STOCK);
           startDTO.setLink_resource_two(URL_FOOD);
           return startDTO;
       }

       startDTO.setTitle(TITLE_LOGIN_REQUIRED);
       startDTO.setLink_resource_one(URL_START);
       startDTO.setLink_resource_two(URL_LOGIN);
       return startDTO;
   }


}
