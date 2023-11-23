package DAO;

import DTO.Vehiculo;
import Conexion.Conexion;
import Mensajes.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean save(Vehiculo objeto) {
        try {
            String query = "INSERT INTO Vehiculo "
                    + "(ID_VEHICULO,VIN,MARCA,MODELO,ANIO,COLOR,PLACA,REPARACIONPREVIA,ID_CLIENTE,ESTATUS,FECHAALTA) VALUES"
                    + "(SEQ_Vehiculo.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getVin());
            ps.setString(2, objeto.getMarca());
            ps.setString(3, objeto.getModelo());
            ps.setInt(4, objeto.getAnio());
            ps.setString(5, objeto.getColor());
            ps.setString(6, objeto.getPlaca());
            ps.setString(7, objeto.getReparacionPrevia());
            ps.setInt(8, objeto.getCliente().getIdCliente());
            ps.setString(9, objeto.getEstatus());
            ps.setString(10, Utils.SDF.format(objeto.getFechaAlta()));

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo registrar el Vehiculo.\n" + e.getMessage();
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

    public static boolean update(Vehiculo objeto) {
        try {
            String query = "UPDATE Vehiculo SET "
                    + " VIN=?,MARCA=?,MODELO=?,ANIO=?,COLOR=?,PLACA=?,REPARACIONPREVIA=?,ID_CLIENTE=?,ESTATUS=?,FECHAALTA=?"
                    + " WHERE ID_VEHICULO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getVin());
            ps.setString(2, objeto.getMarca());
            ps.setString(3, objeto.getModelo());
            ps.setInt(4, objeto.getAnio());
            ps.setString(5, objeto.getColor());
            ps.setString(6, objeto.getPlaca());
            ps.setString(7, objeto.getReparacionPrevia());
            ps.setInt(8, objeto.getCliente().getIdCliente());
            ps.setString(9, objeto.getEstatus());
            ps.setString(10, Utils.SDF.format(objeto.getFechaAlta()));
            ps.setInt(11, objeto.getIdVehiculo());

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo actualizar el Vehiculo.\n" + e.getMessage();
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
            String query = "DELETE FROM Vehiculo"
                    + " WHERE ID_VEHICULO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo eliminar el Vehiculo.\n" + e.getMessage();
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

    public static List<Vehiculo> getData(ResultSet rs) {
        List<Vehiculo> data = new ArrayList<>();
        try {
            while (rs.next()) {
                Vehiculo objeto = new Vehiculo();
                objeto.setIdVehiculo(rs.getInt("ID_VEHICULO"));
                objeto.setVin(rs.getString("VIN"));
                objeto.setMarca(rs.getString("MARCA"));
                objeto.setModelo(rs.getString("MODELO"));
                objeto.setAnio(rs.getInt("ANIO"));
                objeto.setColor(rs.getString("COLOR"));
                objeto.setPlaca(rs.getString("PLACA"));
                objeto.setReparacionPrevia(rs.getString("REPARACIONPREVIA"));
                objeto.setCliente(ClienteDAO.find(rs.getInt("ID_CLIENTE")));
                objeto.setEstatus(rs.getString("ESTATUS"));
                objeto.setFechaAlta(rs.getDate("FECHAALTA"));

                data.add(objeto);
            }
        } catch (SQLException e) {
            System.out.println("Excepcion getData Vehiculo: " + e.getMessage());
        }
        return data;
    }

    public static Vehiculo find(int id) {
        Vehiculo objeto = null;
        try {
            String query = "SELECT * FROM Vehiculo"
                    + " WHERE ID_VEHICULO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            List<Vehiculo> data = getData(rs);
            objeto = !data.isEmpty() ? data.get(0) : null;

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción find Vehiculo. " + e.getMessage());
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

    public static List<Vehiculo> list(String filter) {
        List<Vehiculo> data = new ArrayList<>();
        try {
            String query;
            if (filter == null) {
                query = "SELECT * FROM Vehiculo "
                        + " WHERE estatus = 'Vigente'";
            } else {
                query = "SELECT * FROM Vehiculo"
                        + " WHERE vin LIKE CONCAT(?,'%') OR placa LIKE CONCAT(?,'%')";
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
            System.out.println("Excepción list Vehiculo. " + e.getMessage());
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

    public static List<Vehiculo> listByCliente(int idCliente) {
        List<Vehiculo> data = new ArrayList<>();
        try {
            String query = "SELECT * FROM Vehiculo"
                    + " WHERE id_cliente = ? AND estatus = 'Vigente'";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            data = getData(rs);

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción list Vehiculo. " + e.getMessage());
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
