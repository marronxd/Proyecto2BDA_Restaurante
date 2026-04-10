/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.ConexionBD;
import entidades.Mesa;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import tiposDatosEnums.EstadoComanda;

/**
 *
 * @author luiscarlosbeltran
 */
public class MesaDAO {
    
    /**
     * metodo (C)RUD para guardar una mesa
     * @param mesa la mesa que se quiere guardar
     * @return la mesa guardada
     * @throws PersistenciaException 
     */
    public Mesa guardar(Mesa mesa) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
        
        try{
            em.getTransaction().begin();
            em.persist(mesa);
            em.getTransaction().commit();
            return mesa;
        }catch (Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo registrar la mesa: " + e.getMessage());
        }finally{
            em.close();
        }
    }
    
    /**
     * Metodo C(R)UD para consultar todas las mesas
     * @return lista de todas las mesas
     * @throws PersistenciaException 
     */
    public List<Mesa> obtenerTodas() throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
        
        try{
            List<Mesa> mesas = em.createQuery("SELECT m FROM Mesa m", Mesa.class).getResultList();
            return mesas;
        }catch(Exception e){
            throw new PersistenciaException("Error al consultar las mesas: " + e.getMessage());
        }finally{
            em.close();
        }
    }
    
    /**
     * Metodo CR(U)D para actualizar una mesa
     * @param mesa la mesa que se quiere actualizar
     * @return la mesa actualizada
     * @throws PersistenciaException 
     */
    public Mesa actualizar(Mesa mesa) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            em.getTransaction().begin();
            Mesa actualizada = em.merge(mesa);
            em.getTransaction().commit();
            return actualizada;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar mesa: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    /**
     * Metodo CRU(D) para eliminar una mesa, usa su id
     * @param id el id de la mesa a eliminar
     * @return true si se encuentra y se elimina la mesa, false en caso contrario
     * @throws PersistenciaException 
     */
    public boolean eliminarPorId(Long id) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();

        try {
            Mesa mesa = em.find(Mesa.class, id);

            if (mesa == null) {
                return false;
            }

            em.getTransaction().begin();
            em.remove(mesa);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException ("Error al eliminar mesa" + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    //ahora que lo veo quizas no es necesario este metodo porque mesa ya tiene estado enum, lo dejare en comentarios aqui por si me sirve luego ;-;
//    /**
//     * Metodo para saber si una mesa esta disponible
//     * o sea es una consulta que cuenta cuantas comandas asignadas a la mesa tienen el estado ACTIVA
//     * @param id el id de la mesa a consultarle la disponibilidad
//     * @return true si no se encuentran comandas activas, false en caso contrario
//     * @throws PersistenciaException 
//     */
//    public boolean disponible(Long id) throws PersistenciaException{
//        EntityManager em = ConexionBD.crearConexion();
//        
//        try {
//            Long comandaActiva = em.createQuery("SELECT COUNT(c) FROM Comanda c WHERE c.mesa.id = :idMesa AND c.estado = :estado", Long.class)
//                    .setParameter("idMesa", id).setParameter("estado", EstadoComanda.ABIERTA).getSingleResult();
//
//            return comandaActiva==0;
//    }catch(Exception e){
//        throw new PersistenciaException("Error al consultar la disponibilidad de la mesa: " + e.getMessage());
//    }finally{
//            em.close();
//        }
//    }
}
