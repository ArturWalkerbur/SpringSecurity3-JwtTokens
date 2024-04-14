package com.example.security.services.impl;

import com.example.security.entity.TestResults;
import com.example.security.repository.TestResultsRepository;
import com.example.security.services.TestResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultsServiceImpl implements TestResultsService {

    @Autowired
    private TestResultsRepository testResultsRepository;

    @Override
    public TestResults addTestResults(TestResults testResults) {
        return testResultsRepository.save(testResults);
    }

    @Override
    public List<TestResults> getALlTestResults() {
        return testResultsRepository.findAll();
    }

    @Override
    public TestResults getTestResults(Long id) {
        return testResultsRepository.getReferenceById(id);
    }

    @Override
    public void deleteTestResults(TestResults testResults) {
        testResultsRepository.delete(testResults);
    }
}
