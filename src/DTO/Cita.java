package DTO;

import Mensajes.Utils;
import java.util.Date;

public class Cita {

    private int idCita;
    private Date fechaHora;
    private String descripcion;
    private String estatus;
    private Date fechaAlta;
    private Vehiculo vehiculo;
    private Usuario usuario;

    public Cita() {
        this.fechaHora = new Date();
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Object[] toObject() {
        return new Object[]{idCita, Utils.SDF_TIME.format(fechaHora), vehiculo.getCliente().getCodigoCliente(), 
            vehiculo.getVin(), vehiculo.getPlaca(), usuario.toInfo(), 
            descripcion, estatus, Utils.SDF.format(fechaAlta)};
    }

    public String toInfo() {
        if (this == null) {
            return "";
        }
        return "[" + idCita + "]" + Utils.SDF.format(fechaAlta);
    }

    @Override
    public String toString() {
        return toInfo();
    }

}
