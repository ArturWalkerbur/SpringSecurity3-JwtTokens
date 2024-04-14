package com.example.security.services.impl;

import com.example.security.entity.Assessment;
import com.example.security.repository.AssessmentRepository;
import com.example.security.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Override
    public Assessment addAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    @Override
    public List<Assessment> getALlAssessment() {
        return assessmentRepository.findAll();
    }

    @Override
    public Assessment getAssessment(Long id) {
        return assessmentRepository.getReferenceById(id);
    }

    @Override
    public void deleteAssessment(Assessment assessment) {
        assessmentRepository.delete(assessment);
    }
}
