package DTO;

import Mensajes.Utils;
import java.util.Date;

public class Rol {

    private int idRol;
    private String codigoRol;
    private String nombre;
    private String descripcion;
    private String estatus;
    private Date fechaAlta;

    public Rol() {
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        return new Object[]{idRol, codigoRol, nombre,
            descripcion, estatus, Utils.SDF.format(fechaAlta)};
    }

    public String toInfo() {
        if (this == null) {
            return "";
        }
        return nombre;
    }

    @Override
    public String toString() {
        return "[" + codigoRol + "] " + toInfo();
    }

}
