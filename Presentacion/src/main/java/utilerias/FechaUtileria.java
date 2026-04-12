/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
/**
 *
 * @author aaron
 */
public class FechaUtileria {
    
    
    /**
     * Método de conversion de fechas, setea todos los aprametros que puedan alterar
     * la igualdad de fecha a 0
     * @param fecha
     * @return 
     */
    public static LocalDateTime convertirAInicioDia(Date fecha){
        if(fecha != null){
            // conversion de date a fecha de tipo local date time
            return fecha.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
                    .withHour(0).withMinute(0).withSecond(0).withNano(0);
        }
        return null;
    }
    
    /**
     * Convertir de local date a date al final del dia
     * @param fecha
     * @return 
     */
    public static LocalDateTime convertirAFinalDia(Date fecha) {
        if (fecha == null) return null;
        return fecha.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .withHour(23).withMinute(59).withSecond(59).withNano(0);
    }

}
