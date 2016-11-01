package sample.web.thymeleaf3.dto;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

/**
 * Created by macbookpro on 29/10/16.
 */
public class TablesDS {

    List<TablesDto> tablesDtos;
    List<TablesDto> tableDominancias;
    List<TablesDto> tableInteligencias;
    List<TablesDto> tablePersonalidad;
    List<TablesDto> tableContacto;

    public List<TablesDto> getTablesDtos() {
        return tablesDtos;
    }

    public void setTablesDtos(List<TablesDto> tablesDtos) {
        this.tablesDtos = tablesDtos;
    }

    public List<TablesDto> getTableDominancias() {
        return tableDominancias;
    }

    public void setTableDominancias(List<TablesDto> tableDominancias) {
        this.tableDominancias = tableDominancias;
    }

    public List<TablesDto> getTableInteligencias() {
        return tableInteligencias;
    }

    public void setTableInteligencias(List<TablesDto> tableInteligencias) {
        this.tableInteligencias = tableInteligencias;
    }

    public List<TablesDto> getTablePersonalidad() {
        return tablePersonalidad;
    }

    public void setTablePersonalidad(List<TablesDto> tablePersonalidad) {
        this.tablePersonalidad = tablePersonalidad;
    }

    public List<TablesDto> getTableContacto() {
        return tableContacto;
    }

    public void setTableContacto(List<TablesDto> tableContacto) {
        this.tableContacto = tableContacto;
    }

    public JRDataSource getFiltrosSensoriales(){
        return new JRBeanCollectionDataSource(tablesDtos);
    }

    public JRDataSource getDominancias(){
        return new JRBeanCollectionDataSource(tableDominancias);
    }

    public JRDataSource getInteligencias(){
        return new JRBeanCollectionDataSource(tableInteligencias);
    }

    public JRDataSource getPersonalidad(){
        return new JRBeanCollectionDataSource(tablePersonalidad);
    }

    public JRDataSource getContacto(){
        return new JRBeanCollectionDataSource(tableContacto);
    }
}
