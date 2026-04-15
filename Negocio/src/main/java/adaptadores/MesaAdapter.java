/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import daos.MesaDAO;
import dtos.MesaDTO;
import entidades.Mesa;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiscarlosbeltran
 */
public class MesaAdapter {

    private MesaDAO mesaDAO;

    public MesaAdapter() {
        this.mesaDAO = new MesaDAO();
    }
    
    public static MesaDTO mesaADTO(Mesa m) {

        MesaDTO dto = new MesaDTO();

        dto.setId(m.getId());
        dto.setIdentificador(m.getIdentificador());
        if (m.getEstado() != null) {
            dto.setEstado(m.getEstado().toString());
        }

        return dto;
    }
    

    /**
     * metodo que convierte la lista de mesas entidad a lista de mesasdto
     * @return
     * @throws PersistenciaException 
     */
    public List<MesaDTO> listaEntityADTO(List<Mesa> mesas) throws PersistenciaException {

        List<MesaDTO> lista = new ArrayList<>();

        if (mesas == null){
            return lista;
        }
        
        for (Mesa c : mesas) {
            lista.add(mesaADTO(c));
        }

        return lista;
        
    }
}
