/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio;

import adaptadores.IngredienteAdapter;
import daos.IngredienteDAO;
import dtos.IngredienteDTO;
import entidades.Ingrediente;
import excepciones.NegocioException;
import excepciones.PersistenciaException;

/**
 * Aqui se validan todas las reglas de negocio, a su vez, como en la capa de negocio solo me
 * interesa mantener esa logica por cada objeto, utilizo singleton
 * para centralizar todas esas reglas y tener un control sobre los objetos y que a su vez
 * Dentro de la ram, mantener una sintonía en los datos
 * @author aaron
 */
public class IngredienteBO {
    
    // hecho con singleton como el luis carlos le hizo gg
    
    private static IngredienteBO objetoIngredienteBO;
    private final IngredienteDAO ingredienteDAO;
    
    // --- constructor del objeto cliente bo ---
    
    private IngredienteBO(){
        this.ingredienteDAO = new IngredienteDAO();
    }
    
    // --- constructor publico y estatico, de esta manera solo una instancia se maneja en todo
    // el sistema, es decir, un singleton
    
    public static IngredienteBO obtenerInstancia(){
        if(objetoIngredienteBO == null){
            objetoIngredienteBO = new IngredienteBO();
        }
        return objetoIngredienteBO;
    }
    
    /**
     * 
     * @param ingredienteDTO
     * @return 
     */
    public IngredienteDTO registrarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException{
        
        // -- validar entradas --
        validarIngrediente(ingredienteDTO);
        
        // -- Convertir de de dto a entidad
        
        Ingrediente ingrediente = IngredienteAdapter.convertirDTOAEntidad(ingredienteDTO);
        try{
            Ingrediente registrado = ingredienteDAO.registrarIngrediente(ingrediente);
        }catch(PersistenciaException e){
            throw new NegocioException("No se pudo registrar el ingrediente: " + e.getMessage());
        }
        return IngredienteAdapter.convertirEntidadADTO(ingrediente);
    }
    
    
    

    
    
    /**
     * Método auxiliar para validar las entradas
     * @return 
     */
    private boolean validarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException{
        
        if(ingredienteDTO.getNombre() == null){
            throw new NegocioException("Nombre es un campo obligatorio.");
        }
        
        if(ingredienteDTO.getCantidad_stock() == null){
            throw new NegocioException("Cantidad de stock no puede ser vacío");
        }
        
        if(ingredienteDTO.getCantidad_stock()<0){
            throw new NegocioException("Cantidad de stock no puede ser menor a 0.0.");
        }
        
        if(ingredienteDTO.getUnidad_medida() == null){
            throw new NegocioException("Unidad de medida no puede ser vacío");
        }
        
        if(ingredienteDTO.getEstado() == null){
            throw new NegocioException("Estado invalido.");
        }
        
        return false;
    }
}
