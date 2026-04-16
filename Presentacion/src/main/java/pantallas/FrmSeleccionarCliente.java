/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import controlador.CoordinadorNegocio;
import dtos.ClienteDTO;
import dtos.ComandaDTO;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luiscarlosbeltran
 */
public class FrmSeleccionarCliente extends JDialog {

    private ComandaDTO nuevaComanda;
    private CoordinadorFrames coordinadorF;
    private CoordinadorNegocio coordinadorN;

    private JTextField txtIdCliente;
    private JTable tablaClientes;
    private JTextField txtBuscar;
    private JComboBox<String> cbFiltro;

    private JButton btnCancelar;
    private JButton btnOmitir;
    private JButton btnAgregar;

    public FrmSeleccionarCliente(JFrame parent,
                             CoordinadorFrames coordinadorF,
                             CoordinadorNegocio coordinadorN,
                             ComandaDTO nuevaComanda) {

    super(parent, "Seleccionar cliente", true);

    this.coordinadorF = coordinadorF;
    this.coordinadorN = coordinadorN;
    this.nuevaComanda = nuevaComanda;

    configurarVentana();
    inicializarComponentes();
    cargarInicial();

    setVisible(true);
}

    private void configurarVentana() {
        setSize(900, 600);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        setResizable(false);
    }

    private void inicializarComponentes() {

        JLabel titulo = new JLabel("Seleccione cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));

        add(titulo, BorderLayout.NORTH);

        // ---------------- PANEL CENTRO ----------------
        JPanel centro = new JPanel(new BorderLayout());

        JPanel filtros = new JPanel();

        filtros.add(new JLabel("Buscar:"));

        txtBuscar = new JTextField(20);
        filtros.add(txtBuscar);

        cbFiltro = new JComboBox<>(
                new String[]{"Todos","Nombre","Correo","Numero"});
        filtros.add(cbFiltro);

        filtros.add(new JLabel("ID Cliente:"));

        txtIdCliente = new JTextField(8);
        filtros.add(txtIdCliente);

        centro.add(filtros, BorderLayout.NORTH);

        tablaClientes = new JTable();
        tablaClientes.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID","Nombres","Apellido P.","Apellido M.",
                    "Telefono","Correo","Puntos"
                }
        ));

        tablaClientes.setDefaultEditor(Object.class, null);

        JScrollPane scroll = new JScrollPane(tablaClientes);
        centro.add(scroll, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        // ---------------- BOTONES ABAJO ----------------
        btnCancelar = new JButton("Cancelar");
        btnOmitir = new JButton("Omitir");
        btnAgregar = new JButton("Agregar");

        JPanel sur = new JPanel();
        sur.add(btnCancelar);
        sur.add(btnOmitir);
        sur.add(btnAgregar);

        add(sur, BorderLayout.SOUTH);

        // ---------------- EVENTOS ----------------
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscar();
            }
        });

        tablaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int fila = tablaClientes.getSelectedRow();

                if (fila != -1) {
                    txtIdCliente.setText(
                            tablaClientes.getValueAt(fila, 0).toString()
                    );
                }
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        btnOmitir.addActionListener(e -> guardarSinCliente());

        btnAgregar.addActionListener(e -> guardarConCliente());
    }

    private void cargarInicial() {
        buscar();
    }

    private void buscar() {

        String texto = txtBuscar.getText().trim().toLowerCase();
        String filtro = cbFiltro.getSelectedItem().toString();

        List<ClienteDTO> lista =
                coordinadorF.solicitarBusqueda(texto, filtro);

        llenarTabla(lista);
    }

    private void llenarTabla(List<ClienteDTO> lista) {

        DefaultTableModel modelo =
                (DefaultTableModel) tablaClientes.getModel();

        modelo.setRowCount(0);

        for (ClienteDTO c : lista) {

            modelo.addRow(new Object[]{
                c.getId(),
                c.getNombres(),
                c.getApellidoPaterno(),
                c.getApellidoMaterno(),
                c.getTelefono(),
                c.getCorreo(),
                c.getPuntos()
            });
        }
    }
    
    private void guardarSinCliente() {
        try {
            
            nuevaComanda.setIdCliente(null);
            coordinadorN.registrarComanda(nuevaComanda);
            coordinadorN.ocuparMesa(nuevaComanda.getIdMesa());
            
            JOptionPane.showMessageDialog(this, "Comanda registrada correctamente");
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private void guardarConCliente() {

        try {

            String textoId = txtIdCliente.getText().trim();

            if (textoId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID de cliente");
                return;
            }

            Long idCliente = Long.valueOf(textoId);

            nuevaComanda.setIdCliente(idCliente);

            coordinadorN.registrarComanda(nuevaComanda);
            coordinadorN.ocuparMesa(nuevaComanda.getIdMesa());
            
            JOptionPane.showMessageDialog(this, "Comanda registrada correctamente");

            dispose();

        }catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
}
