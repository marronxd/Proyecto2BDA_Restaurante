/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Inicio;

import daos.IngredienteDAO;
import daos.MesaDAO;
import daos.MeseroDAO;
import entidades.Ingrediente;
import entidades.Mesa;
import entidades.Mesero;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.Random;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.EstadoMesa;
import tiposDatosEnums.UnidadMedida;

/**
 *
 * @author luiscarlosbeltran
 */
public class SuperInsertsMegaMasivos {

    /**
     * EJECUTAR ANTES DE CORRER EL PROGRAMA
     * clase con inserts de ingredientes, un mesero, 20 mesas
     * para probar el programa.
     * 
     * NOTA IMPORTANTISIMA:
     * asegurese de crear el trigger con el script de sql que le pasare
     * este es para calcular los puntos de clientes al cerrar una comanda
     * y tambien vendran los productos en el script de sql
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        //inserts de ingredientes, hechos por aaron
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        // 1. Listas base para combinar y evitar el UniqueConstraint
        String[] nombresBase = {"Harina", "Sal", "Azúcar", "Aceite", "Huevo", "Leche", "Levadura", "Pimienta", "Mantequilla", "Cacao"};
        String[] adjetivos = {"Integral", "Refinado", "Extra", "Orgánico", "Fino", "Estandar"};

        Random random = new Random();
        UnidadMedida[] unidades = UnidadMedida.values();
        EstadoIngrediente[] estados = EstadoIngrediente.values();

        for (int i = 0; i < 30; i++) {
            // 2. Crear el nombre único (Nombre + Adjetivo + Contador)
            String nombreAleatorio = nombresBase[random.nextInt(nombresBase.length)] + " " + 
                                    adjetivos[random.nextInt(adjetivos.length)] + " " + (i + 1);

            // 3. Generar valores aleatorios para los demás campos
            UnidadMedida unidadAzar = unidades[random.nextInt(unidades.length)];
            Double cantidadAzar = 1 + (99 * random.nextDouble());
            EstadoIngrediente estadoAzar = estados[random.nextInt(estados.length)];

            // 4. Instanciar la Entidad usando tu constructor (sin URL de imagen)
            Ingrediente nuevoIngrediente = new Ingrediente(
                nombreAleatorio, 
                unidadAzar, 
                cantidadAzar, 
                estadoAzar
            );

            // 5. Mandar a registrar (usando tu método existente en el BO o DAO)
            try {
                // Aquí llamas a tu método que recibe la ENTIDAD
                ingredienteDAO.registrarIngrediente(nuevoIngrediente);
                System.out.println("Insertado: " + nombreAleatorio + " (" + unidadAzar + ")");
            } catch (Exception ex) {
                System.err.println("Error al insertar registro " + i + ": " + ex.getMessage());
            }
        }
        
        //insert de msero, hecho por luis carlos
        MeseroDAO dao = new MeseroDAO(); 
        
        Mesero m = new Mesero(
        "ABC123",
        "Juan",
        "Perez",
        "Lopez",
        "RFC123",
        "6441234567",
        LocalDate.now());
        
        dao.registrar(m);
        
        //insert de 20 mesas, hecho por luisk (luis carlos)
        MesaDAO mesaDAO = new MesaDAO();
        
        //se crean los objetos mesa, 20 en total
        Mesa mesaA1 = new Mesa("A1", EstadoMesa.DISPONIBLE);
        Mesa mesaA2 = new Mesa("A2", EstadoMesa.DISPONIBLE);
        Mesa mesaA3 = new Mesa("A3", EstadoMesa.DISPONIBLE);
        Mesa mesaA4 = new Mesa("A4", EstadoMesa.DISPONIBLE);
        
        Mesa mesaB1 = new Mesa("B1", EstadoMesa.DISPONIBLE);
        Mesa mesaB2 = new Mesa("B2", EstadoMesa.DISPONIBLE);
        Mesa mesaB3 = new Mesa("B3", EstadoMesa.DISPONIBLE);
        Mesa mesaB4 = new Mesa("B4", EstadoMesa.DISPONIBLE);
        
        Mesa mesaC1 = new Mesa("C1", EstadoMesa.DISPONIBLE);
        Mesa mesaC2 = new Mesa("C2", EstadoMesa.DISPONIBLE);
        Mesa mesaC3 = new Mesa("C3", EstadoMesa.DISPONIBLE);
        Mesa mesaC4 = new Mesa("C4", EstadoMesa.DISPONIBLE);
        
        Mesa mesaD1 = new Mesa("D1", EstadoMesa.DISPONIBLE);
        Mesa mesaD2 = new Mesa("D2", EstadoMesa.DISPONIBLE);
        Mesa mesaD3 = new Mesa("D3", EstadoMesa.DISPONIBLE);
        Mesa mesaD4 = new Mesa("D4", EstadoMesa.DISPONIBLE);
        
        Mesa mesaE1 = new Mesa("E1", EstadoMesa.DISPONIBLE);
        Mesa mesaE2 = new Mesa("E2", EstadoMesa.DISPONIBLE);
        Mesa mesaE3 = new Mesa("E3", EstadoMesa.DISPONIBLE);
        Mesa mesaE4 = new Mesa("E4", EstadoMesa.DISPONIBLE);
        
        //se guardan todas las 20 mesas
        mesaDAO.guardar(mesaA1);
        mesaDAO.guardar(mesaA2);
        mesaDAO.guardar(mesaA3);
        mesaDAO.guardar(mesaA4);
        
        mesaDAO.guardar(mesaB1);
        mesaDAO.guardar(mesaB2);
        mesaDAO.guardar(mesaB3);
        mesaDAO.guardar(mesaB4);
        
        mesaDAO.guardar(mesaC1);
        mesaDAO.guardar(mesaC2);
        mesaDAO.guardar(mesaC3);
        mesaDAO.guardar(mesaC4);
        
        mesaDAO.guardar(mesaD1);
        mesaDAO.guardar(mesaD2);
        mesaDAO.guardar(mesaD3);
        mesaDAO.guardar(mesaD4);
        
        mesaDAO.guardar(mesaE1);
        mesaDAO.guardar(mesaE2);
        mesaDAO.guardar(mesaE3);
        mesaDAO.guardar(mesaE4);
        
        
    }
    
}
