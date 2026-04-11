/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import controlador.CoordinadorNegocio;
import dtos.ClienteDTO;
import dtos.IngredienteDTO;
import excepciones.FachadaException;
import excepciones.NegocioException;
import java.awt.Component;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pantallas.*;
import pantallas.PnlClienteFrecuente;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.UnidadMedida;
import utilerias.FramesUtileria;

/**
 * Esta clase se encarga de coordinar el flujo entre frames, paneles y cualquier elemento 
 * grafico que vea el usuario.
 * @author aaron
 */
public class CoordinadorFrames {
    // inyeccion de ncoordinador negocio
    private CoordinadorNegocio coordinadorN;
    
    private FrmMenuPrincipal frmMenuPrincipal;
    private FrmMenuAdministrador frmMenuAdministrador;
    private FrmInicioSesion frmInicioSesion;
    private DlgEdicionClienteFrecuente dlgEdicionCliente;
    private DlgRegistrarCliente notengotiemposorryprofe;
    private PnlClienteFrecuente pnlClienteFrecuente;
    private FrmInicioSesionEmpleado frmInicioSesionEmpleado;
    // --- ingredientes ---
    private PnlIngredientes pnlIngredientes;
    private DlgRegistrarIngrediente dlgRegistrarIngrediente;
    private DlgModificarIngrediente dlgModificarIngrediente;
    
    public CoordinadorFrames(){
        this.coordinadorN = new CoordinadorNegocio();
    }
    /**
     * Método para iniciar el frame principal del menu
     */
    public void IniciarSistema(){
        if(frmMenuPrincipal == null){
            frmMenuPrincipal = new FrmMenuPrincipal(this);
        }
        frmMenuPrincipal.setVisible(true);
    }
    /**
     * Regresa al menu pincipal desde el m,enu del administrador
     */
    public void mostrarMenuAdministrador(){
        //ocultar menu main
        if(frmInicioSesion != null){
            frmInicioSesion.dispose(); // para liberar la memoria, si se sale
            // por seguridad ya no deberia estar la ventana con los datos, por eos el dispose
            // sobre el setvisible false
        }
         
        // inicializar o recuperar la instancia creada
        if(frmMenuAdministrador == null){
            frmMenuAdministrador = new FrmMenuAdministrador(this);
        }
        
        // mostrar el nuevo frame enfrente
        
        frmMenuAdministrador.setVisible(true);
        frmMenuAdministrador.toFront();
    }
    /**
     * Muestra la pantalla de inicio de sesion donde el usuario valida su usuario
     */
    public void mostrarInicioSesion(){
        //ocultar menu main
        if(frmMenuPrincipal != null){
            frmMenuPrincipal.setVisible(false);
        }
        
        // inicializar o recuperar la instancia creada
        if(frmInicioSesion == null){
            frmInicioSesion = new FrmInicioSesion(this);
        }
        
        // mostrar el nuevo frame enfrente
        
        frmInicioSesion.setVisible(true);
        frmInicioSesion.toFront();
    }
    
    
    /**
     * Metodo para regresar al menu. Gestiona el retorno desde cualquier parte
     */
    public void rergesarAlMenu(){
        if(frmInicioSesion != null){
            frmInicioSesion.dispose(); // libera recursos al destruir la vetnana
        }
        if (frmMenuAdministrador != null){
            frmMenuAdministrador.dispose();
        }
        // verificar si existe, si sí, mostrar ventana y madnarla pal frente
        if(frmMenuPrincipal != null){
            frmMenuPrincipal.setVisible(true);
            frmMenuPrincipal.toFront();
        }
    } 
    /**
     * Mostrar las funciones del paneld e clientes frecuentes
     */
    public void mostrarFuncionesClientesFrecuentes(){
        if(frmMenuAdministrador != null){
            frmMenuAdministrador.setVisible(true);
        }
        if (pnlClienteFrecuente == null){
            pnlClienteFrecuente = new PnlClienteFrecuente(this);
        }
        frmMenuAdministrador.setNuevoContenido(pnlClienteFrecuente);
        pnlClienteFrecuente.setVisible(true);
    }
    /**
     * Método que cancela la modificacion de un cliente, oculta la edicion e un cliente y desaparece 
     * dicha interfaz grafica para liberar memoria
     */
    public void cancelarGestionClienteFrecuente(){
        if(dlgEdicionCliente != null){
            dlgEdicionCliente.dispose();
        }
    }
    public void aceptarGestionClienteFrecuente(){
        if(dlgEdicionCliente!= null){
            dlgEdicionCliente.dispose();
        }
    }
    
