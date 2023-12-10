package com.example.security.services;


import com.example.security.dto.User_dto;
import com.example.security.entity.Roles;
import com.example.security.entity.Users;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepository.findByEmail(email);
        if (users == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return users;
    }

    public String addUser(Users newUser, String rePassword) {
        Users user = usersRepository.findByEmail(newUser.getEmail());
        if (user != null){
            return "register?email-error";
        }
        if (!newUser.getPassword().equals(rePassword)) {
            return "register?password-error";
        }
        newUser.setPassword(passwordEncoder.encode(rePassword));
        Roles userRole = roleRepository.findRoleUser();
        newUser.setRoles(List.of(userRole));
        usersRepository.save(newUser);
        return "Log-in?success";

    }

    public Users getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            return null;
        }

        return (Users) authentication.getPrincipal();
    }

    public String updatePassword(String password, String newPassword, String reNewPassword) {

        Users user = getCurrentUser();

        if(!passwordEncoder.matches(password, user.getPassword())){
            return "profile?equal-error";
        }
        if(!newPassword.equals(reNewPassword)){
            return "profile?retype-error";
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(user);
        return "profile?success";
    }

}
