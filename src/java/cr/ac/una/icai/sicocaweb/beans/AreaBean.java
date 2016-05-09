/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.beans;

import cr.ac.una.icai.sicocaweb.clases.Area;
import cr.ac.una.icai.sicocaweb.datos.AreaAD;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author eric.martinez
 */
public class AreaBean {

    /**
     * Creates a new instance of AreaBean
     */
    private Area laArea;
    private AreaAD laAreaAD;
    private FacesMessage msj;
    private HtmlDataTable tablaAreas;
    private Boolean modificando;
    private List<Area> listaAreas;
    private UIInput inNombre;
        
    public AreaBean() {
        
        this.laArea = new Area();
        this.laAreaAD = new AreaAD();
        this.msj = new FacesMessage();
        this.tablaAreas = new HtmlDataTable();
        this.listaAreas = new ArrayList<>();
        this.modificando = false;
        
    }
    
    private void reiniciarBean(){

        this.laArea = new Area();
        this.laAreaAD = new AreaAD();
        this.msj = new FacesMessage();
        this.tablaAreas = new HtmlDataTable();
        this.listaAreas = new ArrayList<>();
        this.modificando = false;
        
    }

    public Area getLaArea() {
        return laArea;
    }

    public void setLaArea(Area laArea) {
        this.laArea = laArea;
    }

    public AreaAD getLaAreaAD() {
        return laAreaAD;
    }

    public void setLaAreaAD(AreaAD laAreaAD) {
        this.laAreaAD = laAreaAD;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }

    public HtmlDataTable getTablaAreas() {
        return tablaAreas;
    }

    public void setTablaAreas(HtmlDataTable tablaAreas) {
        this.tablaAreas = tablaAreas;
    }

    public Boolean getModificando() {
        return modificando;
    }

    public void setModificando(Boolean modificando) {
        this.modificando = modificando;
    }

    public List<Area> getListaAreas() {
        if (this.listaAreas.isEmpty()) {
            this.listaAreas = this.laAreaAD.consultaTodos();
        }
        return listaAreas;
    }

    public void setListaAreas(List<Area> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public UIInput getInNombre() {
        return inNombre;
    }

    public void setInNombre(UIInput inNombre) {
        this.inNombre = inNombre;
    }
    
    
    
    
    public String agregar(){
        
            
        Integer i = this.laAreaAD.insertar(this.laArea);
            switch(i){

                case 0: this.msj.setSummary("La Area se agrego satisfactoriamente");
                    break;
                case 1: this.msj.setSummary("Error, no se pudo agregar a la base de datos error code:" + i);
                    break;
                case 2: this.msj.setSummary("Ocurrio un error, error code:" + i);
                    break;
                case 3: this.msj.setSummary("el codigo de la pelicula ya existe en la base de datos error code:" + i);
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.reiniciarBean();
            return "ManAreas";
    }
    
    public String seleccionar(){
        
        this.laArea = (Area) this.tablaAreas.getRowData();
        this.modificando =true;
        return "ManAreas";  
    }
    
    public String eliminar(){
       
    this.laArea = (Area) this.tablaAreas.getRowData();
    Integer i = this.laAreaAD.Eliminar(this.laArea);
            switch(i){
                case 0: this.msj.setSummary("La Area se elimino satisfactoriamente");
                        FacesContext.getCurrentInstance().addMessage(null,getMsj());
                    break;
                case 1: this.msj.setSummary("Error, no se pudo eliminar en la base de datos error code:" + i);
                        FacesContext.getCurrentInstance().addMessage(null,getMsj()); 
                    break;
                case 2: this.msj.setSummary("Ocurrio un error, error code:" + i);
                        FacesContext.getCurrentInstance().addMessage(null,getMsj()); 
                    break;
                case 3: this.msj.setSummary("SQLException error code:" + i);
                        FacesContext.getCurrentInstance().addMessage(null,getMsj());
                    break;
            }
            this.reiniciarBean();
            return "ManAreas";
            
    }
    
    public List<SelectItem> llenaAreas(){
        
        List<Area> areas = this.laAreaAD.consultaTodos();
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null, "--Selecione--"));
        for (int i = 0; i < areas.size(); i++) {
            Area are = areas.get(i);
            lista.add(new SelectItem(are.getIdArea(),are.getNombre()));
        }
        
        return lista;
    }
    
    public String modificarArea(){
        
        Integer i = this.laAreaAD.Modificar(this.laArea);
            switch(i){

                case 0: this.msj.setSummary("La Pelicula se modifico satisfactoriamente");
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.modificando=true;
            this.reiniciarBean();
        
        return "ManAreas"; 
    }
    
    public String buscar(){
        
        this.listaAreas = this.laAreaAD.consultaXNombre((String)inNombre.getSubmittedValue());
        if (listaAreas.isEmpty()) {
            this.msj.setSummary("no se encontro la area");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
}
