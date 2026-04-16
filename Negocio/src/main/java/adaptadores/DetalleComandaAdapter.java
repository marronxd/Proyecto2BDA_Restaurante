/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dtos.DetalleComandaDTO;
import entidades.DetalleComanda;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiscarlosbeltran
 */
public class DetalleComandaAdapter {
    
    /**
     * metodo que convierte un entity DetalleComanda a DetalleComandaDTO
     * @param d
     * @return 
     */
    public static DetalleComandaDTO DetalleComandaADTO(DetalleComanda d){
        DetalleComandaDTO dto = new DetalleComandaDTO();
        
        dto.setId(d.getId());
        dto.setCantidadProducto(d.getCantidad_producto());
        dto.setComentarios(d.getComentarios());
        dto.setPrecio(d.getPrecio());
        dto.setSubtotal(d.getSubtotal());
        dto.setIdProducto(d.getProducto().getId());
        
        return dto;
    }
    
    /**
     * metodo que convierte una lista de DetalleComanda a una lista de DetalleComandaDTO
     * @param detalles
     * @return 
     */
    public static List<DetalleComandaDTO> DetalleComandaAListaDTO(List<DetalleComanda> detalles){
        List<DetalleComandaDTO> lista = new ArrayList<>();
        
        for(DetalleComanda d : detalles){
            lista.add(DetalleComandaADTO(d));
        }
        return lista;
    }
    
    /**
     * metodo que convierte DetalleComandaDTO a su entity
     * @param dto
     * @return 
     */
    public static DetalleComanda DTOAEntity(DetalleComandaDTO dto) {

        DetalleComanda d = new DetalleComanda();

        if (dto.getId() != null) {
            d.setId(dto.getId());
        }
        
        d.setPrecio(dto.getPrecio());
        d.setCantidad_producto(dto.getCantidadProducto());
        d.setComentarios(dto.getComentarios());
        d.setSubtotal(dto.getSubtotal());

        if (dto.getIdProducto() != null) {
            Producto p = new Producto();
            p.setId(dto.getIdProducto());
            d.setProducto(p);
        }

        return d;
    }
    
    /**
     * metodo que convierte una lista de DetalleComandaDTO a su una lista de s entity
     * @param dtos
     * @return 
     */
    public static List<DetalleComanda> listaDTOAListaEntity(List<DetalleComandaDTO> dtos) {
        List<DetalleComanda> lista = new ArrayList<>();

        for (DetalleComandaDTO dto : dtos) {
            lista.add(DTOAEntity(dto));
        }

        return lista;
    }
}
