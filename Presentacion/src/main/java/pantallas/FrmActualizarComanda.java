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
 * frame que se abre al actualizar una comanda
 * @author luiscarlosbeltran
 */
public class FrmActualizarComanda extends JDialog {

    private ComandaDTO comanda;

    private JPanel panelProductos;
    private JButton btnCancelar;
    private JButton btnModificar;

    private CoordinadorFrames coordinadorF;
    private CoordinadorNegocio coordinadorN;

    private HashMap<Long, JCheckBox> mapaChecks = new HashMap<>();
    private HashMap<Long, JSpinner> mapaCantidades = new HashMap<>();
    private HashMap<Long, JTextArea> mapaComentarios = new HashMap<>();
    private HashMap<Long, ProductoDTO> mapaProductos = new HashMap<>();

    public FrmActualizarComanda(
            JFrame parent,
            CoordinadorFrames coordinadorF,
            CoordinadorNegocio coordinadorN,
            ComandaDTO comanda) {

        super(parent, "Actualizar Comanda", true);

        this.coordinadorF = coordinadorF;
        this.coordinadorN = coordinadorN;
        this.comanda = comanda;

        setSize(800,550);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        init();
        cargarProductos();
        precargarDetalles();

        setVisible(true);
    }
    
    /**
     * metodo que inicializa los componentes de la interfaz
     */
    private void init() {

        JLabel titulo = new JLabel("Modificar Comanda");

        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        add(titulo, BorderLayout.NORTH);

        panelProductos = new JPanel(new GridLayout(0,3,15,15));

        JScrollPane scroll = new JScrollPane(panelProductos);

        add(scroll, BorderLayout.CENTER);

        btnCancelar = new JButton("Cancelar");
        btnModificar = new JButton("Modificar");

        btnCancelar.addActionListener(e -> dispose());
        btnModificar.addActionListener(e -> modificar());

        JPanel sur = new JPanel();
        sur.add(btnCancelar);
        sur.add(btnModificar);

        add(sur, BorderLayout.SOUTH);
    }
    
    /**
     * metodo para cargar productos
     */
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
    /**
     * metodo para obtener productos, deberia ir en dao de productos pero pues....
     * y de paso los convierte de entity a dto, o sea es del dao y adapter combinado
     * @return 
     */
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

    /**
     * metodo que agarra los detalles de un producto y les crea una tarjeta para 
     * @param producto
     * @return 
     */
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
    
    /**
     * este metodo agarra los detalles que ya venian en la comanda que se va a modificar
     * y los plasma en la interfaz, para que aparezca como se habia guardado el detallecomanda
     */
    private void precargarDetalles() {

        for (DetalleComandaDTO d : comanda.getDetalles()) {

            Long idProd = d.getIdProducto();

            JCheckBox check = mapaChecks.get(idProd);
            JSpinner spinner = mapaCantidades.get(idProd);
            JTextArea txt = mapaComentarios.get(idProd);

            if (check != null) check.setSelected(true);
            if (spinner != null) spinner.setValue(d.getCantidadProducto());
            if (txt != null) txt.setText(d.getComentarios());
        }
    }
    
    /**
     * modifica los detallecomanda segun como se haya dejado en la parte de actualizar una comanda
     */
    private void modificar() {

        try {

        List<DetalleComandaDTO> detalles = new ArrayList<>();
        double total = 0;

        for (Long id : mapaChecks.keySet()) {

            JCheckBox check = mapaChecks.get(id);

            if (check.isSelected()) {

                ProductoDTO p = mapaProductos.get(id);

                int cantidad =
                        (Integer) mapaCantidades.get(id).getValue();

                String comentario =
                        mapaComentarios.get(id).getText();

                double subtotal =
                        cantidad * p.getPrecio();

                DetalleComandaDTO d =
                        new DetalleComandaDTO(
                                cantidad,
                                comentario,
                                p.getPrecio(),
                                subtotal,
                                id
                        );

                    detalles.add(d);

                    total += subtotal;
                }
            }

            if(detalles.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un producto");
                return;
            }

            comanda.setDetalles(detalles);
            comanda.setTotalAcumulado(total);

            coordinadorN.actualizarComanda(comanda);

            JOptionPane.showMessageDialog(this,
                "Comanda actualizada");

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}