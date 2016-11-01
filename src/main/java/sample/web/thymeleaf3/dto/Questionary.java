package sample.web.thymeleaf3.dto;

import java.util.List;

/**
 * Created by macbookpro on 16/10/16.
 */
public class Questionary {

    private String title;
    private Boolean comment;
    private List<Question> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Boolean getComment() {
        return comment;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }
}
