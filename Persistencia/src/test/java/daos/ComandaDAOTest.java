/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Comanda;
import entidades.DetalleComanda;
import entidades.Mesa;
import entidades.Mesero;
import entidades.Producto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiposDatosEnums.EstadoComanda;

/**
 *
 * @author luiscarlosbeltran
 */
public class ComandaDAOTest {

    private final ComandaDAO dao = new ComandaDAO();

    /**
     * pruebas para comandadao
     * debe haber una mesa, producto y mesero con id 1 en la bd
     */
    private Comanda crearComandaPrueba() {

        Mesa mesa = new Mesa();
        mesa.setId(1L);

        Mesero mesero = new Mesero();
        mesero.setId(1L);

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setPrecio(100.0);

        Comanda comanda = new Comanda();
        comanda.setEstado(EstadoComanda.ABIERTA);
        comanda.setFolio("TEST-" + System.currentTimeMillis());
        comanda.setFechaHora_Creacion(LocalDateTime.now());
        comanda.setMesa(mesa);
        comanda.setMesero(mesero);
        comanda.setTotal_acumulado(100.0);

        DetalleComanda detalle = new DetalleComanda();
        detalle.setCantidad_producto(1);
        detalle.setComentarios("Prueba");
        detalle.setPrecio(100.0);
        detalle.setSubtotal(100.0);
        detalle.setProducto(producto);

        List<DetalleComanda> detalles = new ArrayList<>();
        detalles.add(detalle);

        comanda.setDetalles(detalles);

        return comanda;
    }

    @Test
    public void testGuardarComanda() throws Exception {

        Comanda comanda = crearComandaPrueba();

        Comanda guardada = dao.guardarComanda(comanda);

        Assertions.assertNotNull(guardada);
        Assertions.assertNotNull(guardada.getId());
    }

    @Test
    public void testObtenerTodos() throws Exception {

        List<Comanda> lista = dao.obtenerTodos();

        Assertions.assertNotNull(lista);
    }

    @Test
    public void testBuscarPorId() throws Exception {

        Comanda comanda = crearComandaPrueba();
        Comanda guardada = dao.guardarComanda(comanda);

        Comanda encontrada = dao.buscarPorId(guardada.getId());

        Assertions.assertNotNull(encontrada);
        Assertions.assertEquals(guardada.getId(), encontrada.getId());
    }

    @Test
    public void testBuscarPorRangoFechas() throws Exception {

        LocalDateTime inicio = LocalDateTime.now().minusDays(1);
        LocalDateTime fin = LocalDateTime.now().plusDays(1);

        List<Comanda> lista = dao.buscarPorRangoFechas(inicio, fin);

        Assertions.assertNotNull(lista);
    }

    @Test
    public void testActualizarComanda() throws Exception {

        Comanda comanda = crearComandaPrueba();
        Comanda guardada = dao.guardarComanda(comanda);

        guardada.setEstado(EstadoComanda.ENTREGADA);

        Comanda actualizada = dao.actualizarComanda(guardada);

        Assertions.assertEquals(
                EstadoComanda.ENTREGADA,
                actualizada.getEstado()
        );
    }

    @Test
    public void testObtenerNumSigFolio() throws Exception {

        int numero = dao.obtenerNumSigFolio(LocalDateTime.now());

        Assertions.assertTrue(numero >= 1);
    }

    @Test
    public void testObtenerComandasFechas() throws Exception {

        LocalDateTime inicio = LocalDateTime.now().minusDays(1);
        LocalDateTime fin = LocalDateTime.now().plusDays(1);

        List<Comanda> lista = dao.obtenerComandasFechas(inicio, fin);

        Assertions.assertNotNull(lista);
    }
}