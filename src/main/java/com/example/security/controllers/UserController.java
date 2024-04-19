package com.example.security.controllers;

import com.example.security.dto.TestResults_dto;
import com.example.security.dto.UpdatePassword;
import com.example.security.dto.User_dto;
import com.example.security.entity.Assessment;
import com.example.security.entity.TestResults;
import com.example.security.entity.Users;
import com.example.security.repository.UsersRepository;
import com.example.security.services.AssessmentService;
import com.example.security.services.TestResultsService;
import com.example.security.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    TestResultsService testResultsService;

    @Autowired
    AssessmentService assessmentService;

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
    @GetMapping("/get_all_test_results")
    @ResponseBody
    public ResponseEntity<List<TestResults>> getAllTestResults(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        List<TestResults> testResults = testResultsService.getALlTestResults();
        return ResponseEntity.ok(testResults);

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
    @GetMapping("/get_test_result/{id}")
    @ResponseBody
    public ResponseEntity<TestResults> getTestResult(HttpServletRequest request, @PathVariable(name = "id")Long id){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        return ResponseEntity.ok( testResultsService.getTestResults(id));

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
    @PostMapping("/addTestResult")
    @ResponseBody
    public ResponseEntity<String> addNewTestResult(HttpServletRequest request, @RequestBody TestResults_dto testResultsDto){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        try{

            /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(newOrder.getDate());*/

            testResultsService.addTestResults(new TestResults(null, testResultsDto.getDateCompletionAnalysis(), testResultsDto.getHemoglobinLevel(), testResultsDto.getLeukocyteLevel()));

            return ResponseEntity.ok("New result added!");

        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.status(500).body("Error adding result");
        }



    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/deleteTestResult/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTestResult(HttpServletRequest request, @PathVariable(name = "id")Long id){

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        try{

            TestResults testResults = testResultsService.getTestResults(id);

            testResultsService.deleteTestResults(testResults);

            return ResponseEntity.ok("New result deleted!");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error deleting result");
        }



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