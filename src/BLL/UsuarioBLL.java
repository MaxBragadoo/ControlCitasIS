package BLL;

import DAO.UsuarioDAO;
import DTO.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioBLL {

    public static List<Usuario> list(String filter) {
        return UsuarioDAO.list(filter);
    }

    public static Usuario find(int id) {
        return UsuarioDAO.find(id);
    }

    public static boolean add(Usuario objeto) {
        return UsuarioDAO.save(objeto);
    }

    public static boolean edit(Usuario objeto) {
        return UsuarioDAO.update(objeto);
    }

    public static boolean delete(int id) {
        return UsuarioDAO.delete(id);
    }

    public static Usuario login(String username, String password) {
        return UsuarioDAO.login(username, password);
    }

    public static Usuario createDefault() {
        return UsuarioDAO.createDefault();
    }

    public static DefaultTableModel createTable(DefaultTableModel tabla, List<Usuario> data) {
        tabla = clearTable(tabla);
        for (Usuario item : data) {
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
