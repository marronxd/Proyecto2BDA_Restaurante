/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;
import utilerias.BotonUtileria;
import utilerias.ColoresUtileria;
import Controladores.CoordinadorFrames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author aaron
 */
public class FrmMenuPrincipal extends JFrame{
    //ADICIONALES imagenes para usuario
    private String iconoAdministrador = "/IconoAdmin.png";
    private String iconoEmpleado = "/Empleado.png";
    
    // Inyectar dependencia/ objeto coordinador
    private final CoordinadorFrames coordinadorFrames;
    
    /**
     * Contruye al menu principal del inicio
     */
    public FrmMenuPrincipal(CoordinadorFrames coordinadorFrames){
        this.coordinadorFrames = coordinadorFrames;
        configurarVentana();
        inicializarComponentes();
    }
    
    /**
     * Configuración de las ventanas
     */
    private void configurarVentana(){
        setTitle("Sistema de Restaurante");
        setSize(500, 350);
        
        // Agregar los exit and close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // para centrar la ventana en el escritorio
        setForeground(ColoresUtileria.getFONDO());
        // separar título de las opciones
        setLayout(new BorderLayout(20,20));
    }
    
    private void inicializarComponentes(){
        // en la parte superior, esto es el título del sistema
        JLabel lblTitulo = new JLabel("Menú del sistema", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Angkor", Font.BOLD, 18)); // configuración de letras
        lblTitulo.setForeground(ColoresUtileria.getCOLORTITULO());
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));
        add(lblTitulo, BorderLayout.NORTH);
        
        // Botones de acceso a roles, ambos con el mismo tamaño
        JPanel panelOpciones = new JPanel(new GridLayout(1,2,25,25));
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(20,40,40,40));
        
        // crear botones para accesos a empleado o administrador
        JButton btnEmpleado = BotonUtileria.botonUtileriaMenu("Empleado", iconoEmpleado);
        JButton btnAdministrador = BotonUtileria.botonUtileriaMenu("Administrador", iconoAdministrador);
        
        // Interacciones con los eventos
        btnAdministrador.addActionListener(e -> {
           coordinadorFrames.mostrarInicioSesion();
        });
        
        
        panelOpciones.add(btnEmpleado);
        panelOpciones.add(btnAdministrador);
        add(panelOpciones, BorderLayout.CENTER);
        
        

        
    }
}
