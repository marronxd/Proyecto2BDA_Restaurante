/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author aaron
 */
public class BotonUtileria {
    
    
    private BotonUtileria(){
        
    }
    
    public static JButton botonUtileriaCompleto(String texto, String rutaIcono, int ancho, int alto, int posicionTexto){
        JButton boton = new JButton(texto);
        // Manejo de imagen con validación de null para evitar el error "location is null"
        if (rutaIcono != null) {
            URL url = BotonUtileria.class.getResource(rutaIcono);
            if (url != null) {
                ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
                Image imgEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                boton.setIcon(new ImageIcon(imgEscalada));
            }
        }
        return boton;
    }
    
        public static JButton botonUtileriaMenu(String texto, String rutaIcono){
        JButton boton = new JButton(texto);
        URL url = BotonUtileria.class.getResource(rutaIcono); // este es para buscar la ruta de la imagen
        if (rutaIcono != null) {
            ImageIcon iconoOriginal = new ImageIcon(url); // aca creamos la imagen con esa ruta
            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imgEscalada));
        }
        
        // acomodo de la imagen y el texto
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        
        // estandarizar formatos para el boton
        boton.setFont(new Font("Angkor", Font.BOLD, 13));
        boton.setForeground(Color.decode("#6D816B"));
        boton.setBorderPainted(true);
         boton.setFocusPainted(false);
        return boton;
    }
        
    public static JButton botonUtileriaGenérico(String texto){
        JButton boton = new JButton(texto);
        // acomodo de la imagen y el texto
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);

        // estandarizar formatos para el boton
        boton.setFont(new Font("Angkor", Font.BOLD, 13));
        boton.setBorderPainted(true);
        boton.setFocusPainted(false);
        return boton;
     }
    
}
