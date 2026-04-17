/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.io.Serializable;

/**
 * Es rl que almacena la cantidad de ingredientes que tiene un producto
 * @author aaron
 */
public class DetalleProductoDTO implements Serializable{
    
    private Long idIngrediente;      
    private String nombreIngrediente; 
    private Double cantidad;          
    private String unidadMedida;     

    public DetalleProductoDTO() {
    }


    public DetalleProductoDTO(Long idIngrediente, String nombreIngrediente, Double cantidad, String unidadMedida) {
        this.idIngrediente = idIngrediente;
        this.nombreIngrediente = nombreIngrediente;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    public DetalleProductoDTO(String nombreIngrediente, Double cantidad, String unidadMedida) {
        this.nombreIngrediente = nombreIngrediente;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    
    // --- Getters y Setters ---

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
