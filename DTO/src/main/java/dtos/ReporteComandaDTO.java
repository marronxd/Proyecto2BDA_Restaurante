/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDateTime;
/**
 *
 * @author aaron
 */
public class ReporteComandaDTO {
    
    private LocalDateTime fechaHora;
    private String nombreMesa;
    private Double total;
    private String estado;
    private String nombreCliente;

    public ReporteComandaDTO(LocalDateTime fechaHora, String nombreMesa, Double total, String estado, String nombreCliente) {
        this.fechaHora = fechaHora;
        this.nombreMesa = nombreMesa;
        this.total = total;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
    }

    public ReporteComandaDTO() {
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNombreMesa() {
        return nombreMesa;
    }

    public void setNombreMesa(String nombreMesa) {
        this.nombreMesa = nombreMesa;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    
}