    /**
     * Método que muestra la interfaz de gestion de cliente, donde el usuario puede editar un 
     * cliente existente
     */
    public void mostrarGestionCliente(Long id){
        if(dlgEdicionCliente == null){
            
            dlgEdicionCliente = new DlgEdicionClienteFrecuente(frmMenuAdministrador,true ,this);
        }
        
        try{
            ClienteDTO clienteObtenido = coordinadorN.obtenerCliente(id);
            // mandar el cliente al dialogo de edicion
            dlgEdicionCliente.cargarDatos(clienteObtenido);
            
            dlgEdicionCliente.setLocationRelativeTo(frmMenuAdministrador);
            dlgEdicionCliente.toFront();
            dlgEdicionCliente.setVisible(true);
        }catch(NegocioException e){
            FramesUtileria.crearOptionPaneError(dlgEdicionCliente, e.getMessage(), "Error de gestion");
        }
    }
    /**
     * Método que funciona como un intermediario para poder usar el coordinador de negocio
     * tiene el porpósito de buscar clientes por filtros y por todos medios
     * @param texto el texto que se busca coincidencia
     * @param tipo el tipo de filtro que se recibe
     * @return la lista de coincidencias
     */
    public List<ClienteDTO> solicitarBusqueda(String texto, String tipo){
        return coordinadorN.filtraeClientes(texto, tipo);
    }
    
   
    
    
    /**
     * Método que muestra u // no funciona corregir
     */
    public void mostrarRegistroCliente(){
        if(notengotiemposorryprofe == null){
            
            notengotiemposorryprofe = new DlgRegistrarCliente(frmMenuAdministrador,true ,this);
        }
        
        notengotiemposorryprofe.setLocationRelativeTo(frmMenuAdministrador);
        notengotiemposorryprofe.toFront();
        notengotiemposorryprofe.setVisible(true);
        
    }
    /**
     * Método que registra un cliente // pendiente
     * @param clienteAgregar 
     */
    public void mostrarRegistro(ClienteDTO clienteAgregar){
        if (clienteAgregar != null) {
            coordinadorN.registrarCliente(clienteAgregar);
        }
    }
    
    /**
     * Metodo para enviar cambios a coordinadord e negocio
     * @param cliente
     * @return 
     */
    public void enviarCambios(ClienteDTO cliente) throws NegocioException{
        coordinadorN.modificarClienteFrecuente(cliente);
    }
    
    
    
    // ----- Funciones para modulo ingredientes -----
    
    /**
     * método para mostrar al usuario el panel de ingredientes
     */
    public void mostrarFuncionesIngredientes(){
        // validar si esta creado o no por si las moscas
        if(frmMenuAdministrador != null){
            frmMenuAdministrador.setVisible(true); // mostrarlo
        }
        // crear el panel de ingredientes si no existe
        if(pnlIngredientes == null){
            pnlIngredientes = new PnlIngredientes(this);
        }
        // como menu administrador gestiona el contenido que muestra, llamo al metodo
        // que setea panel en el apartado donde sale el contenido
        frmMenuAdministrador.setNuevoContenido(pnlIngredientes);
        // hacer visible el panel
        pnlIngredientes.setVisible(true);
    }
    
    /**
     * Metodo que muestra el dialogo de registro de ingrediente
     */
    public void mostrarFormularioRegistroIngrediente(){
        if(dlgRegistrarIngrediente == null){
            dlgRegistrarIngrediente = new DlgRegistrarIngrediente(frmMenuAdministrador, true, this);
        }
        
        dlgRegistrarIngrediente.setVisible(true);
        dlgRegistrarIngrediente.toFront();
    }
    
    /**
     * Método que muestra al usuario el formulario de edicion de un ingrediente
     */
    public void mostrarFormularioEdicionIngrediente(Long id){
        dlgModificarIngrediente = new DlgModificarIngrediente(frmMenuAdministrador, true, this, id);
        dlgModificarIngrediente.setVisible(true);
        dlgModificarIngrediente.toFront();
    }
    
