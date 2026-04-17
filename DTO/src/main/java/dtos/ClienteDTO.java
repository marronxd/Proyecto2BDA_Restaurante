/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDate;

/**
 * Clase que se encarga de ser plantilla para los datos no registrados en la base de datos o que
 * se extraen de ella, es decir, fuera del contexto.
 * @author luiscarlosbeltran
 */

//para transportar info de clientes a interfaz
public class ClienteDTO {
    
    //atributos
    private Long id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo; //correo es opcional
    private LocalDate fechaRegistro;
    
    private String tipoCliente; //para mostrar que tipo de cliente es
    
    //atributos de frecuente
    private Double puntos;
    private Double totalGastado;
    
    //constructores

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, LocalDate fechaRegistro, String tipoCliente, Double puntos, Double totalGastado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.tipoCliente = tipoCliente;
        this.puntos = puntos;
        this.totalGastado = totalGastado;
    }
    
    //getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Double getPuntos() {
        return puntos;
    }

    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }
    
    
}
