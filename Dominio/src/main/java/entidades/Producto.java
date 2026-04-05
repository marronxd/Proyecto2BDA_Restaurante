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
    
    // porque los detalles se eliminan si el producto deja de exisitr
    @OneToMany(mappedBy = "producto",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<DetalleProducto> detalleProducto;
    public Producto() {
    }

    public List<DetalleProducto> getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(List<DetalleProducto> detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
