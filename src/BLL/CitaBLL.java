package BLL;

import DAO.CitaDAO;
import DTO.Cita;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CitaBLL {

    public static List<Cita> list(String filter) {
        return CitaDAO.list(filter);
    }

    public static Cita find(int id) {
        return CitaDAO.find(id);
    }

    public static boolean add(Cita objeto) {
        return CitaDAO.save(objeto);
    }

    public static boolean edit(Cita objeto) {
        return CitaDAO.update(objeto);
    }

    public static boolean delete(int id) {
        return CitaDAO.delete(id);
    }

    public static DefaultTableModel createTable(DefaultTableModel tabla, List<Cita> data) {
        tabla = clearTable(tabla);
        for (Cita item : data) {
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
