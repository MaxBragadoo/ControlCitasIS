package DAO;

import DTO.Permiso;
import Conexion.Conexion;
import Mensajes.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermisoDAO {

    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean save(Permiso objeto) {
        try {
            String query = "INSERT INTO Permiso "
                    + "(ID_PERMISO,CODIGOPERMISO,NOMBRE,DESCRIPCION,ESTATUS,FECHAALTA) VALUES"
                    + "(SEQ_Permiso.NEXTVAL,?,?,?,?,?)";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getCodigoPermiso());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getDescripcion());
            ps.setString(4, objeto.getEstatus());
            ps.setString(5, Utils.SDF.format(objeto.getFechaAlta()));

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo registrar el Permiso.\n" + e.getMessage();
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

    public static boolean update(Permiso objeto) {
        try {
            String query = "UPDATE Permiso SET "
                    + " CODIGOPERMISO = ?,NOMBRE = ?,DESCRIPCION = ?,ESTATUS = ?,FECHAALTA = ?"
                    + " WHERE ID_PERMISO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getCodigoPermiso());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getDescripcion());
            ps.setString(4, objeto.getEstatus());
            ps.setString(5, Utils.SDF.format(objeto.getFechaAlta()));
            ps.setInt(6, objeto.getIdPermiso());

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo actualizar el Permiso.\n" + e.getMessage();
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
            String query = "DELETE FROM Permiso"
                    + " WHERE ID_PERMISO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo eliminar el Permiso.\n" + e.getMessage();
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

    public static List<Permiso> getData(ResultSet rs) {
        List<Permiso> data = new ArrayList<>();
        try {
            while (rs.next()) {
                Permiso objeto = new Permiso();
                objeto.setIdPermiso(rs.getInt("ID_PERMISO"));
                objeto.setCodigoPermiso(rs.getString("CODIGOPERMISO"));
                objeto.setNombre(rs.getString("NOMBRE"));
                objeto.setDescripcion(rs.getString("DESCRIPCION"));
                objeto.setEstatus(rs.getString("ESTATUS"));
                objeto.setFechaAlta(rs.getDate("FECHAALTA"));

                data.add(objeto);
            }
        } catch (SQLException e) {
            System.out.println("Excepcion getData Permiso: " + e.getMessage());
        }
        return data;
    }

    public static Permiso find(int id) {
        Permiso objeto = null;
        try {
            String query = "SELECT * FROM Permiso"
                    + " WHERE ID_PERMISO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            List<Permiso> data = getData(rs);
            objeto = !data.isEmpty() ? data.get(0) : null;

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción find Permiso. " + e.getMessage());
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

    public static List<Permiso> list(String filter) {
        List<Permiso> data = new ArrayList<>();
        try {
            String query;
            if (filter == null) {
                query = "SELECT * FROM Permiso "
                        + " WHERE estatus = 'Vigente'";
            } else {
                query = "SELECT * FROM Permiso"
                        + " WHERE codigoPermiso LIKE CONCAT(?,'%') OR nombre LIKE CONCAT(?,'%')"
                        + " ORDER BY codigoPermiso";
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
            System.out.println("Excepción list Permiso. " + e.getMessage());
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

    public static List<Permiso> listByRolId(int rolId) {
        List<Permiso> data = new ArrayList<>();
        try {
            String query = "SELECT p.* FROM Permiso p "
                    + " INNER JOIN PermisoRol rp ON rp.id_permiso = p.id_permiso"
                    + " WHERE rp.id_rol = ? AND p.estatus = 'Vigente'";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, rolId);
            rs = ps.executeQuery();

            data = getData(rs);

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción listByRolId Permiso. " + e.getMessage());
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
