package domain;

import conexiones.Conexion;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferajset
 */
public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO() {

    }

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean seleccionar(Usuario user) throws SQLException {
        boolean r = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            String SQL_SELECT = "SELECT u.id_usuario, u.usuario, u.contraseña, u.id_tipo, u.id_departamento, tu.nombre_tipo "
                    + "FROM usuarios u LEFT JOIN Tipo_de_usuario tu ON u.id_tipo=tu.id_tipo";
            stmt = conn.prepareStatement(SQL_SELECT);
            res = stmt.executeQuery();
            while (res.next()) {
                String usuario = res.getString("usuario");
                String password = res.getString("contraseña");
                if (user.getUsuario().equalsIgnoreCase(usuario)) {
                    if (user.getContraseña().equals(password)) {
                        user.setId_usuario(res.getInt("id_usuario"));
                        user.setId_tipo(res.getInt("id_tipo"));
                        user.setNombre_tipo(res.getString("nombre_tipo"));
                        user.setId_departamento(res.getInt("id_departamento"));
                        r = true;
                    }
                }
            }
        } finally {
            try {
                Conexion.close(res);
                Conexion.close(stmt);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return r;
    }

    public void verTareasDepartamento(int id_departamento, int estado, DefaultTableModel modelo) {
        Connection conn = null;
        ResultSet res = null;
        PreparedStatement stmt = null;
        String SQL_SELECT = new String();
        if (estado == 0) {
            SQL_SELECT = "SELECT t.id_tarea, t.descripcion, u.usuario, t.fechaasignacion, t.estado FROM tarea t LEFT JOIN usuarios u ON t.id_usuario=u.id_usuario where estado=0 AND id_departamento='" + id_departamento + "'";
        } else {
            SQL_SELECT = "SELECT t.id_tarea, t.descripcion, u.usuario, t.fechaasignacion, t.estado FROM tarea t LEFT JOIN usuarios u ON t.id_usuario=u.id_usuario where id_departamento='" + id_departamento + "' ORDER BY t.estado ASC";
        }
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            modelo.setRowCount(0);
            stmt = conn.prepareStatement(SQL_SELECT);
            res = stmt.executeQuery();
            while (res.next()) {
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                if (res.getInt(5) == 0) {
                    v.add("ACTIVA");
                }
                if (res.getInt(5) == 1) {
                    v.add("FINALIZADA");
                }
                modelo.addRow(v);
            }
        } catch (SQLException ex) {
            System.out.println("Falla al cargar usuarios");
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(res);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public void verSubs(Usuario usuario, DefaultTableModel modelo) {
        Connection conn = null;
        ResultSet res = null;
        PreparedStatement stmt = null;
        String SQL = new String();
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            switch (usuario.getId_tipo()) {
                case 1 ->
                    SQL = "SELECT id_usuario, nombre, usuario, puesto from Usuarios";
                case 2 ->
                    SQL = "SELECT id_usuario, nombre, usuario, puesto, id_departamento from Usuarios where id_departamento='" + usuario.getId_departamento() + "'";
                case 3 ->
                    SQL = "SELECT id_usuario, nombre, usuario, puesto, id_departamento from Usuarios where id_usuario='" + usuario.getId_usuario() + "'";
            }
            stmt = conn.prepareStatement(SQL);
            res = stmt.executeQuery();
            while (res.next()) {
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                modelo.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Conexion.close(conn);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean asignarTarea(String desc, int id) {
        boolean r = false;
        int idMax = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        String SQL_SELECT = "SELECT NVL(MAX(ID_TAREA),0)+1 FROM TAREA";
        String SQL_INSERT = "INSERT INTO tarea(id_tarea, descripcion, estado, id_usuario)VALUES(?,?,?,?)";
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            stmt = conn.prepareStatement(SQL_SELECT);
            res = stmt.executeQuery();
            while (res.next()) {
                idMax = res.getInt(1);
            }
            int idNuevo = idMax;
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, idNuevo);
            stmt.setString(2, desc);
            stmt.setInt(3, 0);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            conn.commit();
            r = true;
        } catch (SQLException ex) {
            System.out.println("Entramos al rollback");
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(res);
                Conexion.close(conn);
            } catch (SQLException ex) {
            }
        }
        return r;
    }

    public boolean reAsigarTarea(int idTarea, int idUsuario) {
        boolean r = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        String SQL_UPDATE = "UPDATE tarea SET id_usuario=? WHERE id_tarea='" + idTarea + "'";
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
            conn.commit();
            r = true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return r;
    }

    public boolean terminarTarea(int idTarea) {
        boolean r = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        String SQL_UPDATE = "UPDATE tarea SET fechafinalizacion=sysdate, estado=? WHERE ROWNUM=1 AND id_tarea='" + idTarea + "'";
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, 1);
            stmt.executeUpdate();
            conn.commit();
            r = true;
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
            }
        }
        return r;
    }

    public void misTareas(int estado, int idUser, DefaultTableModel modelo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = new String();
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            if (estado == 0) {
                sql = "SELECT t.id_tarea,t.descripcion,u.usuario, t.fechaasignacion, t.estado FROM tarea t LEFT JOIN usuarios u ON u.id_usuario=t.id_usuario where estado=0 and t.id_usuario='" + idUser + "'";
            } else {
                sql = "SELECT t.id_tarea,t.descripcion,u.usuario, t.fechaasignacion, t.estado FROM tarea t LEFT JOIN usuarios u ON u.id_usuario=t.id_usuario where t.id_usuario='" + idUser + "' ORDER BY t.estado ASC";
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                if (rs.getInt(5) == 0) {
                    v.add("ACTIVA");
                }
                if (rs.getInt(5) == 1) {
                    v.add("FINALIZADA");
                }
                modelo.addRow(v);
            }
        } catch (SQLException ex) {

        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(ps);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public void busquedaMayor(int id, DefaultTableModel modelo) {
        if (id != 0) {
            String SQL = "";
            if (id == 1) {
                SQL = "SELECT u.id_usuario, u.nombre, m.tareas FROM MAXIMO M LEFT JOIN USUARIOS U ON u.id_usuario=m.id_usuario where m.tareas=(SELECT MAX(m2.tareas) FROM maximo m2)";
            } else if (id == 2) {
                SQL = "SELECT u.id_usuario, u.nombre, A.tareas FROM atrasadas A LEFT JOIN USUARIOS U ON u.id_usuario=A.id_usuario where A.tareas=(SELECT MAX(A2.tareas) FROM ATRASADAS A2)";
            }
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                con = this.conexion != null ? this.conexion : Conexion.getConnection();
                ps = con.prepareStatement(SQL);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getInt(1));
                    v.add(rs.getString(2));
                    v.add(rs.getInt(3));
                    modelo.addRow(v);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    rs.close();
                    ps.close();
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    public void verTareasUltimosDias(Usuario user, DefaultTableModel modelo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = "";
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            switch (user.getId_tipo()) {
                case 1 ->
                    sql = "SELECT * FROM ULTIMOSCINCODIAS";
                case 2 ->
                    sql = "SELECT * FROM ULTIMOSCINCODIAS WHERE ID_DEPARTAMENTO='" + user.getId_departamento() + "'";
                case 3 ->
                    sql = "SELECT * FROM ULTIMOSCINCODIAS WHERE ID_USUARIO='" + user.getId_usuario() + "'";
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt(1));
                v.add(rs.getString(2));
                v.add(rs.getInt(3));
                v.add(rs.getInt(4));
                v.add(rs.getInt(5));
                v.add(rs.getInt(6));
                v.add(rs.getInt(7));
                v.add(rs.getInt(8));
                modelo.addRow(v);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(ps);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public void verTareasFinalizasPorMes(Usuario usuario, DefaultTableModel modelo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            switch (usuario.getId_tipo()) {
                case 1 -> sql = "SELECT * FROM TAREAULTIMOSMESES";
                case 2 -> sql = "SELECT * FROM TAREAULTIMOSMESES WHERE ID_DEPARTAMENTO='" + usuario.getId_departamento() + "'";
                case 3 -> sql = "SELECT * FROM TAREAULTIMOSMESES WHERE ID_USUARIO='" + usuario.getId_usuario() + "'";
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt(1));
                v.add(rs.getString(2));
                v.add(rs.getInt(3));
                v.add(rs.getInt(4));
                v.add(rs.getInt(5));
                v.add(rs.getInt(6));
                modelo.addRow(v);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(ps);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
}
