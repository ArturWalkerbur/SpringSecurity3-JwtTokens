package com.example.security.controllers;

import com.example.security.dto.Indicator_dto;
import com.example.security.dto.TestResults_dto;
import com.example.security.dto.UpdatePassword;
import com.example.security.dto.User_dto;
import com.example.security.entity.Assessment;
import com.example.security.entity.Users;
import com.example.security.repository.UsersRepository;
import com.example.security.services.AssessmentService;
import com.example.security.services.TestResultsFunctions;
import com.example.security.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    AssessmentService assessmentService;

    @Autowired
    TestResultsFunctions testResultsFunctions;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public Boolean getUserDetails(){
        System.out.println("/info");
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userdata")
    public ResponseEntity<Users> getUserData(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        return ResponseEntity.ok(new Users(usersService.getCurrentUser().getId(), usersService.getCurrentUser().getEmail(), usersService.getCurrentUser().getFullName(), usersService.getCurrentUser().getBirthDate(), usersService.getCurrentUser().getGender(), usersService.getCurrentUser().getLastDiagnosis(), usersService.getCurrentUser().getContact(), usersService.getCurrentUser().getRoles()));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-password")
    public String updatePassword(HttpServletRequest request, @RequestBody UpdatePassword updatePassword){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        String result = usersService.updatePassword(updatePassword.getPassword(), updatePassword.getNewPassword(), updatePassword.getReNewPassword());
        return "Password updated" + result;

    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get_all_assessments")
    @ResponseBody
    public ResponseEntity<List<Assessment>> getAllAssessments(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        List<Assessment> assessments = assessmentService.getALlAssessment();
        return ResponseEntity.ok(assessments);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get_assessment/{id}")
    @ResponseBody
    public ResponseEntity<Assessment> getAssessment(HttpServletRequest request, @PathVariable(name = "id")Long id){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        return ResponseEntity.ok( assessmentService.getAssessment(id));

    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/deleteAssessment/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteAssessment(HttpServletRequest request, @PathVariable(name = "id")Long id){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        try{

            Assessment assessment = assessmentService.getAssessment(id);

            assessmentService.deleteAssessment(assessment);

            return ResponseEntity.ok("New assessment deleted!");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error deleting assessment");
        }



    }




}