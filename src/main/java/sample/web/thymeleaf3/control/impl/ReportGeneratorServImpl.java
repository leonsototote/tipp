package sample.web.thymeleaf3.control.impl;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Component;
import sample.web.thymeleaf3.control.ReportGeneratorServ;
import sample.web.thymeleaf3.dto.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by macbookpro on 23/10/16.
 */
@Component
public class ReportGeneratorServImpl implements ReportGeneratorServ {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public byte[] generateReport(DataReportDto dataReportDto) {

        Map<String, Object> datosReporte = dataReportDto.getParams();

        File templateFile = new File(getClass().getClassLoader().getResource(dataReportDto.getTemplateName()).getFile());

        byte[] file = null;
        try {
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(templateFile);

            if(dataReportDto.getFields()!=null){
                List ds=new ArrayList();
                ds.add(dataReportDto.getFields());
                file = JasperRunManager.runReportToPdf(masterReport, datosReporte, new JRBeanCollectionDataSource(ds));
            }else{
                file = JasperRunManager.runReportToPdf(masterReport, datosReporte, new JREmptyDataSource());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return file;
    }

    public byte[] generateReportTIPP(Survey survey){

        DataReportDto datosReporteDto = new DataReportDto();

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("nombre", survey.getName());
        params.put("edad", survey.getAge());
        params.put("cumpleanos", survey.getDate());
        params.put("respuesta1", survey.getAnswer1()+"\n");
        params.put("respuesta2", survey.getAnswer2()+"\n");
        params.put("respuesta3", survey.getAnswer3()+"\n");
        params.put("respuesta4", survey.getAnswer4()+"\n");
        SimpleDateFormat date = new SimpleDateFormat("dd - MMMM - yyyy", new Locale("es","ar"));
        params.put("hoy", date.format(new Date()).toUpperCase());
        params.put("logo","http://localhost:8080/images/LOGO.png");
        params.put("tipp","http://localhost:8080/images/TIPP.png");
        params.put("blue-line","http://localhost:8080/images/lineBlue.png");
        params.put("footer","http://localhost:8080/images/footer.png");

        List<TablesDto> tablesDtos = getTableData(survey.getSectionSensitives());
        List<TablesDto> tableDominancias = getTableData(survey.getSectionCognitive());
        List<TablesDto> tableInteligencias = getTableData(survey.getSectionIntelligence());
        List<TablesDto> tablesPersonalidad = getTableData(survey.getSectionsProfile());
        List<TablesDto> tablesContacto = getTableData(survey.getSectionContac());

        TablesDS tablesDS = new TablesDS();
        tablesDS.setTablesDtos(tablesDtos);
        tablesDS.setTableDominancias(tableDominancias);
        tablesDS.setTableInteligencias(tableInteligencias);
        tablesDS.setTablePersonalidad(tablesPersonalidad);
        tablesDS.setTableContacto(tablesContacto);

        datosReporteDto.setParams(params);
        datosReporteDto.setFields(tablesDS);

        datosReporteDto.setTemplateName("static/jasper/TIPP-master.jasper");

        return generateReport(datosReporteDto);

    }

    private List<TablesDto> getTableData(List<Section> sections){
        List<TablesDto> tablesDtos = new ArrayList<TablesDto>();
        TablesDto tablesDto;
        StringBuilder description;
        for (Section section : sections){
            tablesDto = new TablesDto();
            tablesDto.setDominancia(section.getShorName());
            tablesDto.setEspecifica(section.getSpecificName());
            tablesDto.setPorcentaje(section.getPorcentage());
            description = new StringBuilder();
            Integer index = 1;
            for(Question question : section.getQuestions()){
                if (question.getAnswerBoolean()){
                    if (question.getFlagWordQuestion() && section.getJustWord()){
                        description.append("-");
                        description.append(question.getWordQuestion());
                        if(index%5 == 0){
                            description.append("\n");
                        }else{
                            description.append("     ");
                        }
                        index++;
                    }else if(!section.getJustWord()){
                        description.append(question.getQuestion());
                        description.append("\n");
                    }

                }
            }
            tablesDto.setDescripcion(description.toString());
            tablesDtos.add(tablesDto);
        }

        return tablesDtos;
    }

}
