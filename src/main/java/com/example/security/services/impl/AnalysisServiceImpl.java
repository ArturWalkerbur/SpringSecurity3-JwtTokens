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
            return "Показатель RBC(Эритроциты) повышен (выше нормы). Возможно обезвоживание. Когда организм обезвоживается, уровень жидкости в крови снижается. Это может происходить из-за различных причин, таких как недостаток потребления воды, потеря жидкости через повышенное потоотделение, диарею, рвоту или другие причины. Обезвоживание может привести к повышенной концентрации крови и увеличению вязкости крови. Организм реагирует на это, увеличивая производство эритроцитов, чтобы обеспечить более эффективный транспорт кислорода к тканям. Поэтому повышенное количество эритроцитов (RBC) может быть реакцией на обезвоживание. ";
        } else {
            return "Показатель RBC(Эритроциты) понижен (меньше нормы). Возможно гипергидратация. Гипергидратация означает избыток воды в организме. Это может произойти при чрезмерном потреблении жидкости или при проблемах с функцией почек, которые регулируют уровень воды и электролитов в организме. При гипергидратации кровь разбавляется, что может привести к уменьшению концентрации красных кровяных клеток. В ответ на это организм может снизить производство эритроцитов. Поэтому пониженное количество эритроцитов (RBC) может быть следствием гипергидратации.";
        }

    }

    @Override
    public String commentIndicator(String indicatorName, double indicatorIndex, String gender, int age) {
        StandarDataComparison data = comparisonService.findComparisonData(indicatorName, gender, age);
        switch (indicatorName) {
            case "rbc":
                if(indicatorIndex < data.getMin()){
                    return "Эритроциты(RBC) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Эритроциты(RBC) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "hemoglobin":
                if(indicatorIndex < data.getMin()){
                    return "Гемоглобин(HGB) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Гемоглобин(HGB) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "hematocrit":
                if(indicatorIndex < data.getMin()){
                    return "Гематокрит(HCT) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Гематокрит(HCT) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "mchc":
                if(indicatorIndex < data.getMin()){
                    return "Средняя концентрация гемоглобина в эритроците(MCHC) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Средняя концентрация гемоглобина в эритроците(MCHC) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "mcv":
                if(indicatorIndex < data.getMin()){
                    return "Средний объем эритроцитов(MCV) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Средний объем эритроцитов(MCV) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "mch":
                if(indicatorIndex < data.getMin()){
                    return "Среднее содержание гемоглобина в эритроците(MCH)меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Среднее содержание гемоглобина в эритроците(MCH)больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "colorIndicator":
                if(indicatorIndex < data.getMin()){
                    return "Цветной показатель меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Цветной показатель больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "reticulocytes":
                if(indicatorIndex < data.getMin()){
                    return "Ретикулоциты меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Ретикулоциты больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "platelets":
                if(indicatorIndex < data.getMin()){
                    return "Тромбоциты(PLT) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Тромбоциты(PLT) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "wbc":
                if(indicatorIndex < data.getMin()){
                    return "Лейкоциты(WBC) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Лейкоциты(WBC) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "neutrophils":
                if(indicatorIndex < data.getMin()){
                    return "Нейтрофилы(RBC) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Нейтрофилы(RBC) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "eosinophils":
                if(indicatorIndex < data.getMin()){
                    return "Эозинофилы(EOS%) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Эозинофилы(EOS%) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }

            case "basophils":
                if(indicatorIndex < data.getMin()){
                    return "Базофилы меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Базофилы больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "lymphocytes":
                if(indicatorIndex < data.getMin()){
                    return "Лимфоциты(LYM%) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Лимфоциты(LYM%) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "monocytes":
                if(indicatorIndex < data.getMin()){
                    return "Моноциты(MON%) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Моноциты(MON%) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "ESR":
                if(indicatorIndex < data.getMin()){
                    return "Скорость оседания эритроцитов(ESR) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Скорость оседания эритроцитов(ESR) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            case "thrombocrit":
                if(indicatorIndex < data.getMin()){
                    return "Тромбокрит(PCT) меньше минимальной ("+data.getMin()+") рекомендованной нормы!";
                } else {
                    return "Тромбокрит(PCT) больше максимальной ("+data.getMax()+") рекомендованной нормы!";
                }
            default:
                return "";
        }
    }
}
