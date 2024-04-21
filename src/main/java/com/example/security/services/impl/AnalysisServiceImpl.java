package com.example.security.services.impl;

import com.example.security.services.AnalysisService;
import org.springframework.stereotype.Service;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Override
    public String analyzeBloodParameters(double rbc, double hemoglobin, double hematocrit, double mchc, double mcv, double mch) {
        return null;
    }

    @Override
    public String analyzeBloodParameters(double rbc) {
        return null;
    }
}
