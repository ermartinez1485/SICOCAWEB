/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.beans;

import cr.ac.una.icai.sicocaweb.clases.Instructor;
import cr.ac.una.icai.sicocaweb.datos.InstructorAD;
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
public class InstructorBean {

    private Instructor instructor;
    private InstructorAD instructorAD;
    private FacesMessage msj;
    private HtmlDataTable tablaInstructores;
    private Boolean modificando;
    private List<Instructor> listaInstructores;
    private UIInput inNombre;

    /**
     * Creates a new instance of InstructorBean
     */
    public InstructorBean() {
        this.instructor = new Instructor();
        this.instructorAD = new InstructorAD();
        this.msj = new FacesMessage();
        this.tablaInstructores = new HtmlDataTable();
        this.modificando = false;
        this.listaInstructores = new ArrayList<>();
        this.inNombre = new UIInput();

    }

    private void reiniciarBean() {
        this.instructor = new Instructor();
        this.instructorAD = new InstructorAD();
        this.msj = new FacesMessage();
        this.tablaInstructores = new HtmlDataTable();
        this.modificando = false;
        this.listaInstructores = new ArrayList<>();
        this.inNombre = new UIInput();
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public InstructorAD getInstructorAD() {
        return instructorAD;
    }

    public void setInstructorAD(InstructorAD instructorAD) {
        this.instructorAD = instructorAD;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }

    public HtmlDataTable getTablaInstructores() {
        return tablaInstructores;
    }

    public void setTablaInstructores(HtmlDataTable tablaInstructores) {
        this.tablaInstructores = tablaInstructores;
    }

    public Boolean getModificando() {
        return modificando;
    }

    public void setModificando(Boolean modificando) {
        this.modificando = modificando;
    }

    public List<Instructor> getListaInstructores() {
        if (this.listaInstructores.isEmpty()) {
            this.listaInstructores = this.instructorAD.consultaTodos();
        }
        return listaInstructores;
    }

    public void setListaInstructores(List<Instructor> listaInstructores) {
        this.listaInstructores = listaInstructores;
    }

    public UIInput getInNombre() {
        return inNombre;
    }

    public void setInNombre(UIInput inNombre) {
        this.inNombre = inNombre;
    }
    
    public String agregar(){
        
            
        Integer i = this.instructorAD.insertar(this.instructor);
            switch(i){

                case 0: this.msj.setSummary("El Instructor se agrego satisfactoriamente");
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
            return "ManInstructores";
    }
    
    public String seleccionar(){
        
        this.instructor = (Instructor) this.tablaInstructores.getRowData();
        this.modificando =true;
        return "ManInstructores";  
    }
    
    public String eliminar(){
       
    this.instructor = (Instructor) this.tablaInstructores.getRowData();
    Integer i = this.instructorAD.Eliminar(this.instructor);
            switch(i){
                case 0: this.msj.setSummary("El Instructor se elimino satisfactoriamente");
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
            return "ManInstructores";
            
    }
    
    public String modificarInstructor(){
        
        Integer i = this.instructorAD.Modificar(this.instructor);
            switch(i){

                case 0: this.msj.setSummary("El Instructor se modifico satisfactoriamente");
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.modificando=true;
            this.reiniciarBean();
        
        return "ManInstructores"; 
    }
    
    public String buscar(){
        
        this.listaInstructores = this.instructorAD.consultaXNombre((String)inNombre.getSubmittedValue());
        if (listaInstructores.isEmpty()) {
            this.msj.setSummary("no se encontro el instructor");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
    
    public List<SelectItem> llenaInstructores(){
        
        List<Instructor> instructor = this.instructorAD.consultaTodos();
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null, "--Selecione--"));
        for (int i = 0; i < instructor.size(); i++) {
            Instructor inst = instructor.get(i);
            lista.add(new SelectItem(inst.getIdInstructor(),inst.getNombre()));
        }
        return lista;
    }
}