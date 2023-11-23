package DTO;

import Mensajes.Utils;
import java.util.Date;

public class Usuario {

    private int idUsuario;
    private String codigoUsuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String telefono;
    private String username;
    private String password;
    private Rol rol;
    private String estatus;
    private Date fechaAlta;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Object[] toObject() {
        return new Object[]{idUsuario, codigoUsuario, nombre, apellidoP, apellidoM, telefono,
            username, rol.toInfo(), estatus, Utils.SDF.format(fechaAlta)};
    }

    public String toInfo() {
        if (this == null) {
            return "";
        }
        return nombre + " " + apellidoP + " " + apellidoM;
    }

    @Override
    public String toString() {
        return "[" + codigoUsuario + "] " + toInfo();
    }

}
