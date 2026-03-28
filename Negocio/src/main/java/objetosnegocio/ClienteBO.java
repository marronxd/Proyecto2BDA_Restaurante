/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio;

import adaptadores.ClienteAdapter;
import daos.ClienteDAO;
import dtos.ClienteDTO;
import entidades.Cliente;
import java.util.List;

/**
 *
 * @author luiscarlosbeltran
 */
public class ClienteBO {
    
    //hecho como singleton porque aviso la profe
    private static ClienteBO instancia;

    private final ClienteDAO clienteDAO;

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
}
