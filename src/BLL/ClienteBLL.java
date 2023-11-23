package BLL;

import DAO.ClienteDAO;
import DTO.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClienteBLL {

    public static List<Cliente> list(String filter) {
        return ClienteDAO.list(filter);
    }

    public static Cliente find(int id) {
        return ClienteDAO.find(id);
    }

    public static boolean add(Cliente objeto) {
        return ClienteDAO.save(objeto);
    }

    public static boolean edit(Cliente objeto) {
        return ClienteDAO.update(objeto);
    }

    public static boolean delete(int id) {
        return ClienteDAO.delete(id);
    }

    public static DefaultTableModel createTable(DefaultTableModel tabla, List<Cliente> data) {
        tabla = clearTable(tabla);
        for (Cliente item : data) {
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
