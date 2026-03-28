/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import com.sun.java.accessibility.util.AWTEventMonitor;
import entidades.ClienteFrecuente;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.*;
import javax.swing.JPanel;
import utilerias.BotonUtileria;
import utilerias.FramesUtileria;
import utilerias.UtileriasPaneles;

/**
 *
 * @author aaron
 */
public class FrmEdicionClienteFrecuente extends JDialog{
    private CoordinadorFrames coordinadorF;
    private ClienteFrecuente clienteActual;
    // Componentes (Cajas de texto)
    private JTextField txtNombre, txtApellidoP, txtApellidoM, txtTelefono, txtPuntos;
    
    public FrmEdicionClienteFrecuente(Frame parent, boolean modal, CoordinadorFrames coordinadorF) {
        super(parent, modal);
        this.coordinadorF = coordinadorF;
        
        // 1. Configuración básica usando tu utilería
        FramesUtileria.configurarVentanaGestion(this, "Modificacion de datos");
        this.setSize(400, 450); // Ajusta el tamaño según necesites
        this.setLayout(new BorderLayout());

        // 2. Panel Central (Formulario)
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Inicializar campos
        txtNombre = new JTextField();
        txtApellidoP = new JTextField();
        txtApellidoM = new JTextField();
        txtTelefono = new JTextField();
        txtPuntos = new JTextField();
        txtPuntos.setEditable(false); // Los puntos suelen ser automáticos, pero se muestran

        panelFormulario.add(new JLabel("Nombres:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido Paterno:"));
        panelFormulario.add(txtApellidoP);
        panelFormulario.add(new JLabel("Apellido Materno:"));
        panelFormulario.add(txtApellidoM);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Puntos Actuales:"));
        panelFormulario.add(txtPuntos);

        add(panelFormulario, BorderLayout.CENTER);

        // 3. Panel Inferior (Botones usando tus utilerías)
        JButton btnCancelar = BotonUtileria.botonUtileriaGenérico("Cancelar");
        JButton btnAceptar = BotonUtileria.botonUtileriaGenérico("Aceptar");

        JPanel panelOpciones = UtileriasPaneles.panelOpciones();
        panelOpciones.add(btnCancelar);
        panelOpciones.add(btnAceptar);
        add(panelOpciones, BorderLayout.SOUTH);

        // 4. Eventos
        btnCancelar.addActionListener(e -> {
            coordinadorF.cancelarGestionClienteFrecuente(); // Simplemente cerramos
        });

        btnAceptar.addActionListener(e -> {
            guardarCambios();
        });
    }

    /**
     * Método para llenar los campos cuando el coordinador abre la ventana
     */
    public void cargarDatos(ClienteFrecuente cliente) {
        this.clienteActual = cliente;
        txtNombre.setText(cliente.getNombres());
        txtApellidoP.setText(cliente.getApellidoPaterno());
        txtApellidoM.setText(cliente.getApellidoMaterno());
        txtTelefono.setText(cliente.getTelefono());
        txtPuntos.setText(String.valueOf(cliente.getPuntos()));
    }

    /**
     * Recolecta los datos de los campos y se los pasa al coordinador
     */
    private void guardarCambios() {
        // Actualizamos el objeto con lo que hay en los campos
        clienteActual.setNombres(txtNombre.getText());
        clienteActual.setApellidoPaterno(txtApellidoP.getText());
        clienteActual.setApellidoMaterno(txtApellidoM.getText());
        clienteActual.setTelefono(txtTelefono.getText());
        
        // El coordinador de negocio se encargará de la persistencia
        // coordinadorF.getCoordinadorNegocio().actualizarCliente(clienteActual);
        
        JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
        this.dispose();
    }
    
}
