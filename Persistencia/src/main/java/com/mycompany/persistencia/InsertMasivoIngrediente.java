/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.persistencia;

import daos.IngredienteDAO;
import entidades.Ingrediente;
import java.util.Random;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.UnidadMedida;

/**
 *
 * @author aaron
 */
public class InsertMasivoIngrediente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
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

    } 
}
