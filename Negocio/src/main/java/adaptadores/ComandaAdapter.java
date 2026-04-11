/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dtos.ComandaDTO;
import entidades.Cliente;
import entidades.Comanda;
import entidades.DetalleComanda;
import entidades.Mesa;
import entidades.Mesero;
import java.util.ArrayList;
import java.util.List;
import tiposDatosEnums.EstadoComanda;

/**
 *
 * @author luiscarlosbeltran
 */
public class ComandaAdapter {
    /**
     * metodo que convierte una entity Comanda a su dto
     * @param c
     * @return 
     */
    public static ComandaDTO ComandaADTO(Comanda c) {
        ComandaDTO dto = new ComandaDTO();

        dto.setId(c.getId());
        dto.setEstado(c.getEstado().name());
        dto.setFolio(c.getFolio());
        dto.setFechaHoraCreacion(c.getFechaHora_Creacion());
        dto.setTotalAcumulado(c.getTotal_acumulado());

        //mete la mesa
        dto.setIdMesa(c.getMesa().getId());
        
        //mete al cliente
        if (c.getCliente() != null) {
            dto.setIdCliente(c.getCliente().getId());
        }

        //mete los detalles usando el otro adapter que puse (DetalleComandaAdapter)
        dto.setDetalles(DetalleComandaAdapter.DetalleComandaAListaDTO(c.getDetalles()));

        //mete el mesero
        dto.setIdMesero(c.getMesero().getId());
        return dto;
    }
    
    /**
     * metodo que convierte una lista de entity Comanda a una lista de su dto
     * @param comandas
     * @return 
     */
    public static List<ComandaDTO> listaComandaAListaDTO(List<Comanda> comandas) {
        List<ComandaDTO> lista = new ArrayList<>();

        for (Comanda c : comandas) {
            lista.add(ComandaADTO(c));
        }
        return lista;
    }
    
    
    public static Comanda DTOAEntity(ComandaDTO dto) {
        Comanda c = new Comanda();

        if (dto.getId() != null) {
            c.setId(dto.getId());
        }

        c.setEstado(EstadoComanda.valueOf(dto.getEstado()));
        c.setFolio(dto.getFolio());
        c.setFechaHora_Creacion(dto.getFechaHoraCreacion());
        c.setTotal_acumulado(dto.getTotalAcumulado());

        //pone la mesa, agarra el id de la que venia en el dto
        Mesa m = new Mesa();
        m.setId(dto.getIdMesa());
        c.setMesa(m);  

        //cliente (puede ser null)
        if (dto.getIdCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getIdCliente());
            c.setCliente(cliente);
        }

        //detalles
        List<DetalleComanda> detalles = DetalleComandaAdapter.listaDTOAListaEntity(dto.getDetalles());

        //pone la relacion con cada detallecomanda asociado a la comanda
        for (DetalleComanda d : detalles) {
            d.setComanda(c);
        }
        c.setDetalles(detalles);
        
        //mesero
        Mesero mesero = new Mesero();
        mesero.setId(dto.getIdMesero());
        c.setMesero(mesero);

        return c;
    }
}
