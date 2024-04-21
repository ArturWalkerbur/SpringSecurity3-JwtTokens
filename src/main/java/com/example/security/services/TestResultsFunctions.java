package com.example.security.services;

import com.example.security.dto.Indicator_dto;
import com.example.security.dto.TestResults_dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestResultsFunctions {

    @Autowired
    private AnalysisService analysisService;

    public List<Indicator_dto> convertToIndicators(TestResults_dto testResults) {
        List<Indicator_dto> indicators = new ArrayList<>();

        if (testResults.getHemoglobinLevel() != 0) {
            indicators.add(createIndicator("hemoglobin", testResults.getHemoglobinLevel()));
        }
        if (testResults.getRbcLevel() != 0) {
            indicators.add(createIndicator("rbc", testResults.getRbcLevel()));
        }
        if (testResults.getColorIndicatorLevel() != 0) {
            indicators.add(createIndicator("colorIndicator", testResults.getColorIndicatorLevel()));
        }
        if (testResults.getReticulocytesLevel() != 0) {
            indicators.add(createIndicator("reticulocytes", testResults.getReticulocytesLevel()));
        }
        if (testResults.getPlateletsLevel() != 0) {
            indicators.add(createIndicator("platelets", testResults.getPlateletsLevel()));
        }
        if (testResults.getWbcLevel() != 0) {
            indicators.add(createIndicator("wbc", testResults.getWbcLevel()));
        }
        if (testResults.getNeutrophilsLevel() != 0) {
            indicators.add(createIndicator("neutrophils", testResults.getNeutrophilsLevel()));
        }
        if (testResults.getEosinophilsLevel() != 0) {
            indicators.add(createIndicator("eosinophils", testResults.getEosinophilsLevel()));
        }
        if (testResults.getBasophilsLevel() != 0) {
            indicators.add(createIndicator("basophils", testResults.getBasophilsLevel()));
        }
        if (testResults.getLymphocytesLevel() != 0) {
            indicators.add(createIndicator("lymphocytes", testResults.getLymphocytesLevel()));
        }
        if (testResults.getMonocytesLevel() != 0) {
            indicators.add(createIndicator("monocytes", testResults.getMonocytesLevel()));
        }
        if (testResults.getESRLevel() != 0) {
            indicators.add(createIndicator("ESR", testResults.getESRLevel()));
        }
        if (testResults.getHematocritLevel() != 0) {
            indicators.add(createIndicator("hematocrit", testResults.getHematocritLevel()));
        }
        if (testResults.getMchc() != 0) {
            indicators.add(createIndicator("mchc", testResults.getMchc()));
        }
        if (testResults.getMcv() != 0) {
            indicators.add(createIndicator("mcv", testResults.getMcv()));
        }
        if (testResults.getMch() != 0) {
            indicators.add(createIndicator("mch", testResults.getMch()));
        }
        if (testResults.getThrombocritLevel() != 0) {
            indicators.add(createIndicator("thrombocrit", testResults.getThrombocritLevel()));
        }

        return indicators;
    }

    public String chooseAnalysisFunction(List<Indicator_dto> filteredIndicators) {
        boolean rbc = false, hemoglobin = false, hematocrit = false, mchc = false, mcv = false, mch = false,
                colorIndicator = false, reticulocytes = false, platelets = false, wbc = false,
                neutrophils = false, eosinophils = false, basophils = false, lymphocytes = false,
                monocytes = false, esr = false, thrombocrit = false;
        double rbcIdx = 0, hemoglobinIdx = 0, hematocritIdx = 0, mchcIdx = 0, mcvIdx = 0, mchIdx = 0,
                colorIndicatorIdx = 0, reticulocytesIdx = 0, plateletsIdx = 0, wbcIdx = 0,
                neutrophilsIdx = 0, eosinophilsIdx = 0, basophilsIdx = 0, lymphocytesIdx = 0,
                monocytesIdx = 0, esrIdx = 0, thrombocritIdx = 0;

        String commentTexts = "";

        for (Indicator_dto indicator : filteredIndicators) {
            String name = indicator.getName();
            switch (name) {
                case "rbc":
                    rbc = true;
                    rbcIdx = indicator.getIndex();
                    //commentTexts = commentTexts + commentIndicator(indicator.getName(), indicator.getIndex());
                    break;
                case "hemoglobin":
                    hemoglobin = true;
                    hemoglobinIdx = indicator.getIndex();
                    break;
                case "hematocrit":
                    hematocrit = true;
                    hematocritIdx = indicator.getIndex();
                    break;
                case "mchc":
                    mchc = true;
                    mchcIdx = indicator.getIndex();
                    break;
                case "mcv":
                    mcv = true;
                    mcvIdx = indicator.getIndex();
                    break;
                case "mch":
                    mch = true;
                    mchIdx = indicator.getIndex();
                    break;
                case "colorIndicator":
                    colorIndicator = true;
                    colorIndicatorIdx = indicator.getIndex();
                    break;
                case "reticulocytes":
                    reticulocytes = true;
                    reticulocytesIdx = indicator.getIndex();
                    break;
                case "platelets":
                    platelets = true;
                    plateletsIdx = indicator.getIndex();
                    break;
                case "wbc":
                    wbc = true;
                    wbcIdx = indicator.getIndex();
                    break;
                case "neutrophils":
                    neutrophils = true;
                    neutrophilsIdx = indicator.getIndex();
                    break;
                case "eosinophils":
                    eosinophils = true;
                    eosinophilsIdx = indicator.getIndex();
                    break;
                case "basophils":
                    basophils = true;
                    basophilsIdx = indicator.getIndex();
                    break;
                case "lymphocytes":
                    lymphocytes = true;
                    lymphocytesIdx = indicator.getIndex();
                    break;
                case "monocytes":
                    monocytes = true;
                    monocytesIdx = indicator.getIndex();
                    break;
                case "ESR":
                    esr = true;
                    esrIdx = indicator.getIndex();
                    break;
                case "thrombocrit":
                    thrombocrit = true;
                    thrombocritIdx = indicator.getIndex();
                    break;
            }
        }

        String mainCommentText = "";

        if (rbc && hemoglobin && hematocrit && mchc && mcv && mch) {
            mainCommentText = analysisService.analyzeBloodParameters(rbcIdx, hemoglobinIdx, hematocritIdx, mchcIdx, mcvIdx, mchIdx);
        } else if (rbc) {
            mainCommentText = analysisService.analyzeBloodParameters(rbcIdx);
        }

        return mainCommentText+commentTexts;
    }

    private Indicator_dto createIndicator(String name, double index) {
        Indicator_dto indicator = new Indicator_dto();
        indicator.setName(name);
        indicator.setIndex(index);
        indicator.setNormal(checkIfNormal(name, index));
        return indicator;
    }

    private boolean checkIfNormal(String name, double index) {
        // Здесь вы можете добавить свои логику проверки для каждого показателя
        // Например, сравнение с определенными диапазонами значений
        // Верните true, если значение находится в норме, и false в противном случае
        return true; // Для примера, всегда возвращаем true
    }



}
