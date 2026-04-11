/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author luiscarlosbeltran
 */
@Entity
@Table(name = "detalles_comandas")
public class DetalleComanda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalleComanda")
    private Long id;
    
    @Column(name = "cantidad_producto", nullable = false)
    private Integer cantidad_producto;
    
    @Column(name = "comentarios", nullable = true)
    private String comentarios;
    
    @Column(name = "precio", nullable = false)
    private Double precio;
    
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;
    
    //relacion hacia comanda
    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;
    
    //relacion hacia producto
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    
    
    
    
    //constructor vacio
    public DetalleComanda() {
    }
    
    //constructor que calcula precio y subtotal en automatico
    public DetalleComanda(Integer cantidad_producto, String comentarios, Comanda comanda, Producto producto) {
        this.cantidad_producto = cantidad_producto;
        this.comentarios = comentarios;
        this.comanda = comanda;
        this.producto = producto;
        this.precio = producto.getPrecio();
        this.subtotal = producto.getPrecio() * cantidad_producto;
    }
    
    //get y set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad_producto() {
        return cantidad_producto;
    }

    //si se cambia la cantidad de productos, modifica el subtotal en automatico
    public void setCantidad_producto(Integer cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
        this.subtotal = this.precio * cantidad_producto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
