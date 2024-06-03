package com.example.security.services.impl;

import com.example.security.entity.StandarDataComparison;
import com.example.security.repository.StandarDataComparisonRepository;
import com.example.security.services.StandarDataComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandarDataComparisonServiceImpl implements StandarDataComparisonService {

    @Autowired
    StandarDataComparisonRepository comparisonRepository;

    @Override
    public StandarDataComparison addComparisonData(StandarDataComparison data) {
        return comparisonRepository.save(data);
    }

    @Override
    public StandarDataComparison findComparisonData(String indicator, String gender, int age) {
        System.out.println(indicator + " " + gender + " " + age);
        return comparisonRepository.findByGenderAndAge(indicator, gender, age);
    }

    @Override
    public void deleteComparisonData(StandarDataComparison data) {
        comparisonRepository.delete(data);
    }
}
