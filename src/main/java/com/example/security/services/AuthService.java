package com.example.security.services;

import com.example.security.dto.User_dto;

public interface AuthService {
    String login(User_dto loginDto);
}
