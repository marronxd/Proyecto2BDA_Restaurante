/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import dtos.IngredienteDTO;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.UnidadMedida;
import utilerias.BotonUtileria;
import utilerias.FramesUtileria;
import utilerias.UtileriasPaneles;

/**
 * Dialogo que sive como formulario para modificar un ingrediente existente
 * @author aaron
 */
public class DlgModificarIngrediente extends JDialog{
        
    private CoordinadorFrames coordinadorF;
    // Componentes (Cajas de texto) todos junto y ya
    private JTextField txtNombre, txtCantidad, txtUrl;
    //los combobox
    private JComboBox<UnidadMedida> cbxUnidadMedida;
    private JComboBox<EstadoIngrediente> cbxEstado;
    
    // crear y configurar el filechooser
    private JFileChooser fileChooser = UtileriasPaneles.generarFileChooserImagen();
    
    // label donde se escalará la imagen que se almacene
    private JLabel lblImagenEscalada;
    
    // auxiliar para guardar la ruta del archivo que seleccione
    private File archivo;
    
    // auxiliar para mantener el id del ingrediente que obtenemos
    private Long idIngredienteActualizar;
    
    // -- para que el usuario pueda cargar una imagen -- 
    // modal es que bloquea la interaccion con el fondo
    public DlgModificarIngrediente(Frame parent, boolean modal, CoordinadorFrames coordinadorF, Long id) {
        super(parent, modal);
        this.coordinadorF = coordinadorF;
        this.idIngredienteActualizar = id;
        
        // Configuración del formulario
        FramesUtileria.configurarVentanaGestion(this, "Actualizar de ingredientes");
        this.setSize(400, 450); // ajuste de tamaño
        this.setLayout(new BorderLayout());

        // En cuanto al panel central, agregarle un layout para que se vea con 2  columnas por fila
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Inicializar campos gg ---
        
        lblImagenEscalada = new JLabel(); // inicializa el label de la imagen (para el formulario)

        cbxEstado = new JComboBox();
        cbxUnidadMedida = new JComboBox();
        
        txtNombre = new JTextField();
        txtCantidad = new JTextField();
        txtUrl = new JTextField();
        txtUrl.setEditable(false); // no se puede editar por el usuario, solo ver
        
       
        
        // --- agregar componentes al panel central o mas bien, del formulario ---
        
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Cantidad de stock:"));
        panelFormulario.add(txtCantidad);
        panelFormulario.add(new JLabel("Ruta:"));
        panelFormulario.add(txtUrl);
        // --- Agregar combobox al panel central ---
        panelFormulario.add(cbxUnidadMedida);
        panelFormulario.add(cbxEstado);
        // --- Agregar label ---
        panelFormulario.add(lblImagenEscalada);
        // --- Llenar los combobox con informacion ---
        cbxEstado.setModel(new DefaultComboBoxModel<>(EstadoIngrediente.values()));
        cbxUnidadMedida.setModel(new DefaultComboBoxModel<>(UnidadMedida.values()));
        
        // --- Agregar  panel del formulario al centro del dialogo ---
        add(panelFormulario, BorderLayout.CENTER);

        // --- Creación de botones ---
        JButton btnCancelar = BotonUtileria.botonUtileriaGenérico("Cancelar");
        JButton btnAceptar = BotonUtileria.botonUtileriaGenérico("Aceptar");
        JButton btnSeleccionImagen = BotonUtileria.botonUtileriaGenérico("Seleccionar imagen");
        
        panelFormulario.add(btnSeleccionImagen); // agrega el boton de seleccion al formulario

        // --- Crear panel donde van las opciones de gestion ---
        JPanel panelOpciones = UtileriasPaneles.panelOpciones();
        
        // --- Añadir los botones al panel ---
        panelOpciones.add(btnCancelar);
        panelOpciones.add(btnAceptar);
        // --- Lo acomodamos abajo del buscador ---
        add(panelOpciones, BorderLayout.SOUTH);
        
        // --- precargar datos ---
        cargarDatos();
        
        // --- Eventos de interacción ---
        
        // --- Boton de cancelar ---
        btnCancelar.addActionListener(e -> {
            coordinadorF.cancelarEdicionIngrediente();
        });
        // --- Boton de aceptar el registro ---
        btnAceptar.addActionListener(e -> {
            guardarCambios();
            coordinadorF.aceptarEdicionIngrediente();
        });
        // --- Boton de seleccionar imagen ---
        btnSeleccionImagen.addActionListener(e ->{
            int ventana = fileChooser.showOpenDialog(null);
            if(ventana == JFileChooser.APPROVE_OPTION){ // si se seleciona un archivo
                this.archivo = fileChooser.getSelectedFile();
                txtUrl.setText(archivo.getPath()); // al textfield se le setea la ruta del archivo
                ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                Image imagen = icon.getImage().getScaledInstance(300, 350, Image.SCALE_SMOOTH); // reescalar la imagen
                lblImagenEscalada.setIcon(new ImageIcon(imagen)); // setear foto al lbl
            }
        });
        
        this.setLocationRelativeTo(null); // para que salga en el medio
    }

    /**
     * Método auxiliar: Toma el ingrediente que se va a modificar y carga los datos en los campos
     */
    public void cargarDatos(){
        IngredienteDTO ingredienteActualizar = coordinadorF.solicitarObtenerIngrediente(idIngredienteActualizar);
        // --- setear campos a los textfield ---
        txtNombre.setText(ingredienteActualizar.getNombre());
        txtCantidad.setText(String.valueOf(ingredienteActualizar.getCantidad_stock())); // parseo a string
        txtUrl.setText(ingredienteActualizar.getUrl());
        // --- Setearle el valor que tiene de estado y unidad de medida al combobox ---
        cbxEstado.setSelectedItem(ingredienteActualizar.getEstado());
        cbxUnidadMedida.setSelectedItem(ingredienteActualizar.getUnidad_medida());
        // --- setear la imagen al label ---
        lblImagenEscalada.setIcon(UtileriasPaneles.configurarImagen(ingredienteActualizar.getImagen()));
    }
    
    
    /**
     *  Método auxiliar: Recolecta los datos de los campos y se los pasa al coordinador
     */
    private void guardarCambios() {
        // sacar los datos binarios del archivo
        byte[] datos = null;
        if (this.archivo != null) {
            try {
                datos = Files.readAllBytes(this.archivo.toPath()); // lee los bytes del archivo
            } catch (IOException ex) {
                System.err.println("Error al leer la imagen: " + ex.getMessage());
            }
        }else{ // en los casos que se mantenga la misma imagen
            datos = coordinadorF.solicitarObtenerIngrediente(idIngredienteActualizar).getImagen();
        }
        // Extraer los Enums haciendo un CAST
        UnidadMedida unidadSeleccionada = (UnidadMedida) cbxUnidadMedida.getSelectedItem();
        EstadoIngrediente estadoSeleccionado = (EstadoIngrediente) cbxEstado.getSelectedItem();
        
        // --- envio de informacion al coordinador ---
        coordinadorF.solicitarActualizarIngrediente(
                idIngredienteActualizar,
                txtNombre.getText(), 
                Double.valueOf(txtCantidad.getText()), 
                unidadSeleccionada, 
                estadoSeleccionado, 
                txtUrl.getText(), 
                datos
                );  
    }
}
