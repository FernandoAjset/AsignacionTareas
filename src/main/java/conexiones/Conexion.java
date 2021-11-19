package conexiones;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author ferajset
 */
public class Conexion {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String JDBC_USER = "ADMIN_DBA";
    private static final String JDBC_PASS = "umg2021";
    private static Connection conne = null;
    private static BasicDataSource ds = new BasicDataSource();

    public static DataSource getDataSource() {

        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASS);
        ds.setInitialSize(5);
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conne = getDataSource().getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
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
