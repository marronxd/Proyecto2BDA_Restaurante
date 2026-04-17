
package daos;
import conexion.ConexionBD;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 * Clase dao que se encarga de la conexion con la base de datos.
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
     * guardar un cliente en la BD
     * @param cliente a guardad
     * @return cliente guardado
     */
    public void Eliminar(Cliente cliente){
        
        EntityManager em = ConexionBD.crearConexion();
        
        try {
            em.getTransaction().begin();
            em.find(Cliente.class, cliente);
             Cliente clienteGestionado = em.find(Cliente.class, cliente.getId());

            if (clienteGestionado != null) {
                em.remove(clienteGestionado);
            }
            em.getTransaction().commit(); 
            
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
     * busca a todos los clientes, sin filtros
     * @return 
     */
    public List<Cliente> obtenerTodos() {
        
        EntityManager em = ConexionBD.crearConexion();
    
        try {
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
        
        return clientes;
        } catch (Exception e) {
            return null;
        } finally {
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
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.nombres like :nombre", Cliente.class)
                           .setParameter("nombre", "%" + nom + "%")
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
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.telefono like :telefono", Cliente.class)
                           .setParameter("telefono", "%"+  tel+"%")
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
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.correo like :correo", Cliente.class)
                           .setParameter("correo", "%" +corr + "%")
                           .getResultList();
            return clientes;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Aaron
     * Método que devuelve un cliente en base a un id
     * @param id el id del cliente frecuente
     * @return 
     */
    public ClienteFrecuente obtenerCliente(Long idBuscado) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
        try{
           String sentenciaJPQL = """
                                  Select cf from ClienteFrecuente cf where 
                                  cf.id = :id
                                  """;
            TypedQuery<ClienteFrecuente> query = em.createQuery(sentenciaJPQL, ClienteFrecuente.class);
            query.setParameter("id", idBuscado);
            ClienteFrecuente clienteBuscado = query.getSingleResult();
            return clienteBuscado;
        }catch(Exception e){
            throw new PersistenciaException("Cliente no registrado en la base de datos");
        }finally{
            em.close();
        }
    }
    
    /**
     * aaron
     * Método para actualizar un registro de cliente frecuente
     */
    public Cliente actualizarCliente(Cliente cliente) throws PersistenciaException{
        EntityManager em = ConexionBD.crearConexion();
        try {
           em.getTransaction().begin();
        
            // Esta línea hace toda la chamba:
            // 1. Busca si el ID existe.
            // 2. Compara qué cambió.
            // 3. Ejecuta el SQL UPDATE necesario.
            Cliente actualizado = em.merge(cliente); 

            em.getTransaction().commit();
            return actualizado;
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error de busqueda: " + e.getMessage());
        }finally{
            em.close();
        }
    }
}
