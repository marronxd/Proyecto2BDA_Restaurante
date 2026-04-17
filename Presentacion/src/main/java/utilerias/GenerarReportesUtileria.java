/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;
import dtos.ReporteClienteDTO;
import dtos.ReporteComandaDTO;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
/**
 * Crea un reporte de una inspeccion, y lo arroja. Puede descargarse dicho reporte
 * @author aaron
 */
public class GenerarReportesUtileria {
     public static void lanzarReporte(List<ReporteComandaDTO> datos){
         try{
             // 1. Cargamos el diseño desde el JRXML (el de bloc de notas)
            // Asegúrate de que el archivo se llame exactamente así en src/main/resources
            InputStream is = GenerarReportesUtileria.class.getResourceAsStream("/ReportesComandas.jrxml");

            if (is == null) {
                throw new Exception("No se pudo encontrar el archivo .jrxml en resources");
            }

            // 2. Compilamos en memoria (esto ignora archivos .jasper viejos)
                 JasperReport jr = JasperCompileManager.compileReport(is);

            // 3. Llenamos con tus parámetros (puedes mandarlos vacíos por ahora)
            Map<String, Object> params = new HashMap<>();
            params.put("FechaCreacionReporte", new java.util.Date());
            params.put("Fecha1", new java.util.Date());
            params.put("Fecha2", new java.util.Date());

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(datos);

            JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

            // 4. Mostramos
            JasperViewer.viewReport(jp, false);
         }catch(Exception e){
             System.err.println(e.getMessage());
         }
     }
     
     public static void lanzarReporteClientes(List<ReporteClienteDTO> datos){
        try{
            InputStream is = GenerarReportesUtileria.class.getResourceAsStream("/ReporteClientes.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            Map<String, Object> params = new HashMap<>();
            params.put("FechaCreacionReporte", new java.util.Date());
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(datos);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);
            JasperViewer.viewReport(jp, false);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
     
}
