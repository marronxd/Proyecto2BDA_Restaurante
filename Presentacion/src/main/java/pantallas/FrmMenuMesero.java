/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import controlador.CoordinadorNegocio;
import dtos.ComandaDTO;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luiscarlosbeltran
 */
public class FrmMenuMesero extends FrmPlantillaMesero {

    private CoordinadorFrames coordinadorF;
    private CoordinadorNegocio coordinadorN;

    public FrmMenuMesero(CoordinadorFrames coordinador, CoordinadorNegocio coordinadorN) {
        super("Menu Mesero");
        this.coordinadorF = coordinador;
        this.coordinadorN = coordinadorN;

        cuerpoEstructura();

        //actionlistener del boton de volver
        btnVolver.addActionListener(e -> {
            if (panelContenido.getComponent(0) != panelMenuInicial) {
                mostrarMenuInicial();
            } else {
                coordinadorF.rergesarAlMenu();
                dispose();
            }
        });
        
        //actionlistener del boton para ver comandas
        btnVerComandas.addActionListener(e -> {

            PnlComandasMesero panel = new PnlComandasMesero(coordinadorF);

            setNuevoContenido(panel);

        });
        
        //actionlistener del boton para crear comandas
        btnCrearComandas.addActionListener(e -> {

        //se manda desde aqui el comandadto que se va a llenar para persistir al final en bd
        ComandaDTO nuevaComanda = new ComandaDTO();
        nuevaComanda.setEstado("ABIERTA");
        nuevaComanda.setFechaHoraCreacion(LocalDateTime.now());
            try {
                nuevaComanda.setFolio(coordinadorN.darFolio());
            } catch (PersistenciaException ex) {
                Logger.getLogger(FrmMenuMesero.class.getName()).log(Level.SEVERE, null, ex);
            }

        FrmSeleccionarMesa frm = new FrmSeleccionarMesa(
            this,
            coordinadorF,
            coordinadorN,
            nuevaComanda
        );

        frm.setVisible(true);
        });
    }
}
