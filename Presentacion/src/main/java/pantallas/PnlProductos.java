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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    // Componentes del Panel Registrar
    private JTextField txtRegNombre, txtRegPrecio;
    private JTextArea txtRegDescripcion;
    private JComboBox<String> cbTipo;

    // Componentes del Panel Buscar
    private JTextField txtBusNombre, txtBusCategoria;
    private JCheckBox chkMuerte, chkVida;

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

        // Configuración para el botón Regresar
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton regresarMenu = utilerias.BotonUtileria.botonUtileriaGenérico("Regresar al menu");
        add(regresarMenu, gbc);
        regresarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarMenuBotonActionPerformed(evt);
            }
        });
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
                // Panel de Registro: Guardamos las referencias en las variables "Reg"
                txtRegNombre = agregarCampo("Nombre");
                txtRegPrecio = agregarCampo("Precio");
                txtRegDescripcion = agregarAreaTexto("Descripción");
                String[] opcionesTipo = {"Seleccione...", "Platillo", "Bebida", "Postre", "Entrada"};
                cbTipo = agregarCombo("Tipo", opcionesTipo);

                JButton btnRegistrar = crearBotonPersonalizado("Registrar", new Color(210, 180, 140), Color.BLACK);
                add(Box.createVerticalGlue());
                add(btnRegistrar);

                btnRegistrar.addActionListener(e -> {
                    registrar(); // Aquí ya puedes usar txtRegNombre.getText(), etc.
                });

            } else {
                // Panel de Búsqueda: Guardamos las referencias en las variables "Bus"
                txtBusNombre = agregarCampo("Nombre");
                txtBusCategoria = agregarCampo("Categoría");

                JLabel lblRes = new JLabel("Resultados:");
                lblRes.setForeground(Color.WHITE);
                add(lblRes);

                chkMuerte = new JCheckBox("Sándwich de la muerte $150");
                chkVida = new JCheckBox("Sándwich de la vida $170");
                // Estilo para los CheckBox (opcional para que se vean bien en el fondo café)
                chkMuerte.setOpaque(false);
                chkMuerte.setForeground(Color.WHITE);
                chkVida.setOpaque(false);
                chkVida.setForeground(Color.WHITE);

                add(chkMuerte);
                add(chkVida);

                JButton btnEditar = crearBotonPersonalizado("Editar", new Color(80, 110, 90), Color.WHITE);
                add(Box.createVerticalGlue());
                add(btnEditar);

                btnEditar.addActionListener(e -> {
                    // Ejemplo de validación en búsqueda:
                    if (chkMuerte.isSelected()) {
                        System.out.println("Editar sándwich de la muerte");
                    }
                });
            }
        }

        private JTextField agregarCampo(String label) {
            JLabel l = new JLabel(label);
            l.setForeground(Color.BLACK);
            JTextField tf = new JTextField();
            tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            add(l);
            add(tf);
            add(Box.createRigidArea(new Dimension(0, 10)));
            return tf;
        }

        private JTextArea agregarAreaTexto(String label) {
            JLabel l = new JLabel(label);
            JTextArea ta = new JTextArea(3, 20);
            ta.setLineWrap(true);
            JScrollPane sp = new JScrollPane(ta);
            sp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
            add(l);
            add(sp);
            add(Box.createRigidArea(new Dimension(0, 10)));
            return ta;
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

        private JComboBox<String> agregarCombo(String label, String[] items) {
            JLabel l = new JLabel(label);
            l.setForeground(Color.BLACK);

            JComboBox<String> combo = new JComboBox<>(items);
            combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            combo.setBackground(Color.WHITE);

            add(l);
            add(combo);
            add(Box.createRigidArea(new Dimension(0, 10)));

            return combo;
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

    private void regresarMenuBotonActionPerformed(java.awt.event.ActionEvent evt) {
        coordinadorF.rergesarAlMenu();
    }

    private void registrar() {
        //validaciones de campos
        String nombre = txtRegNombre.getText().trim();
        String precioTexto = txtRegPrecio.getText().trim();
        String descripcion = txtRegDescripcion.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre en el panel de registro está vacío");
            return;
        }
        if (precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El precio del producto no puede estar vacio");
            return;
        }
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "la descripcion del producto no puede estar vacio");
            return;
        }

        if (cbTipo.getSelectedIndex() == 0) { 
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un tipo de producto.");
            cbTipo.requestFocus();
            return;
        }

        // Si todo está bien, obtienes el valor así:
        String tipoSeleccionado = cbTipo.getSelectedItem().toString();
        System.out.println("Tipo: " + tipoSeleccionado);

        try {
            // Intentar convertir a número (double)
            double precio = Double.parseDouble(precioTexto);

            //Validación lógica (No puede ser gratis o negativo en este contexto)
            if (precio <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número mayor a 0.");
                txtRegPrecio.requestFocus();
                return;
            }

        } catch (NumberFormatException e) {
            // Si el usuario escribió letras o caracteres extraños
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido (ejemplo: 150.50).");
            txtRegPrecio.requestFocus();
            return;
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
