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

@Controller
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
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
        String email = register_dto.getEmail();
        if (email.contains("%40")) {
            email = email.replace("%40", "@");
        }
        System.out.println("aaaaaaaa");
        String result = usersService.addUser(new Users(null, email, register_dto.getPassword(), register_dto.getFullName(), register_dto.getBirthDate(), register_dto.getGender(), register_dto.getLastDiagnosis(), register_dto.getContact()), register_dto.getRePassword());
        System.out.println("eeeeeeee");
        return "New user registered?" + result;
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/activate/{code}")
    @ResponseBody
    public String activate(@PathVariable String code){

        System.out.println("ffffff");

        boolean isActivated = usersService.activateUser(code);

        if (isActivated){
            return "Account successfully activated!";
        } else {
            return "Activation code not found!";
        }

    }


}
