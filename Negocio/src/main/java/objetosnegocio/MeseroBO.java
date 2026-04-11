/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio;

import daos.MeseroDAO;
import entidades.Mesero;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import sesiones.SesionMesero;

/**
 *
 * @author luiscarlosbeltran
 */
public class MeseroBO {
    
    private static MeseroBO instancia;
    private MeseroDAO meseroDAO = new MeseroDAO();
    
    private MeseroBO(){
        this.meseroDAO=new MeseroDAO();
    }
    
    public static MeseroBO getInstance(){
        if(instancia ==null){
            instancia = new MeseroBO();
        }
        return instancia;
    }
    
    public void iniciarSesion(String codigo) throws PersistenciaException, NegocioException{
        Mesero mesero = meseroDAO.buscarPorCodigo(codigo);
        
        if(mesero == null){
            throw new NegocioException("Codigo de acceso incorrecto");
        }
        SesionMesero.iniciarSesion(mesero);
    }
}
