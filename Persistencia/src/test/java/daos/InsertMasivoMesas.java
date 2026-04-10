/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package daos;

import entidades.Mesa;
import excepciones.PersistenciaException;
import tiposDatosEnums.EstadoMesa;

/**
 *
 * @author luiscarlosbeltran
 */

//insert masivo de mesas. La puse en el paquete de pruebasDAO porque se esta probando una parte del MesaDAO (guardar)
public class InsertMasivoMesas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        MesaDAO mesaDAO = new MesaDAO();
        
        //se crean los objetos mesa, 20 en total
        Mesa mesaA1 = new Mesa("A1", EstadoMesa.DISPONIBLE);
        Mesa mesaA2 = new Mesa("A2", EstadoMesa.DISPONIBLE);
        Mesa mesaA3 = new Mesa("A3", EstadoMesa.DISPONIBLE);
        Mesa mesaA4 = new Mesa("A4", EstadoMesa.DISPONIBLE);
        
        Mesa mesaB1 = new Mesa("B1", EstadoMesa.DISPONIBLE);
        Mesa mesaB2 = new Mesa("B2", EstadoMesa.DISPONIBLE);
        Mesa mesaB3 = new Mesa("B3", EstadoMesa.DISPONIBLE);
        Mesa mesaB4 = new Mesa("B4", EstadoMesa.DISPONIBLE);
        
        Mesa mesaC1 = new Mesa("C1", EstadoMesa.DISPONIBLE);
        Mesa mesaC2 = new Mesa("C2", EstadoMesa.DISPONIBLE);
        Mesa mesaC3 = new Mesa("C3", EstadoMesa.DISPONIBLE);
        Mesa mesaC4 = new Mesa("C4", EstadoMesa.DISPONIBLE);
        
        Mesa mesaD1 = new Mesa("D1", EstadoMesa.DISPONIBLE);
        Mesa mesaD2 = new Mesa("D2", EstadoMesa.DISPONIBLE);
        Mesa mesaD3 = new Mesa("D3", EstadoMesa.DISPONIBLE);
        Mesa mesaD4 = new Mesa("D4", EstadoMesa.DISPONIBLE);
        
        Mesa mesaE1 = new Mesa("E1", EstadoMesa.DISPONIBLE);
        Mesa mesaE2 = new Mesa("E2", EstadoMesa.DISPONIBLE);
        Mesa mesaE3 = new Mesa("E3", EstadoMesa.DISPONIBLE);
        Mesa mesaE4 = new Mesa("E4", EstadoMesa.DISPONIBLE);
        
        //se guardan todas las 20 mesas
        mesaDAO.guardar(mesaA1);
        mesaDAO.guardar(mesaA2);
        mesaDAO.guardar(mesaA3);
        mesaDAO.guardar(mesaA4);
        
        mesaDAO.guardar(mesaB1);
        mesaDAO.guardar(mesaB2);
        mesaDAO.guardar(mesaB3);
        mesaDAO.guardar(mesaB4);
        
        mesaDAO.guardar(mesaC1);
        mesaDAO.guardar(mesaC2);
        mesaDAO.guardar(mesaC3);
        mesaDAO.guardar(mesaC4);
        
        mesaDAO.guardar(mesaD1);
        mesaDAO.guardar(mesaD2);
        mesaDAO.guardar(mesaD3);
        mesaDAO.guardar(mesaD4);
        
        mesaDAO.guardar(mesaE1);
        mesaDAO.guardar(mesaE2);
        mesaDAO.guardar(mesaE3);
        mesaDAO.guardar(mesaE4);
    }
    
}
