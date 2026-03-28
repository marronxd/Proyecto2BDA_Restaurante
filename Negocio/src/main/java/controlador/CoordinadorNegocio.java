/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Frame;
import dtos.ClienteDTO;
import java.util.List;
import objetosnegocio.ClienteBO;

/**
 *
 * @author aaron
 */
public class CoordinadorNegocio {
    
    private ClienteBO cliente;

    public CoordinadorNegocio() {
    }
    
    public List<ClienteDTO> filtrarTodosClientes(){
        return cliente.obtenerTodos();
    }
}
