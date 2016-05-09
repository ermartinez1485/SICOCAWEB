/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sicocaweb.datos;

import cr.ac.una.icai.sicocaweb.clases.Empleado;
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
public class EmpleadoAD {

    private Conexion con;
    private PreparedStatement sentencia;

    public EmpleadoAD() {
        this.con = new Conexion();
        this.sentencia = null;
    }

    public List<Empleado> consultaTodos() {

        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("select * from sicocaweb.empleado");
                ResultSet resultado = this.sentencia.executeQuery();

                List<Empleado> lista = new ArrayList<>();

                while (resultado.next()) {
                    Empleado empleado = new Empleado();
                    empleado.setCedula(resultado.getInt(1));
                    empleado.setNombre(resultado.getString(2));
                    empleado.setApellido1(resultado.getString(3));
                    empleado.setApellido2(resultado.getString(4));
                    empleado.setTelefono(resultado.getInt(5));
                    empleado.setCorreo(resultado.getString(6));
                    empleado.setIdArea(resultado.getInt(7));
                    lista.add(empleado);
                }

                this.con.desconectarse();

                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            } else {
                return null; //retorna 1 cuando no se conecta a la DB
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoAD.class.getName()).log(Level.SEVERE, null, ex);
            return null;  //retorna 2 cuando se cae la conexion
        }
    }

    public Integer insertar(Empleado empleado) {
        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("insert into sicocaweb.empleado values (?,?,?,?,?,?,?)");
                this.sentencia.setInt(1, empleado.getCedula());
                this.sentencia.setString(2, empleado.getNombre());
                this.sentencia.setString(3, empleado.getApellido1());
                this.sentencia.setString(4, empleado.getApellido2());
                this.sentencia.setInt(5, empleado.getTelefono());
                this.sentencia.setString(6, empleado.getCorreo());
                this.sentencia.setInt(7, empleado.getIdArea());
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
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
                return 2;  //retorna 2 cuando se cae la conexion
            }
        }
    }

    public Integer Modificar(Empleado empleado) {
        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("UPDATE `sicocaweb`.`empleado` SET `nombre`=?, `apellido1`=?,`apellido2`=?,`telefono`=?,`correo`=?,`idArea`=? "
                        + " WHERE `cedula`=? ");

                this.sentencia.setString(1, empleado.getNombre());
                this.sentencia.setString(2, empleado.getApellido1());
                this.sentencia.setString(3, empleado.getApellido2());
                this.sentencia.setInt(4, empleado.getTelefono());
                this.sentencia.setString(5, empleado.getCorreo());
                this.sentencia.setInt(6, empleado.getIdArea());
                this.sentencia.setInt(7, empleado.getCedula());

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
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
                return 2;  //retorna 2 cuando se cae la conexion
            }
        }
    }

    public Integer Eliminar(Empleado empleado) {
        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("DELETE FROM `sicocaweb`.`empleado` WHERE `cedula`=? ");

                this.sentencia.setInt(1, empleado.getCedula());
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
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
                return 2;  //retorna 2 cuando se cae la conexion
            }
        }
    }

    public Empleado consultaXId(Integer idEmpleado) {

        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("select * from sicocaweb.empleado where idEmpleado =?");
                this.sentencia.setInt(1, idEmpleado);
                ResultSet resultado = this.sentencia.executeQuery();

                Empleado empleado = new Empleado();
                while (resultado.next()) {
                    empleado.setCedula(resultado.getInt(1));
                    empleado.setNombre(resultado.getString(2));
                    empleado.setApellido1(resultado.getString(3));
                    empleado.setApellido2(resultado.getString(4));
                    empleado.setTelefono(resultado.getInt(5));
                    empleado.setCorreo(resultado.getString(6));
                    empleado.setIdArea(resultado.getInt(7));
                }
                this.con.desconectarse();
                return empleado; //retorna 0 cuando se conecta a la DB y todo esta bien
            } else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoAD.class.getName()).log(Level.SEVERE, null, ex);
            return null;  //retorna 2 cuando se cae la conexion
        }
    }

    public List<Empleado> consultaXNombre(String nombre) {

        try {
            if (this.con.conectarse()) {

                this.sentencia = this.con.getCon().prepareStatement("select * from sicocaweb.empleado where nombre=?");
                this.sentencia.setString(1, nombre);
                ResultSet resultado = this.sentencia.executeQuery();

                List<Empleado> lista = new ArrayList<>();

                while (resultado.next()) {
                    Empleado empleado = new Empleado();
                    empleado.setCedula(resultado.getInt(1));
                    empleado.setNombre(resultado.getString(2));
                    empleado.setApellido1(resultado.getString(3));
                    empleado.setApellido2(resultado.getString(4));
                    empleado.setTelefono(resultado.getInt(5));
                    empleado.setCorreo(resultado.getString(6));
                    empleado.setIdArea(resultado.getInt(7));
                    lista.add(empleado);
                }

                this.con.desconectarse();

                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            } else {
                return null; //retorna 1 cuando no se conecta a la DB
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoAD.class.getName()).log(Level.SEVERE, null, ex);
            return null;  //retorna 2 cuando se cae la conexion
        }
    }
}
