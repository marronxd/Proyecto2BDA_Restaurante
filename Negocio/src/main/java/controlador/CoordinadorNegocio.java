/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Frame;
import dtos.ClienteDTO;
import dtos.IngredienteDTO;
import excepciones.NegocioException;
import java.util.List;
import objetosnegocio.ClienteBO;
import objetosnegocio.IngredienteBO;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.UnidadMedida;

/**
 * Clase coordinadora de todos los eventos que requieran de una lógica interna acorde al negocio
 * Se encarga de maanejar las funciones operativas
 * @author aaron
 */
public class CoordinadorNegocio {
    
    private final ClienteBO cliente;
    private final IngredienteBO ingrediente;
    
    public CoordinadorNegocio() {
        this.cliente = ClienteBO.getInstance();
        this.ingrediente = IngredienteBO.obtenerInstancia();
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
        return cliente.actualizarCliente(clienteModificar);
    }
    
    // --- Ingrediente ---
    /**
     * Método para filtrar ingredientes 
     * @param texto que se busca filtrar
     * @param tipoFiltro como se va a filtrar
     * @return
     * @throws NegocioException 
     */
    public List<IngredienteDTO> filtrarIngrediente(String texto, String tipoFiltro) throws NegocioException{
        return ingrediente.buscarIngredientes(texto, tipoFiltro);
    }
    
    /**
     * Método que llama al bo para eliminar un ingrediente en concreto
     * @param id
     * @throws NegocioException 
     */
    public void eliminarIngrediente(Long id) throws NegocioException{
        ingrediente.eliminarIngrediente(id);
    }
    
    public void registrarIngrediente(String nombre, Double cantidad, UnidadMedida unidad, EstadoIngrediente estado, String url, byte[] imagen) throws NegocioException{
        IngredienteDTO  ingredienteDTO = new IngredienteDTO();
        ingredienteDTO.setNombre(nombre);
        ingredienteDTO.setCantidad_stock(cantidad);
        ingredienteDTO.setUnidad_medida(unidad.name());
        ingredienteDTO.setEstado(estado.name());
        ingredienteDTO.setUrl(url);
        ingredienteDTO.setImagen(imagen);
        System.out.println("url: " + url + "i: " + imagen);
        ingrediente.registrarIngrediente(ingredienteDTO);
    }
    
    public IngredienteDTO obtenerIngrediente(Long id) throws NegocioException{
        return ingrediente.obtenerIngrediente(id);
    }
    
    /**
     * Método para modificar un ingrediente ya existente con datos nuevos
     * @param id
     * @param nombre
     * @param cantidad
     * @param unidad
     * @param estado
     * @param url
     * @param imagen
     * @return 
     */
    public IngredienteDTO modificarIngrediente(Long id, String nombre, Double cantidad, UnidadMedida unidad, EstadoIngrediente estado, String url, byte[] imagen) throws NegocioException{
        // crear objeto
        IngredienteDTO  ingredienteDTO = new IngredienteDTO();
        // setear valores
        ingredienteDTO.setId(id);
        ingredienteDTO.setNombre(nombre);
        ingredienteDTO.setCantidad_stock(cantidad);
        ingredienteDTO.setUnidad_medida(unidad.name());
        ingredienteDTO.setEstado(estado.name());
        ingredienteDTO.setUrl(url);
        ingredienteDTO.setImagen(imagen);
        return ingrediente.actualizarIngrediente(ingredienteDTO);
    }
    
}
