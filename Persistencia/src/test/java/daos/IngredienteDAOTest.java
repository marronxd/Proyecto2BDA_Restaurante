/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.Ingrediente;
import excepciones.PersistenciaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.UnidadMedida;

/**
 *
 * @author aaron
 */
public class IngredienteDAOTest {
    
//    private IngredienteDAO intancia;
//    
//    public IngredienteDAOTest() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//        intancia = new IngredienteDAO();
//    }
//
//    /**
//     * Test of obtenerIngredientesFiltros method, of class IngredienteDAO.
//     */
//    @Test
//    public void testObtenerIngredientesFiltros() throws Exception {
//  
//        Ingrediente resultado = intancia.obtenerIngredientesFiltros("Pan frances", "Nombre").getFirst();
//        Ingrediente resultado2 = intancia.obtenerIngredientesFiltros("piez", "Unidad").getFirst();
//        
//        assertEquals("Pan frances", resultado.getNombre());
//        assertEquals("PIEZAS", resultado2.getUnidad_medida().name());
//    }
//
//    /**
//     * Test of registrarIngrediente method, of class IngredienteDAO.
//     */
//    @Test
//    public void testRegistrarIngrediente() throws Exception {
//        Ingrediente ingrediente = new Ingrediente("Pan frances", UnidadMedida.PIEZAS, 40.0, EstadoIngrediente.ACTIVO);
//  
//        Ingrediente resultado = intancia.registrarIngrediente(ingrediente);
//        
//        assertEquals(ingrediente.getNombre(), resultado.getNombre());
//    }
//
//    /**
//     * Test of eliminarIngrediente method, of class IngredienteDAO.
//     */
//    @Test
//    public void testEliminarIngrediente() throws Exception {
//        assertDoesNotThrow(() -> {
//            intancia.eliminarIngrediente(Long.valueOf(46));
//        }, "Error al eliminar. No puede borrarse un ingrediente usado en un producto activo.");
//
//        assertThrows(PersistenciaException.class, () -> {
//            intancia.eliminarIngrediente(Long.valueOf(2131));
//        }, "Error al eliminar. No puede borrarse un ingrediente usado en un producto activo.");
//    
//    }
//
//    /**
//     * Test of obtenerIngredientePorID method, of class IngredienteDAO.
//     */
//    @Test
//    public void testObtenerIngredientePorID() throws Exception {
//
//        Ingrediente resultado = intancia.obtenerIngredientePorID(Long.valueOf(22));
//
//        assertNotNull(resultado);
//        assertEquals(Long.valueOf(22), resultado.getId());
//        assertEquals("Sal Fino 22", resultado.getNombre());
//    }
//
//    /**
//     * Test of actualizarIngrediente method, of class IngredienteDAO.
//     */
//    @Test
//    public void testActualizarIngrediente() throws Exception {
//        Ingrediente ingrediente = new Ingrediente("Cebolla",UnidadMedida.GRAMOS ,10.0, EstadoIngrediente.ACTIVO);
//        Ingrediente guardado = intancia.registrarIngrediente(ingrediente);
//        
//        guardado.setNombre("Cebolla Morada");
//        guardado.setCantidad_stock(15.5);
//        guardado.setEstado(EstadoIngrediente.ACTIVO);
//        
//        Ingrediente actualizado = intancia.actualizarIngrediente(guardado);
//        
//        assertNotNull(actualizado);
//        assertEquals("Cebolla Morada", actualizado.getNombre());
//        assertEquals(15.5, actualizado.getCantidad_stock());
//        assertEquals(guardado.getId(), actualizado.getId());
//        
//        Ingrediente buscado = intancia.obtenerIngredientePorID(guardado.getId());
//        assertEquals("Cebolla Morada", buscado.getNombre());
//    }
    
}
