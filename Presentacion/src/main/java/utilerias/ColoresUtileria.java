/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.Color;

/**
 *
 * @author aaron
 */
public class ColoresUtileria {
    
    private static final Color FONDO = Color.decode("#FFFCF9");
    private static final Color COLORTITULO = Color.decode("#783939");
    private static final Color COLORVERDE = Color.decode("#6D816B");
    private static final Color COLORMENULATERAL = Color.decode("#FFF2E4");
    private static final Color COLORLINEA = Color.decode("#9A8166");
    
    private ColoresUtileria(){
    
    }

    public static Color getCOLORLINEA() {
        return COLORLINEA;
    }

    public static Color getCOLORMENULATERAL() {
        return COLORMENULATERAL;
    }
  
    public static Color getFONDO() {
        return FONDO;
    }

    public static Color getCOLORTITULO() {
        return COLORTITULO;
    }

    public static Color getCOLORVERDE() {
        return COLORVERDE;
    }
    
}
