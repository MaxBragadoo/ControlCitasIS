package DAO;

import DTO.Cita;
import Conexion.Conexion;
import Mensajes.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean save(Cita objeto) {
        try {
            String query = "INSERT INTO Cita "
                    + "(ID_CITA,FECHAHORA,DESCRIPCION,ID_VEHICULO,ID_USUARIO,ESTATUS,FECHAALTA) VALUES"
                    + "(SEQ_Cita.NEXTVAL,TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'),?,?,?,?,?)";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, Utils.SDF_TIME.format(objeto.getFechaHora()));
            ps.setString(2, objeto.getDescripcion());
            ps.setInt(3, objeto.getVehiculo().getIdVehiculo());
            ps.setInt(4, objeto.getUsuario().getIdUsuario());
            ps.setString(5, objeto.getEstatus());
            ps.setString(6, Utils.SDF.format(objeto.getFechaAlta()));

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo registrar el Cita.\n" + e.getMessage();
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

    public static boolean update(Cita objeto) {
        try {
            String query = "UPDATE Cita SET "
                    + " FECHAHORA=TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'),DESCRIPCION=?,ID_VEHICULO=?,ID_USUARIO=?,ESTATUS=?,FECHAALTA=?"
                    + " WHERE ID_CITA = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, Utils.SDF_TIME.format(objeto.getFechaHora()));
            ps.setString(2, objeto.getDescripcion());
            ps.setInt(3, objeto.getVehiculo().getIdVehiculo());
            ps.setInt(4, objeto.getUsuario().getIdUsuario());
            ps.setString(5, objeto.getEstatus());
            ps.setString(6, Utils.SDF.format(objeto.getFechaAlta()));
            ps.setInt(7, objeto.getIdCita());

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo actualizar el Cita.\n" + e.getMessage();
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
            Cita cita = find(id);
            if (!cita.getEstatus().equals("Espera")) {
                Utils.MESSAGE = "La cita no se puede eliminar porque no está en ESPERA";
                return false;
            }

            String query = "DELETE FROM Cita"
                    + " WHERE ID_CITA = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo eliminar el Cita.\n" + e.getMessage();
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

    public static List<Cita> getData(ResultSet rs) {
        List<Cita> data = new ArrayList<>();
        try {
            while (rs.next()) {
                Cita objeto = new Cita();
                objeto.setIdCita(rs.getInt("ID_CITA"));
                objeto.setFechaHora(rs.getDate("FECHAHORA"));
                objeto.setDescripcion(rs.getString("DESCRIPCION"));
                objeto.setVehiculo(VehiculoDAO.find(rs.getInt("ID_VEHICULO")));
                objeto.setUsuario(UsuarioDAO.find(rs.getInt("ID_USUARIO")));
                objeto.setEstatus(rs.getString("ESTATUS"));
                objeto.setFechaAlta(rs.getDate("FECHAALTA"));

                data.add(objeto);
            }
        } catch (SQLException e) {
            System.out.println("Excepcion getData Cita: " + e.getMessage());
        }
        return data;
    }

    public static Cita find(int id) {
        Cita objeto = null;
        try {
            String query = "SELECT * FROM Cita"
                    + " WHERE ID_CITA = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            List<Cita> data = getData(rs);
            objeto = !data.isEmpty() ? data.get(0) : null;

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción find Cita. " + e.getMessage());
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

    public static List<Cita> list(String filter) {
        List<Cita> data = new ArrayList<>();
        try {
            String query = "SELECT * FROM Cita c"
                    + " INNER JOIN VEHICULO v ON v.ID_VEHICULO = c.ID_VEHICULO "
                    + " INNER JOIN CLIENTE c ON c.ID_CLIENTE = v.ID_CLIENTE "
                    + " WHERE c.ID_CITA LIKE CONCAT(?,'%') "
                    + " OR TO_CHAR(c.FECHAHORA, 'YYYY-MM-DD HH24:MI') LIKE CONCAT(?,'%') "
                    + " OR c.CODIGOCLIENTE LIKE CONCAT(?,'%') "
                    + " OR v.PLACA LIKE CONCAT(?,'%') "
                    + " OR v.VIN LIKE CONCAT(?,'%') ";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, filter);
            ps.setString(2, filter);
            ps.setString(3, filter);
            ps.setString(4, filter);
            ps.setString(5, filter);
            rs = ps.executeQuery();

            data = getData(rs);

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción list Cita. " + e.getMessage());
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
