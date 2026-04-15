/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;

/**
 *
 * @author luiscarlosbeltran
 */
public class FrmMenuMesero extends FrmPlantillaMesero {

    private CoordinadorFrames coordinadorF;

    public FrmMenuMesero(CoordinadorFrames coordinador) {
        super("Menu Mesero");
        this.coordinadorF = coordinador;

        cuerpoEstructura();

        //actionlistener del boton de volver
        btnVolver.addActionListener(e ->{
            this.dispose();
            coordinadorF.rergesarAlMenu();
        });
        
        //actionlistener del boton para ver comandas
        btnVerComandas.addActionListener(e ->{
        });
        
        //actionlistener del boton para crear comandas
        btnCrearComandas.addActionListener(e ->{
        });
    }
}
