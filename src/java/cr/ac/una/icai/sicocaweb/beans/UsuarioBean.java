/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.beans;

import cr.ac.una.icai.sicocaweb.clases.Usuario;
import cr.ac.una.icai.sicocaweb.datos.UsuarioAD;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author eric.martinez
 */
public class UsuarioBean {

    private Usuario elUser;
    private FacesMessage msj;
    private UsuarioAD usuarioAD;

    public Usuario getElUser() {
        return elUser;
    }

    public void setElUser(Usuario elUser) {
        this.elUser = elUser;
    }

    public FacesMessage getMsj() {
        return msj;
    }

    public void setMsj(FacesMessage msj) {
        this.msj = msj;
    }

    public UsuarioBean() {
        this.elUser = new Usuario();
        this.msj = new FacesMessage();
        this.usuarioAD = new UsuarioAD();
    }
    
    public String aceptar(){
        
        Integer status = 0;
        status = usuarioAD.consultaUser(this.elUser.getNombreUsuario(),this.elUser.getPassword());

            if(status==4) {
                return "main";
            }
            else{
                this.msj.setSummary("Datos incorrectos");
                FacesContext.getCurrentInstance().addMessage(null,getMsj());
                return "login";
            }
    }
}