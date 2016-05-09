/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.beans;

import cr.ac.una.icai.sicocaweb.clases.Curso;
import cr.ac.una.icai.sicocaweb.clases.Empleado;
import cr.ac.una.icai.sicocaweb.clases.Matricula;
import cr.ac.una.icai.sicocaweb.datos.MatriculaAD;
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
public class MatriculaBean {
    
    private Matricula matricula;
    private MatriculaAD matriculaAD;
    private Empleado empleado;
    private Curso elCuros;
    private FacesMessage msj;
    private HtmlDataTable tablaMatricula;
    private Boolean modificando;
    private List<Matricula> listaMatriculas;
    private UIInput inNombre;

    public MatriculaBean() {
        this.matricula =  new Matricula();
        this.matriculaAD = new MatriculaAD();
        this.empleado = new Empleado();
        this.elCuros = new Curso();
        this.msj = new FacesMessage();
        tablaMatricula = new HtmlDataTable();
        this.modificando = false;
        this.listaMatriculas = new ArrayList<>();
        this.inNombre = new UIInput();
    }
    
    private void reiniciarBean() {
        this.matricula =  new Matricula();
        this.matriculaAD = new MatriculaAD();
        this.empleado = new Empleado();
        this.elCuros = new Curso();
        this.msj = new FacesMessage();
        tablaMatricula = new HtmlDataTable();
        this.modificando = false;
        this.listaMatriculas = new ArrayList<>();
        this.inNombre = new UIInput();
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public MatriculaAD getMatriculaAD() {
        return matriculaAD;
    }

    public void setMatriculaAD(MatriculaAD matriculaAD) {
        this.matriculaAD = matriculaAD;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Curso getElCuros() {
        return elCuros;
    }

    public void setElCuros(Curso elCuros) {
        this.elCuros = elCuros;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }

    public HtmlDataTable getTablaMatricula() {
        return tablaMatricula;
    }

    public void setTablaMatricula(HtmlDataTable tablaMatricula) {
        this.tablaMatricula = tablaMatricula;
    }

    public Boolean getModificando() {
        return modificando;
    }

    public void setModificando(Boolean modificando) {
        this.modificando = modificando;
    }

    public UIInput getInNombre() {
        return inNombre;
    }

    public void setInNombre(UIInput inNombre) {
        this.inNombre = inNombre;
    }

    
    
    public List<Matricula> getListaMatriculas() {
        if (this.listaMatriculas.isEmpty()) {
            this.listaMatriculas = this.matriculaAD.consultaTodos();
        }
        return listaMatriculas;
    }

    public void setListaMatriculas(List<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }

    
    public String agregar(){
        this.matricula.setCedula(empleado.getCedula());
        this.matricula.setIdCurso(elCuros.getIdCurso());
        this.matricula.setNota(0);
        Integer i = this.matriculaAD.insertar(this.matricula);
            switch(i){

                case 0: this.msj.setSummary("la matricula se agrego satisfactoriamente");
                    break;
                case 1: this.msj.setSummary("Error, no se pudo agregar a la base de datos error code:" + i);
                    break;
                case 2: this.msj.setSummary("Ocurrio un error, error code:" + i);
                    break;
                case 3: this.msj.setSummary("el codigo del empleado ya existe en la base de datos error code:" + i);
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.reiniciarBean();
            return "ManMatricula";
    }
    
    public String seleccionar(){
        
        this.matricula = (Matricula) this.tablaMatricula.getRowData();
        this.modificando =true;
        return "ManMatricula"; 
    }
    
    public String eliminar(){
       
    this.empleado = (Empleado) this.tablaMatricula.getRowData();
    Integer i = this.matriculaAD.Eliminar(this.matricula);
            switch(i){
                case 0: this.msj.setSummary("la matricula se elimino satisfactoriamente");
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
            return "ManMatricula";
            
    }
    
    public String modificarEmpleado(){ 
        
        //this.matricula.setCedula(empleado.getCedula());
        /*this.matricula.setIdCurso(elCuros.getIdCurso());
        String cedString = (String)inNombre.getSubmittedValue();
        Integer cedInt;
        
        if (cedString.equals("")) {
            cedInt=0;
        }
        else{
            cedInt = Integer.parseInt(cedString);
        }
        this.matricula.setCedula(cedInt);
        */
        
        this.matricula = (Matricula) this.tablaMatricula.getRowData();
        
        Integer i = this.matriculaAD.Modificar(this.matricula);
            switch(i){

                case 0: this.msj.setSummary("EL empleado se modifico satisfactoriamente");
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.modificando=true;
            this.reiniciarBean();
        
        return "ManMatricula"; 
    }
    
   
}
