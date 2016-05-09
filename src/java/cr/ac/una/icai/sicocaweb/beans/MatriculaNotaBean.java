/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.beans;

/**
 *
 * @author eric.martinez
 */
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

/**
 *
 * @author eric.martinez
 */
public class MatriculaNotaBean {
    
    private Matricula matricula;
    private MatriculaAD matriculaAD;
    private Empleado empleado;
    private Curso elCuros;
    private FacesMessage msj;
    private HtmlDataTable tablaMatricula;
    private Boolean modificando;
    private List<Matricula> listaMatriculas;
    private UIInput inNombre;
    private UIInput inCurso;

    public MatriculaNotaBean() {
        this.matricula =  new Matricula();
        this.matriculaAD = new MatriculaAD();
        this.empleado = new Empleado();
        this.elCuros = new Curso();
        this.msj = new FacesMessage();
        tablaMatricula = new HtmlDataTable();
        this.modificando = false;
        this.listaMatriculas = new ArrayList<>();
        this.inNombre = new UIInput();
        this.inCurso = new UIInput();
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
        this.inCurso = new UIInput();
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

    public UIInput getInCurso() {
        return inCurso;
    }

    public void setInCurso(UIInput inCurso) {
        this.inCurso = inCurso;
    }
    
    

    public String seleccionar(){
        
        this.matricula = (Matricula) this.tablaMatricula.getRowData();
        this.modificando =true;
        return "ManMatriculaNota"; 
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
            return "ManMatriculaNota";
            
    }
    
    public String modificarEmpleado(){ 

        //this.matricula = (Matricula) this.tablaMatricula.getRowData();
        this.matricula.setIdCurso(elCuros.getIdCurso());
        Integer i = this.matriculaAD.Modificar(this.matricula);
            switch(i){

                case 0: this.msj.setSummary("EL empleado se modifico satisfactoriamente");
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.modificando=true;
            this.reiniciarBean();
        
        return "ManMatriculaNota"; 
    }
    
    public String buscar(){
        //this.matricula.setIdCurso(elCuros.getIdCurso());
        Integer cedEmple = this.matricula.getCedula();
        Integer codCurso = this.elCuros.getIdCurso();

        this.listaMatriculas = this.matriculaAD.consultaXCedula(cedEmple, codCurso);
        
        if (this.listaMatriculas.isEmpty()) {
             this.msj.setSummary("no se encontro el empleado");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
    public String verTodos(){
        

        this.listaMatriculas = this.matriculaAD.consultaTodos();
        
        if (this.listaMatriculas.isEmpty()) {
             this.msj.setSummary("no se encontro el empleado");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
}

