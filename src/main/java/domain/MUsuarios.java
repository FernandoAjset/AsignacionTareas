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
public class MUsuarios {

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

    @Override
    public String toString() {
        return this.nombre;
    }

    public Vector<MUsuarios> mostrarUsuarios(int id_tipo, int id_departamento) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = null;
        Vector<MUsuarios> dep = new Vector<MUsuarios>();
        MUsuarios dat = null;
        try {
            conexion = Conexion.getConnection();
            String sql = "";
            if (id_tipo == 1) {
                sql = "SELECT * from usuarios";
            } else {
                sql = "SELECT * from usuarios where id_departamento='" + id_departamento + "'";
            }
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new MUsuarios();
            dat.setId(0);
            dat.setNombre("seleccione usuario");
            dep.add(dat);
            while (rs.next()) {
                dat = new MUsuarios();
                dat.setId(rs.getInt("id_usuario"));
                dat.setNombre(rs.getString("usuario"));
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
