package com.example.security.services;

public interface AnalysisService {

    String analyzeBloodParameters(double rbc, double hemoglobin, double hematocrit, double mchc, double mcv, double mch, String gender, int age);

    String analyzeBloodParameters(double rbc, String gender, int age);

    String commentIndicator(String indicatorName, double indicatorIndex, String gender, int age);

}
