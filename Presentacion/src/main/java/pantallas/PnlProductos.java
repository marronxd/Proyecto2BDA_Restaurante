/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author piña
 */
public class PnlProductos extends javax.swing.JPanel {
    
    private CoordinadorFrames coordinadorF;

    public PnlProductos(CoordinadorFrames coordinadorF) {
        this.coordinadorF = coordinadorF;
        // Configuración del fondo general 
        setBackground(new Color(253, 246, 240));
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Tarjeta Registrar
        gbc.gridx = 0;
        add(new TarjetaFormulario("Registrar Producto", new Color(107, 142, 120), true), gbc);

        // Tarjeta Buscar
        gbc.gridx = 1;
        add(new TarjetaFormulario("Buscar Producto", new Color(166, 137, 102), false), gbc);
    }

    // Clase interna para crear las tarjetas redondeadas
    private class TarjetaFormulario extends JPanel {
        private Color colorFondo;

        public TarjetaFormulario(String titulo, Color color, boolean esRegistro) {
            this.colorFondo = color;
            setOpaque(false); 
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(30, 30, 30, 30));

            // Título
            JLabel lblTitulo = new JLabel(titulo);
            lblTitulo.setForeground(Color.WHITE);
            lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
            lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(lblTitulo);
            add(Box.createRigidArea(new Dimension(0, 20)));

            if (esRegistro) {
                agregarCampo("Nombre", "Sandwich de la muerte");
                agregarCampo("Precio", "$150.00");
                agregarAreaTexto("Descripción", "Sandwich");
                
                // Botón Registrar
                JButton btn = crearBotonPersonalizado("Registrar", new Color(210, 180, 140), Color.BLACK);
                add(Box.createVerticalGlue());
                add(btn);
            } else {
                agregarCampo("Nombre", "Sándwich");
                agregarCampo("Categoría", "Categoría...");
                
                JLabel lblRes = new JLabel("Resultados:");
                lblRes.setForeground(Color.WHITE);
                add(lblRes);
                
                add(new JCheckBox("Sándwich de la muerte $150"));
                add(new JCheckBox("Sándwich de la vida $170"));
                
                // Botón Editar 
                JButton btn = crearBotonPersonalizado("Editar", new Color(80, 110, 90), Color.WHITE);
                add(Box.createVerticalGlue());
                add(btn);
            }
        }

        private void agregarCampo(String label, String placeholder) {
            JLabel l = new JLabel(label);
            l.setForeground(Color.BLACK);
            JTextField tf = new JTextField(placeholder);
            tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            add(l);
            add(tf);
            add(Box.createRigidArea(new Dimension(0, 10)));
        }

        private void agregarAreaTexto(String label, String texto) {
            JLabel l = new JLabel(label);
            JTextArea ta = new JTextArea(texto, 3, 20);
            JScrollPane sp = new JScrollPane(ta);
            sp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
            add(l);
            add(sp);
            add(Box.createRigidArea(new Dimension(0, 10)));
        }

        private JButton crearBotonPersonalizado(String texto, Color bg, Color fg) {
            JButton b = new JButton(texto);
            b.setBackground(bg);
            b.setForeground(fg);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFont(new Font("SansSerif", Font.BOLD, 14));
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            return b;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(colorFondo);
           
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
            g2.dispose();
            super.paintComponent(g);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
