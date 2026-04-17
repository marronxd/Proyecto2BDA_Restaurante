/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio;

import adaptadores.ClienteAdapter;
import daos.ClienteDAO;
import dtos.ClienteDTO;
import dtos.ReporteClienteDTO;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import observer.InventarioObserver;

/**
 * Se encarga de validar toda la logica de negocio. Centraliza reglas antes de siquiera acceder a 
 * la capa de persistencia.
 * @author luiscarlosbeltran
 */
public class ClienteBO {
    
    //hecho como singleton porque aviso la profe
    private static ClienteBO instancia;

    private final ClienteDAO clienteDAO;
    
    // --- patron observer, la lista ---
    private final List<InventarioObserver> observadores = new ArrayList<>();

    private ClienteBO() {
        this.clienteDAO = new ClienteDAO();
    }

    //el constructor publico y static para que sea solo una instancia en todo el sistema
    //osea el singleton
    public static ClienteBO getInstance() {
        if (instancia == null) {
            instancia = new ClienteBO();
        }
        return instancia;
    }
    
        // --- Métodos para la gestion de  los observadores
    /**
     * Este es como una subscripcion de yutu en la que una vez est´´es dentro
     * recibiras notificaciones de los cambios
     * @param observador 
     */
    public void  suscribir(InventarioObserver observador){
        this.observadores.add(observador);
    }
    
    /**
     * esta es la notificacion que se activa cada que se sube un cambio
     */
    private void notificar(){
        for(InventarioObserver obs: observadores){
            obs.actualizarInventario();
        }
    }
    /**
     * metodo para registrar un cliente.
     * recibe un clienteDTO de presentacion, lo pasa a entity con el metodo del adapter y lo manda al dao para persistir en BD
     * @param dto
     * @return 
     */
    public ClienteDTO registrarCliente(ClienteDTO dto) {
        if (dto.getNombres() == null) {
            //(podemos cambiar el tipo de excepcion despues si hacemos una personalizada)
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        
        if (dto.getApellidoPaterno() == null) {
            throw new IllegalArgumentException("El apellido paterno es obligatorio");
        }
        
        if (dto.getTelefono() == null) {
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        
        if (dto.getFechaRegistro() == null) {
            throw new IllegalArgumentException("La fecha no se esta guardando. Al crear el DTO hay que poner su fecha_registro = LocalDate.now()");
        }

        notificar();
        Cliente cliente = ClienteAdapter.DTOAEntity(dto);
        Cliente guardado = clienteDAO.guardar(cliente);

        return ClienteAdapter.ClienteADTO(guardado);
    }
    
    /**
     * busca todos losclientes, sin filtros
     * @return 
     */
    public List<ClienteDTO> obtenerTodos() {
        return ClienteAdapter.ClienteADTOLista(clienteDAO.obtenerTodos());
    }
    
    /**
     * busca clientes por el nombre dado
     * usa el metodo del adapter para convertirlos a DTO y que presentacion use la lista de DTO
     * @param nombre
     * @return 
     */
    public List<ClienteDTO> buscarPorNombre(String nombre) {
        return ClienteAdapter.ClienteADTOLista(clienteDAO.buscarPorNombre(nombre));
    }
    
    /**
     * busca clientes por telefono
     * @param telefono
     * @return 
     */
    public List<ClienteDTO> buscarPorTelefono(String telefono) {
        return ClienteAdapter.ClienteADTOLista(clienteDAO.buscarPorTelefono(telefono));
    }
    
    /**
     * busca clientes por correo
     * @param correo
     * @return 
     */
    public List<ClienteDTO> buscarPorCorreo(String correo) {
        return ClienteAdapter.ClienteADTOLista(clienteDAO.buscarPorCorreo(correo));
    }
    
    /**
     * eliminar cliente
     */
    
    /**
     * Buscar cliente. Regresa un cliente que coincida con la entrada del usuario
     * - AA
     */
    public ClienteDTO buscarClienteId(Long id) throws NegocioException {
        try{
            ClienteDTO Buscado = ClienteAdapter.ClienteADTO(clienteDAO.obtenerCliente(id));
            return Buscado;
        }catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    /**
     * 
     */
    public ClienteDTO actualizarCliente(ClienteDTO clienteActualizado) throws NegocioException{
        try{
            // sacar primero el cliente dto actual
            ClienteDTO actual = buscarClienteId(clienteActualizado.getId());
            clienteActualizado.setId(actual.getId());
            // asignarle los datos modificados
            if(clienteActualizado.getFechaRegistro() == null){
                clienteActualizado.setFechaRegistro(actual.getFechaRegistro());
            }
            
            // convertir a entidad
            Cliente actualizado = ClienteAdapter.DTOAEntity(clienteActualizado);
            notificar();
            // guardar el cleinte modificado
            Cliente guardado = clienteDAO.actualizarCliente(actualizado);
            //mandarlo al dao
            return ClienteAdapter.ClienteADTO(guardado);
            
        }catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    } 
    
    public List<ReporteClienteDTO> generarReporteClientes(String nombreFiltro) throws NegocioException{
        try {
            List<Cliente> clientes;
            
            if(nombreFiltro == null || nombreFiltro.isEmpty()){
                clientes = clienteDAO.obtenerTodos();
            } else {
            clientes = clienteDAO.buscarPorNombre(nombreFiltro);
            }
            
            List<ReporteClienteDTO> reporte = new ArrayList<>();
            for(Cliente c : clientes){
                if(c instanceof ClienteFrecuente cf){
                    String nombreCompleto = cf.getNombres() + " " + cf.getApellidoPaterno();
                    reporte.add(new ReporteClienteDTO(nombreCompleto, cf.getTotalGastado(), cf.getPuntos(), cf.getFechaRegistro()));
                }
            }
            return reporte;
        } catch(Exception e){
            throw new NegocioException("Error al generar el reporte: " + e.getMessage());
        }
    }
}
