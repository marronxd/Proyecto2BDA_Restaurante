/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author luiscarlosbeltran
 */
public class ComandaDTO {
    private Long id;
    private String estado;
    private String folio;
    private LocalDateTime fechaHoraCreacion;
    private Double totalAcumulado;
    
    //la relacion con mesa
    private Long idMesa;
    
    //la relacion con cliente, puede ser null
    private Long idCliente;
    
    //la relacion con detallecomanda, pueden ser varios y por eso es una lista
    private List<DetalleComandaDTO> detalles;

    public ComandaDTO() {
    }

    //constructor con todo
    public ComandaDTO(Long id, String estado, String folio, LocalDateTime fechaHoraCreacion, Double totalAcumulado, Long idMesa, Long idCliente, List<DetalleComandaDTO> detalles) {
        this.id = id;
        this.estado = estado;
        this.folio = folio;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.totalAcumulado = totalAcumulado;
        this.idMesa = idMesa;
        this.idCliente = idCliente;
        this.detalles = detalles;
    }
    
    //con todo menos cliente
    public ComandaDTO(Long id, String estado, String folio, LocalDateTime fechaHoraCreacion, Double totalAcumulado, Long idMesa, List<DetalleComandaDTO> detalles) {
        this.id = id;
        this.estado = estado;
        this.folio = folio;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.totalAcumulado = totalAcumulado;
        this.idMesa = idMesa;
        this.detalles = detalles;
    }
    

    //constructor con algunos detalles iniciales + cliente
    public ComandaDTO(String estado, String folio, LocalDateTime fechaHoraCreacion, Long idMesa, Long idCliente) {
        this.estado = estado;
        this.folio = folio;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.idMesa = idMesa;
        this.idCliente = idCliente;
    }
    
    //sin cliente
    public ComandaDTO(String estado, String folio, LocalDateTime fechaHoraCreacion, Long idMesa) {
        this.estado = estado;
        this.folio = folio;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.idMesa = idMesa;
    }

    //get y set
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public Double getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(Double totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }

    public Long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<DetalleComandaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComandaDTO> detalles) {
        this.detalles = detalles;
    }
    
    
    
}
