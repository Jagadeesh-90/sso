package com.be.sso.backend.controller;

import com.be.sso.backend.config.JWTGenerator;
import com.be.sso.backend.dto.LoginDTO;
import com.be.sso.backend.dto.UsersDTO;
import com.be.sso.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.PATCH,})
public class    UserController {
    @Autowired
    UserService userService;

    @Autowired
    JWTGenerator jwtGenerator;

    @PostMapping("/save")
    public UsersDTO saveUser(@RequestBody UsersDTO user){
        return userService.save(user);
    }

    @GetMapping("/dashboard")
    public UsersDTO getUser(@RequestParam Long userId){
        return userService.findUser(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            if(loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
                throw new RuntimeException("UserName or Password is Empty");
            }
            UsersDTO userData = userService.getUserByEmailAndPassword(loginDTO);
            if(userData == null){
                throw new RuntimeException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(loginDTO), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
