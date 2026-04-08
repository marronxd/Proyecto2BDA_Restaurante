/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dtos.IngredienteDTO;
import entidades.Ingrediente;
import java.util.ArrayList;
import java.util.List;
import tiposDatosEnums.*;
/**
 * Clase cuya chamba principal es la traducción de tipos de dato
 * @author aaron
 */
public class IngredienteAdapter {
    
    /**
     * Método para convertir de entidad a dto
     * @param ingrediente la entidad a convertir
     * @return 
     */
    public static IngredienteDTO convertirEntidadADTO(Ingrediente ingrediente){
        // crear objeto dto
        IngredienteDTO ingredienteDTO = new IngredienteDTO();
        
        // --- sacar valores de la entidad y ponerlos en el objeto nuevo
    
        ingredienteDTO.setId(ingrediente.getId());
        ingredienteDTO.setNombre(ingrediente.getNombre());
        ingredienteDTO.setCantidad_stock(ingrediente.getCantidad_stock());
        ingredienteDTO.setUnidad_medida(ingrediente.getUnidad_medida().name());
        ingredienteDTO.setEstado(ingrediente.getEstado().name());
        ingredienteDTO.setUrl(ingrediente.getUrl_imagen());
        ingredienteDTO.setImagen(ingrediente.getImagen());
        
        // --- regresar objeto dto construido ---
        return ingredienteDTO;
    }
    
    /**
     * Método para transformar un dto a entidad
     * @param ingredienteDTO
     * @return 
     */
    public static Ingrediente convertirDTOAEntidad(IngredienteDTO ingredienteDTO){
        
        // --- crear objeto ---
        Ingrediente ingrediente = new Ingrediente();
        
        // --- setear valores ---
        
        // porque el id puede no estar si no ha sido registrado el ingrediente
        if(ingredienteDTO.getId() != null || !ingredienteDTO.getUrl().isEmpty()){
            ingrediente.setId(ingredienteDTO.getId());
        }
        
        ingrediente.setNombre(ingredienteDTO.getNombre());
        // --- cambiar de string a unidad de medida nuevamente
        ingrediente.setUnidad_medida(UnidadMedida.valueOf(ingredienteDTO.getUnidad_medida()));
        ingrediente.setCantidad_stock(ingredienteDTO.getCantidad_stock());
        // --- cambiar de string a estado
        ingrediente.setEstado(EstadoIngrediente.valueOf(ingredienteDTO.getEstado()));
        
        // porque el url es opcional, puede ser null
        if(ingredienteDTO.getUrl() != null){
            if(!ingredienteDTO.getUrl().isEmpty()){
                 ingrediente.setUrl_imagen(ingredienteDTO.getUrl());
            }
        }
        if(ingredienteDTO.getImagen() != null){
            ingrediente.setImagen(ingredienteDTO.getImagen());
        }
        return ingrediente;
    }
    
    /**
     * Método para convertir una lista de entidades a lista de dtos
     * @param ingredientes la lista que se extrae de la base de datos
     * @return 
     */
    public static List<IngredienteDTO> convertirListaEntidadADTO(List<Ingrediente> ingredientes){
        // crear lista
        List<IngredienteDTO> lista = new ArrayList<>();
        
        if(lista == null){
            return lista;
        }
        
        // --- Convertir de entidad a dto ---
        
        for(Ingrediente ingrediente : ingredientes){
            lista.add(convertirEntidadADTO(ingrediente));
        }
        return lista;
    }
}
