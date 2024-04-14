package com.example.security.services;

import com.example.security.entity.TestResults;

import java.util.List;

public interface TestResultsService {

    TestResults addTestResults(TestResults testResults);
    List<TestResults> getALlTestResults();
    TestResults getTestResults(Long id);
    void deleteTestResults(TestResults testResults);

}
