package com.example.security.services;

import com.example.security.entity.Assessment;
import com.example.security.entity.Users;

import java.util.List;

public interface AssessmentService {

    Assessment addAssessment(Assessment assessment);
    List<Assessment> getALlAssessment();
    Assessment getAssessment(Long id);
    void deleteAssessment(Assessment assessment);
    List<Long> getALlAssessmentId(Users user);
}
