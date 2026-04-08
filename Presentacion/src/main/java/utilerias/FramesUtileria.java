/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Component;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author aaron
 */
public class FramesUtileria{
    
    private static final String FUENTE = "Roboto";
    
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
        dialogo.setResizable(false); 
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.setLocationRelativeTo(null); // Centrar en pantalla
    }
    
    /**
     * Método para configurar iconos, se le asigna la ruta y el tamaño que tendra
     * @param rutaURL
     * @param coordenadaX
     * @param CoordenadaY
     * @return 
     */
    public static Image configurarIconos(String rutaURL, int coordenadaX, int CoordenadaY){
        URL mRuta = FramesUtileria.class.getResource(rutaURL);
        ImageIcon imagen = new ImageIcon(mRuta);
        Image imagenRescalada = imagen.getImage().getScaledInstance(coordenadaX, CoordenadaY, Image.SCALE_SMOOTH);
        return imagenRescalada;
    }
    
    /**
     * Método que crea un optionpane de error
     * @param padre
     * @param cuerpo
     * @param titulo 
     */
    public static void crearOptionPaneError(Component padre, String cuerpo, String titulo){
        JOptionPane.showMessageDialog(padre, cuerpo, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Método que configura un option pane de exito aviso, mas bien
     * @param padre
     * @param cuerpo
     * @param titulo 
     */
    public static void crearOptionPaneExito(Component padre, String cuerpo, String titulo){
        JOptionPane.showMessageDialog(padre, cuerpo, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
