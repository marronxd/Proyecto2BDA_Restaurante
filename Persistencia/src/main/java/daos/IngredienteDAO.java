/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;
import conexion.ConexionBD;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import tiposDatosEnums.UnidadMedida;
/**
 * Dao que hace conexion de la base de datos
 * @author aaron
 */
public class IngredienteDAO {
    
    // --- Metodo para filtrar ingredientes ---
    
    /**
     * Método que hace la busqueda por filtros
     * @param UnidadFiltro
     * @return 
     */
    public List<Ingrediente> obtenerIngredientesFiltros(
            String nombreFiltro,
            UnidadMedida unidadFiltro) throws PersistenciaException{
        // establecer entityManager
        EntityManager em = ConexionBD.crearConexion();
        
       
        try{
             //Crear un criteria builder
            CriteriaBuilder cb = em.getCriteriaBuilder();
            //crear cquery
            CriteriaQuery<Ingrediente> query = cb.createQuery(Ingrediente.class);
            
            // root es el objeto que manejo (tambien llamado raiz), o sea, ingrediente 
            
            Root<Ingrediente> objeto = query.from(Ingrediente.class);
            
            // predicate son como las reglas, en este caso los filtros que se deben cumplir
            List<Predicate>  filtros = new ArrayList<>();
            
            // --- se empieza a filtrar ---
            
            // validaciones si eligieron filtrar nombre
            if(nombreFiltro != null || !nombreFiltro.isEmpty()){
                // obtenemos el nombre de la variable raiz, en este caso es "nombre"
                filtros.add(cb.like(objeto.get("nombre"), "%" + nombreFiltro + "%"));
            }
            
            // validacion si eligieron filtrar por unidad
            if(unidadFiltro != null){
                filtros.add(cb.equal(objeto.get("unidad_medida"), unidadFiltro));
            }
            
            // si eligieron todo, soloamente se filtra todo
            query.where(filtros.toArray(new Predicate[0]));
                
            // creamos la sentencia, ejecutamos y obtenemos resultado
            return em.createQuery(query).getResultList();
        }catch(Exception e){
            throw new PersistenciaException("Error al obtener listado de registros.");
        }finally{
            em.close();
        }
    }
    
    // --- Metodos CRUD ---
    
    /**
     * Metodo que registra un ingrediente en la base de datos
     * @param ingrediente  es el ingrediente que se quiere registrar
     * @return el ingrediente registrado
     * @throws PersistenciaException 
     */
    public Ingrediente registrarIngrediente(Ingrediente ingrediente) throws PersistenciaException{
        if(ingrediente == null){
            throw new PersistenciaException("Ingrediente registrado no puede ser null");
        }
        EntityManager em = ConexionBD.crearConexion();
      
        try{
  
            // empezar transaccion
            em.getTransaction().begin();
            
            em.persist(ingrediente);
            
            em.getTransaction().commit();
            return ingrediente;
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar el ingrediente en la base de datos :"+ e.getMessage());
        }finally{
            em.close();
        }
    }
    
    /**
     * Método para eliminar un ingrediente de la base de datos
     * @param idEliminar que se busca
     * @return el ingrediente que se eliminó
     * @throws PersistenciaException 
     */
    public Ingrediente eliminarIngrediente(Long idEliminar) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
      
        try{
  
            // empezar transaccion
            em.getTransaction().begin();
            
            // verificar existencia
            Ingrediente eliminado = em.find(Ingrediente.class, idEliminar);
            if(eliminado == null){
                throw new Exception("El ingrediente que intenta eliminar, no existe");
            }
            
            em.remove(eliminado);
            // por si solo ya verifica que no esté siendo usado en un producto
            em.getTransaction().commit();
            return eliminado;
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar. No puede borrarse in ingrediente usado en un producto activo. "+ e.getMessage());
        }finally{
            em.close();
        }
    }
    
    /**
     * Método para obtener un ingrediente con un id específico
     * @param id el id recibido
     * @return el ingrediente buscado
     * @throws PersistenciaException 
     */
    public Ingrediente obtenerIngredientePorID(Long id) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
      
        try{
            // meter en contexto
            Ingrediente buscado = em.find(Ingrediente.class, id);
            return buscado;
        }catch(Exception e){
            throw new PersistenciaException("Error al obtener el ingrediente en la BD: " + e.getMessage());
        }finally{
            em.close();
        }
    } 
    
    /**
     * Método para actualizar un ingrediente existente
     * @param ingredienteActualizado ingrediente con los cambios nuevos
     * @return regresa el ingrediente con esos cambios
     * @throws PersistenciaException 
     */
    public Ingrediente actualizarIngrediente(Ingrediente ingredienteActualizado) throws PersistenciaException{
        
        EntityManager em = ConexionBD.crearConexion();
        try{
            // entablar conexion
            em.getTransaction().begin();
            
            // se hace toda la chamba
            Ingrediente actualizado = em.merge(ingredienteActualizado);
            
            // mando el cambio
            em.getTransaction().commit();
            return actualizado;
            
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar al ingrediente: " + e.getMessage());
        }finally{
            em.close();
        }
    }

}
