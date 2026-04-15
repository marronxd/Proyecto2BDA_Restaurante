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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author luiscarlosbeltran
 */
@Entity
@Table(name = "meseros")
@DiscriminatorValue("Mesero")
@PrimaryKeyJoinColumn(name = "id_empleado")
public class Mesero extends Empleado implements Serializable {
    
    @Column(name = "codigo_acceso", nullable = false, unique = true)
    private String codigo_acceso;

    //constructor vacio
    public Mesero() {
    }

    //con todo
    public Mesero(String codigo_acceso, Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String rfc, String telefono, LocalDate fechaRegistro) {
        super(id, nombres, apellidoPaterno, apellidoMaterno, rfc, telefono, fechaRegistro);
        this.codigo_acceso = codigo_acceso;
    }

    //todo menos id
    public Mesero(String codigo_acceso, String nombres, String apellidoPaterno, String apellidoMaterno, String rfc, String telefono, LocalDate fechaRegistro) {
        super(nombres, apellidoPaterno, apellidoMaterno, rfc, telefono, fechaRegistro);
        this.codigo_acceso = codigo_acceso;
    }

    //todo menos id, apellido materno
    public Mesero(String codigo_acceso, String nombres, String apellidoPaterno, String rfc, String telefono, LocalDate fechaRegistro) {
        super(nombres, apellidoPaterno, rfc, telefono, fechaRegistro);
        this.codigo_acceso = codigo_acceso;
    }
    
    //get y set

    public String getCodigo_acceso() {
        return codigo_acceso;
    }

    public void setCodigo_acceso(String codigo_acceso) {
        this.codigo_acceso = codigo_acceso;
    }

    
    
    
}
