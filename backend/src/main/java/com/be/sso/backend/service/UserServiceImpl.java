package com.be.sso.backend.service;

import com.be.sso.backend.dto.LoginDTO;
import com.be.sso.backend.dto.UsersDTO;
import com.be.sso.backend.entity.Users;
import com.be.sso.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UsersDTO save(UsersDTO theUser) {
        return UsersDTO.convertEntityToDto(userRepository.save(UsersDTO.convertDtoToEntity(theUser)));
    }

    @Override
    public UsersDTO findUser(Long id) {
        Optional<Users> theUser = userRepository.findById(id);
        return theUser.map(UsersDTO::convertEntityToDto).orElse(null);
    }

    @Override
    public UsersDTO getUserByEmailAndPassword(LoginDTO loginDTO) {
        Optional<Users> user = userRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
        if(user.isEmpty()){
            throw new RuntimeException("Invalid email and password");
        }
        return UsersDTO.convertEntityToDto(user.get());
    }
}
