/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package daos;

import entidades.Mesero;
import excepciones.PersistenciaException;
import java.time.LocalDate;

/**
 *
 * @author luiscarlosbeltran
 */
public class InsertMesero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        //clase para insertar un mesero solo para probar la funcionalidad de las comandas
        MeseroDAO dao = new MeseroDAO(); 
        
        Mesero m = new Mesero(
        "ABC123",
        "Juan",
        "Perez",
        "Lopez",
        "RFC123",
        "6441234567",
        LocalDate.now());
        
        dao.registrar(m);
        
    }
}
