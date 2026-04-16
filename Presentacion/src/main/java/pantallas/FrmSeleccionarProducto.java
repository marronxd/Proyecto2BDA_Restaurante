/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import conexion.ConexionBD;
import controlador.CoordinadorNegocio;
import dtos.ComandaDTO;
import dtos.DetalleComandaDTO;
import dtos.ProductoDTO;
import entidades.Producto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author luiscarlosbeltran
 */
public class FrmSeleccionarProducto extends JDialog {

    private ComandaDTO nuevaComanda;

    private JPanel panelProductos;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private CoordinadorFrames coordinadorF;
    private CoordinadorNegocio coordinadorN;

    private HashMap<Long, JSpinner> mapaCantidades = new HashMap<>();
    private HashMap<Long, JCheckBox> mapaChecks = new HashMap<>();
    private HashMap<Long, ProductoDTO> mapaProductos = new HashMap<>();
    private HashMap<Long, JTextArea> mapaComentarios = new HashMap<>();

    public FrmSeleccionarProducto(JFrame parent,
                              CoordinadorFrames coordinadorF,
                              CoordinadorNegocio coordinadorN,
                              ComandaDTO nuevaComanda) {

    super(parent, "Seleccionar productos", true);

    this.coordinadorF = coordinadorF;
    this.coordinadorN = coordinadorN;
    this.nuevaComanda = nuevaComanda;

    configurarVentana();
    inicializarComponentes();
    cargarProductos();

    setVisible(true);
}

    private void configurarVentana() {
        setSize(800, 550);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        setResizable(false);
    }

    private void inicializarComponentes() {

        JLabel titulo = new JLabel("Seleccione productos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));

        add(titulo, BorderLayout.NORTH);

        panelProductos = new JPanel(new GridLayout(0, 3, 15, 15));
        panelProductos.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panelProductos.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(panelProductos);
        add(scroll, BorderLayout.CENTER);

        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Confirmar");

        btnCancelar.addActionListener(e -> dispose());

        btnConfirmar.addActionListener(e -> confirmarSeleccion());

        JPanel sur = new JPanel();
        sur.add(btnCancelar);
        sur.add(btnConfirmar);

        add(sur, BorderLayout.SOUTH);
    }

    private void cargarProductos() {

        List<ProductoDTO> productos = obtenerProductosDTO();

        for (ProductoDTO p : productos) {
            panelProductos.add(crearTarjetaProducto(p));
        }

        panelProductos.revalidate();
        panelProductos.repaint();
    }

    //se que la capa presentacion no debe interactuar con la BD, PEROOO...
    //pero no tenemos nada del modulo productos, el que sabe sabe :(
    private List<ProductoDTO> obtenerProductosDTO() {

        List<ProductoDTO> lista = new ArrayList<>();

        EntityManager em = ConexionBD.crearConexion();

        try {

            List<Producto> productos =
                    em.createQuery("SELECT p FROM Producto p", Producto.class)
                      .getResultList();

            for (Producto p : productos) {

                ProductoDTO dto = new ProductoDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getPrecio()
                );

                lista.add(dto);
            }

        } finally {
            em.close();
        }

        return lista;
    }

    private JPanel crearTarjetaProducto(ProductoDTO producto) {

    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    card.setBackground(new Color(245,245,245));

    JLabel lblNombre = new JLabel(producto.getNombre(), SwingConstants.CENTER);
    lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel lblPrecio = new JLabel("$" + producto.getPrecio());
    lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);

    JCheckBox check = new JCheckBox("Agregar");
    check.setAlignmentX(Component.CENTER_ALIGNMENT);

    SpinnerNumberModel modelo = new SpinnerNumberModel(1,1,50,1);
    JSpinner spinner = new JSpinner(modelo);
    spinner.setMaximumSize(new Dimension(60,30));
    spinner.setAlignmentX(Component.CENTER_ALIGNMENT);

    JTextArea txtComentario = new JTextArea(3, 12);
    txtComentario.setLineWrap(true);
    txtComentario.setWrapStyleWord(true);

    JScrollPane scrollTexto = new JScrollPane(txtComentario);
    scrollTexto.setMaximumSize(new Dimension(160,60));

    JLabel lblComentario = new JLabel("Comentarios:");
    lblComentario.setAlignmentX(Component.CENTER_ALIGNMENT);

    mapaChecks.put(producto.getId(), check);
    mapaCantidades.put(producto.getId(), spinner);
    mapaProductos.put(producto.getId(), producto);
    mapaComentarios.put(producto.getId(), txtComentario);

    card.add(Box.createVerticalStrut(10));
    card.add(lblNombre);
    card.add(Box.createVerticalStrut(10));
    card.add(lblPrecio);
    card.add(Box.createVerticalStrut(10));
    card.add(spinner);
    card.add(Box.createVerticalStrut(10));
    card.add(lblComentario);
    card.add(Box.createVerticalStrut(5));
    card.add(scrollTexto);
    card.add(Box.createVerticalStrut(10));
    card.add(check);
    card.add(Box.createVerticalStrut(10));

    return card;
}

    private void confirmarSeleccion() {

        List<DetalleComandaDTO> detalles = new ArrayList<>();
        double total = 0;

        for (Long id : mapaChecks.keySet()) {

            JCheckBox check = mapaChecks.get(id);

            if (check.isSelected()) {

                ProductoDTO producto = mapaProductos.get(id);

                int cantidad = (Integer) mapaCantidades.get(id).getValue();

                double subtotal = cantidad * producto.getPrecio();

                String comentario = mapaComentarios.get(id).getText().trim();

                DetalleComandaDTO detalle = new DetalleComandaDTO(
                    cantidad,
                    comentario,
                    producto.getPrecio(),
                    subtotal,
                    producto.getId()
                );

                detalles.add(detalle);

                total += subtotal;
            }
        }

        if (detalles.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Las comandas deben llevar al menos un producto");
            return;
        }

        nuevaComanda.setDetalles(detalles);
        nuevaComanda.setTotalAcumulado(total);

        JOptionPane.showMessageDialog(this,
                "Productos agregados correctamente");

        dispose();

        new FrmSeleccionarCliente(
        (JFrame) getParent(),
        coordinadorF,
        coordinadorN,
        nuevaComanda
        );
    }
}
