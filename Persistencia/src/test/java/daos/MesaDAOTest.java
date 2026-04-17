/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Mesa;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tiposDatosEnums.EstadoMesa;

/**
 *
 * @author luiscarlosbeltran
 */
public class MesaDAOTest {

    private final MesaDAO dao = new MesaDAO();

    /**
     * crea una mesa especial para pruebas
     */
    private Mesa crearMesaPrueba() {

        Mesa mesa = new Mesa();
        mesa.setIdentificador("TEST-" + System.currentTimeMillis());
        mesa.setEstado(EstadoMesa.DISPONIBLE);

        return mesa;
    }

    @Test
    public void testGuardarMesa() throws Exception {

        Mesa mesa = crearMesaPrueba();

        Mesa guardada = dao.guardar(mesa);

        Assertions.assertNotNull(guardada);
        Assertions.assertNotNull(guardada.getId());
    }

    @Test
    public void testObtenerTodas() throws Exception {

        List<Mesa> lista = dao.obtenerTodas();

        Assertions.assertNotNull(lista);
    }

    @Test
    public void testBuscarPorId() throws Exception {

        Mesa mesa = crearMesaPrueba();
        Mesa guardada = dao.guardar(mesa);

        Mesa encontrada = dao.buscarPorId(guardada.getId());

        Assertions.assertNotNull(encontrada);
        Assertions.assertEquals(
                guardada.getId(),
                encontrada.getId()
        );
    }

    @Test
    public void testActualizarMesa() throws Exception {

        Mesa mesa = crearMesaPrueba();
        Mesa guardada = dao.guardar(mesa);

        guardada.setEstado(EstadoMesa.OCUPADA);

        Mesa actualizada = dao.actualizar(guardada);

        Assertions.assertEquals(
                EstadoMesa.OCUPADA,
                actualizada.getEstado()
        );
    }

    @Test
    public void testEliminarMesa() throws Exception {

        Mesa mesa = crearMesaPrueba();
        Mesa guardada = dao.guardar(mesa);

        boolean eliminado =
                dao.eliminarPorId(guardada.getId());

        Assertions.assertTrue(eliminado);
    }

    @Test
    public void testDisponible() throws Exception {

        Mesa mesa = crearMesaPrueba();
        Mesa guardada = dao.guardar(mesa);

        boolean disponible =
                dao.disponible(guardada.getId());

        Assertions.assertTrue(disponible);
    }

    @Test
    public void testEliminarMesaNoExistente() throws Exception {

        boolean eliminado =
                dao.eliminarPorId(999999L);

        Assertions.assertFalse(eliminado);
    }
}