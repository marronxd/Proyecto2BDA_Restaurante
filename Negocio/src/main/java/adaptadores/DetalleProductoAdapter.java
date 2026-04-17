/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;
import dtos.DetalleProductoDTO;
import entidades.DetalleProducto;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase cuya chamba principal es la traducción de tipos de dato para los detalles (recetas)
 * 
 * Puede cambiar todo esto cuando se programe producto, dependientdo la encesidad
 * @author aaron
 */
public class DetalleProductoAdapter {

    /**
     * Método para convertir de entidad a dto
     * @param detalle la entidad de la base de datos
     * @return objeto DTO para la vista
     */
    public static DetalleProductoDTO convertirEntidadADTO(DetalleProducto detalle) {
        DetalleProductoDTO dto = new DetalleProductoDTO();

        // Extraemos datos de la entidad y de su relación con Ingrediente
        dto.setIdIngrediente(detalle.getIngrediente().getId());
        dto.setNombreIngrediente(detalle.getIngrediente().getNombre());
        dto.setCantidad(detalle.getCantidad());
        // Convertimos el Enum de la unidad de medida a String
        dto.setUnidadMedida(detalle.getIngrediente().getUnidad_medida().name());

        return dto;
    }

    /**
     * Método para transformar un dto a entidad
     * Nota: Para que funcione al 100%, el BO debería buscar el objeto 
     * Producto e Ingrediente real antes de persistir.
     * @param dto
     * @return 
     */
    public static DetalleProducto convertirDTOAEntidad(DetalleProductoDTO dto) {
        DetalleProducto detalle = new DetalleProducto();
        
        // Setear la cantidad
        detalle.setCantidad(dto.getCantidad());
        
        // puede cambiar cuando se programe producto gg
        // Nota: El ID del detalle suele ser autoincrementable, 
        // pero vinculamos el ID del ingrediente para que el BO sepa cuál es.
        // detalle.setIngrediente(ingredienteEncontradoPorID); 
        
        return detalle;
    }

    /**
     * Método para convertir una lista de entidades a lista de dtos
     * @param detalles la lista de la receta (ingredientes por producto)
     * @return 
     */
    public static List<DetalleProductoDTO> convertirListaEntidadADTO(List<DetalleProducto> detalles) {
        List<DetalleProductoDTO> listaDTOs = new ArrayList<>();
        
        if (detalles == null) {
            return listaDTOs;
        }

        for (DetalleProducto detalle : detalles) {
            listaDTOs.add(convertirEntidadADTO(detalle));
        }
        
        return listaDTOs;
    }
}
