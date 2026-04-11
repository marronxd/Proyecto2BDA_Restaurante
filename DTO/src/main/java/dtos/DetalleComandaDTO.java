/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;


/**
 *
 * @author luiscarlosbeltran
 */
public class DetalleComandaDTO {
    private Long id;
    private Integer cantidadProducto;
    private String comentarios;
    private Double precio;
    private Double subtotal;
    
    
    //relacion con producto
    private Long idProducto;
    
    //constructor vacio
    public DetalleComandaDTO() {
    }

    //constructor con todo
    public DetalleComandaDTO(Long id, Integer cantidadProducto, String comentarios, Double precio, Double subtotal, Long idProducto) {
        this.id = id;
        this.cantidadProducto = cantidadProducto;
        this.comentarios = comentarios;
        this.precio = precio;
        this.subtotal = subtotal;
        this.idProducto = idProducto;
    }
    
    //constructor sin el id
    public DetalleComandaDTO(Integer cantidadProducto, String comentarios, Double precio, Double subtotal, Long idProducto) {
        this.cantidadProducto = cantidadProducto;
        this.comentarios = comentarios;
        this.precio = precio;
        this.subtotal = subtotal;
        this.idProducto = idProducto;
    }
    
    //get y set
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
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

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    
    
}
