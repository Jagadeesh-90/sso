package com.be.sso.backend.service;

import com.be.sso.backend.dto.LoginDTO;
import com.be.sso.backend.dto.UsersDTO;

public interface UserService {
    UsersDTO save(UsersDTO user);
    UsersDTO findUser(Long id);
    UsersDTO getUserByEmailAndPassword(LoginDTO loginDTO);
}
