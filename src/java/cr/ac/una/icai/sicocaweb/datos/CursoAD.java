/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.datos;

import cr.ac.una.icai.sicocaweb.clases.Curso;
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
public class CursoAD {
    
    private Conexion con;
    private PreparedStatement sentencia;

    public CursoAD() {
        this.con = new Conexion();
        this.sentencia = null;
    }
    
    public List<Curso> consultaTodos(){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.curso");
                ResultSet resultado = this.sentencia.executeQuery();
                
                List<Curso> lista = new ArrayList<>();
                
                while (resultado.next()){
                    Curso curso = new Curso();
                    curso.setIdCurso(resultado.getInt(1));
                    curso.setNombre(resultado.getString(2));
                    curso.setHorasDuracion(resultado.getInt(3));
                    //this.sentencia.setDate(3, new java.sql.Date(alquiler.getFechaAlquiler().getTime()));
                    curso.setFechaInicio(new java.sql.Date(resultado.getDate(4).getTime()));
                    curso.setFechaFinalizacion(new java.sql.Date(resultado.getDate(5).getTime()));
                    curso.setPrecio(resultado.getDouble(6));
                    curso.setIdInstructor(resultado.getInt(7));

                    lista.add(curso);
                }
                
                this.con.desconectarse();
                
                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(CursoAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
    
    public Integer insertar(Curso curso){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("insert into sicocaweb.curso values (?,?,?,?,?,?,?)");
                this.sentencia.setInt(1, curso.getIdCurso());
                this.sentencia.setString(2, curso.getNombre());
                this.sentencia.setInt(3, curso.getHorasDuracion());
                this.sentencia.setDate(4, new java.sql.Date(curso.getFechaInicio().getTime()));
                this.sentencia.setDate(5, new java.sql.Date(curso.getFechaFinalizacion().getTime()));
                this.sentencia.setDouble(6, curso.getPrecio());
                this.sentencia.setInt(7, curso.getIdInstructor());
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
                    Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Integer Modificar(Curso curso){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("UPDATE `sicocaweb`.`curso` SET `nombre`=?, `horasDuracion`=?,`fechaInicio`=?,`fechaFinalizacion`=?,`precio`=?,`idInstructor`=? "
                        + " WHERE `idCurso`=? ");
                
                this.sentencia.setString(1, curso.getNombre());
                this.sentencia.setInt(2, curso.getHorasDuracion());
                this.sentencia.setDate(3, new java.sql.Date(curso.getFechaInicio().getTime()));
                this.sentencia.setDate(4, new java.sql.Date(curso.getFechaFinalizacion().getTime()));
                this.sentencia.setDouble(5, curso.getPrecio());
                this.sentencia.setInt(6, curso.getIdInstructor());
                this.sentencia.setInt(7, curso.getIdCurso());
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
                    Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Integer Eliminar(Curso curso){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("DELETE FROM `sicocaweb`.`curso` WHERE `idCurso`=? ");
                
                this.sentencia.setInt(1, curso.getIdCurso());
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
                    Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Curso consultaXId(Integer idCurso){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.curso where idCurso =?");
                this.sentencia.setInt(1, idCurso);
                ResultSet resultado = this.sentencia.executeQuery();
                
                Curso curso = new Curso();
                while (resultado.next()){
                    
                    curso.setIdCurso(resultado.getInt(1));
                    curso.setNombre(resultado.getString(2));
                    curso.setHorasDuracion(resultado.getInt(3));
                    //(3, new java.sql.Date(alquiler.getFechaAlquiler().getTime()));
                    curso.setFechaInicio(new java.sql.Date(resultado.getDate(4).getTime()));
                    curso.setFechaFinalizacion(new java.sql.Date(resultado.getDate(5).getTime()));
                    curso.setPrecio(resultado.getDouble(6));
                    curso.setIdInstructor(resultado.getInt(7));
                }
                this.con.desconectarse();
                return curso; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
        } catch (SQLException ex) {
                Logger.getLogger(CursoAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
    
    public List<Curso> consultaXNombre(String nombre){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.curso where nombre=?");
                this.sentencia.setString(1, nombre);
                ResultSet resultado = this.sentencia.executeQuery();
                
                List<Curso> lista = new ArrayList<>();
                
                while (resultado.next()){
                    Curso curso = new Curso();
                    curso.setIdCurso(resultado.getInt(1));
                    curso.setNombre(resultado.getString(2));
                    curso.setHorasDuracion(resultado.getInt(3));
                    //this.sentencia.setDate(3, new java.sql.Date(alquiler.getFechaAlquiler().getTime()));
                    curso.setFechaInicio(new java.sql.Date(resultado.getDate(4).getTime()));
                    curso.setFechaFinalizacion(new java.sql.Date(resultado.getDate(5).getTime()));
                    curso.setPrecio(resultado.getDouble(6));
                    curso.setIdInstructor(resultado.getInt(7));
                    lista.add(curso);
                }
                
                this.con.desconectarse();
                
                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(CursoAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
}
