
package dtos;

/**
 *
 * @author piña
 */
public enum EnumTipoProducto {
    SELECCIONE("Seleccione..."),
    PLATILLO("Platillo"),
    BEBIDA("Bebida"),
    POSTRE("Postre"),
    ENTRADA("Entrada");

    private final String nombre;

    
    EnumTipoProducto(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

