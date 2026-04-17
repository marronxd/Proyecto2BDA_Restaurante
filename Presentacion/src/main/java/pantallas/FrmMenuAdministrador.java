/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import javax.swing.*;
import utilerias.FramesUtileria;

/**
 *
 * @author aaron
 */
public class FrmMenuAdministrador extends FrmPlantillaAdmin{
    
    private final CoordinadorFrames coordinadorF;
     
    
    //crear frame principal
    public FrmMenuAdministrador(CoordinadorFrames coordinador){
        super("Menu Administrador");
        this.coordinadorF = coordinador;
        
        cuerpoEstructura();
        
        btnClientesFrecuentes.addActionListener(e ->{
            coordinadorF.mostrarFuncionesClientesFrecuentes();
        });
        
        btnIngredientes.addActionListener(e ->{
            coordinadorF.mostrarFuncionesIngredientes();
        });
        
        btnReportes.addActionListener(e ->{
            coordinadorF.mostrarFuncionesReportes();

        });
        
        btnComandas.addActionListener(e ->{
            coordinadorF.mostrarFuncionesComandas();

        });
        
        btnProductos.addActionListener(e ->{
            coordinadorF.mostarFuncionesProductos();
        });
    }

    
}
