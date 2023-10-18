package com.be.sso.backend.config;

import com.be.sso.backend.dto.LoginDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTGeneratorImpl implements JWTGenerator{

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.message}")
    private String message;

    @Override
    public Map<String, String> generateToken(LoginDTO loginDTO) {
        String jwtToken="";
        jwtToken = Jwts.builder().setSubject(loginDTO.getEmail()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 43200000)).signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }
}
