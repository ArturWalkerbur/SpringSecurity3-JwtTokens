package com.example.security.services;


import com.example.security.entity.StandarDataComparison;

import java.util.List;

public interface StandarDataComparisonService {

    StandarDataComparison addComparisonData(StandarDataComparison data);


    StandarDataComparison findComparisonData(String indicator, String gender, int age);

    void deleteComparisonData(StandarDataComparison data);


}
