package BLL;

import DAO.RolDAO;
import DTO.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RolBLL {

    public static List<Rol> list(String filter) {
        return RolDAO.list(filter);
    }

    public static Rol find(int id) {
        return RolDAO.find(id);
    }

    public static boolean add(Rol objeto) {
        return RolDAO.save(objeto);
    }

    public static boolean addPermiso(int idRol, int idPermiso) {
        return RolDAO.addPermiso(idRol, idPermiso);
    }

    public static boolean edit(Rol objeto) {
        return RolDAO.update(objeto);
    }

    public static boolean delete(int id) {
        return RolDAO.delete(id);
    }

    public static boolean deletePermiso(int idRol, int idPermiso) {
        return RolDAO.deletePermiso(idRol, idPermiso);
    }

    public static DefaultTableModel createTable(DefaultTableModel tabla, List<Rol> data) {
        tabla = clearTable(tabla);
        for (Rol item : data) {
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
