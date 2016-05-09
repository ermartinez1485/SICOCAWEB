/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author eric.martinez
 */
public class UsuarioAD {
    
    private Conexion con;
    private PreparedStatement sentencia;

    public UsuarioAD() {
        this.con = new Conexion();
        this.sentencia = null;
    }
    
    public Integer consultaUser(String user, String pass){
            
            try {
            if(this.con.conectarse()){
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.usuario where nombreUsuario =? and password=?");
                this.sentencia.setString(1, user);
                this.sentencia.setString(2, pass);
                
                ResultSet resultado = this.sentencia.executeQuery();
                
                if (resultado.next()) {
                    this.con.desconectarse();
                    return 4; //el user y password is correcto
                }
                
                this.con.desconectarse();
                return 0;//return us; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return 1;//return null; //retorna 1 cuando no se conecta a la DB
            }
        } catch (SQLException ex) {
                Logger.getLogger(UsuarioAD.class.getName()).log(Level.SEVERE, null, ex);
                return 2;  //retorna 2 cuando se cae la conexion
            }
        }
}
