/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Image;
import java.net.URL;
import javax.swing.*;
import utilerias.*;

/**
 * Frm para que el administrador pueda iniciar sesión 
 * @author aaron
 */
public class FrmInicioSesion extends JFrame{
    private final String CONTRASENIA = "1";
    private final String USUARIO = "a";
    
    //Mera estetica de imagenes
    private String iconoSesion = "/R (1).jpg";
    private String iconoLogo = "/logo.png";
    
    private CoordinadorFrames coordinador;

    public FrmInicioSesion(CoordinadorFrames coordinador) {
        this.coordinador = coordinador;
        FramesUtileria.configurarVentanaEmergente(this, "Inicio de sesión");
        this.setSize(900, 550);
        this.setLocationRelativeTo(null);

        // hacer un panel de fondo que sea la base de todo y asignarle un borderLayout
        JPanel pnlFondo = new JPanel(new BorderLayout());
        // configurar el panel
        pnlFondo.setBackground(ColoresUtileria.getFONDO());
        
        // DERECHA crear un jLabel lateral que sea una imagen y configuración
        JLabel lateral = new JLabel();
        lateral.setPreferredSize(new Dimension(400, 0));
        lateral.setOpaque(true);
        lateral.setBackground(ColoresUtileria.getCOLORLINEA());
        
        // crear icono
        lateral.setIcon(new ImageIcon(FramesUtileria.configurarIconos(iconoSesion, 400, 520)));
        // panel que contendrá el formulario
        JPanel pnlContenido = new JPanel(new GridBagLayout());
        pnlContenido.setBackground(ColoresUtileria.getFONDO());

        /**
         * Creación de panel interno donde iran las cosas, dara la impresión para que no 
         * se amontonen las cosas
         */
        JPanel pnlCentral = new JPanel();
        pnlCentral.setLayout(new BoxLayout(pnlCentral, BoxLayout.Y_AXIS));
        pnlCentral.setOpaque(false);
        pnlCentral.setPreferredSize(new Dimension(350, 450));

        // ELEMENTO DEL LOGIN

        // cracion de botones basicos
        JButton btnIniciar = BotonUtileria.botonUtileriaGenérico("Iniciar");
        JButton btnRegresar = BotonUtileria.botonUtileriaGenérico("Volver al menu");

        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(FramesUtileria.configurarIconos(iconoLogo, 149, 48)));
        pnlCentral.add(lblLogo);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 30)));
        // creacion de labels retroalimentativos
        JLabel lblTitulo = new JLabel("Inicio de sesión");
        lblTitulo.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 38));
        lblTitulo.setForeground(ColoresUtileria.getCOLORTITULO());
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlCentral.add(lblTitulo);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 30)));

        // text de usuario y contraseña. Entrada del usuario
        JTextField txtUsuario = new JTextField("Introduzca el usuario...");

        // configuracion del textfield del nombre usuario
        txtUsuario.setMaximumSize(new Dimension(300, 35));
        txtUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtUsuario.setBackground(Color.WHITE);
        txtUsuario.addFocusListener(new FocusAdapter(){

            // cuando se interactué con el componente, activa este evento y el placeholder
            // desaparece
            @Override
            public void focusGained(FocusEvent evt){
                if(txtUsuario.getText().equals("Introduzca el usuario...")){
                    txtUsuario.setText("");
                    txtUsuario.setForeground(Color.BLACK);
                }
            }
            // caso contrario cuando se deja de interactuar y la entrada/contenido
            // es vacío, vuelve a mostrar el placeHolder
            @Override
            public void focusLost(FocusEvent evt) {
                if (txtUsuario.getText().isEmpty()) {
                    txtUsuario.setText("Introduzca el usuario...");
                    txtUsuario.setForeground(Color.GRAY);
                }
            }
        });
        txtUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);

        // contraseña 
        JPasswordField contrasenia = new JPasswordField("****");

        // configuracion del textfield de la contraseña usuario
        contrasenia.setMaximumSize(new Dimension(300, 35));
        contrasenia.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        contrasenia.setBackground(Color.WHITE);
        contrasenia.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent evt){
                    contrasenia.setText("");
            }
            @Override
            public void focusLost(FocusEvent evt) {
                if (String.valueOf(contrasenia.getPassword()).isEmpty()){
                    contrasenia.setText("****");
                    contrasenia.setForeground(Color.GRAY);
                }
            }
        });
        contrasenia.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 14));
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlCentral.add(lblUser);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlCentral.add(txtUsuario);

        pnlCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 14));
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlCentral.add(lblPass);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlCentral.add(contrasenia);

        pnlCentral.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel pnlAcciones = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        pnlAcciones.setOpaque(false);
        pnlAcciones.add(btnIniciar);
        pnlAcciones.add(Box.createRigidArea(new Dimension(10, 0)));
        pnlAcciones.add(btnRegresar);
        pnlAcciones.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlCentral.add(pnlAcciones);

        // incorporacion de elementos
        pnlContenido.add(pnlCentral);

        pnlFondo.add(lateral, BorderLayout.EAST);
        pnlFondo.add(pnlContenido, BorderLayout.CENTER);
        this.add(pnlFondo);

        // acciones de los botones
        btnIniciar.addActionListener(e -> {
            // recuperar contraseñas y usuarios de los textFields
            String contraRecuperada = new String(contrasenia.getPassword()).strip();
            String userRecuperado = txtUsuario.getText().strip();
            if(contraRecuperada.equals(CONTRASENIA) &&  userRecuperado.equals(USUARIO)){
                coordinador.mostrarMenuAdministrador();

            }else{
                JOptionPane.showMessageDialog(this, "Usuario o contraseña inválido:\n" + "Contraseña: " + contraRecuperada + "\n"+ "Usuario:" + userRecuperado,
                    "Error de autenticación", 
                    JOptionPane.ERROR_MESSAGE);
            }

        });

        btnRegresar.addActionListener(e -> {
            coordinador.rergesarAlMenu();
        });

        this.setVisible(true);
    }
    
    
    
    public String getCONTRASENIA() {
        return CONTRASENIA;
    }

    public String getUsuario() {
        return USUARIO;
    }
    
    
    
}
