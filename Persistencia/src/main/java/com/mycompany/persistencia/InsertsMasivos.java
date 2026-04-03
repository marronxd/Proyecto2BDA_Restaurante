/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistencia;

import daos.ClienteDAO;
import entidades.ClienteFrecuente;



/**
 *
 * @author aaron
 */
public class InsertsMasivos {

    public static void main(String[] args) {
            // 1. Instanciamos el DAO donde tienes tu método guardar
        ClienteDAO dao = new ClienteDAO();

        // 2. Datos de prueba (Nombres, ApellidoP, ApellidoM, Telefono, Correo, Puntos)
        String[][] datos = {
            {"Danna Paola", "Ibarra", "Piña", "6441112233", "danna.f@mail.com", "500.0", "0.0"},
            {"Aaron", "Perez", "Lopez", "6444445566", "aaron.p@mail.com", "150.0", "0.0"},
            {"Luis", "Garcia", "Meza", "6628889900", "luis.g@mail.com", "200.0", "0.0"},
            {"Maria", "Robles", "Soto", "6447771122", "maria.r@mail.com", "350.0", "0.0"},
            {"Jose", "Campos", "Duarte", "6623334455", "jose.c@mail.com", "100.0", "0.0"}
        };

        System.out.println("--- Iniciando carga masiva ---");

        for (String[] fila : datos) {
            try {
                ClienteFrecuente nuevo = new ClienteFrecuente();
                nuevo.setNombres(fila[0]);
                nuevo.setApellidoPaterno(fila[1]);
                nuevo.setApellidoMaterno(fila[2]);
                nuevo.setTelefono(fila[3]);
                nuevo.setCorreo(fila[4]);
                nuevo.setPuntos(Double.valueOf(fila[5]));
                nuevo.setTotalGastado(Double.valueOf(fila[6])); 

                //   Linea para agregar fechas a las personas (pudo haber sido asi como la profe una vez puso)
                // para que sean random pero no quiero jeje
                nuevo.setFechaRegistro(java.time.LocalDate.now()); 

                dao.guardar(nuevo);
                System.out.println("Insertado con éxito: " + fila[0]);

            } catch (Exception e) {
                System.err.println("Error con " + fila[0] + ": " + e.getMessage());
            }
        }
    }
}
