package sample.web.thymeleaf3.control.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.web.thymeleaf3.control.ReportGeneratorServ;
import sample.web.thymeleaf3.control.TippSurveyServ;
import sample.web.thymeleaf3.dto.Question;
import sample.web.thymeleaf3.dto.Section;
import sample.web.thymeleaf3.dto.Survey;

import java.util.List;

/**
 * Created by macbookpro on 23/10/16.
 */
@Component
public class TippSurveyServImpl implements TippSurveyServ {

    @Autowired
    ReportGeneratorServ reportGeneratorServ;

    public byte[] getReportTipp(Survey surveyAnswers, Survey surveyQuestions) {

        copyPropertiesSections(surveyAnswers.getSectionSensitives(), surveyQuestions.getSectionSensitives());
        copyPropertiesSections(surveyAnswers.getSectionContac(), surveyQuestions.getSectionContac());
        copyPropertiesSections(surveyAnswers.getSectionIntelligence(), surveyQuestions.getSectionIntelligence());
        copyPropertiesSections(surveyAnswers.getSectionCognitive(), surveyQuestions.getSectionCognitive());
        copyPropertiesSections(surveyAnswers.getSectionsProfile(), surveyQuestions.getSectionsProfile());

        BeanUtils.copyProperties(surveyAnswers,
                                 surveyQuestions,
                                 "sectionSensitives",
                                 "sectionContac",
                                 "sectionIntelligence",
                                 "sectionCognitive",
                                 "sectionsProfile");

        byte[] reportTipp = reportGeneratorServ.generateReportTIPP(surveyQuestions);

        return reportTipp;
    }

    private void copyPropertiesSections(List<Section> sectionAnswers, List<Section> sectionQuestions){
        int indexSectionAnswer = 0;
        for(Section sectionSensitiveQuestions : sectionQuestions){

            List<Question> questionariesQuestions = sectionSensitiveQuestions.getQuestions();
            Section sectionSensitiveAnswer = sectionAnswers.get(indexSectionAnswer++);
            List<Question> questionariesAnswer = sectionSensitiveAnswer.getQuestions();

            int indexQuestionaryAnswer = 0;
            for(Question questionQuestions : questionariesQuestions){
                Question questionAnswer = questionariesAnswer.get(indexQuestionaryAnswer++);
                BeanUtils.copyProperties(questionAnswer, questionQuestions,"question","wordQuestion","flagWordQuestion");
            }

            BeanUtils.copyProperties(sectionSensitiveAnswer,
                    sectionSensitiveQuestions,
                    "title",
                    "details1",
                    "details2",
                    "questions",
                    "justWord",
                    "shorName",
                    "specificName");
        }
    }

}
