/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import dtos.ClienteDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import objetosnegocio.ClienteBO;
import objetosnegocio.MeseroBO;

/**
 *
 * @author luiscarlosbeltran
 */
public class CoordinadorNegocio {
    //clase que es como un intermediario entre presentacion y BO
    
    private final ClienteBO clienteBO;
    private final MeseroBO meseroBO;
    
    public CoordinadorNegocio() {
        this.clienteBO = ClienteBO.getInstance();
        this.meseroBO = MeseroBO.getInstance();
    }
    
    /**
     * llama al metodo para registrar un cliente de ClienteBO
     * nada de logica aqui ni en ningun metodo, solo llaman a BO
     * @param dto
     * @return 
     */
    public ClienteDTO registrarCliente(ClienteDTO dto) {
        return clienteBO.registrarCliente(dto);
    }
    
    /**
     * llama al metodo para obtener a todos los clientes (el de sin filtros)
     * @return 
     */
    public List<ClienteDTO> obtenerTodosClientes() {
        return clienteBO.obtenerTodos();
    }
    
    /**
     * llama al metodo para busqueda filtrada por nombre
     * @param nombre
     * @return 
     */
    public List<ClienteDTO> buscarClientePorNombre(String nombre) {
        return clienteBO.buscarPorNombre(nombre);
    }
    
    /**
     * llama al metodo para busqueda filtrada por telefono
     * @param telefono
     * @return 
     */
    public List<ClienteDTO> buscarClientePorTelefono(String telefono) {
        return clienteBO.buscarPorTelefono(telefono);
    }
    
    /**
     * llama al metodo para busqueda filtrada por correo
     * @param correo
     * @return 
     */
    public List<ClienteDTO> buscarClientePorCorreo(String correo) {
        return clienteBO.buscarPorCorreo(correo);
    }
    
    
    public void iniciarSesionMesero(String codigo) throws PersistenciaException, NegocioException{
        meseroBO.iniciarSesion(codigo);
    }
    
}
