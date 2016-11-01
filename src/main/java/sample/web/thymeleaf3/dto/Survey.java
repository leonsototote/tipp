package sample.web.thymeleaf3.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by macbookpro on 16/10/16.
 */
@Component
@ConfigurationProperties("survey")
public class Survey {

    private String name;
    private Integer age;
    private String date;
    private String phone;
    private String cellphone;
    private String status;
    private String couple;
    private List<String> siblings;
    private List<String> sons;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private List<Section> sectionSensitives;
    private List<Section> sectionCognitive;
    private List<Section> sectionIntelligence;
    private List<Section> sectionContac;
    private List<Section> sectionsProfile;
    private List<Section> sectionsStrengths;
    private List<Section> sectionsTraits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCouple() {
        return couple;
    }

    public void setCouple(String couple) {
        this.couple = couple;
    }

    public List<String> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<String> siblings) {
        this.siblings = siblings;
    }

    public List<String> getSons() {
        return sons;
    }

    public void setSons(List<String> sons) {
        this.sons = sons;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Section> getSectionSensitives() {
        return sectionSensitives;
    }

    public void setSectionSensitives(List<Section> sectionSensitives) {
        this.sectionSensitives = sectionSensitives;
    }

    public List<Section> getSectionCognitive() {
        return sectionCognitive;
    }

    public void setSectionCognitive(List<Section> sectionCognitive) {
        this.sectionCognitive = sectionCognitive;
    }

    public List<Section> getSectionIntelligence() {
        return sectionIntelligence;
    }

    public void setSectionIntelligence(List<Section> sectionIntelligence) {
        this.sectionIntelligence = sectionIntelligence;
    }

    public List<Section> getSectionContac() {
        return sectionContac;
    }

    public void setSectionContac(List<Section> sectionContac) {
        this.sectionContac = sectionContac;
    }

    public List<Section> getSectionsProfile() {
        return sectionsProfile;
    }

    public void setSectionsProfile(List<Section> sectionsProfile) {
        this.sectionsProfile = sectionsProfile;
    }

    public List<Section> getSectionsStrengths() {
        return sectionsStrengths;
    }

    public void setSectionsStrengths(List<Section> sectionsStrengths) {
        this.sectionsStrengths = sectionsStrengths;
    }

    public List<Section> getSectionsTraits() {
        return sectionsTraits;
    }

    public void setSectionsTraits(List<Section> sectionsTraits) {
        this.sectionsTraits = sectionsTraits;
    }
}
