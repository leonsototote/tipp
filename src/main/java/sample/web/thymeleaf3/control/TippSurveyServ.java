package sample.web.thymeleaf3.control;

import sample.web.thymeleaf3.dto.Survey;

/**
 * Created by macbookpro on 23/10/16.
 */
public interface TippSurveyServ {

    public byte[] getReportTipp(Survey surveyAnswers, Survey surveyQuestions);

}
