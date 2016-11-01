package sample.web.thymeleaf3.dto;

import java.util.Map;

/**
 * Created by macbookpro on 23/10/16.
 */
public class DataReportDto<T>  {

    //path relativo de la ubicacion del template
    private String pathTemplate;
    //path relativo de la ubicacion de las imagenes
    private String pathImageTemplate;
    //nombre de la plantilla con la que se generara el reporte con terminacion .jasper
    private String templateName;
    //parametros de la plantilla de jasper Map<(nombre del parametro en jasper),(contenido del parametro)>
    private Map<String,String> params;
    //imagenes de la plantilla de jasper Map<(Nombre de la imagen en jasper),(nombre de la imagen con extencion)>
    private Map<String,String> images;
    //Lista con bean de los field en jasper
    private T fields;
    //Archivo generado
    private byte[] file;

    public String getPathTemplate() {
        return pathTemplate;
    }
    public void setPathTemplate(String pathTemplate) {
        this.pathTemplate = pathTemplate;
    }
    public String getPathImageTemplate() {
        return pathImageTemplate;
    }
    public void setPathImageTemplate(String pathImageTemplate) {
        this.pathImageTemplate = pathImageTemplate;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public Map<String, String> getParams() {
        return params;
    }
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    public Map<String, String> getImages() {
        return images;
    }
    public void setImages(Map<String, String> images) {
        this.images = images;
    }
    public T getFields() {
        return fields;
    }
    public void setFields(T fields) {
        this.fields = fields;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }

}
