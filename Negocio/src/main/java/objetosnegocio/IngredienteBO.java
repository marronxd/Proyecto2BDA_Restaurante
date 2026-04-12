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
import java.util.ArrayList;
import java.util.List;
import observer.InventarioObserver;
import tiposDatosEnums.UnidadMedida;

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
    
    // --- patron observer, la lista ---
    private final List<InventarioObserver> observadores = new ArrayList<>();
    
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
     * Método para validar si un cliente se puede registrar o no
     * @param ingredienteDTO
     * @return 
     */
    public IngredienteDTO registrarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException{
        
        // -- validar entradas --
        validarIngrediente(ingredienteDTO);
        
        // -- Convertir de de dto a entidad
        
        Ingrediente ingrediente = IngredienteAdapter.convertirDTOAEntidad(ingredienteDTO);
        Ingrediente registrado = null;
        try{
            registrado = ingredienteDAO.registrarIngrediente(ingrediente);
            notificar();
        }catch(PersistenciaException e){
            throw new NegocioException("No se pudo registrar el ingrediente: " + e.getMessage());
        }
        return IngredienteAdapter.convertirEntidadADTO(registrado);
    }
    
    /**
     * Método para eliminar un ingrediente
     * @param id
     * @return
     * @throws NegocioException 
     */
    public IngredienteDTO eliminarIngrediente(Long id) throws NegocioException{
        
        if(id == null){
            throw new NegocioException("Id nulo.");
        }
        Ingrediente eliminar = null;
        try{
            eliminar = ingredienteDAO.eliminarIngrediente(id);
            notificar();
        }catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
        return IngredienteAdapter.convertirEntidadADTO(eliminar);
    }
    
    /**
     * Método para obtener una lista de ingredientes
     * @param nombre
     * @param unidad
     * @return
     * @throws NegocioException 
     */
    public List<IngredienteDTO> buscarIngredientes(String nombre, String tipoFiltro) throws NegocioException{
        try{
            return IngredienteAdapter.convertirListaEntidadADTO(ingredienteDAO.obtenerIngredientesFiltros(nombre, tipoFiltro));
        }catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    /**
     * Método para obtener ingrediente mediante su id
     * @param id
     * @return
     * @throws NegocioException 
     */
    public IngredienteDTO obtenerIngrediente(Long id) throws NegocioException{
        if(id == null){
            throw new NegocioException("Id vacío.");
        }
        try{
            return IngredienteAdapter.convertirEntidadADTO(ingredienteDAO.obtenerIngredientePorID(id));
        }catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    /**
     * Método para actualizar un ingrediente en concreto
     * @param ingredienteActualizado
     * @return
     * @throws NegocioException 
     */
    public IngredienteDTO actualizarIngrediente(IngredienteDTO ingredienteActualizado) throws NegocioException{
        try{
            // 1. Convertir DTO a Entidad
        Ingrediente actualizado = IngredienteAdapter.convertirDTOAEntidad(ingredienteActualizado);
        
        // 2. Guardar en la base de datos PRIMERO
        Ingrediente resultado = ingredienteDAO.actualizarIngrediente(actualizado);
        
        // 3. Notificar a los observadores DESPUÉS de persistir el cambio
        notificar(); 
        
        // 4. Retornar el DTO resultante
        return IngredienteAdapter.convertirEntidadADTO(resultado);
        }catch(PersistenciaException e){
            throw new NegocioException("Error de actualización.");
        }
    }
    
    /**
     * Método auxiliar para validar las entradas
     * @return 
     */
    private boolean validarIngrediente(IngredienteDTO ingredienteDTO) throws NegocioException{
        if(ingredienteDTO == null){
            throw new NegocioException("Usuario no valido.");
        }
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
