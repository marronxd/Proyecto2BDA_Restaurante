/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import utilerias.BotonUtileria;
import utilerias.ColoresUtileria;
import utilerias.FramesUtileria;
import utilerias.UtileriasPaneles;

/**
 *
 * @author aaron
 * nota para mi yo del futuro, esta clase es abstracta porque al igual que en el trabajo anterior
 * solo sirve para que sea una plantilla para las hijas, no para crear objetos
 */
public abstract class FrmPlantillaAdmin extends JFrame{
    
    // solo las clases hijas tienen acceso
    protected JPanel panelMenuLateral;
    protected JPanel panelContenido;
    // reservar referencias, las pondre todas amontonadas pq me da hueva hacer un chorro
    protected JButton btnComandas, btnProductos, btnIngredientes, 
            btnClientesFrecuentes, btnReportes;
    
    /*
        cONSTRUCTOR DE PLANTILLA
    */
    public FrmPlantillaAdmin(String titulo){
        FramesUtileria.configurarVentanaPrincipal(this, titulo);
    }
    /**
     * Cuerpo de la estructura del panel de admin
     */
    public void cuerpoEstructura(){
        // creacion del menu lateral
        panelMenuLateral = new JPanel();
        // tunear al panel lateral
        panelMenuLateral.setBackground(ColoresUtileria.getCOLORMENULATERAL());
        
        UtileriasPaneles.configurarSideBar(panelMenuLateral);
        
        // creacion del panel que contendra las funciones de cada opcion
        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(ColoresUtileria.getFONDO());
        
        // crear los botones del sidebar
        btnComandas = BotonUtileria.botonSidebar("Comandas");
        btnProductos = BotonUtileria.botonSidebar("Productos");
        btnIngredientes = BotonUtileria.botonSidebar("Ingredientes");
        btnClientesFrecuentes = BotonUtileria.botonSidebar("Clientes\n Frecuentes");
        btnReportes = BotonUtileria.botonSidebar("Reportes");
        
        // agregar los botones al menu
        panelMenuLateral.add(btnComandas);
        panelMenuLateral.add(btnProductos);
        panelMenuLateral.add(btnIngredientes);
        panelMenuLateral.add(btnClientesFrecuentes);
        panelMenuLateral.add(btnReportes);
        // envolver el panel lateral en un scroll
        JScrollPane scrollMenu = UtileriasPaneles.generarScrollMenu(panelMenuLateral);
        
        // acomodar los paneles
        this.add(scrollMenu, BorderLayout.WEST);
        
        // 2. CREAR UN PANEL CONTENEDOR PARA LA DERECHA
        JPanel contenedorDerecha = new JPanel(new BorderLayout());
        contenedorDerecha.setBackground(ColoresUtileria.getFONDO());

        // 3. Agregar el encabezado al NORTH de este contenedor
        contenedorDerecha.add(crearEncabezado(), BorderLayout.NORTH);
        
        contenedorDerecha.add(panelContenido, BorderLayout.CENTER);
        this.add(contenedorDerecha, BorderLayout.CENTER);
        
        // acomodar el titulo principal 
    }
    
    /**
     * El encabezado del sistema
     * @return 
     */
    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ColoresUtileria.getFONDO());
        
        // Le damos un poco de altura fija para que respire
        panel.setPreferredSize(new Dimension(0, 80));
        JLabel titulo = new JLabel("Restaurante Genérico", SwingConstants.CENTER);
        titulo.setFont(new Font(FramesUtileria.getFUENTE(), Font.BOLD, 24));
        titulo.setForeground(ColoresUtileria.getCOLORTITULO());
        
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColoresUtileria.getCOLORLINEA()));
        panel.add(titulo, BorderLayout.NORTH);
        return panel;
    }
}
