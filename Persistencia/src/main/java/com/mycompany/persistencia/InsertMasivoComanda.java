/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia;

import daos.ComandaDAO;
import daos.MesaDAO;
import daos.MeseroDAO;
import entidades.*;
import java.time.LocalDateTime;
import tiposDatosEnums.EstadoComanda;
import excepciones.PersistenciaException;
import java.util.ArrayList;

/**
 *
 * @author aaron
 */
public class InsertMasivoComanda  {
    
    public static void main(String[] args) throws PersistenciaException {
        ComandaDAO dao = new ComandaDAO();
        MesaDAO mesaDao = new MesaDAO();
        MeseroDAO empleadoDAO = new MeseroDAO();
        Mesa mesa = mesaDao.buscarPorId(Long.valueOf(1));
        Mesero m = empleadoDAO.buscarPorCodigo("ABC123");
        Comanda comanda = new Comanda(EstadoComanda.ENTREGADA, "1", LocalDateTime.now(), mesa, m);
        comanda.setDetalles(new ArrayList<>());
        dao.guardarComanda(comanda);
    }

}