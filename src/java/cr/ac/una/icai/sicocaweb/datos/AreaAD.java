/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.datos;

import cr.ac.una.icai.sicocaweb.clases.Area;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author eric.martinez
 */
public class AreaAD {
    
    private Conexion con;
    private PreparedStatement sentencia;

    public AreaAD() {
        this.con = new Conexion();
        this.sentencia = null;
    }
    
    public List<Area> consultaTodos(){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.area");
                ResultSet resultado = this.sentencia.executeQuery();
                
                List<Area> lista = new ArrayList<>();
                
                while (resultado.next()){
                    Area area = new Area();
                    area.setIdArea(resultado.getInt(1));
                    area.setNombre(resultado.getString(2));
                    area.setUbicacion(resultado.getString(3));

                    lista.add(area);
                }
                
                this.con.desconectarse();
                
                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(AreaAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
    
    public Integer insertar(Area area){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("insert into sicocaweb.area values (?,?,?)");
                this.sentencia.setInt(1, area.getIdArea());
                this.sentencia.setString(2, area.getNombre());
                this.sentencia.setString(3, area. getUbicacion());

                this.sentencia.executeUpdate();
                this.con.desconectarse();
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien
            
            }else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }
        
        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")){
                return 3;
            }else{
                    Logger.getLogger(Area.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Integer Modificar(Area area){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("UPDATE `sicocaweb`.`area` SET `nombre`=?, `ubicacion`=? "
                        + " WHERE `idArea`=? ");
                
                this.sentencia.setString(1, area.getNombre());
                this.sentencia.setString(2, area. getUbicacion());
                this.sentencia.setInt(3, area.getIdArea());
                
                
                
                this.sentencia.executeUpdate();
                this.con.desconectarse();
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien
            
            }else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }
        
        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")){
                return 3;
            }else{
                    Logger.getLogger(Area.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Integer Eliminar(Area area){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("DELETE FROM `sicocaweb`.`area` WHERE `idArea`=? ");
                
                this.sentencia.setInt(1, area.getIdArea());
                this.sentencia.executeUpdate();
                this.con.desconectarse();
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien
            
            }else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }
        
        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")){
                return 3;
            }else{
                    Logger.getLogger(Area.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Area consultaXCedula(Integer idArea){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.area where idArea =?");
                this.sentencia.setInt(1, idArea);
                ResultSet resultado = this.sentencia.executeQuery();
                
                Area area = new Area();
                while (resultado.next()){
                    area.setIdArea(resultado.getInt(1));
                    area.setNombre(resultado.getString(2));
                    area.setUbicacion(resultado.getString(3));

                }
                this.con.desconectarse();
                return area; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
        } catch (SQLException ex) {
                Logger.getLogger(AreaAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
    
    public List<Area> consultaXNombre(String nombre){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.areaente where nombre=?");
                this.sentencia.setString(1, nombre);
                ResultSet resultado = this.sentencia.executeQuery();
                
                List<Area> lista = new ArrayList<>();
                
                while (resultado.next()){
                    Area area = new Area();
                    area.setIdArea(resultado.getInt(1));
                    area.setNombre(resultado.getString(2));
                    area.setUbicacion(resultado.getString(3));
                    lista.add(area);
                }
                
                this.con.desconectarse();
                
                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(AreaAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
}
