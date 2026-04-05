/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author aaron
 */
@Entity
public class DetalleProducto implements Serializable{
    
    // --- Atributos base de la tabla intermedia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    
    // ---- almacena relacion con INGREDIENTE y PRODUCTO
    
    @ManyToOne
    @JoinColumn(name = "id_ingrediente")
    private Ingrediente ingrediente;
    
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
    
    
    // --- Constructores ---

    /**
     * Constructor vacío
     */
    public DetalleProducto() {
    }

    /**
     * Constructor con todo
     * @param cantidad
     * @param ingrediente
     * @param producto 
     */
    public DetalleProducto(Double cantidad, Ingrediente ingrediente, Producto producto) {
        this.cantidad = cantidad;
        this.ingrediente = ingrediente;
        this.producto = producto;
    }
    
    // --- getter y setters --- 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
