/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Frame;
import dtos.ClienteDTO;
import excepciones.NegocioException;
import java.util.List;
import objetosnegocio.ClienteBO;

/**
 * Clase coordinadora de todos los eventos que requieran de una lógica interna acorde al negocio
 * Se encarga de maanejar las funciones operativas
 * @author aaron
 */
public class CoordinadorNegocio {
    
    private final ClienteBO cliente;
    
    public CoordinadorNegocio() {
        this.cliente = ClienteBO.getInstance();
    }
    
    /**
     * Método que decide qué tipo de filtro regresar al usuario
     * @param texto
     * @param tipo
     * @return 
     */
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
    
    /**
     * Método que registra clientes en la base de datos
     * @param clienteAgregar
     * @return 
     */
    public ClienteDTO registrarCliente(ClienteDTO clienteAgregar){
        return cliente.registrarCliente(clienteAgregar);
    }
    
    /**
     * Método que regresa un cliente en base al id
     * @param id
     * @return
     * @throws NegocioException 
     */
    public ClienteDTO obtenerCliente(Long id) throws NegocioException{
        return cliente.buscarClienteId(id);
    }
    
    public ClienteDTO modificarClienteFrecuente(ClienteDTO clienteModificar) throws NegocioException{
        return cliente.ActualizarCliente(clienteModificar);
    }
}
