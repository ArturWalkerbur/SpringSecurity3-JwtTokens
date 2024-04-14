package com.example.security.services;

import com.example.security.entity.Assessment;

import java.util.List;

public interface AssessmentService {

    Assessment addAssessment(Assessment assessment);
    List<Assessment> getALlAssessment();
    Assessment getAssessment(Long id);
    void deleteAssessment(Assessment assessment);

}
