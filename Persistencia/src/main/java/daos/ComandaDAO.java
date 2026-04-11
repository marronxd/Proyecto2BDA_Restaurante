/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.ConexionBD;
import entidades.Comanda;
import entidades.DetalleComanda;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author luiscarlosbeltran
 */
public class ComandaDAO {
    
    /**
     * Metodo para guardar una comanda, junto con sus detalles
     * @param comanda la comanda a guardar
     * @return la comanda guardada
     * @throws PersistenciaException 
     */
    public Comanda guardarComanda(Comanda comanda) throws PersistenciaException{
        
        EntityManager em = ConexionBD.crearConexion();
        
        try{
            em.getTransaction().begin();
            
            //itera sobre la lista de detalles de la comanda, establece la relacion
            //pasa a un DetalleComanda, asigna relacion con la comanda, pasa a la siguiente y repite hasta que no haya mas
            for(DetalleComanda d : comanda.getDetalles()){
                d.setComanda(comanda);
            }
            
            em.persist(comanda);
            em.getTransaction().commit();
            
            return comanda;
        }catch (Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo guardar la comanda: " + e.getMessage());
        }finally{
            em.close();
        }
    }
    
    /**
     * Metodo que consulta todas las comandas
     * como si fuera un "select * from comandas" en sql
     * @return la lista de comandas
     * @throws PersistenciaException 
     */
    public List<Comanda> obtenerTodos() throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
        
        try{
            List<Comanda> comandas = em.createQuery("SELECT c FROM Comanda c", Comanda.class).getResultList();
            return comandas;
        }catch(Exception e){
            throw new PersistenciaException("Error al consultar las comandas: " + e.getMessage());
        }finally{
            em.close();
        }
    }
    
    public Comanda buscarPorId(Long id) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
        
        try{
            return em.find(Comanda.class, id);
        }catch (Exception e){
            throw new PersistenciaException("Error al buscar comanda por id: " + e.getMessage());
        } finally{
            em.close();
        }
    }
    
    /**
     * Metodo para buscar comandas registradas entre el rango de fechas especificado
     * @param inicio fecha inicial del rango
     * @param fin fecha final del rango
     * @return la lista de comandas creadas en el rango de fechas
     * @throws PersistenciaException 
     */
    public List<Comanda> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException{
        
        EntityManager em = ConexionBD.crearConexion();
        
        try{
            List<Comanda> comandas = em.createQuery("SELECT c FROM Comanda c WHERE c.fechaHora_Creacion BETWEEN :inicio AND :fin"
                    , Comanda.class).setParameter("inicio", inicio).setParameter("fin", fin).getResultList();
            return comandas;
        }catch(Exception e){
            throw new PersistenciaException("Error al consultar las comandas por fechas: " + e.getMessage());
        }finally{
            em.close();
        }
    }
    
    /**
     * metodo para actualizar una comanda
     * @param comanda la comanda a actualizar
     * @return la comanda actualizada
     * @throws PersistenciaException 
     */
    public Comanda actualizarComanda(Comanda comanda) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
        
        try{
            em.getTransaction().begin();            
            Comanda actualizada = em.merge(comanda);
            em.getTransaction().commit();
            return actualizada;
        }catch(Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se puedo actualizar la comanda: " + e.getMessage());
        }finally{
            em.close();
        }
    }
    
    /**
     * metodo que se usa solo para calcular el numero consecutivo del folio
     * cuenta las consultas que han habido en el dia y da el numero + 1
     * @param fecha
     * @return 
     */
    public int obtenerNumSigFolio(LocalDateTime fecha) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            String fechaStr = fecha.format(
                java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd")
            );

            Long count = em.createQuery("SELECT COUNT(c) FROM Comanda c WHERE FUNCTION('DATE_FORMAT', c.fechaHora_Creacion, '%Y%m%d') = :fecha",
                Long.class).setParameter("fecha", fechaStr).getSingleResult();
            
            return count.intValue() + 1;
        }catch(Exception e){
            throw new PersistenciaException("Error al relaizar la consulta: " +e.getMessage());
        }finally {
        em.close();
        }
    }
}
