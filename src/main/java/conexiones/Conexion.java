package conexiones;

import java.sql.*;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author ferajset
 */
public class Conexion {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    
    //CREDENCIALES DEL USUARIO ORACLE, NO TIENE RELACION CON LOS USUARIOS CREADOS EN EL CATALOGO DE LA BD (TABLA USUARIOS) 
    private static final String JDBC_USER = "ADMIN_DBA";
    private static final String JDBC_PASS = "umg2021";
    
    private static Connection conne = null;
    private static final BasicDataSource ds = new BasicDataSource();

    public static DataSource getDataSource() {

        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASS);
        ds.setInitialSize(5);
        return ds;
    }

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conne = getDataSource().getConnection();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showConfirmDialog(null, "Falla del Driver", "Problema de conexion", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Credenciales principales incorrectas", "Acceso Denegado", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        return conne;
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(PreparedStatement smtm) throws SQLException {
        smtm.close();
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
    }

    public static void closeDs() throws SQLException {
        ds.close();
    }
}
