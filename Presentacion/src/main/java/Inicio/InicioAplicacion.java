/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Inicio;

import Controladores.CoordinadorFrames;

/**
 *
 * @author aaron
 */
public class InicioAplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Configuramos el LookAndFeel para que se vea "bonita" (System Look)
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { /* Ignorar si falla */ }

        // Iniciamos el Coordinador
        CoordinadorFrames coordinador = new CoordinadorFrames();
        
        // Abrimos la pantalla inicial
        coordinador.IniciarSistema();
    }    
    
}
