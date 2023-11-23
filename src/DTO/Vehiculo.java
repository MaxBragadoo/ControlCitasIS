package DTO;

import Mensajes.Utils;
import java.util.Date;

public class Vehiculo {

    private int idVehiculo;
    private String vin;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private String placa;
    private String reparacionPrevia;
    private String estatus;
    private Date fechaAlta;
    private Cliente cliente;

    public Vehiculo() {
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getReparacionPrevia() {
        return reparacionPrevia;
    }

    public void setReparacionPrevia(String reparacionPrevia) {
        this.reparacionPrevia = reparacionPrevia;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Object[] toObject() {
        return new Object[]{idVehiculo, vin, marca, modelo, anio, color, placa,
            cliente.toInfo(), estatus, Utils.SDF.format(fechaAlta), reparacionPrevia};
    }

    public String toInfo() {
        if (this == null) {
            return "";
        }
        return placa;
    }

    @Override
    public String toString() {
        return "[" + vin + "] " + toInfo();
    }

}
