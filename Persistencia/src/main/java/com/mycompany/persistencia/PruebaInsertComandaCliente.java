/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.persistencia;

import conexion.ConexionBD;
import daos.ClienteDAO;
import daos.ComandaDAO;
import entidades.ClienteFrecuente;
import entidades.Comanda;
import entidades.DetalleComanda;
import entidades.Mesa;
import entidades.Mesero;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import tiposDatosEnums.EstadoComanda;

/**
 *
 * @author luiscarlosbeltran
 */
public class PruebaInsertComandaCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        ClienteDAO clienteDAO = new ClienteDAO();
        ComandaDAO comandaDAO = new ComandaDAO();
        
        try{
            //CLIENTE FREC
            ClienteFrecuente cliente = new ClienteFrecuente();
            cliente.setNombres("Juan");
            cliente.setApellidoPaterno("Perez");
            cliente.setApellidoMaterno("Lopez");
            cliente.setTelefono("6441234567");
            cliente.setCorreo("juan@gmail.com");
            cliente.setFechaRegistro(LocalDate.now());
            cliente.setPuntos(0.0);
            cliente.setTotalGastado(0.0);

            cliente = (ClienteFrecuente) clienteDAO.guardar(cliente);
            System.out.println("Cliente guardado con ID: " + cliente.getId());
            
            //saca mesero, mesa y producto de la BD
            //si no tienen especificamente estos, no corran este main
            //aparte, es la version de producto que solo tiene id y precio, antes de que nuestra compañera suba su parte de productos 
            EntityManager em = ConexionBD.crearConexion();

            Mesa mesa = em.find(Mesa.class, 1L);
            Mesero mesero = em.find(Mesero.class, 1L);
            Producto producto = em.find(Producto.class, 1L);

            em.close();
            
            //COMANDA
            Comanda comanda = new Comanda();
            comanda.setEstado(EstadoComanda.ABIERTA);
            comanda.setFechaHora_Creacion(LocalDateTime.now());
            comanda.setMesa(mesa);
            comanda.setMesero(mesero);
            comanda.setCliente(cliente);
            //el metodo especial para el folio de la comanda
            int numFolio = comandaDAO.obtenerNumSigFolio(LocalDateTime.now());
            comanda.setFolio(comanda.generarFolio(numFolio));
            
            //DETALLECOMANDA
            DetalleComanda detalle = new DetalleComanda(2, "Sin cebolla", comanda, producto);
            
            detalle.setComanda(comanda);
            
            List<DetalleComanda> detalles = new ArrayList<>();
            detalles.add(detalle);

            comanda.setDetalles(detalles);
            
            //calcual total
            double total = 0.0;
            for (DetalleComanda d : detalles) {
                total += d.getSubtotal();
            }
            comanda.setTotal_acumulado(total);
            
            Comanda guardada = comandaDAO.guardarComanda(comanda);

            System.out.println("Comanda guardada con ID: " + guardada.getId());
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
    }
    
}
