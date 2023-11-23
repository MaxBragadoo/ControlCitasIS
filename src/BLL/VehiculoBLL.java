package BLL;

import DAO.VehiculoDAO;
import DTO.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VehiculoBLL {

    public static List<Vehiculo> list(String filter) {
        return VehiculoDAO.list(filter);
    }

    public static List<Vehiculo> listByCliente(int idCliente) {
        return VehiculoDAO.listByCliente(idCliente);
    }

    public static Vehiculo find(int id) {
        return VehiculoDAO.find(id);
    }

    public static boolean add(Vehiculo objeto) {
        return VehiculoDAO.save(objeto);
    }

    public static boolean edit(Vehiculo objeto) {
        return VehiculoDAO.update(objeto);
    }

    public static boolean delete(int id) {
        return VehiculoDAO.delete(id);
    }

    public static DefaultTableModel createTable(DefaultTableModel tabla, List<Vehiculo> data) {
        tabla = clearTable(tabla);
        for (Vehiculo item : data) {
            tabla.addRow(item.toObject());
        }
        return tabla;
    }

    public static DefaultTableModel clearTable(DefaultTableModel tabla) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.removeRow(i);
            i--;
        }
        return tabla;
    }
}
