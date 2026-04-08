/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * Clase ingrediente que represeta un ingrediente no registrado o tambien ingredientes
 * registrados que se van a manipular antes de regresarlo
 * @author aaron
 */
public class IngredienteDTO {
    
    //atributos de ingrediente
    private Long id;
    private String nombre;
    private String unidad_medida;
    private Double cantidad_stock;
    private String estado;
    private String url;
    private byte[] imagen;

    // --- COnstructores ---
    public IngredienteDTO() {
    }
    
    /**
     * Constructor sin id ni url
     * @param nombre
     * @param unidad_medida
     * @param cantidad_stock
     * @param estado 
     */
    public IngredienteDTO(String nombre, String unidad_medida, Double cantidad_stock, String estado) {
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.cantidad_stock = cantidad_stock;
        this.estado = estado;
    }

    /**
     * Constructor con todo menos id
     * @param nombre
     * @param unidad_medida
     * @param cantidad_stock
     * @param estado
     * @param url 
     */
    public IngredienteDTO(String nombre, String unidad_medida, Double cantidad_stock, String estado, String url, byte[] imagen) {
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.cantidad_stock = cantidad_stock;
        this.estado = estado;
        this.url = url;
        this.imagen = imagen;
    }

    /**
     * constructor con todo menos url
     * @param id
     * @param nombre
     * @param unidad_medida
     * @param cantidad_stock
     * @param estado 
     */
    public IngredienteDTO(Long id, String nombre, String unidad_medida, Double cantidad_stock, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.cantidad_stock = cantidad_stock;
        this.estado = estado;
    }

    /**
     * constructor con todo
     * @param id
     * @param nombre
     * @param unidad_medida
     * @param cantidad_stock
     * @param estado
     * @param url 
     */
    public IngredienteDTO(Long id, String nombre, String unidad_medida, Double cantidad_stock, String estado, String url, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.cantidad_stock = cantidad_stock;
        this.estado = estado;
        this.url = url;
        this.imagen = imagen;
    }

    // --- getters y setters
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

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public Double getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(Double cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
