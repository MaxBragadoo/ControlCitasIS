package DTO;

import Mensajes.Utils;
import java.util.Date;

public class Permiso {

    private int idPermiso;
    private String codigoPermiso;
    private String nombre;
    private String estatus;
    private String descripcion;
    private Date fechaAlta;

    public Permiso() {
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getCodigoPermiso() {
        return codigoPermiso;
    }

    public void setCodigoPermiso(String codigoPermiso) {
        this.codigoPermiso = codigoPermiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Object[] toObject() {
        return new Object[]{idPermiso, codigoPermiso, nombre,
            descripcion, estatus, Utils.SDF.format(fechaAlta)};
    }

    public Object[] toObjectRow() {
        return new Object[]{idPermiso, false, codigoPermiso, nombre,
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
        return "[" + codigoPermiso + "] " + toInfo();
    }

}
