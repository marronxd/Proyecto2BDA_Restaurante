/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author luiscarlosbeltran
 */
public class DlgReporteCliente extends javax.swing.JDialog {

    private CoordinadorFrames coordinadorFrames;
    private JTextField txtNombre;

    public DlgReporteCliente(JFrame parent, boolean modal, CoordinadorFrames coordinadorF) {
        super(parent, modal);
        this.coordinadorFrames = coordinadorF;

        setTitle("Reporte de Clientes");
        setSize(300, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        txtNombre = new JTextField();

        panel.add(new JLabel("Filtrar por nombre:"));
        panel.add(txtNombre);

        add(panel, BorderLayout.CENTER);

        JButton btnGenerar = new JButton("Generar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCancelar);
        panelBotones.add(btnGenerar);

        add(panelBotones, BorderLayout.SOUTH);

        btnGenerar.addActionListener(e -> {
            coordinadorFrames.solicitarReporteClientes(txtNombre.getText());
        });

        btnCancelar.addActionListener(e -> {
            coordinadorFrames.cancelarReporteClientes();
        });
    }
}