package com.example.security.services;

import com.example.security.dto.Comment_dto;

public interface AnalysisService {

    Comment_dto analyzeBloodParameters(double rbc, double hemoglobin, double hematocrit, double mchc, double mcv, double mch, String gender, int age);

    Comment_dto analyzeBloodParameters(double rbc, String gender, int age);

    Comment_dto commentIndicator(String indicatorName, double indicatorIndex, String gender, int age);

}
