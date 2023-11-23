package DAO;

import DTO.Cliente;
import Conexion.Conexion;
import Mensajes.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean save(Cliente objeto) {
        try {
            String query = "INSERT INTO Cliente "
                    + "(ID_CLIENTE,CODIGOCLIENTE,NOMBRE,APELLIDOP,APELLIDOM,CORREO,TELEFONO,ESTATUS,FECHAALTA) VALUES"
                    + "(SEQ_Cliente.NEXTVAL,?,?,?,?,?,?,?,?)";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getCodigoCliente());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellidoP());
            ps.setString(4, objeto.getApellidoM());
            ps.setString(5, objeto.getCorreo());
            ps.setString(6, objeto.getTelefono());
            ps.setString(7, objeto.getEstatus());
            ps.setString(8, Utils.SDF.format(objeto.getFechaAlta()));

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo registrar el Cliente.\n" + e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.MESSAGE = "No se pudo cerrar la conexión";
            }
        }
        return false;
    }

    public static boolean update(Cliente objeto) {
        try {
            String query = "UPDATE Cliente SET "
                    + " CODIGOCLIENTE=?,NOMBRE=?,APELLIDOP=?,APELLIDOM=?,CORREO=?,TELEFONO=?,ESTATUS=?,FECHAALTA=?"
                    + " WHERE ID_CLIENTE = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getCodigoCliente());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellidoP());
            ps.setString(4, objeto.getApellidoM());
            ps.setString(5, objeto.getCorreo());
            ps.setString(6, objeto.getTelefono());
            ps.setString(7, objeto.getEstatus());
            ps.setString(8, Utils.SDF.format(objeto.getFechaAlta()));
            ps.setInt(9, objeto.getIdCliente());

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo actualizar el Cliente.\n" + e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.MESSAGE = "No se pudo cerrar la conexión";
            }
        }
        return false;
    }

    public static boolean delete(int id) {
        try {
            String query = "DELETE FROM Cliente"
                    + " WHERE ID_CLIENTE = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo eliminar el Cliente.\n" + e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.MESSAGE = "No se pudo cerrar la conexión";
            }
        }
        return false;
    }

    public static List<Cliente> getData(ResultSet rs) {
        List<Cliente> data = new ArrayList<>();
        try {
            while (rs.next()) {
                Cliente objeto = new Cliente();
                objeto.setIdCliente(rs.getInt("ID_CLIENTE"));
                objeto.setCodigoCliente(rs.getString("CODIGOCLIENTE"));
                objeto.setNombre(rs.getString("NOMBRE"));
                objeto.setApellidoP(rs.getString("APELLIDOP"));
                objeto.setApellidoM(rs.getString("APELLIDOM"));
                objeto.setCorreo(rs.getString("CORREO"));
                objeto.setTelefono(rs.getString("TELEFONO"));
                objeto.setEstatus(rs.getString("ESTATUS"));
                objeto.setFechaAlta(rs.getDate("FECHAALTA"));

                data.add(objeto);
            }
        } catch (SQLException e) {
            System.out.println("Excepcion getData Cliente: " + e.getMessage());
        }
        return data;
    }

    public static Cliente find(int id) {
        Cliente objeto = null;
        try {
            String query = "SELECT * FROM Cliente"
                    + " WHERE ID_CLIENTE = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            List<Cliente> data = getData(rs);
            objeto = !data.isEmpty() ? data.get(0) : null;

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción find Cliente. " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión");
            }
        }
        return objeto;
    }

    public static List<Cliente> list(String filter) {
        List<Cliente> data = new ArrayList<>();
        try {
            String query;
            if (filter == null) {
                query = "SELECT * FROM Cliente "
                        + " WHERE estatus = 'Vigente'";
            } else {
                query = "SELECT * FROM Cliente"
                        + " WHERE codigoCliente LIKE CONCAT(?,'%') OR nombre LIKE CONCAT(?,'%')";
            }

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            if(filter != null){
            ps.setString(1, filter);
            ps.setString(2, filter);
            }
            rs = ps.executeQuery();

            data = getData(rs);

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción list Cliente. " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión");
            }
        }
        return data;
    }
}
