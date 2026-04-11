/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Mesero;

/**
 * Clase que sirve para gestionar la sesion de un mesero
 * @author luiscarlosbeltran
 */
public class SesionMesero {
    private static Mesero meseroActual;
    
    public static void iniciarSesion(Mesero mesero){
        meseroActual = mesero;
    }
    
    
    
    public static Mesero getMeseroActual(){
        return meseroActual;
    }
    
    public static void cerrarSesion(){
        meseroActual = null;
    }
}
