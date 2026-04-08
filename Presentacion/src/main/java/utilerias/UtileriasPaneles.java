/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    
    /**
     * Crea un panel en el que acomodo los botones para que se vayan acomodando a como lleguenxd
     * @return  el panel configurado
     */
    public static JPanel panelOpciones(){
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        return panelAcciones;
    }
    
    /**
     * Genera y configura el scroll del menu lateral y en un futuro se puede usar pa cualquier scroll
     * @param panelMenu
     * @return 
     */
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
    
    /**
     * Método auxiliar que crea y configura un filechooser
     * @return 
     */
    public static JFileChooser generarFileChooserImagen(){
        // --- crear el filechooser ---
        JFileChooser fileChooser = new JFileChooser();
        
        // configurarlo para que solo reciba imagenes
        FileNameExtensionFilter filtroImagen = new 
        FileNameExtensionFilter("Formato de imagenes (*jpg, jpeg, png*)", "jpg", "jpeg", "png");
    
        // agregar filtro al file chooser
        fileChooser.setFileFilter(filtroImagen);
        // darle un nombre al dialogo
        fileChooser.setDialogTitle("Abrir archivo");
        // darle una ruta para que guarde las imagenes DE MOMENTO NO
        //File ruta  = new File()
        
        return fileChooser;      
    }
    
    /**
     * Configura y crea una imagen a partir del peso de la imagen
     * @param pesoImagen
     * @return 
     */
    public static ImageIcon configurarImagen(byte[] pesoImagen){
        if(pesoImagen != null){
            // convertir el arreglo de bytes a icono
            ImageIcon iconoOriginal = new ImageIcon(pesoImagen);

            // como la voy a reescalar, la voy a pasar convertir en imagen
            Image imagen = iconoOriginal.getImage();

            // reescalarla
            Image imagenEscalada = imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon iconoFinal = new ImageIcon(imagenEscalada);
        return iconoFinal;
        }
        return null;
    }
}
