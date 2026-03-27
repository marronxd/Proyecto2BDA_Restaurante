
package daos;
import conexion.ConexionBD;
import entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
/**
 *
 * @author piña
 */
public class ClienteDAO {
    
    /**
     * guardar un cliente en la BD
     * @param cliente a guardad
     * @return cliente guardado
     */
    public Cliente guardar(Cliente cliente){
        
        EntityManager em = ConexionBD.crearConexion();
        
        try {
            em.getTransaction().begin();
            em.persist(cliente); //guardamos cliente
            em.getTransaction().commit(); 
            
            return cliente;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                
            }
            throw e;
        }finally{
            em.close(); 
        }
    }
    
    /**
     * buscar un cliente por nombre
     * @param nom nombre a buscar en los reguitros
     * @return en caso de encontrara coincidencias un cliente en caso contrario null
     */
    public List<Cliente> buscarPorNombre(String nom){
        
        EntityManager em = ConexionBD.crearConexion();
        try {
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.nombres = :nombre", Cliente.class)
                           .setParameter("nombre", nom)
                           .getResultList();
            return clientes;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * buscar un cliente por telefono
     * @param tel telefono a buscar en los reguistros
     * @return en caso de encontrara coincidencias un cliente en caso contrario null
     */
    public List<Cliente> buscarPorTelefono(String tel){
        
        EntityManager em = ConexionBD.crearConexion();
        try {
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class)
                           .setParameter("telefono", tel)
                           .getResultList();
            return clientes;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    /**
     * buscar un cliente por correo
     * @param corr correo a buscar en los reguistros
     * @return en caso de encontrara coincidencias un cliente en caso contrario null
     */
    public List<Cliente> buscarPorCorreo(String corr){
        
        EntityManager em = ConexionBD.crearConexion();
        try {
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.correo = :correo", Cliente.class)
                           .setParameter("correo", corr)
                           .getResultList();
            return clientes;
        } catch (Exception e) {
            return null;
        }
    }
}
