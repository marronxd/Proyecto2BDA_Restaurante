/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import controlador.CoordinadorNegocio;
import dtos.ClienteDTO;
import excepciones.FachadaException;
import excepciones.NegocioException;
import java.awt.Frame;
import java.util.List;
import pantallas.*;
import pantallas.pnlClienteFrecuente;
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
    private crearCliente notengotiemposorryprofe;
    // mostrar paneles para los frames de administrador
    private pnlClienteFrecuente pnlClienteFrecuente;
    
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
            pnlClienteFrecuente = new pnlClienteFrecuente(this);
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
            
            notengotiemposorryprofe = new crearCliente(frmMenuAdministrador,true ,this);
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
    
}
