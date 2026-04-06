/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import tiposDatosEnums.EstadoComanda;
import tiposDatosEnums.EstadoMesa;

/**
 *
 * @author luiscarlosbeltran
 */

@Entity
@Table(name = "mesas")
public class Mesa implements Serializable{
    
    @Id
    @Column(name = "id_mesa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "identificador", nullable = false, unique = true)
    private String identificador;
    //diferente al id, digamos que es el nombre de mesa (A1, A2, B1, etc)
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMesa estado;
    
    
    //parte de la relacion a comanda
    @OneToMany(mappedBy = "mesa")
    private List <Comanda> comandas;
    
    //constructor vacio
    public Mesa() {
    }
    
    //constructor con todo
    public Mesa(String identificador, EstadoMesa estado) {
        this.identificador = identificador;
        this.estado = estado;
    }
    
    //get y set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
    
    
}
