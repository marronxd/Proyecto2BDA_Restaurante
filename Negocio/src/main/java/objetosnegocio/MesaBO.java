/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio;

import adaptadores.MesaAdapter;
import daos.ClienteDAO;
import daos.MesaDAO;
import dtos.MesaDTO;
import entidades.Mesa;
import excepciones.PersistenciaException;
import java.util.List;
import tiposDatosEnums.EstadoMesa;

/**
 *
 * @author luiscarlosbeltran
 */
public class MesaBO {
    //hecho como singleton porque aviso la profe
    private static MesaBO instancia;

    private final MesaDAO MesaDAO;

    private MesaBO() {
        this.MesaDAO = new MesaDAO();
    }

    //el constructor publico y static para que sea solo una instancia en todo el sistema
    //osea el singleton
    public static MesaBO getInstance() {
        if (instancia == null) {
            instancia = new MesaBO();
        }
        return instancia;
    }
    
    MesaAdapter mesaAdapter = new MesaAdapter();
    
    
    
    public List<MesaDTO> obtenerTodas() throws PersistenciaException {
        return mesaAdapter.listaEntityADTO(MesaDAO.obtenerTodas());
    }
    
    public void ocuparMesa(Long idMesa) throws PersistenciaException {
    Mesa mesa = MesaDAO.buscarPorId(idMesa);
    mesa.setEstado(EstadoMesa.OCUPADA);
    MesaDAO.actualizar(mesa);
}
    
}
