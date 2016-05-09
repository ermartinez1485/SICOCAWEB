/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.beans;

import cr.ac.una.icai.sicocaweb.clases.Curso;
import cr.ac.una.icai.sicocaweb.datos.CursoAD;
import java.util.ArrayList;
import java.util.Date;
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
public class CursoBean {
    
    private Curso curso;
    private CursoAD cursoAD;
    private FacesMessage msj;
    private HtmlDataTable tablaCursos;
    private Boolean modificando;
    private List<Curso> listaCursos;
    private UIInput inNombre;
    private Date fInicio;
    private Date fFinal;
    
    
    /**
     * Creates a new instance of CursoBean
     */
    public CursoBean() {
        
        this.curso = new Curso();
        this.cursoAD = new CursoAD();
        this.msj = new FacesMessage();
        this.tablaCursos = new HtmlDataTable();
        this.modificando = false;
        this.listaCursos = new ArrayList<>();
        this.inNombre = new UIInput();
        this.fInicio = new Date();
        this.fFinal = new Date();
        
    }

    private void reiniciarBean() {
        this.curso = new Curso();
        this.cursoAD = new CursoAD();
        this.msj = new FacesMessage();
        this.tablaCursos = new HtmlDataTable();
        this.modificando = false;
        this.listaCursos = new ArrayList<>();
        this.inNombre = new UIInput();
        this.fInicio = new Date();
        this.fFinal = new Date();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public CursoAD getCursoAD() {
        return cursoAD;
    }

    public void setCursoAD(CursoAD cursoAD) {
        this.cursoAD = cursoAD;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }

    public HtmlDataTable getTablaCursos() {
        return tablaCursos;
    }

    public void setTablaCursos(HtmlDataTable tablaCursos) {
        this.tablaCursos = tablaCursos;
    }

    public Boolean getModificando() {
        return modificando;
    }

    public void setModificando(Boolean modificando) {
        this.modificando = modificando;
    }

    public List<Curso> getListaCursos() {
        if (this.listaCursos.isEmpty()) {
            this.listaCursos = this.cursoAD.consultaTodos();
        }
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public UIInput getInNombre() {
        return inNombre;
    }

    public void setInNombre(UIInput inNombre) {
        this.inNombre = inNombre;
    }

    public Date getfInicio() {
        return fInicio;
    }

    public void setfInicio(Date fInicio) {
        this.fInicio = fInicio;
    }

    public Date getfFinal() {
        return fFinal;
    }

    public void setfFinal(Date fFinal) {
        this.fFinal = fFinal;
    }
 

    
    public String agregar(){
        
        Integer i = this.cursoAD.insertar(this.curso);
            switch(i){

                case 0: this.msj.setSummary("El Curso se agrego satisfactoriamente");
                    break;
                case 1: this.msj.setSummary("Error, no se pudo agregar a la base de datos error code:" + i);
                    break;
                case 2: this.msj.setSummary("Ocurrio un error, error code:" + i);
                    break;
                case 3: this.msj.setSummary("el codigo del curso ya existe en la base de datos error code:" + i);
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.reiniciarBean();
            return "ManCursos";
    }
    
    public String seleccionar(){
        
        this.curso = (Curso) this.tablaCursos.getRowData();
        this.modificando =true;
        return "ManCursos";  
    }
    
    
    
    public String eliminar(){
       
    this.curso = (Curso) this.tablaCursos.getRowData();
    Integer i = this.cursoAD.Eliminar(this.curso);
            switch(i){
                case 0: this.msj.setSummary("El Curso se elimino satisfactoriamente");
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
            return "ManCursos";
            
    }
    
    public List<SelectItem> llenaCursos(){
        
        List<Curso> clientes = this.cursoAD.consultaTodos();
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null, "--Selecione--"));
        for (int i = 0; i < clientes.size(); i++) {
            Curso cli = clientes.get(i);
            lista.add(new SelectItem(cli.getIdCurso(),cli.getNombre()));
        }
        
        return lista;
    }
    
    public String modificarCurso(){
        
        Integer i = this.cursoAD.Modificar(this.curso);
            switch(i){

                case 0: this.msj.setSummary("EL curso se modifico satisfactoriamente");
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.modificando=true;
            this.reiniciarBean();
        
        return "ManCursos"; 
    }
    
    public String buscar(){
        
        this.listaCursos = this.cursoAD.consultaXNombre((String)inNombre.getSubmittedValue());
        if (listaCursos.isEmpty()) {
            this.msj.setSummary("no se encontro el curso");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
}
