/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.datos;

import cr.ac.una.icai.sicocaweb.clases.Instructor;
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
public class InstructorAD {
    
    private Conexion con;
    private PreparedStatement sentencia;

    public InstructorAD() {
        this.con = new Conexion();
        this.sentencia = null;
    }
    
    public List<Instructor> consultaTodos(){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.instructor");
                ResultSet resultado = this.sentencia.executeQuery();
                
                List<Instructor> lista = new ArrayList<>();
                
                while (resultado.next()){
                    Instructor instructor = new Instructor();
                    instructor.setIdInstructor(resultado.getInt(1));
                    instructor.setNombre(resultado.getString(2));
                    instructor.setGradoAcademico(resultado.getString(3));
                    instructor.setAnnosExperiencia(resultado.getInt(4));
                    instructor.setTelefono(resultado.getString(5));
                    instructor.setCorreo(resultado.getString(6));

                    lista.add(instructor);
                }
                
                this.con.desconectarse();
                
                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(InstructorAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
    
    public Integer insertar(Instructor instructor){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("insert into sicocaweb.instructor values (?,?,?,?,?,?)");
                this.sentencia.setInt(1, instructor.getIdInstructor());
                this.sentencia.setString(2, instructor.getNombre());
                this.sentencia.setString(3, instructor.getGradoAcademico());
                this.sentencia.setInt(4, instructor.getAnnosExperiencia());
                this.sentencia.setString(5, instructor.getTelefono());
                this.sentencia.setString(6, instructor.getCorreo());
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
                    Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Integer Modificar(Instructor instructor){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("UPDATE `sicocaweb`.`instructor` SET `nombre`=?, `gradoAcademico`=?,`annosExperiencia`=?,`telefono`=?,`correo`=? "
                        + " WHERE `idInstructor`=? ");
                
                
                this.sentencia.setString(1, instructor.getNombre());
                this.sentencia.setString(2, instructor.getGradoAcademico());
                this.sentencia.setInt(3, instructor.getAnnosExperiencia());
                this.sentencia.setString(4, instructor.getTelefono());
                this.sentencia.setString(5, instructor.getCorreo());
                this.sentencia.setInt(6, instructor.getIdInstructor());
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
                    Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Integer Eliminar(Instructor instructor){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("DELETE FROM `sicocaweb`.`instructor` WHERE `idInstructor`=? ");
                
                this.sentencia.setInt(1, instructor.getIdInstructor());
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
                    Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Instructor consultaXId(Integer idInstructor){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.instructor where idInstructor =?");
                this.sentencia.setInt(1, idInstructor);
                ResultSet resultado = this.sentencia.executeQuery();
                
                Instructor instructor = new Instructor();
                while (resultado.next()){
                    instructor.setIdInstructor(resultado.getInt(1));
                    instructor.setNombre(resultado.getString(2));
                    instructor.setGradoAcademico(resultado.getString(3));
                    instructor.setAnnosExperiencia(resultado.getInt(4));
                    instructor.setTelefono(resultado.getString(5));
                    instructor.setCorreo(resultado.getString(6));
                }
                this.con.desconectarse();
                return instructor; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
        } catch (SQLException ex) {
                Logger.getLogger(InstructorAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
    
    public List<Instructor> consultaXNombre(String nombre){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from sicocaweb.instructor where nombre=?");
                this.sentencia.setString(1, nombre);
                ResultSet resultado = this.sentencia.executeQuery();
                
                List<Instructor> lista = new ArrayList<>();
                
                while (resultado.next()){
                    Instructor instructor = new Instructor();
                    instructor.setIdInstructor(resultado.getInt(1));
                    instructor.setNombre(resultado.getString(2));
                    instructor.setGradoAcademico(resultado.getString(3));
                    instructor.setAnnosExperiencia(resultado.getInt(4));
                    instructor.setTelefono(resultado.getString(5));
                    instructor.setCorreo(resultado.getString(6));
                    lista.add(instructor);
                }
                
                this.con.desconectarse();
                
                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(InstructorAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
}
