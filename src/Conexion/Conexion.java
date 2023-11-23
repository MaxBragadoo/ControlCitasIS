package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String user = "db_control_citas";
    private static final String password = "123";

    public static Connection getConnection() {

        Connection conexion = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se conectò" + ex.getMessage());
        }

        return conexion;
    }

}
