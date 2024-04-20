package com.example.security.services.impl;

import com.example.security.configs.JwtTokenProvider;
import com.example.security.dto.User_dto;
import com.example.security.entity.Users;
import com.example.security.repository.UsersRepository;
import com.example.security.services.AuthService;
import com.example.security.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsersRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Override
    public String login(User_dto loginDto) {

        String email = loginDto.getEmail();
        if (email.contains("%40")) {
            email = email.replace("%40", "@");
        }

        Users user = userRepository.findByEmail(email);
        if(user.isEnabled()){
            return "Account not activated!";
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, loginDto.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println(authentication.isAuthenticated());

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}