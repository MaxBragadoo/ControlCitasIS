package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String user = "sys as sysdba";
    private static Connection conn = null;
   private static  String base_datos = "xe";
    // private String url,user,pass;
    private static String url = "jdbc:oracle:thin:@149.56.47.191:1521:"+base_datos;
  //  private static String user = "sys as sysdba";
    private static String pass = "oracle";



    public static Connection getConnection()
    {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, "sys as sysdba", pass);
            conn.setAutoCommit(false);
            if (conn != null) {
                System.out.println("Conexion exitosa");
            }else{
                System.out.println("Error: no se logro la conexion a la base de datos");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: no se logro la conexion a la base de datos");
        }
        return conn;
    }

}

