package com.example.security.services.impl;

import com.example.security.dto.Comment_dto;
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
    public Comment_dto analyzeBloodParameters(double rbc, double hemoglobin, double hematocrit, double mchc, double mcv, double mch, String gender, int age) {

        StandarDataComparison rbcStan = comparisonService.findComparisonData("rbc", gender, age);
        StandarDataComparison hemoglobinStan = comparisonService.findComparisonData("hemoglobin", gender, age);
        StandarDataComparison hematocritStan = comparisonService.findComparisonData("hematocrit", gender, age);
        StandarDataComparison mchcStan = comparisonService.findComparisonData("mchc", gender, age);
        StandarDataComparison mcvStan = comparisonService.findComparisonData("mcv", gender, age);
        StandarDataComparison mchStan = comparisonService.findComparisonData("mch", gender, age);

        if(rbc > rbcStan.getMax() && hemoglobin > hemoglobinStan.getMax() && hematocrit > hematocritStan.getMax() && mchc > mchcStan.getMax() && mcv > mcvStan.getMax() && mcv > mcvStan.getMax()){
            return new Comment_dto("Показатели RBC, гемоглобин, гематокрит, MCHC, MCV, MCH повышены(выше нормы). Возможно проблемы с почками, костным мозгом и сердечной недостаточностью." +
                    "Проблемы с почками могут влиять на состав крови из-за их роли в регуляции процессов образования крови и удаления токсинов. Например, заболевания почек, такие как хроническая почечная недостаточность, могут привести к анемии из-за недостатка эритропоэтина (гормона, который стимулирует образование эритроцитов).\n" +
                    "\n" +
                    "Проблемы с костным мозгом, такие как миелопролиферативные заболевания (например, полицитемия веры), могут вызывать избыточное производство эритроцитов и, следовательно, повышение всех вышеперечисленных параметров.\n" +
                    "\n" +
                    "Сердечная недостаточность также может вызвать изменения в составе крови. Например, из-за снижения эффективности кровообращения организм может начать продуцировать больше эритроцитов, чтобы компенсировать недостаток кислорода, что приводит к полицитемии", 1);

        } else {
            return new Comment_dto("", 0);
        }
    }

    @Override
    public Comment_dto analyzeBloodParameters(double rbc, String gender, int age) {
        StandarDataComparison rbcStan = comparisonService.findComparisonData("rbc", gender, age);
        if(rbc > rbcStan.getMax()){
            return new Comment_dto("Показатель RBC(Эритроциты) повышен (выше нормы). Возможно обезвоживание. Также высокое количество эритроцитов может наблюдаться у людей, живущих на большой высоте (не хватает кислорода)", 0.5);
        } else {
            return new Comment_dto("Показатель RBC(Эритроциты) понижен (меньше нормы). Возможно гипергидратация. Гипергидратация означает избыток воды в организме. Также Низкое количество эритроцитов может быть симптомом анемии из-за потери крови, гемолиза или дефицита витамина В9 (фолиевая кислота) и В12 (цианокобаламин).", 0.5);
        }

    }

    @Override
    public Comment_dto commentIndicator(String indicatorName, double indicatorIndex, String gender, int age) {
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
        Comment_dto estimation = IndicatorStatusReturn(indicatorName, indicatorIndex, index_norm);
        return new Comment_dto(comment_text + " " + estimation.getComment_part(), estimation.getNegative_rating());
    }

    private Comment_dto IndicatorStatusReturn(String indicatorName, double indicatorIndex, double indexNorm){
        Comment_dto comment = new Comment_dto();
        switch (indicatorName) {
            case "rbc":
                if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 1){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "hemoglobin":
                if(Math.abs(indicatorIndex - indexNorm) < 15){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 25){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "hematocrit":
                if(Math.abs(indicatorIndex - indexNorm) < 8){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 16){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "mchc":
                if(Math.abs(indicatorIndex - indexNorm) < 30){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 50){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "mcv":
                if(Math.abs(indicatorIndex - indexNorm) < 15){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 30){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "mch":
                if(Math.abs(indicatorIndex - indexNorm) < 5){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 10){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "colorIndicator":
                if(Math.abs(indicatorIndex - indexNorm) < 0.1){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "reticulocytes":
                if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 2){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "platelets":
                if(Math.abs(indicatorIndex - indexNorm) < 30){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 45){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "wbc":
                if(Math.abs(indicatorIndex - indexNorm) < 2){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 4){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "neutrophils":
                if(Math.abs(indicatorIndex - indexNorm) < 10){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 25){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "eosinophils":
                if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 2){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "basophils":
                if(Math.abs(indicatorIndex - indexNorm) < 0.2){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 0.5){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "lymphocytes":
                if(Math.abs(indicatorIndex - indexNorm) < 5){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 10){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "monocytes":
                if(Math.abs(indicatorIndex - indexNorm) < 1.5){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 3){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "ESR":
                if(Math.abs(indicatorIndex - indexNorm) < 2){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 5){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            case "thrombocrit":
                if(Math.abs(indicatorIndex - indexNorm) < 0.1){
                    comment.setComment_part("Некритично.");
                    comment.setNegative_rating(0.05);
                    return comment;
                } else if(Math.abs(indicatorIndex - indexNorm) < 0.3){
                    comment.setComment_part("Стоит обратить внимание.");
                    comment.setNegative_rating(0.1);
                    return comment;
                } else {
                    comment.setComment_part("Следует обратиться к врачу.");
                    comment.setNegative_rating(0.15);
                    return comment;
                }

            default:
                comment.setComment_part("?");
                comment.setNegative_rating(0);
                return comment;
        }
    }

}
