/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import conexiones.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.CONSULTAS;

/**
 *
 * @author Fer
 */
public class MDepartamentos {

    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return this.nombre;
    }

    public Vector<MDepartamentos> mostrarDepas() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = null;
        Vector<MDepartamentos> dep = new Vector<MDepartamentos>();
        MDepartamentos dat = null;
        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT * from departamento";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new MDepartamentos();
            dat.setId(0);
            dat.setNombre("seleccione departamento");
            dep.add(dat);
            while (rs.next()) {
                dat = new MDepartamentos();
                dat.setId(rs.getInt("id_departamento"));
                dat.setNombre(rs.getString("nombred"));
                dep.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CONSULTAS.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(ps);
                Conexion.close(conexion);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return dep;
    }
}
