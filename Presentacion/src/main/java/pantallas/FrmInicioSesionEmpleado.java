/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import controlador.CoordinadorNegocio;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utilerias.BotonUtileria;
import utilerias.ColoresUtileria;
import utilerias.FramesUtileria;

/**
 *
 * @author luiscarlosbeltran
 */
public class FrmInicioSesionEmpleado extends JFrame {

    private String iconoSesion = "/R (1).jpg";
    private String iconoLogo = "/logo.png";

    private CoordinadorFrames coordinador;
    private CoordinadorNegocio coordinadorNegocio;

    public FrmInicioSesionEmpleado(CoordinadorFrames coordinador, CoordinadorNegocio coordinadorNegocio) {
        this.coordinador = coordinador;
        this.coordinadorNegocio = coordinadorNegocio;

        FramesUtileria.configurarVentanaEmergente(this, "Inicio de sesión empleado");
        this.setSize(900, 550);
        this.setLocationRelativeTo(null);

        JPanel pnlFondo = new JPanel(new BorderLayout());
        pnlFondo.setBackground(ColoresUtileria.getFONDO());

        JLabel lateral = new JLabel();
        lateral.setPreferredSize(new Dimension(400, 0));
        lateral.setOpaque(true);
        lateral.setBackground(ColoresUtileria.getCOLORLINEA());
        lateral.setIcon(new ImageIcon(FramesUtileria.configurarIconos(iconoSesion, 400, 520)));

        JPanel pnlContenido = new JPanel(new GridBagLayout());
        pnlContenido.setBackground(ColoresUtileria.getFONDO());

        JPanel pnlCentral = new JPanel();
        pnlCentral.setLayout(new BoxLayout(pnlCentral, BoxLayout.Y_AXIS));
        pnlCentral.setOpaque(false);
        pnlCentral.setPreferredSize(new Dimension(350, 450));

        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(FramesUtileria.configurarIconos(iconoLogo, 149, 48)));

        JLabel lblTitulo = new JLabel("Acceso Mesero");
        lblTitulo.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 32));
        lblTitulo.setForeground(ColoresUtileria.getCOLORTITULO());

        JTextField txtCodigo = new JTextField("Ingrese código de acceso...");
        txtCodigo.setMaximumSize(new Dimension(300, 35));

        txtCodigo.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent evt){
                if(txtCodigo.getText().equals("Ingrese código de acceso...")){
                    txtCodigo.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent evt){
                if(txtCodigo.getText().isEmpty()){
                    txtCodigo.setText("Ingrese código de acceso...");
                }
            }
        });

        JButton btnIniciar = BotonUtileria.botonUtileriaGenérico("Iniciar");
        JButton btnRegresar = BotonUtileria.botonUtileriaGenérico("Volver");

        pnlCentral.add(lblLogo);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        pnlCentral.add(lblTitulo);
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 30)));

        pnlCentral.add(new JLabel("Código de acceso:"));
        pnlCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlCentral.add(txtCodigo);

        pnlCentral.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel pnlAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlAcciones.setOpaque(false);
        pnlAcciones.add(btnIniciar);
        pnlAcciones.add(btnRegresar);

        pnlCentral.add(pnlAcciones);

        pnlContenido.add(pnlCentral);

        pnlFondo.add(lateral, BorderLayout.EAST);
        pnlFondo.add(pnlContenido, BorderLayout.CENTER);

        this.add(pnlFondo);

        
        btnIniciar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();

            if(codigo.isEmpty() || codigo.equals("Ingrese código de acceso")){
                JOptionPane.showMessageDialog(this, "Ingrese un código válido");
                return;
            }

            try {
                coordinadorNegocio.iniciarSesionMesero(codigo);
                
                JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso");
                
                //aqui pondre la siguiente pantalla
                //coordinador.mostrarMenuMesero;
                dispose();
            }catch(NegocioException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }catch(PersistenciaException ex){
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnRegresar.addActionListener(e -> {
            coordinador.rergesarAlMenu();
            dispose();
        });

        this.setVisible(true);
    }
}
