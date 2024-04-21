package com.example.security.services;

public interface AnalysisService {

    String analyzeBloodParameters(double rbc, double hemoglobin, double hematocrit, double mchc, double mcv, double mch);

    String analyzeBloodParameters(double rbc);

}
