package DTO;

import Mensajes.Utils;
import java.util.Date;

public class Cliente {

    private int idCliente;
    private String codigoCliente;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String telefono;
    private String estatus;
    private Date fechaAlta;

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        return new Object[]{idCliente, codigoCliente, nombre,
            apellidoP, apellidoM, correo, telefono, estatus, Utils.SDF.format(fechaAlta)};
    }

    public String toInfo() {
        if (this == null) {
            return "";
        }
        return nombre + " " + apellidoP + " " + apellidoM;
    }

    @Override
    public String toString() {
        return "[" + codigoCliente + "] " + toInfo();
    }

}
