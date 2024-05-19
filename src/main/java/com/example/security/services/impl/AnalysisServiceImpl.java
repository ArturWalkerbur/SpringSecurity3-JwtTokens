package com.example.security.services.impl;

import com.example.security.entity.StandarDataComparison;
import com.example.security.services.AnalysisService;
import com.example.security.services.StandarDataComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private StandarDataComparisonService comparisonService;

    @Override
    public String analyzeBloodParameters(double rbc, double hemoglobin, double hematocrit, double mchc, double mcv, double mch, String gender, int age) {

        StandarDataComparison rbcStan = comparisonService.findComparisonData("rbc", gender, age);
        StandarDataComparison hemoglobinStan = comparisonService.findComparisonData("hemoglobin", gender, age);
        StandarDataComparison hematocritStan = comparisonService.findComparisonData("hematocrit", gender, age);
        StandarDataComparison mchcStan = comparisonService.findComparisonData("mchc", gender, age);
        StandarDataComparison mcvStan = comparisonService.findComparisonData("mcv", gender, age);
        StandarDataComparison mchStan = comparisonService.findComparisonData("mch", gender, age);

        if(rbc > rbcStan.getMax() && hemoglobin > hemoglobinStan.getMax() && hematocrit > hematocritStan.getMax() && mchc > mchcStan.getMax() && mcv > mcvStan.getMax() && mcv > mcvStan.getMax()){
            return "Показатели RBC, гемоглобин, гематокрит, MCHC, MCV, MCH повышены(выше нормы). Возможно проблемы с почками, костным мозгом и сердечной недостаточностью." +
                    "Проблемы с почками могут влиять на состав крови из-за их роли в регуляции процессов образования крови и удаления токсинов. Например, заболевания почек, такие как хроническая почечная недостаточность, могут привести к анемии из-за недостатка эритропоэтина (гормона, который стимулирует образование эритроцитов).\n" +
                    "\n" +
                    "Проблемы с костным мозгом, такие как миелопролиферативные заболевания (например, полицитемия веры), могут вызывать избыточное производство эритроцитов и, следовательно, повышение всех вышеперечисленных параметров.\n" +
                    "\n" +
                    "Сердечная недостаточность также может вызвать изменения в составе крови. Например, из-за снижения эффективности кровообращения организм может начать продуцировать больше эритроцитов, чтобы компенсировать недостаток кислорода, что приводит к полицитемии";

        } else {
            return "";
        }
    }

    @Override
    public String analyzeBloodParameters(double rbc, String gender, int age) {
        StandarDataComparison rbcStan = comparisonService.findComparisonData("rbc", gender, age);
        if(rbc > rbcStan.getMax()){
            return "Показатель RBC(Эритроциты) повышен (выше нормы). Возможно обезвоживание. Также высокое количество эритроцитов может наблюдаться у людей, живущих на большой высоте (не хватает кислорода)";
        } else {
            return "Показатель RBC(Эритроциты) понижен (меньше нормы). Возможно гипергидратация. Гипергидратация означает избыток воды в организме. Также Низкое количество эритроцитов может быть симптомом анемии из-за потери крови, гемолиза или дефицита витамина В9 (фолиевая кислота) и В12 (цианокобаламин).";
        }

    }

    @Override
    public String commentIndicator(String indicatorName, double indicatorIndex, String gender, int age) {
        StandarDataComparison data = comparisonService.findComparisonData(indicatorName, gender, age);
        String comment_text = "";
        double index_norm = 0;
        switch (indicatorName) {
            case "rbc":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Эритроциты(RBC) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Эритроциты(RBC) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "hemoglobin":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Гемоглобин(HGB) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Гемоглобин(HGB) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "hematocrit":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Гематокрит(HCT) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Гематокрит(HCT) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "mchc":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Средняя концентрация гемоглобина в эритроците(MCHC) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Средняя концентрация гемоглобина в эритроците(MCHC) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "mcv":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Средний объем эритроцитов(MCV) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Средний объем эритроцитов(MCV) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "mch":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Среднее содержание гемоглобина в эритроците(MCH)меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Среднее содержание гемоглобина в эритроците(MCH)больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "colorIndicator":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Цветной показатель меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Цветной показатель больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "reticulocytes":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Ретикулоциты меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Ретикулоциты больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "platelets":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Тромбоциты(PLT) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Тромбоциты(PLT) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "wbc":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Лейкоциты(WBC) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Лейкоциты(WBC) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "neutrophils":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Нейтрофилы(RBC) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Нейтрофилы(RBC) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "eosinophils":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Эозинофилы(EOS%) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Эозинофилы(EOS%) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "basophils":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Базофилы меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Базофилы больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "lymphocytes":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Лимфоциты(LYM%) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Лимфоциты(LYM%) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "monocytes":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Моноциты(MON%) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Моноциты(MON%) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "ESR":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Скорость оседания эритроцитов(ESR) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Скорость оседания эритроцитов(ESR) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            case "thrombocrit":
                if(indicatorIndex < data.getMin()){
                    comment_text = "Тромбокрит(PCT) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                    index_norm = data.getMin();
                } else {
                    comment_text = "Тромбокрит(PCT) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                    index_norm = data.getMax();
                }
                break;
            default:
                comment_text = "";
                break;
        }
        return comment_text + " " + IndicatorStatusReturn(indicatorName, indicatorIndex, index_norm);
    }

    private String IndicatorStatusReturn(String indicatorName, double indicatorIndex, double indexNorm){
        switch (indicatorName) {
            case "rbc":
                if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 1){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "hemoglobin":
                if(Math.abs(indicatorIndex - indexNorm) < 15){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 25){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "hematocrit":
                if(Math.abs(indicatorIndex - indexNorm) < 8){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 16){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "mchc":
                if(Math.abs(indicatorIndex - indexNorm) < 30){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 50){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "mcv":
                if(Math.abs(indicatorIndex - indexNorm) < 15){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 30){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "mch":
                if(Math.abs(indicatorIndex - indexNorm) < 5){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 10){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "colorIndicator":
                if(Math.abs(indicatorIndex - indexNorm) < 0.1){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "reticulocytes":
                if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 2){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "platelets":
                if(Math.abs(indicatorIndex - indexNorm) < 30){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 45){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "wbc":
                if(Math.abs(indicatorIndex - indexNorm) < 2){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 4){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "neutrophils":
                if(Math.abs(indicatorIndex - indexNorm) < 10){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 25){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "eosinophils":
                if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 2){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "basophils":
                if(Math.abs(indicatorIndex - indexNorm) < 0.2){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "lymphocytes":
                if(Math.abs(indicatorIndex - indexNorm) < 5){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 10){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "monocytes":
                if(Math.abs(indicatorIndex - indexNorm) < 1.5){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 3){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "ESR":
                if(Math.abs(indicatorIndex - indexNorm) < 2){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 5){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            case "thrombocrit":
                if(Math.abs(indicatorIndex - indexNorm) < 0.1){
                    return "Некритично.";
                } else if(Math.abs(indicatorIndex - indexNorm) < 0.3){
                    return "Стоит обратить внимание.";
                } else {
                    return "Следует обратиться к врачу.";
                }

            default:
                return "?";
        }
    }

}
