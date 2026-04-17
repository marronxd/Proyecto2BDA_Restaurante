/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package observer;

/**
 * Contrato que se encarga de notificar cuando surge un cambio en algun momento,
 * solicita actualizacion mediante suscripciones
 * @author aaron
 */
public interface InventarioObserver {
    public void actualizarInventario();
}
