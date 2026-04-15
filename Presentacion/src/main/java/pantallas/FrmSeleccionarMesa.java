/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import controlador.CoordinadorNegocio;
import dtos.ComandaDTO;
import dtos.MesaDTO;
import excepciones.PersistenciaException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author luiscarlosbeltran
 */
public class FrmSeleccionarMesa extends JDialog {

    private CoordinadorNegocio coordinadorNegocio;
    private ComandaDTO nuevaComanda;

    private JPanel panelMesas;
    private JButton btnCancelar;

    public FrmSeleccionarMesa(JFrame parent,
                            CoordinadorNegocio coordinadorNegocio,
                            ComandaDTO nuevaComanda) {

        super(parent, "Seleccionar mesa", true);

        this.coordinadorNegocio = coordinadorNegocio;
        this.nuevaComanda = nuevaComanda;

        configurarVentana();
        inicializarComponentes();
        cargarMesas();
    }

    private void configurarVentana() {
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    private void inicializarComponentes() {

        JLabel titulo = new JLabel("Seleccione una mesa", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        add(titulo, BorderLayout.NORTH);

        panelMesas = new JPanel(new GridLayout(0, 4, 20, 20));
        panelMesas.setBackground(Color.WHITE);
        panelMesas.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JScrollPane scroll = new JScrollPane(panelMesas);
        add(scroll, BorderLayout.CENTER);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel sur = new JPanel();
        sur.add(btnCancelar);

        add(sur, BorderLayout.SOUTH);
    }

    private void cargarMesas() {

        try {
            List<MesaDTO> mesas = coordinadorNegocio.obtenerMesas();

            for (MesaDTO mesa : mesas) {
                panelMesas.add(crearBotonMesa(mesa));
            }

            panelMesas.revalidate();
            panelMesas.repaint();

        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar mesas:\n" + ex.getMessage());
        }
    }

    private JButton crearBotonMesa(MesaDTO mesa) {

    JButton boton = new JButtonCircular(
            mesa.getIdentificador(),
            mesa.getEstado().equalsIgnoreCase("DISPONIBLE")
                    ? Color.GREEN
                    : Color.RED
    );

    boton.setPreferredSize(new Dimension(90, 90));
    boton.setMinimumSize(new Dimension(90, 90));
    boton.setMaximumSize(new Dimension(90, 90));

    boton.addActionListener(e -> seleccionarMesa(mesa));

    return boton;
}

    private void seleccionarMesa(MesaDTO mesa) {

        if (mesa.getEstado().equalsIgnoreCase("DISPONIBLE")) {

            nuevaComanda.setIdMesa(mesa.getId());

            JOptionPane.showMessageDialog(this,
                    "Mesa asignada correctamente");

            dispose();

            // Aquí después abrirás la siguiente pantalla
            // productos / cliente / etc

        } else {

            JOptionPane.showMessageDialog(this,
                    "Mesa no disponible");
        }
    }

    /**
     * Botón circular
     */
    class JButtonCircular extends JButton {

        private Color color;

        public JButtonCircular(String texto, Color color) {
    super(texto);
    this.color = color;
    setForeground(Color.BLACK);
    setFont(new Font("Arial", Font.BOLD, 16));
    setFocusPainted(false);
    setContentAreaFilled(false);
}

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(color);
            g2.fillOval(0, 0, getWidth(), getHeight());

            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
        }

        @Override
        public boolean contains(int x, int y) {
            int radius = getWidth() / 2;
            return Math.pow(x - radius, 2) + Math.pow(y - radius, 2)
                    <= radius * radius;
        }
    }
}