/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.DetalleProducto;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
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
    
    private IngredienteDAO instancia;
    
    public IngredienteDAOTest() {
    }
    
    @BeforeEach
    public void setUp() {
        instancia = new IngredienteDAO();
    }
    
    @Test
    public void testObtenerIngredientesFiltros_FlujoBase() throws Exception {
        List<Ingrediente> resultadoNombre = instancia.obtenerIngredientesFiltros("Harina", "Nombre");
        assertNotNull(resultadoNombre);

        List<Ingrediente> resultadoUnidad = instancia.obtenerIngredientesFiltros("GRAMOS", "Unidad de medida"); 
        assertNotNull(resultadoUnidad);
    }

    @Test
    public void testObtenerIngredientesFiltros_CasoAlternativo_NoExistente() throws Exception {
        List<Ingrediente> resultado = instancia.obtenerIngredientesFiltros("Kriptonita", "Nombre");
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía al buscar un ingrediente que no existe");
    }

    @Test
    public void testRegistrarIngrediente_FlujoBase() throws Exception {
        Ingrediente ingrediente = new Ingrediente("Mantequilla Especial Test1111", UnidadMedida.GRAMOS, 15.5, EstadoIngrediente.ACTIVO);
        Ingrediente resultado = instancia.registrarIngrediente(ingrediente);
        
        assertNotNull(resultado.getId(), "El ID no debería ser nulo después de persistir");
        assertEquals("Mantequilla Especial Test1111", resultado.getNombre());
    }

    @Test
    public void testRegistrarIngrediente_CasoAlternativo_Nulo() {
        PersistenciaException exception = assertThrows(PersistenciaException.class, () -> {
            instancia.registrarIngrediente(null);
        });
        // Coincide con el throw de tu línea 99
        assertEquals("Ingrediente registrado no puede ser null", exception.getMessage());
    }

    @Test
    public void testEliminarIngrediente_FlujoBase() throws Exception {
        Ingrediente ingrediente = new Ingrediente("Cacao Para Borrar", UnidadMedida.GRAMOS, 100.0, EstadoIngrediente.ACTIVO);
        Ingrediente guardado = instancia.registrarIngrediente(ingrediente);
        
        assertDoesNotThrow(() -> {
            Ingrediente eliminado = instancia.eliminarIngrediente(guardado.getId());
            assertEquals(guardado.getId(), eliminado.getId());
        });
    }

    @Test
    public void testEliminarIngrediente_CasoAlternativo_NoExistente() {
        PersistenciaException exception = assertThrows(PersistenciaException.class, () -> {
            instancia.eliminarIngrediente(999999L);
        });
        // Coincide con el catch de tu línea 146
        assertEquals("Error al eliminar. No puede borrarse un ingrediente usado en un producto activo.", exception.getMessage());
    }

    @Test
    public void testObtenerIngredientePorID_FlujoBase() throws Exception {
        Ingrediente ingrediente = new Ingrediente("Azúcar Test ID", UnidadMedida.GRAMOS, 20.0, EstadoIngrediente.ACTIVO);
        Ingrediente guardado = instancia.registrarIngrediente(ingrediente);

        Ingrediente resultado = instancia.obtenerIngredientePorID(guardado.getId());

        assertNotNull(resultado);
        assertEquals(guardado.getId(), resultado.getId());
    }

    @Test
    public void testObtenerIngredientePorID_CasoAlternativo_NoExistente() throws Exception {
        Ingrediente resultado = instancia.obtenerIngredientePorID(999999L);
        assertNull(resultado, "Debería regresar null al buscar un ID que no existe");
    }

    @Test
    public void testActualizarIngrediente_FlujoBase() throws Exception {
        Ingrediente ingrediente = new Ingrediente("Leche Original", UnidadMedida.MILILITROS, 10.0, EstadoIngrediente.ACTIVO);
        Ingrediente guardado = instancia.registrarIngrediente(ingrediente);
        
        guardado.setNombre("Leche Modificada");
        guardado.setCantidad_stock(50.0);
        
        Ingrediente actualizado = instancia.actualizarIngrediente(guardado);
        
        assertEquals("Leche Modificada", actualizado.getNombre());
        assertEquals(50.0, actualizado.getCantidad_stock());
    }

    @Test
    public void testActualizarIngrediente_CasoAlternativo_Nulo() {
        PersistenciaException exception = assertThrows(PersistenciaException.class, () -> {
            instancia.actualizarIngrediente(null);
        });
        // Coincide con el catch de tu línea 193 (em.merge(null) falla y cae en el catch general)
        assertEquals("Posible causa, ingrediente existente.", exception.getMessage());
    }

    @Test
    public void testDescontarStockPorProducto_FlujoBase() throws Exception {
        // 1. Registramos ingrediente para tener stock
        Ingrediente ingrediente = new Ingrediente("Queso Test", UnidadMedida.GRAMOS, 500.0, EstadoIngrediente.ACTIVO);
        Ingrediente guardado = instancia.registrarIngrediente(ingrediente);
        
        // 2. Simulamos la receta del producto
        DetalleProducto detalle = new DetalleProducto();
        detalle.setIngrediente(guardado);
        detalle.setCantidad(100.0); // La receta pide 100g
        
        List<DetalleProducto> receta = new ArrayList<>();
        receta.add(detalle);
        
        // 3. Descontamos stock (compraron 2 productos = 200g)
        assertDoesNotThrow(() -> {
            instancia.descontarStockPorProducto(receta, 2.0);
        });
        
        // 4. Verificamos que el stock se haya actualizado
        Ingrediente actualizado = instancia.obtenerIngredientePorID(guardado.getId());
        assertEquals(300.0, actualizado.getCantidad_stock(), "El stock debió bajar de 500 a 300");
    }

    @Test
    public void testDescontarStockPorProducto_CasoAlternativo_StockInsuficiente() throws Exception {
        // 1. Registramos ingrediente con poco stock
        Ingrediente ingrediente = new Ingrediente("Tomate Test", UnidadMedida.PIEZAS, 5.0, EstadoIngrediente.ACTIVO);
        Ingrediente guardado = instancia.registrarIngrediente(ingrediente);
        
        // 2. Simulamos receta que pide 2 piezas
        DetalleProducto detalle = new DetalleProducto();
        detalle.setIngrediente(guardado);
        detalle.setCantidad(2.0); 
        
        List<DetalleProducto> receta = new ArrayList<>();
        receta.add(detalle);
        
        // 3. Intentamos hacer 5 productos (pide 10 piezas, pero solo tenemos 5)
        PersistenciaException exception = assertThrows(PersistenciaException.class, () -> {
            instancia.descontarStockPorProducto(receta, 5.0);
        });
        
        // Coincide con tu excepción de la línea 220
        assertTrue(exception.getMessage().contains("No hay suficiente Tomate Test"), 
                  "El mensaje debe indicar stock insuficiente");
    }
}
