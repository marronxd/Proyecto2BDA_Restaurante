/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import controlador.CoordinadorNegocio;
import dtos.ComandaDTO;

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

        //la comanda que se va a llenar
        ComandaDTO nuevaComanda = new ComandaDTO();

        FrmSeleccionarMesa frm = new FrmSeleccionarMesa(this, coordinadorN, nuevaComanda);

        frm.setVisible(true);
        });
    }
}
