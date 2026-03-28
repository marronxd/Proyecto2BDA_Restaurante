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
    
    private final ClienteBO cliente;

    public CoordinadorNegocio() {
        this.cliente = ClienteBO.getInstance();
    }
    
    public List<ClienteDTO> filtraeClientes(String texto, String tipo){
        if(texto == null || texto.isEmpty()){
            return cliente.obtenerTodos();
        }
        return switch(tipo){
            case "Nombre" -> cliente.buscarPorNombre(texto);
            case "Numero" -> cliente.buscarPorTelefono(texto);
            case "Correo" -> cliente.buscarPorCorreo(texto);
            default -> cliente.obtenerTodos();
        };
    }
    
    public ClienteDTO registrarCliente(ClienteDTO clienteAgregar){
        return cliente.registrarCliente(clienteAgregar);
    }
}
