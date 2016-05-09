/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.beans;

import cr.ac.una.icai.sicocaweb.clases.Area;
import cr.ac.una.icai.sicocaweb.clases.Empleado;
import cr.ac.una.icai.sicocaweb.datos.EmpleadoAD;
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
public class EmpleadoBean {
    
    private Empleado empleado;
    private EmpleadoAD empleadoAD;
    private Area laArea;
    private FacesMessage msj;
    private HtmlDataTable tablaEmpleados;
    private Boolean modificando;
    private List<Empleado> listaEmpleados;
    private UIInput inNombre;

    /**
     * Creates a new instance of EmpleadoBean
     */
    public EmpleadoBean() {
        
        this.empleado = new Empleado();
        this.empleadoAD = new EmpleadoAD();
        this.msj = new FacesMessage();
        this.tablaEmpleados = new HtmlDataTable();
        this.modificando = false;
        this.listaEmpleados = new ArrayList<>();
        this.inNombre = new UIInput();
        this.laArea = new Area();
    }
    
    private void reiniciarBean() {
        this.empleado = new Empleado();
        this.empleadoAD = new EmpleadoAD();
        this.msj = new FacesMessage();
        this.tablaEmpleados = new HtmlDataTable();
        this.modificando = false;
        this.listaEmpleados = new ArrayList<>();
        this.inNombre = new UIInput();
        this.laArea = new Area();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoAD getEmpleadoAD() {
        return empleadoAD;
    }

    public void setEmpleadoAD(EmpleadoAD empleadoAD) {
        this.empleadoAD = empleadoAD;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }

    public HtmlDataTable getTablaEmpleados() {
        return tablaEmpleados;
    }

    public void setTablaEmpleados(HtmlDataTable tablaEmpleados) {
        this.tablaEmpleados = tablaEmpleados;
    }

    public Boolean getModificando() {
        return modificando;
    }

    public void setModificando(Boolean modificando) {
        this.modificando = modificando;
    }

    public List<Empleado> getListaEmpleados() {
        if (this.listaEmpleados.isEmpty()) {
            this.listaEmpleados = this.empleadoAD.consultaTodos();
        }
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public UIInput getInNombre() {
        return inNombre;
    }

    public void setInNombre(UIInput inNombre) {
        this.inNombre = inNombre;
    }

    public Area getLaArea() {
        return laArea;
    }

    public void setLaArea(Area laArea) {
        this.laArea = laArea;
    }
    
    
    
    public String agregar(){
        this.empleado.setIdArea(laArea.getIdArea());
        Integer i = this.empleadoAD.insertar(this.empleado);
            switch(i){

                case 0: this.msj.setSummary("El Empleado se agrego satisfactoriamente");
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
            return "ManEmpleados";
    }
    
    public String seleccionar(){
        
        this.empleado = (Empleado) this.tablaEmpleados.getRowData();
        this.modificando =true;
        return "ManEmpleados";  
    }
    
    public String eliminar(){
       
    this.empleado = (Empleado) this.tablaEmpleados.getRowData();
    Integer i = this.empleadoAD.Eliminar(this.empleado);
            switch(i){
                case 0: this.msj.setSummary("El Empleado se elimino satisfactoriamente");
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
            return "ManEmpleados";
            
    }
    
    public List<SelectItem> llenaEmpleados(){
        
        List<Empleado> emp = this.empleadoAD.consultaTodos();
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null, "--Selecione--"));
        for (int i = 0; i < emp.size(); i++) {
            Empleado empl = emp.get(i);
            lista.add(new SelectItem(empl.getCedula(),empl.getNombre()));
        }
        
        return lista;
    }
    
    public String modificarEmpleado(){
        
        Integer i = this.empleadoAD.Modificar(this.empleado);
            switch(i){

                case 0: this.msj.setSummary("EL empleado se modifico satisfactoriamente");
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null,getMsj());
            this.modificando=true;
            this.reiniciarBean();
        
        return "ManEmpleados"; 
    }
    
    public String buscar(){
        
        this.listaEmpleados = this.empleadoAD.consultaXNombre((String)inNombre.getSubmittedValue());
        if (listaEmpleados.isEmpty()) {
            this.msj.setSummary("no se encontro el empleado");
            FacesContext.getCurrentInstance().addMessage(null, getMsj());
        }
        return "";
    }
    
}
