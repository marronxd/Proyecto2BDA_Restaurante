/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.UnidadMedida;

/**
 * Entidad que se conecta a la base de datos, representa la tabla de los ingredientes
 * @author aaron
 */
@Entity
/**
 * Lo que hace el uniqueContraints es decir que lo que haya en la tla, tendrá reglas de exclusividad
 * es decir, que no se podra repetir y sera valor unico
 * 
 * Dentro de esa regla, le pasamos una notación que hace referencia a que de esas exclusividades
 * tendrá que seguir una lista de exclusivas, dentro de la lista paso 2 atributos de la entidad
 * de esa forma ningun registro puede tener tanto nombre como unidad de medida igual.
 */
@Table(name = "ingredientes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre", "unidad_medida"})

})
public class Ingrediente implements Serializable{
    
    @Id
    @Column(name = "id_ingrediente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "unidad_medida", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadMedida unidad_medida;
            
    @Column(name = "cantidad_stock", nullable = false)
    private Double cantidad_stock;
    
    @Column(name = "estado_ingrediente", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoIngrediente estado;
            
    @Column(name = "imagen", nullable = true)
    private String url_imagen;
    
    // --- Conexion con la tabla intermedia ---
    
    @OneToMany(mappedBy = "ingrediente") 
    private List<DetalleProducto> detalleProducto;
    
    // ------------ Constructores de la clase ----------
    
    /**
     * constructor vacio que necesita JPA
     */ 
    public Ingrediente() {
        
    }
    
    /**
     * constructor con tocho morocho
     * @param nombre
     * @param unidad_medida
     * @param cantidad_stock
     * @param estado
     * @param url_imagen 
     */
    public Ingrediente(String nombre, UnidadMedida unidad_medida, Double cantidad_stock, EstadoIngrediente estado, String url_imagen) {
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.cantidad_stock = cantidad_stock;
        this.estado = estado;
        this.url_imagen = url_imagen;
    }
    
    /**
     * Contructor sin url de la imagen
     * @param nombre
     * @param unidad_medida
     * @param cantidad_stock
     * @param estado 
     */
    public Ingrediente(String nombre, UnidadMedida unidad_medida, Double cantidad_stock, EstadoIngrediente estado) {
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.cantidad_stock = cantidad_stock;
        this.estado = estado;
    }

    
    // -- SETTERS Y GETTERS
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UnidadMedida getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(UnidadMedida unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public Double getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(Double cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public EstadoIngrediente getEstado() {
        return estado;
    }

    public void setEstado(EstadoIngrediente estado) {
        this.estado = estado;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public List<DetalleProducto> getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(List<DetalleProducto> detalleProducto) {
        this.detalleProducto = detalleProducto;
    }
    
    
    
    
    
    
    
}
