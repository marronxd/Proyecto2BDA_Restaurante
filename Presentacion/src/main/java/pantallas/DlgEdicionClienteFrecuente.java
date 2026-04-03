/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import dtos.ClienteDTO;
import excepciones.FachadaException;
import excepciones.NegocioException;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.*;
import javax.swing.JPanel;
import utilerias.BotonUtileria;
import utilerias.FramesUtileria;
import utilerias.UtileriasPaneles;

/**
 *  JDialog que muestra el formulario de edicion de un cliente existente
 *  Permite introducir las entradas pertinentes para que un cliente pueda ser editado
 * @author aaron
 */
public class DlgEdicionClienteFrecuente extends JDialog{
    private CoordinadorFrames coordinadorF;
            
    // Componentes (Cajas de texto) todos junto y ya
    private JTextField txtNombre, txtApellidoP, txtApellidoM, txtTelefono, txtPuntos, txtCorreo;
    
    //auxiliar
    private ClienteDTO clienteA;
    /**
     * Constructor que arma el JDialog
     * @param parent la ventana/frame en la cual se abre el JDialog
     * @param modal si es modal o no, es decir, si se bloquea el frame detras o no
     * @param coordinadorF objeto 
     */
    public DlgEdicionClienteFrecuente(Frame parent, boolean modal, CoordinadorFrames coordinadorF) {
        super(parent, modal);
        this.coordinadorF = coordinadorF;

        
        // 1. Configuración básica usando tu utilería
        FramesUtileria.configurarVentanaGestion(this, "Modificacion de datos");
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
        txtPuntos = new JTextField();
        txtCorreo = new JTextField();
        txtPuntos.setEditable(false); // Los puntos se muestran pero no se editan

        panelFormulario.add(new JLabel("Nombres:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido Paterno:"));
        panelFormulario.add(txtApellidoP);
        panelFormulario.add(new JLabel("Apellido Materno:"));
        panelFormulario.add(txtApellidoM);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Correo:"));
        panelFormulario.add(txtCorreo);
        panelFormulario.add(new JLabel("Puntos Actuales:"));
        panelFormulario.add(txtPuntos);

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
            
            try {
                guardarCambios();
            } catch (FachadaException ex) {
                Logger.getLogger(DlgEdicionClienteFrecuente.class.getName()).log(Level.SEVERE, null, ex);
            }
            coordinadorF.aceptarGestionClienteFrecuente();
        });
        
    }
    
    /**
     * Método para llenar los campos cuando el coordinador abre la ventana
     */
    public void cargarDatos(ClienteDTO cliente) {
        clienteA = cliente;
        txtNombre.setText(cliente.getNombres());
        txtApellidoP.setText(cliente.getApellidoPaterno());
        txtApellidoM.setText(cliente.getApellidoMaterno());
        txtTelefono.setText(cliente.getTelefono());
        txtCorreo.setText(cliente.getCorreo());
        txtPuntos.setText(String.valueOf(cliente.getPuntos()));
    }
    
    /**
     * Recolecta los datos de los campos y se los pasa al coordinador
     */
    private void guardarCambios()throws FachadaException {
        ClienteDTO clientedto = new ClienteDTO();
        // Actualizamos el objeto con lo que hay en los campos
        clientedto.setId(clienteA.getId());
        clientedto.setNombres(txtNombre.getText());
        clientedto.setApellidoPaterno(txtApellidoP.getText());
        clientedto.setApellidoMaterno(txtApellidoM.getText());
        clientedto.setTelefono(txtTelefono.getText());
        clientedto.setCorreo(txtCorreo.getText());
        clientedto.setPuntos(clienteA.getPuntos());
        
        clientedto.setTipoCliente(clienteA.getTipoCliente());
        //El coordinador de negocio se encargará de la persistencia
        try{
            coordinadorF.enviarCambios(clientedto);
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
            this.dispose();
        }catch(NegocioException e){
            throw new FachadaException("Error de guardado de datos: " + e.getMessage());
        }
        
        
    }

    
}