    /**
     * Método para cancelar y destruir la edicion de ingrediente
     */
    public void cancelarEdicionIngrediente(){
        if(dlgModificarIngrediente != null){
            dlgModificarIngrediente.dispose();
        }
    }
    /**
     * Método para ocultar el formulario de edicion y mostrar mensaje de exito 
     */
    public void aceptarEdicionIngrediente(){
        if(dlgModificarIngrediente!= null){
            dlgModificarIngrediente.dispose();
            FramesUtileria.crearOptionPaneExito(pnlIngredientes, "Ingrediente actualizado.", "Operaciòn exitosa");
        }
    }
    /**
     * Método que tras cancelar la acción, destruye el formulario de registro
     */
    public void cancelarRegistroIngrediente(){
        if(dlgRegistrarIngrediente != null){
            dlgRegistrarIngrediente.dispose();
        }
    }
    
    /**
     * Método que tras aceptar el registro, destruye el formulario y crea un mensaje
     * retroalimentativo
     */
    public void aceptarRegistroIngrediente(){
        if(dlgRegistrarIngrediente!= null){
            dlgRegistrarIngrediente.dispose();
            FramesUtileria.crearOptionPaneExito(pnlIngredientes, "Ingrediente registrado.", "Operaciòn exitosa");
        }
    }
    
     /**
     * Método que solicita la busqueda de registros de ingredientes
     * @param texto es el texto que el usuario introduce
     * @param tipoFiltro es el filtro que se busca
     * @return
     * @throws NegocioException 
     */
    public List<IngredienteDTO> solicitarBusquedaIngrediente(String texto, String tipoFiltro) throws NegocioException{
        return coordinadorN.filtrarIngrediente(texto, tipoFiltro);
    }
    
    /**
     * Método auxiliar para la gestion que hara el coordinador del negocio
     * @param idIngrediente
     * @throws NegocioException 
     */
    public void solicitarEliminarIngrediente(Long idIngrediente) throws NegocioException{
        try {
            coordinadorN.eliminarIngrediente(idIngrediente);
            FramesUtileria.crearOptionPaneExito(pnlIngredientes, "Ingrediente con id: " + idIngrediente + " eliminado", "Registro eliminado");

        } catch (NegocioException ex) {
            FramesUtileria.crearOptionPaneError(pnlIngredientes, ex.getMessage(), "Operación fallida");
        }
    }
    
    /**
     *  Método auxiliar para solicitar al coordinador de negocio que registre un ingrediente
     * @param nombre
     * @param cantidad
     * @param unidad
     * @param estado
     * @param url
     * @param imagen 
     */
    public void solicitarRegistrarIngrediente(String nombre, Double cantidad, UnidadMedida unidad, EstadoIngrediente estado, String url, byte[] imagen){
        try {
            coordinadorN.registrarIngrediente(nombre, cantidad, unidad, estado, url, imagen);
            FramesUtileria.crearOptionPaneExito(dlgRegistrarIngrediente, "Ingrediente registrado exitosamente", "Registro guardado");
        } catch (NegocioException ex) {
             FramesUtileria.crearOptionPaneError(dlgRegistrarIngrediente, ex.getMessage(), "Operación fallida");
        }
    }
    
    /**
     * Método auxiliar que solicita al coordinador de negocio obtener un ingrediente
     * @param id
     * @return 
     */
    public IngredienteDTO solicitarObtenerIngrediente(Long id){
        try {
            return coordinadorN.obtenerIngrediente(id);
        } catch (NegocioException ex) {
            FramesUtileria.crearOptionPaneError(pnlIngredientes, ex.getMessage(), "Operación fallida");
        }
        return null;
    }
    
    /**
     * Método auxiliar para solicitar actualización de un cliente en concreto
     * @param id
     * @param nombre
     * @param cantidad
     * @param unidad
     * @param estado
     * @param url
     * @param imagen 
     */
    public void solicitarActualizarIngrediente(Long id, String nombre, Double cantidad, UnidadMedida unidad, EstadoIngrediente estado, String url, byte[] imagen){
        try {
            coordinadorN.modificarIngrediente(id, nombre, cantidad, unidad, estado, url, imagen);
        } catch (NegocioException ex) {
            FramesUtileria.crearOptionPaneError(pnlIngredientes, ex.getMessage(), "Operacion fallida");
        }
    }
    
    /**
     * metodo para abrir la pantalla de inicio de sesion de empleados
     */
    public void mostrarInicioSesionEmpleado(){
        if(frmMenuPrincipal!=null){
            frmMenuPrincipal.setVisible(false);
        }
        
        if(frmInicioSesionEmpleado == null){
            frmInicioSesionEmpleado = new FrmInicioSesionEmpleado(this, coordinadorN);
        }
        
        frmInicioSesionEmpleado.setVisible(true);
        frmInicioSesionEmpleado.toFront();
        
    }
}