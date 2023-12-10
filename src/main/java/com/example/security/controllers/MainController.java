package com.example.security.controllers;


import com.example.security.dto.JWTAuthResponse;
import com.example.security.dto.Register_dto;
import com.example.security.dto.User_dto;
import com.example.security.entity.Users;
import com.example.security.services.AuthService;
import com.example.security.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/api/auth")
public class MainController {



    @Autowired
    UsersService usersService;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody User_dto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        System.out.println(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    @ResponseBody
    public String Register(@RequestBody Register_dto register_dto){
        System.out.println(register_dto.getEmail());
        System.out.println(register_dto.getFullname());
        String result = usersService.addUser(new Users(null, register_dto.getEmail(), register_dto.getPassword(), register_dto.getFullname()), register_dto.getRePassword());
        return "New user registered!" + result;
    }


}
