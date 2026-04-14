/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.Cliente;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author piña
 */
public class ClienteDAOTest {
    
    //Preparacion
    private ClienteDAO instance;

    public ClienteDAOTest() {
    }

//    @BeforeEach
//    public void setUp() {
//        instance = new ClienteDAO();
//    }
//    //Test de cada metodo
//    @Test
//    public void testGuardar() {
//        System.out.println("Ejecutando: guardar");
//
//        // Creamos un cliente 
//        String correoPrueba = "danna@mail.com";
//        Cliente cliente = new Cliente("Danna", "Ibarra", "Piña", "6441224266", correoPrueba, LocalDate.now());
//        Cliente result = instance.guardar(cliente);
//
//        // Verificaciones (Assertions)
//        assertNotNull(result, "El cliente guardado no debería ser nulo");
//        assertNotNull(result.getId(), "La base de datos debería haber generado un ID");
//        assertEquals("Danna", result.getNombres());
//    }
//    @Test
//    public void testBuscarPorNombre() {
//        System.out.println("Prueba Individual: buscarPorNombre");
//        String nom = "Danna"; // Nombre que ya existe en la tabñla de clientes
//
//        List<Cliente> result = instance.buscarPorNombre(nom);
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty(), "Debería encontrar al menos un registro con ese nombre");
//    }
//
//    @Test
//    public void testBuscarPorTelefono() {
//        System.out.println("Prueba Individual: buscarPorTelefono");
//        String tel = "6441224266";
//
//        List<Cliente> result = instance.buscarPorTelefono(tel);
//
//        assertNotNull(result);
//        // Verificamos que el teléfono del primer resultado sea el correcto
//        assertEquals(tel, result.get(0).getTelefono());
//    }
//
//    @Test
//    public void testBuscarPorCorreo() {
//        System.out.println("Prueba Individual: buscarPorCorreo");
//        String corr = "danna@mail.com";
//
//        List<Cliente> result = instance.buscarPorCorreo(corr);
//
//        assertNotNull(result);
//        // Verificamos que el correo del primer resultado sea el correcto
//        assertEquals(corr, result.get(0).getCorreo());
//    }

}
