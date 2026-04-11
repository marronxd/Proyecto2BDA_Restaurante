/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import tiposDatosEnums.EstadoComanda;

/**
 *
 * @author luiscarlosbeltran
 */
@Entity
@Table(name = "comandas")
public class Comanda implements Serializable {
    
    @Id
    @Column(name = "id_comanda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoComanda estado;
    
    @Column(name = "folio", nullable = false, unique = true)
    private String folio;
    
    @Column(name = "fechaHora_creacion", nullable = false)
    private LocalDateTime fechaHora_Creacion;
    
    @Column(name = "total_acumulado", nullable = false)
    private Double total_acumulado = 0.0;
    //se calcula sumando todos los subtotales de las tablas "DetalleComanda" relacionadas con esta comanda
    //siempre empieza como 0.0
    
    
    //llave foranea a mesa
    @ManyToOne
    @JoinColumn (name = "id_mesa", nullable = false)
    private Mesa mesa;
    
    //llave foranea a cliente, puede ser null porque debe dejar no asignar cliente
    @ManyToOne
    @JoinColumn (name = "id_cliente")
    private Cliente cliente;
    
    //llave foranea a mesero
    @ManyToOne
    @JoinColumn(name = "id_mesero", nullable = false)
    private Mesero mesero;
    
    //relacion con detallecomanda
    //cascadas para casos de guardar, actualizar y eliminar
    @OneToMany(mappedBy = "comanda", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<DetalleComanda> detalles;
    
    

    //constructor vacio
    public Comanda(){
    }

    //constructor con todo, 
    public Comanda(EstadoComanda estado, String folio, LocalDateTime fechaHora_Creacion, Mesa mesa, Mesero mesero) {
        this.estado = estado;
        this.folio = folio;
        this.fechaHora_Creacion = fechaHora_Creacion;
        this.mesa = mesa;
        this.mesero = mesero;
    }
    
    //metodos get y set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFechaHora_Creacion() {
        return fechaHora_Creacion;
    }

    public void setFechaHora_Creacion(LocalDateTime fechaHora_Creacion) {
        this.fechaHora_Creacion = fechaHora_Creacion;
    }

    public Double getTotal_acumulado() {
        return total_acumulado;
    }

    public void setTotal_acumulado(Double total_acumulado) {
        this.total_acumulado = total_acumulado;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComanda> detalles) {
        this.detalles = detalles;
    }
    
    //metodo que genera automaticamente un folio
    public String generarFolio(int numero){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyyMMdd");
        
        folio = "OB-"+ LocalDateTime.now().format(formateador) +"-"+ numero;
        return folio;
    }

    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }
    
    
}
