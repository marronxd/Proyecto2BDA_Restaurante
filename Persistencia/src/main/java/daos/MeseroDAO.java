/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.ConexionBD;
import entidades.Mesero;
import excepciones.PersistenciaException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author luiscarlosbeltran
 */
public class MeseroDAO {
    /**
     * metodo para registrar un mesero, solo se usa para dejar uno listo para probar el sistema
     * no es una funcionalidad del sistema real
     * @param mesero
     * @return
     * @throws PersistenciaException 
     */
    public Mesero registrar(Mesero mesero) throws PersistenciaException {

        EntityManager em = ConexionBD.crearConexion();

        try {
            em.getTransaction().begin();

            em.persist(mesero);

            em.getTransaction().commit();

            return mesero;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new PersistenciaException("No se pudo registrar el mesero: " + e.getMessage());

        } finally {
            em.close();
        }
    }
    
    /**
     * metodo para buscar mesero por codigo, se usara para saber que mesero ha iniciado sesion y asignarlo a las comandas que se registren
     * @param codigo
     * @return
     * @throws PersistenciaException 
     */
    public Mesero buscarPorCodigo(String codigo) throws PersistenciaException {
        EntityManager em = ConexionBD.crearConexion();

        try {
            return em.createQuery("SELECT m FROM Mesero m WHERE m.codigo_acceso = :codigo", Mesero.class
            ).setParameter("codigo", codigo).getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar mesero: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    
    
}
