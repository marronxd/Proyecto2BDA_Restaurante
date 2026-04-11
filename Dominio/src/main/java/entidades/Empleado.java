/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author luiscarlosbeltran
 */
@Entity
@Table(name = "empleados")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_empleado")
public class Empleado implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;
    
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;
    
    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;
    
    @Column(name = "apellido_materno", nullable = true, length = 100)
    private String apellidoMaterno;
    
    @Column(name = "rfc", nullable = false, length = 20)
    private String rfc;
    
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;
    
    @Column(name = "fecha_registro", nullable = true)
    private LocalDate fechaRegistro;
    
    //constructor vacio
    public Empleado() {
    }

    //constructor con todo
    public Empleado(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String rfc, String telefono, LocalDate fechaRegistro) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    //todo menos id
    public Empleado(String nombres, String apellidoPaterno, String apellidoMaterno, String rfc, String telefono, LocalDate fechaRegistro) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }
    
    //todo menos id, apellido materno

    public Empleado(String nombres, String apellidoPaterno, String rfc, String telefono, LocalDate fechaRegistro) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }
    
    //get y set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
