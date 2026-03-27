/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import utilerias.*;

/**
 *
 * @author aaron
 */
public class FrmInicioSesion extends JFrame{
    private final String CONTRASENIA = "1";
    private final String USUARIO = "a";
    private CoordinadorFrames coordinador;

    public FrmInicioSesion(CoordinadorFrames coordinador) {
        this.coordinador = coordinador;
        FramesUtileria.configurarVentanaEmergente(this, "Inicio de sesión");

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#F1ECC3"));
        JPanel pnlCentral = new JPanel();
        pnlCentral.setLayout(new BoxLayout(pnlCentral, BoxLayout.Y_AXIS));
        pnlCentral.setBackground(Color.decode("#F1ECC3"));
        pnlCentral.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JButton btnIniciar = BotonUtileria.botonUtileriaGenérico("Iniciar");
        JButton btnRegresar = BotonUtileria.botonUtileriaGenérico("Volver al menu");

        JLabel lblTitulo = new JLabel("Inicio de sesión");
        lblTitulo.setFont(new Font("Angkor", Font.BOLD, 28));
        lblTitulo.setForeground(ColoresUtileria.getCOLORTITULO());
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlCentral.add(lblTitulo);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 30)));

        // text de usuario y contraseña
        JTextField txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(300, 35));
        
        JPasswordField contrasenia = new JPasswordField();
        contrasenia.setMaximumSize(new Dimension(300, 35));

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 14));
        lblUser.setAlignmentX(Component.RIGHT_ALIGNMENT);
        pnlCentral.add(lblUser);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlCentral.add(txtUsuario);

        pnlCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 14));
        lblPass.setAlignmentX(Component.RIGHT_ALIGNMENT);
        pnlCentral.add(lblPass);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlCentral.add(contrasenia);

        pnlCentral.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel pnlAcciones = UtileriasPaneles.panelOpciones();
        pnlAcciones.setOpaque(false);
        pnlAcciones.add(btnIniciar);
        pnlAcciones.add(btnRegresar);

        pnlCentral.add(pnlAcciones);

        this.add(pnlCentral, new GridBagConstraints());

        
 
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
