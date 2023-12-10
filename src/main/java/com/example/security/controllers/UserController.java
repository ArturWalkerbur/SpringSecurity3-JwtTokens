package com.example.security.controllers;

import com.example.security.dto.UpdatePassword;
import com.example.security.entity.Users;
import com.example.security.repository.UsersRepository;
import com.example.security.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public Boolean getUserDetails(){
        System.out.println("/info");
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-password")
    public String updatePassword(@RequestBody UpdatePassword updatePassword){
        String result = usersService.updatePassword(updatePassword.getPassword(), updatePassword.getNewPassword(), updatePassword.getReNewPassword());
        return "Password updated" + result;

    }


}