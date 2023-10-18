package com.be.sso.backend.config;


import com.be.sso.backend.dto.LoginDTO;

import java.util.Map;

public interface JWTGenerator {
    Map<String, String> generateToken(LoginDTO loginDTO);
}
