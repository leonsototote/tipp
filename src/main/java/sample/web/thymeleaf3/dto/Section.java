package sample.web.thymeleaf3.dto;

import java.util.List;

/**
 * Created by macbookpro on 16/10/16.
 */
public class Section {

    private String title;
    private String details1;
    private String details2;
    private Boolean comment;
    private List<Question> questions;
    private Integer porcentage;
    private boolean justWord;

    private String shorName;
    private String specificName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails1() {
        return details1;
    }

    public void setDetails1(String details1) {
        this.details1 = details1;
    }

    public String getDetails2() {
        return details2;
    }

    public void setDetails2(String details2) {
        this.details2 = details2;
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

    public String getShorName() {
        return shorName;
    }

    public void setShorName(String shorName) {
        this.shorName = shorName;
    }

    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    public Integer getPorcentage() {
        return porcentage;
    }

    public void setPorcentage(Integer porcentage) {
        this.porcentage = porcentage;
    }

    public boolean getJustWord() {
        return justWord;
    }

    public void setJustWord(boolean justWord) {
        this.justWord = justWord;
    }
}
