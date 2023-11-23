package Mensajes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import javax.swing.JFrame;

public class Utils {

    public static String MESSAGE;
    public static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    private static String SECRET_KEY = "L1UKBTJ+zljnC/mM/gr8hg==";

    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Advertencia", 2);
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", 0);
    }

    public static void showExcepción(String message) {
        JOptionPane.showMessageDialog(null, "Excepción: " + message, "Excepción", 0);
    }

    public static void showExcepcion() {
        JOptionPane.showMessageDialog(null, MESSAGE, "Excepción", 0);
    }

    public static void showInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", 1);
    }

    public static String encode(String strToEncrypt) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error al encriptar: " + e.toString());
        }
        return null;
    }

    //encriptacion
    public static String decode(String strToDecrypt) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error al desencriptar: " + e.toString());
        }
        return null;
    }

    //Ventana
    public static void initFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        frame.setSize(screenWidth, frame.getHeight());
        frame.setLocationRelativeTo(null);
    }
}
