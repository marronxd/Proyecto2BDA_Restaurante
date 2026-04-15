/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utilerias.BotonUtileria;
import utilerias.ColoresUtileria;
import utilerias.FramesUtileria;

/**
 *
 * @author luiscarlosbeltran
 */
public abstract class FrmPlantillaMesero extends JFrame {

    protected JPanel panelContenido;
    protected JButton btnCrearComandas;
    protected JButton btnVerComandas;
    protected JButton btnVolver;

    public FrmPlantillaMesero(String titulo) {
        FramesUtileria.configurarVentanaPrincipal(this, titulo);
    }

    public void cuerpoEstructura() {

        this.setLayout(new BorderLayout());

        this.add(crearEncabezado(), BorderLayout.NORTH);

        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(ColoresUtileria.getFONDO());

        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(ColoresUtileria.getFONDO());
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Menu Mesero");
        lblTitulo.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 28));
        lblTitulo.setForeground(ColoresUtileria.getCOLORTITULO());
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Seleccione una opcion");
        lblSub.setFont(new Font(FramesUtileria.getFUENTE(), Font.PLAIN, 16));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCrearComandas = BotonUtileria.botonUtileriaGenérico("Crear comanda");
        btnCrearComandas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCrearComandas.setMaximumSize(new Dimension(220, 45));
        
        btnVerComandas = BotonUtileria.botonUtileriaGenérico("Ver comandas");
        btnVerComandas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerComandas.setMaximumSize(new Dimension(220, 45));
        
        btnVolver = BotonUtileria.botonUtileriaGenérico("Volver");
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setMaximumSize(new Dimension(220, 45));

        
        panelCentro.add(lblTitulo);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentro.add(lblSub);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 30)));
        panelCentro.add(btnCrearComandas);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 30)));
        panelCentro.add(btnVerComandas);

        panelContenido.add(panelCentro, BorderLayout.CENTER);

        this.add(panelContenido, BorderLayout.CENTER);
        
        
        JPanel panelSur = new JPanel();
        panelCentro.setBackground(ColoresUtileria.getFONDO());
        panelSur.add(btnVolver);
        this.add(panelSur, BorderLayout.SOUTH);
    }

    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ColoresUtileria.getFONDO());
        panel.setPreferredSize(new Dimension(0, 80));

        JLabel titulo = new JLabel("Restaurante Genérico", SwingConstants.CENTER);
        titulo.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 24));
        titulo.setForeground(ColoresUtileria.getCOLORTITULO());

        panel.setBorder(BorderFactory.createMatteBorder(
                0, 0, 2, 0, ColoresUtileria.getCOLORLINEA()));

        panel.add(titulo, BorderLayout.CENTER);

        return panel;
    }

    public void setNuevoContenido(JPanel nuevoPanel) {
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
}