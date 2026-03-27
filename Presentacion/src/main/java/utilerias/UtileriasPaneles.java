/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author aaron
 */
public class UtileriasPaneles {

    private UtileriasPaneles() {
    }
    
    public static void configurarSideBar(JPanel sidebar){
        //  aplicar color de fondo
        
        // establecer ancho fijo
        sidebar.setPreferredSize(new Dimension(250, 0));
        
        // Apilación hacia abajo, o sea vertical
        sidebar.setLayout(new  BoxLayout(sidebar, BoxLayout.Y_AXIS));
        
        // borde para que se vea una separación
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
    }
    
    /**
     * COnfiguración del contenido de la pagina
     */
    public static void configurarContenido(JPanel contenido){
        contenido.setBackground(ColoresUtileria.getFONDO());
        // Agregamos un margen interno para que nada toque las orillas
        contenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    
    public static JPanel panelOpciones(){
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        return panelAcciones;
    }
    
    public static JScrollPane generarScrollMenu(JPanel panelMenu) {
        JScrollPane scroll = new JScrollPane(panelMenu);

        // 1. Quitar el borde del scroll para que no se vea el cuadro gris
        scroll.setBorder(null);
         
        // Poner efecto de sombreado
        scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(0, 0, 0, 20)));
        // 2. Hacer que el fondo del scroll sea transparente o del mismo color crema
        scroll.getViewport().setBackground(panelMenu.getBackground());

        // 3. Configurar barras (opcional: ocultar la horizontal siempre)
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // 4. IMPORTANTE: Definir el ancho del scroll
        scroll.setPreferredSize(new java.awt.Dimension(150, 0));

        return scroll;
    }
    
    public static void aplicarSombraSidebar(JPanel panel) {
        // Creamos un borde que solo pinte el lado derecho (Right) con un gris suave
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, new Color(0, 0, 0, 110)));
    }
}
