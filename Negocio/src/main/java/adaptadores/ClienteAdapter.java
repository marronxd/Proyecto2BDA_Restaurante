/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dtos.ClienteDTO;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import java.util.ArrayList;
import java.util.List;
import encriptador.Encriptador;
/**
 *
 * @author luiscarlosbeltran
 */
public class ClienteAdapter {
    
    /**
     * Metodo que recibe objetos Cliente (de las consultas del DAO) y 
     * los convierte a DTO
     * @param c
     * @return 
     */
    public static ClienteDTO ClienteADTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        //le asigna los atributos al dto, los saca del Cliente que devolvio el dao
        dto.setId(c.getId());
        dto.setNombres(c.getNombres());
        dto.setApellidoPaterno(c.getApellidoPaterno());
        dto.setApellidoMaterno(c.getApellidoMaterno());
        dto.setTelefono(c.getTelefono());
        dto.setCorreo(c.getCorreo());
        dto.setFechaRegistro(c.getFechaRegistro());
        //
        dto.setTipoCliente(c.getClass().getSimpleName());
        
        // --- LÓGICA DE DESENCRIPTACIÓN ---
        try {
            if (c.getTelefono() != null) {
                dto.setTelefono(Encriptador.desencriptar(c.getTelefono()));
            }
        } catch (Exception e) {
            // Si falla (ej. el dato no estaba encriptado), dejamos el original
            dto.setTelefono(c.getTelefono());
        }
        //si es un cliente frecuente se le asignan los atributos
        if (c instanceof ClienteFrecuente cf) {
            dto.setPuntos(cf.getPuntos());
            dto.setTotalGastado(cf.getTotalGastado());
        }

        return dto;
    }
    
    /**
     * guarda una lista ClienteDTO convertidos de CLiente (con el metodo de arriba)
     * regresa la consulta completa del dao, pero con ClienteDTO en vez de cliente
     * @param clientes
     * @return 
     */
    public static List<ClienteDTO> ClienteADTOLista(List<Cliente> clientes) {
        List<ClienteDTO> lista = new ArrayList<>();

        if (clientes == null){
            return lista;
        }

        for (Cliente c : clientes) {
            lista.add(ClienteADTO(c));
        }

        return lista;
    }
    
    /**
     * para convertir objetos tipo ClienteDTO a la entity Cliente, y que sea insertado
     * a la BD con un DAO, porque el dao recibe un Cliente
     * @param dto
     * @return 
     */
    public static Cliente DTOAEntity(ClienteDTO dto) {
        
        Cliente cliente = new Cliente();

        if ("ClienteFrecuente".equalsIgnoreCase(dto.getTipoCliente()) || "Frecuente".equalsIgnoreCase(dto.getTipoCliente())) {
            ClienteFrecuente cf = new ClienteFrecuente();
            cf.setPuntos(dto.getPuntos() != null ? dto.getPuntos() : 0.0);
            cf.setTotalGastado(dto.getTotalGastado() != null ? dto.getTotalGastado() : 0.0);
            cliente = cf;
        } else {
            cliente = new Cliente();
        }
        if(dto.getId() != null){
            cliente.setId(dto.getId());
        }
        cliente.setNombres(dto.getNombres());
        cliente.setApellidoPaterno(dto.getApellidoPaterno());
        cliente.setApellidoMaterno(dto.getApellidoMaterno());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());
        cliente.setFechaRegistro(dto.getFechaRegistro());

        try {
            if (dto.getTelefono() != null) {
                cliente.setTelefono(Encriptador.encriptar(dto.getTelefono()));
            }
        } catch (Exception e) {
            cliente.setTelefono(dto.getTelefono());
        }
        return cliente;
    }
    
    
    
}
