/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Mesero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author luiscarlosbeltran
 */
public class MeseroDAOTest {

    private final MeseroDAO dao = new MeseroDAO();

    /**
     * Crea un mesero especial para pruebas
     */
    private Mesero crearMeseroPrueba() {

        Mesero mesero = new Mesero();
        mesero.setNombres("Juan");
        mesero.setApellidoPaterno("Perez");
        mesero.setApellidoMaterno("Lopez");
        mesero.setRfc("hola");
        mesero.setTelefono("1234567890");
        mesero.setCodigo_acceso("probandoo");

        return mesero;
    }

    @Test
    public void testRegistrarMesero() throws Exception {

        Mesero mesero = crearMeseroPrueba();

        Mesero guardado = dao.registrar(mesero);

        Assertions.assertNotNull(guardado);
        Assertions.assertNotNull(guardado.getId());
    }

    @Test
    public void testBuscarPorCodigo() throws Exception {

        Mesero mesero = crearMeseroPrueba();

        Mesero guardado = dao.registrar(mesero);

        Mesero encontrado =
                dao.buscarPorCodigo(
                        guardado.getCodigo_acceso()
                );

        Assertions.assertNotNull(encontrado);

        Assertions.assertEquals(
                guardado.getCodigo_acceso(),
                encontrado.getCodigo_acceso()
        );
    }

    @Test
    public void testBuscarPorCodigoNoExistente() throws Exception {

        Mesero encontrado =
                dao.buscarPorCodigo("NO_EXISTE");

        Assertions.assertNull(encontrado);
    }
}