/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import dtos.ClienteDTO;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.*;
import javax.swing.JPanel;
import utilerias.BotonUtileria;
import utilerias.FramesUtileria;
import utilerias.UtileriasPaneles;

/**
 * dialogo que muestra un formulario para registrar un cliente 
 * @author aaron
 */
public class DlgRegistrarCliente extends JDialog{
    private CoordinadorFrames coordinadorF;
    private ClienteDTO clienteActual;
    // Componentes (Cajas de texto) todos junto y ya
    private JTextField txtNombre, txtApellidoP, txtApellidoM, txtTelefono, txtCorreo;
    
    public DlgRegistrarCliente(Frame parent, boolean modal, CoordinadorFrames coordinadorF) {
        super(parent, modal);
        this.coordinadorF = coordinadorF;
        
        // 1. Configuración básica usando tu utilería
        FramesUtileria.configurarVentanaGestion(this, "Registro de cliente");
        this.setSize(400, 450); // ajuste de tamaño
        this.setLayout(new BorderLayout());

        // 2. Panel Central
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Inicializar campos gg
        txtNombre = new JTextField();
        txtApellidoP = new JTextField();
        txtApellidoM = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        panelFormulario.add(new JLabel("Nombres:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido Paterno:"));
        panelFormulario.add(txtApellidoP);
        panelFormulario.add(new JLabel("Apellido Materno:"));
        panelFormulario.add(txtApellidoM);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Correo (opcional):"));
        panelFormulario.add(txtCorreo);

        add(panelFormulario, BorderLayout.CENTER);

        // 3. Panel Inferior (Botones usando  utilerías)
        JButton btnCancelar = BotonUtileria.botonUtileriaGenérico("Cancelar");
        JButton btnAceptar = BotonUtileria.botonUtileriaGenérico("Aceptar");

        JPanel panelOpciones = UtileriasPaneles.panelOpciones();
        panelOpciones.add(btnCancelar);
        panelOpciones.add(btnAceptar);
        add(panelOpciones, BorderLayout.SOUTH);

        // 4. Eventos
        btnCancelar.addActionListener(e -> {
            coordinadorF.cancelarGestionClienteFrecuente(); 
        });

        btnAceptar.addActionListener(e -> {
            guardarCambios();
        });
    }

    /**
     * Método para llenar los campos cuando el coordinador abre la ventana
     */
    public void cargarDatos(ClienteDTO cliente) {
        this.clienteActual = cliente;
        txtNombre.setText(cliente.getNombres());
        txtApellidoP.setText(cliente.getApellidoPaterno());
        txtApellidoM.setText(cliente.getApellidoMaterno());
        txtTelefono.setText(cliente.getTelefono());
        txtCorreo.setText(cliente.getCorreo());
    }

    /**
     * Recolecta los datos de los campos y se los pasa al coordinador
     */
    private void guardarCambios() {
        try {
            
            //validacioneds que los campso no esten vacios
            if (txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
                return;
            }
            
            if (txtApellidoP.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El apellido paterno es obligatorio");
                return;
            }
            
            if (txtTelefono.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El teléfono es obligatorio");
                return;
            }
            
            //validaciones para que tengan contendio esperado
            if (!txtNombre.getText().trim().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(this, "El nombre solo debe contener letras");
                return;
            }

            if (!txtApellidoP.getText().trim().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(this, "El apellido paterno solo debe contener letras");
                return;
            }

            if (!txtApellidoM.getText().trim().isEmpty() && !txtApellidoM.getText().trim().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(this, "El apellido materno solo debe contener letras");
                return;
            }
            
            if (!txtTelefono.getText().trim().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El teléfono solo debe contener números");
                return;
            }
            
            ClienteDTO dto = new ClienteDTO();
            
            dto.setNombres(txtNombre.getText().trim());
            dto.setApellidoPaterno(txtApellidoP.getText().trim());
            dto.setApellidoMaterno(txtApellidoM.getText().trim());
            dto.setTelefono(txtTelefono.getText().trim());
            
            if (!txtCorreo.getText().trim().isEmpty()) {
                dto.setCorreo(txtCorreo.getText().trim());
            } else {
                dto.setCorreo(null);
            }
            
            dto.setFechaRegistro(LocalDate.now());
            dto.setTipoCliente("Frecuente");
            dto.setPuntos(0.0);
            dto.setTotalGastado(0.0);
            
            coordinadorF.registrarCliente(dto);

            JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente");
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar cliente");
            e.printStackTrace();
        }
    }

    public ClienteDTO getClienteActual() {
        return clienteActual;
    }
    
}
