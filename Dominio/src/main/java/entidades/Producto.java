/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import javax.annotation.processing.Generated;
import javax.persistence.*;

/**
 *
 * @author aaron
 */

@Entity
@Table(name = "Productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //atributo del nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //atributo del precio
    @Column(name = "precio", nullable = false)
    private Double precio;
    
    // porque los detalles se eliminan si el producto deja de exisitr
    @OneToMany(mappedBy = "producto",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<DetalleProducto> detalleProducto;
    public Producto() {
    }
    
    //relacion con detallecomanda
    @OneToMany(mappedBy = "producto")
    private List<DetalleComanda> detalles;
    
    //Set y get

    public List<DetalleProducto> getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(List<DetalleProducto> detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DetalleComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComanda> detalles) {
        this.detalles = detalles;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
