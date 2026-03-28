/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.Frame;
import pantallas.*;
import pantallas.pnlClienteFrecuente;

/**
 *
 * @author aaron
 */
public class CoordinadorFrames {
    // inyeccion de ncoordinador negocio
    //private CoordinadorNegocio negocio;
    
    private FrmMenuPrincipal frmMenuPrincipal;
    private FrmMenuAdministrador frmMenuAdministrador;
    private FrmInicioSesion frmInicioSesion;
    private FrmEdicionClienteFrecuente frmEdicionCliente;
   
    // mostrar paneles para los frames de administrador
    private pnlClienteFrecuente pnlClienteFrecuente;
    
    public CoordinadorFrames(){
    
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
    public void cancelarGestionClienteFrecuente(){
        if(frmEdicionCliente != null){
            frmEdicionCliente.dispose();
        }
    }
    public void mostrarGestionCliente(){
        if(frmEdicionCliente == null){
            
            frmEdicionCliente = new FrmEdicionClienteFrecuente(frmMenuAdministrador,true ,this);
        }
        frmEdicionCliente.setLocationRelativeTo(frmMenuAdministrador);
        frmEdicionCliente.toFront();
        frmEdicionCliente.setVisible(true);
        
    }
    
}
