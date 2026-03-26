/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.Frame;
import pantallas.FrmMenuPrincipal;

/**
 *
 * @author aaron
 */
public class CoordinadorFrames {
    
    private FrmMenuPrincipal frmMenuPrincipal;
    
    public CoordinadorFrames(){
    
    }
    public void IniciarSistema(){
        if(frmMenuPrincipal == null){
            frmMenuPrincipal = new FrmMenuPrincipal(this);
        }
        frmMenuPrincipal.setVisible(true);
    }
}
