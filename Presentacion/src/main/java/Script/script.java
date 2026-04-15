/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Script;

import conexion.ConexionBD;
import entidades.Cliente;
import entidades.ClienteFrecuente;
import entidades.Comanda;
import entidades.DetalleComanda;
import entidades.DetalleProducto;
import entidades.Ingrediente;
import entidades.Mesa;
import entidades.Mesero;
import entidades.Producto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tiposDatosEnums.EstadoComanda;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.EstadoMesa;
import tiposDatosEnums.UnidadMedida;

/**
 *
 * @author piña
 */
public class script {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EntityManager em = ConexionBD.crearConexion();

        try {
            em.getTransaction().begin();

            // 1. CREAR MESA Y MESERO
            Mesa mesa1 = new Mesa("Mesa-01", EstadoMesa.DISPONIBLE);
            em.persist(mesa1);

            Mesero mesero1 = new Mesero("ACCESO123", "Carlos", "Sánchez", "García", "SAGC900101", "555-0101", LocalDate.now());
            em.persist(mesero1);

            // 2. CREAR INGREDIENTES
            Ingrediente carne = new Ingrediente("Carne de Res", UnidadMedida.GRAMOS, 5000.0, EstadoIngrediente.ACTIVO);
            Ingrediente pan = new Ingrediente("Pan Brioche", UnidadMedida.PIEZAS, 50.0, EstadoIngrediente.ACTIVO);
            em.persist(carne);
            em.persist(pan);

            // 3. CREAR PRODUCTO
            Producto hamburguesa = new Producto();
            hamburguesa.setPrecio(150.0);
            em.persist(hamburguesa);

            // 4. CREAR RECETA (DetalleProducto)
            DetalleProducto receta1 = new DetalleProducto(200.0, carne, hamburguesa);
            DetalleProducto receta2 = new DetalleProducto(1.0, pan, hamburguesa);
            em.persist(receta1);
            em.persist(receta2);

            // 5. CREAR CLIENTE (Frecuente)
            ClienteFrecuente cliente1 = new ClienteFrecuente(10.0, 0.0, "Maria", "Luna", "Sol", "555-9999", "maria@mail.com", LocalDate.now());
            em.persist(cliente1);

            // 6. CREAR COMANDA
            // Constructor: (estado, folio, fecha, mesa, mesero)
            Comanda comanda1 = new Comanda(EstadoComanda.ABIERTA, "FOLIO-ABC", LocalDateTime.now(), mesa1, mesero1);
            comanda1.setCliente(cliente1);
            em.persist(comanda1);

            // 7. CREAR DETALLE DE COMANDA (Lo que pidió el cliente)
            // Constructor: (cantidad, comentarios, comanda, producto)
            DetalleComanda pedido1 = new DetalleComanda(2, "Sin cebolla por favor", comanda1, hamburguesa);
            // El subtotal y precio se calculan solos en tu constructor de DetalleComanda
            em.persist(pedido1);

            em.getTransaction().commit();
            System.out.println("✅ ¡Base de datos poblada con éxito!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("❌ Error en el proceso: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
