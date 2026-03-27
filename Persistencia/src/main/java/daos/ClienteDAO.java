
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
        //creamos la conexion a la BD
        EntityManager em = ConexionBD.crearConexion();
        //
        try {
            em.getTransaction().begin();
            em.persist(cliente); //guardamos cliente
            em.getTransaction().commit(); 
            
            return cliente;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                //rollback en caso de que algo falle
            }
            throw e;
        }finally{
            em.close(); //cerramos la conexion
        }
    }
    
    public List<Cliente> buscarPorNombre(String nom){
        //creamos la conexion a la BD
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
    
    public List<Cliente> buscarPorTelefono(String tel){
        //creamos la conexion a la BD
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
    
    public List<Cliente> buscarPorCorreo(String corr){
        //creamos la conexion a la BD
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
