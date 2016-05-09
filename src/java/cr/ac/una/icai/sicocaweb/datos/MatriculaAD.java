/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.datos;

import cr.ac.una.icai.sicocaweb.clases.Matricula;
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
public class MatriculaAD {

    private Conexion con;
    private PreparedStatement sentencia;

    public MatriculaAD() {
        this.con = new Conexion();
        this.sentencia = null;
    }

    public List<Matricula> consultaTodos() {

        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("select * from sicocaweb.matricula");
                ResultSet resultado = this.sentencia.executeQuery();

                List<Matricula> lista = new ArrayList<>();

                while (resultado.next()) {
                    Matricula matricula = new Matricula();
                    matricula.setIdCurso(resultado.getInt(1));
                    matricula.setCedula(resultado.getInt(2));
                    matricula.setNota(resultado.getInt(3));
                    lista.add(matricula);
                }

                this.con.desconectarse();

                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            } else {
                return null; //retorna 1 cuando no se conecta a la DB
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatriculaAD.class.getName()).log(Level.SEVERE, null, ex);
            return null;  //retorna 2 cuando se cae la conexion
        }
    }

    public Integer insertar(Matricula matricula) {
        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("insert into sicocaweb.matricula values (?,?,?)");
                this.sentencia.setInt(1, matricula.getIdCurso());
                this.sentencia.setInt(2, matricula.getCedula());
                this.sentencia.setInt(3, matricula.getNota());
                this.sentencia.executeUpdate();
                this.con.desconectarse();
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien

            } else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }

        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")) {
                return 3;
            } else {
                Logger.getLogger(Matricula.class.getName()).log(Level.SEVERE, null, ex);
                return 2;  //retorna 2 cuando se cae la conexion
            }
        }
    }

    public Integer Modificar(Matricula matricula) {
        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("UPDATE `sicocaweb`.`matricula` SET `nota`=? "
                        + " WHERE `idCurso`=? and `cedula`=?  ");
                
                this.sentencia.setInt(1, matricula.getNota());
                this.sentencia.setInt(2, matricula.getIdCurso());
                this.sentencia.setInt(3, matricula.getCedula());
                this.sentencia.executeUpdate();
                this.con.desconectarse();
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien

            } else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }

        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")) {
                return 3;
            } else {
                Logger.getLogger(Matricula.class.getName()).log(Level.SEVERE, null, ex);
                return 2;  //retorna 2 cuando se cae la conexion
            }
        }
    }

    public Integer Eliminar(Matricula matricula) {
        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("DELETE FROM `sicocaweb`.`matricula` "
                        + "WHERE `idCurso`=? and `cedula`=? ");

                this.sentencia.setInt(1, matricula.getIdCurso());
                this.sentencia.setInt(2, matricula.getCedula());
                this.sentencia.executeUpdate();
                this.con.desconectarse();
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien

            } else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }

        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")) {
                return 3;
            } else {
                Logger.getLogger(Matricula.class.getName()).log(Level.SEVERE, null, ex);
                return 2;  //retorna 2 cuando se cae la conexion
            }
        }
    }

    public Matricula consultaXId(Integer idMatricula) {

        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("select * from sicocaweb.matricula where idMatricula =?");
                this.sentencia.setInt(1, idMatricula);
                ResultSet resultado = this.sentencia.executeQuery();

                Matricula matricula = new Matricula();
                while (resultado.next()) {
                    matricula.setIdCurso(resultado.getInt(1));
                    matricula.setCedula(resultado.getInt(2));
                    matricula.setCedula(resultado.getInt(3));
                }
                this.con.desconectarse();
                return matricula; //retorna 0 cuando se conecta a la DB y todo esta bien
            } else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaAD.class.getName()).log(Level.SEVERE, null, ex);
            return null;  //retorna 2 cuando se cae la conexion
        }
    }

    public List<Matricula> consultaXNombre(String nombre) {

        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("select * from sicocaweb.matricula where nombre=?");
                this.sentencia.setString(1, nombre);
                ResultSet resultado = this.sentencia.executeQuery();

                List<Matricula> lista = new ArrayList<>();

                while (resultado.next()) {
                    Matricula matricula = new Matricula();
                    matricula.setIdCurso(resultado.getInt(1));
                    matricula.setCedula(resultado.getInt(2));
                    matricula.setCedula(resultado.getInt(3));
                    lista.add(matricula);
                }

                this.con.desconectarse();

                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            } else {
                return null; //retorna 1 cuando no se conecta a la DB
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatriculaAD.class.getName()).log(Level.SEVERE, null, ex);
            return null;  //retorna 2 cuando se cae la conexion
        }
        
        
    }
    
    public List<Matricula> consultaXCedula(Integer cedula, Integer curso) {

        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("select * from sicocaweb.matricula where cedula=? and idCurso=? ");
                this.sentencia.setInt(1, cedula);
                this.sentencia.setInt(2, curso);
                
                ResultSet resultado = this.sentencia.executeQuery();

                List<Matricula> lista = new ArrayList<>();

                while (resultado.next()) {
                    Matricula matricula = new Matricula();
                    matricula.setIdCurso(resultado.getInt(1));
                    matricula.setCedula(resultado.getInt(2));
                    matricula.setNota(resultado.getInt(3));
                    lista.add(matricula);
                }

                this.con.desconectarse();

                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            } else {
                return null; //retorna 1 cuando no se conecta a la DB
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatriculaAD.class.getName()).log(Level.SEVERE, null, ex);
            return null;  //retorna 2 cuando se cae la conexion
        }
        
        
    }
}
