/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import controlador.CoordinadorNegocio;
import dtos.ComandaDTO;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author luiscarlosbeltran
 */
public class FrmModificarComanda extends JDialog {

    private CoordinadorFrames coordinadorF;
    private CoordinadorNegocio coordinadorN;

    private JButton btnCancelar;
    private JButton btnActualizar;
    private JButton btnCerrar;

    public FrmModificarComanda(JFrame parent,
                               CoordinadorFrames coordinadorF,
                               CoordinadorNegocio coordinadorN) {

        super(parent, "Modificar Comanda", true);

        this.coordinadorF = coordinadorF;
        this.coordinadorN = coordinadorN;

        configurarVentana();
        inicializarComponentes();

        setVisible(true);
    }

    private void configurarVentana() {
        setSize(950, 650);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        setResizable(false);
    }

    private void inicializarComponentes() {

        JLabel titulo = new JLabel("Modificar Comanda", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));

        add(titulo, BorderLayout.NORTH);

        //el panel buscador en el centro
        PnlBuscadorComandas pnlBuscador =
                new PnlBuscadorComandas(coordinadorF);

        add(pnlBuscador, BorderLayout.CENTER);

        
        //botones
        btnCancelar = new JButton("Cancelar");
        btnActualizar = new JButton("Actualizar");
        btnCerrar = new JButton("Cerrar");

        JPanel sur = new JPanel();
        sur.add(btnCancelar);
        sur.add(btnActualizar);
        sur.add(btnCerrar);

        add(sur, BorderLayout.SOUTH);
        
        //actionlisteners botones
        btnCancelar.addActionListener(e -> dispose());

        btnActualizar.addActionListener(e -> abrirModificar());

        btnCerrar.addActionListener(e -> cerrarComanda());
       
    }
    
    /**
     * metodo para cerrrar una comanda
     */
    private void cerrarComanda() {

    try {

        String texto = JOptionPane.showInputDialog(
                this,
                "Ingrese el ID de la comanda a cerrar:"
        );

        // Canceló
        if (texto == null) {
            return;
        }

        texto = texto.trim();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un ID"
            );
            return;
        }

        Long idComanda = Long.valueOf(texto);

        coordinadorN.cerrarComanda(idComanda);

        JOptionPane.showMessageDialog(
                this,
                "Comanda cerrada correctamente"
        );

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
                this,
                "ID inválido"
        );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    /**
     * metodo para abrir la parte de modificar comandas
     */
    private void abrirModificar() {
        try {

            String texto = JOptionPane.showInputDialog(this, "Ingrese ID de la comanda a modificar:");
            if (texto == null) return;
            texto = texto.trim();

            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this,"Debe ingresar un ID");
                return;
            }
            
            Long id = Long.valueOf(texto);

            ComandaDTO comanda = coordinadorN.buscarComandaPorId(id);

            if (comanda == null) {
                JOptionPane.showMessageDialog(this, "Comanda no encontrada");
            return;
            }

            new FrmActualizarComanda(
                (JFrame) getParent(),
                coordinadorF,
                coordinadorN,
                comanda
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    
}
