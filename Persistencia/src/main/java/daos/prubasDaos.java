
package daos;

import entidades.Cliente;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author piña
 */
public class prubasDaos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();
//        LocalDate hoy = LocalDate.now();
//        Cliente cliente = new Cliente("maria", "perez", "felix", "641111111", "correito@gmail",hoy);
//        dao.guardar(cliente);
//        Cliente cliente2 = new Cliente("pepe", "lopez", "torres", "123456789", "correDePepe@gmail",hoy);
//        dao.guardar(cliente2);
        
        List<Cliente> listaTel = dao.buscarPorTelefono("641111111");
        System.out.println("telefonos");
        listaTel.forEach(c -> System.out.println("Nombre: " + c.getNombres()));
        
        
        System.out.println("correos");
        List<Cliente> listaCorreo = dao.buscarPorCorreo("correDePepe@gmail");
        
        listaCorreo.forEach(c -> System.out.println("Nombre: " + c.getNombres()));

   
    }
    
}
