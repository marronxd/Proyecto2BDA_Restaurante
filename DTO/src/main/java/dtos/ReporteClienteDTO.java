/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDate;

/**
 *
 * @author luiscarlosbeltran
 */
public class ReporteClienteDTO {
    //atributos
    private String nombre;
    private Double totalGastado;
    private Double puntos;
    private LocalDate fechaRegistro;

    //constructor con todos
    public ReporteClienteDTO(String nombre, Double totalGastado, Double puntos, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
        this.fechaRegistro = fechaRegistro;
    }
    
    //get y set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public Double getPuntos() {
        return puntos;
    }

    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
