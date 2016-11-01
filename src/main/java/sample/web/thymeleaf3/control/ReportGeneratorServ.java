package sample.web.thymeleaf3.control;

import sample.web.thymeleaf3.dto.DataReportDto;
import sample.web.thymeleaf3.dto.Survey;

/**
 * Created by macbookpro on 23/10/16.
 */
public interface ReportGeneratorServ {

    public byte[] generateReportTIPP(Survey survey);

}
