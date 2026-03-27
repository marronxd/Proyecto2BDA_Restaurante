/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author luiscarlosbeltran
 */
@Entity
@Table(name = "cliente_frecuente")
@DiscriminatorValue("Frecuente")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class ClienteFrecuente extends Cliente implements Serializable {
    
    //------ATRIBUTOS
    @Column(name = "puntos", nullable = false)
    private Double puntos;

    @Column(name = "total_gastado", nullable = false)
    private Double totalGastado;
    
    //---COSNTRUCTORES
    //vacio
    public ClienteFrecuente() {
        super();
    }
    
    //solo atributos de frecuente (asi vimos ejemplo con la profe pero creo que nunca se usa)
    public ClienteFrecuente(Double puntos, Double totalGastado) {
        this.puntos = puntos;
        this.totalGastado = totalGastado;
    }
    
    //con todo de todo
    public ClienteFrecuente(Double puntos, Double totalGastado, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaRegistro) {
        super(nombres, apellidoPaterno, apellidoMaterno, telefono, fechaRegistro);
        this.puntos = puntos;
        this.totalGastado = totalGastado;
    }
    
    //con todo menos correo del padre
    public ClienteFrecuente(Double puntos, Double totalGastado, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, LocalDate fechaRegistro) {
        super(nombres, apellidoPaterno, apellidoMaterno, telefono, correo, fechaRegistro);
        this.puntos = puntos;
        this.totalGastado = totalGastado;
    }
    
    //get y set

    public Double getPuntos() {
        return puntos;
    }

    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }
    
    
}
