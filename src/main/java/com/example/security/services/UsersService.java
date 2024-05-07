package com.example.security.services;


import com.example.security.dto.User_dto;
import com.example.security.entity.Roles;
import com.example.security.entity.Users;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UsersRepository;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

import java.util.*;

public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MailSender mailSender;

    @Value("${Url.debug}")
    private String hostserver;

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
            return "Email-error!UserExists";
        }
        if (!newUser.getPassword().equals(rePassword)) {
            return "register?password-error";
        }
        newUser.setPassword(passwordEncoder.encode(rePassword));
        Roles userRole = roleRepository.findRoleUser();
        newUser.setRoles(List.of(userRole));
        newUser.setActivationCode(UUID.randomUUID().toString());
        usersRepository.save(newUser);



        if(!StringUtils.isEmpty(newUser.getEmail())){

            String message = String.format(
                    "Привет, %s! \n" +
                            "Вас приветствует компания MedicalApps. Пожалуйста, перейдите по ссылке чтобы подтвердить вашу почту: "+hostserver+"/api/auth/activate/%s \n\n" +
                            "Если вы не регистрировались у нас, проигнорируйте это сообщение!",
                    newUser.getUsername(),
                    newUser.getActivationCode()
            );

            mailSender.send(newUser.getEmail(), "Activation Code", message);

        }

        return "register?success&butNotAuthenticated";

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

    public boolean activateUser(String code) {

        Users user = usersRepository.findByActivationCode(code);

        if (user == null){
            return false;
        }

        user.setActivationCode(null);

        usersRepository.save(user);

        return true;

    }

    public String forgottenPassword(String email){

        Users user = usersRepository.findByEmail(email);

        if(user != null){

            user.setActivationCode(UUID.randomUUID().toString());
            usersRepository.save(user);

            String message = String.format(
                    "Привет, %s! \n" +
                            "Для восстановления пароля, пожалуйста, перейдите по этой ссылке: "+hostserver+"/api/auth/recover-password/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Password Recovery", message);

            return "success";

        } else {
            return "something wrong!";
        }

    }

    public String recoverPassword(String newPassword, String reNewPassword, String code) {
        Users user = usersRepository.findByActivationCode(code);

        if (user == null){
            return "recover-password?code-error";
        }

        if(!newPassword.equals(reNewPassword)){
            return "recover-password?retype-error";
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setActivationCode(null);
        usersRepository.save(user);
        return "profile?success";
    }

}
