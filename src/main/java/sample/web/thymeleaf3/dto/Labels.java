package sample.web.thymeleaf3.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by macbookpro on 31/10/16.
 */

public class Labels {

    private Survey survey = new Survey();

    public Survey getSurvey() {
        return survey;
    }
}
