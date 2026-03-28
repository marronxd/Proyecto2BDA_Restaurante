/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author aaron
 */
public class FramesUtileria{
    
    private static final String FUENTE = "Angkor";
    
    public FramesUtileria(){
        
    }
    /**
     * Configura las propiedades básicas de cualquier Frame del proyecto.
     * @param frame El JFrame que queremos configurar.
     * @param titulo El título de la ventana.
     */
    public static void configurarVentanaPrincipal(JFrame frame, String titulo) {
        frame.setTitle(titulo);
        frame.setSize(1000, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        // Aplicamos el color de fondo global que ya definiste en ColoresUtileria
        frame.getContentPane().setBackground(ColoresUtileria.getFONDO());
        
        // Establecemos el layout base para el menú lateral
        frame.setLayout(new BorderLayout());
    }
    /**
     * Frame para que que cada pantalla decida que tipod e layout tendrá, no viene definido
     * @param frame
     * @param titulo 
     */
    public static void configurarVentanaEmergente(JFrame frame, String titulo) {
        frame.setTitle(titulo);
        frame.setSize(600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(ColoresUtileria.getFONDO());
        // NO ponemos layout aquí, dejamos que cada pantalla decida
    }
  
    public static String getFUENTE() {
        return FUENTE;
    }
    /**
     * Genera un dialog para solicitud de campos para el usuario
     * @param dialogo
     * @param titulo 
     */
    public static void configurarVentanaGestion(JDialog dialogo, String titulo) {
        dialogo.setTitle(titulo);
        dialogo.setResizable(false); // Por lo general los diálogos no se estiran
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.setLocationRelativeTo(null); // Centrar en pantalla
        // Aquí puedes agregar el icono o colores que ya usas en el de JFrame
    }
    
    
}
