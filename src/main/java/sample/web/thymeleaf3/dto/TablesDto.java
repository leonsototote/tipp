package sample.web.thymeleaf3.dto;

/**
 * Created by macbookpro on 29/10/16.
 */
public class TablesDto {

    private String dominancia;
    private String especifica;
    private Integer porcentaje;
    private String descripcion;

    public String getDominancia() {
        return dominancia;
    }

    public void setDominancia(String dominancia) {
        this.dominancia = dominancia;
    }

    public String getEspecifica() {
        return especifica;
    }

    public void setEspecifica(String especifica) {
        this.especifica = especifica;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
