/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;
import dtos.ReporteComandaDTO;
import entidades.Comanda;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author aaron
 */
public class ReporteComandaAdapter {
    
    
    public static ReporteComandaDTO convertirEntidadADTO(Comanda comanda){
        // crear objeto dto
        ReporteComandaDTO reporteDTO = new ReporteComandaDTO();
        
        // --- sacar valores de la entiadad n el objeto dto
        
        reporteDTO.setFechaHora(comanda.getFechaHora_Creacion());
        if(comanda.getMesa() != null){
            reporteDTO.setNombreMesa(comanda.getMesa().getIdentificador());
        }
        reporteDTO.setTotal(comanda.getTotal_acumulado());
        reporteDTO.setEstado(comanda.getEstado().name());
        if(comanda.getCliente() != null){
            if(comanda.getCliente().getNombres() != null){
                reporteDTO.setNombreCliente(comanda.getCliente().getNombres());
            }
        }
        return reporteDTO;
    }
    
    public static List<ReporteComandaDTO> convertirListaEntidadADTO(List<Comanda> comandas){
        List<ReporteComandaDTO> reportes = new ArrayList<>();
        for(Comanda comanda : comandas){
            reportes.add(convertirEntidadADTO(comanda));
        }
        return reportes;
    }
}
