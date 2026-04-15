/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Script;

import entidades.Cliente;
import entidades.ClienteFrecuente;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author piña
 */
public class script {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 1. Conexión (Asegúrate que el nombre "RestaurantePU" sea el de tu persistence.xml)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestaurantePU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // --- REGISTRO 1: Cliente Normal ---
            Cliente c1 = new Cliente("Ana", "Martínez", "López", "555-1111", "ana@mail.com", LocalDate.now());

            // --- REGISTRO 2: Cliente Frecuente (Oro) ---
            ClienteFrecuente cf1 = new ClienteFrecuente(
                    500.0, // puntos
                    12500.0, // total gastado
                    "Roberto", // nombre
                    "Gómez", // paterno
                    "Ruiz", // materno
                    "555-2222", // tel
                    "roberto@mail.com",
                    LocalDate.now()
            );

            // --- REGISTRO 3: Cliente Frecuente (Nuevo) ---
            ClienteFrecuente cf2 = new ClienteFrecuente(
                    50.0,
                    200.0,
                    "Lucía",
                    "Fernández",
                    null, // apellido materno null
                    "555-3333",
                    "lucia@mail.com",
                    LocalDate.now()
            );

            // 2. Guardar todo
            em.persist(c1);
            em.persist(cf1);
            em.persist(cf2);

            em.getTransaction().commit();
            System.out.println("✅ ¡Registros insertados correctamente!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("❌ Error al insertar: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

}
