/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package excepciones;

/**
 *
 * @author aaron
 */
public class FachadaException extends Exception {

    /**
     * Creates a new instance of <code>FachadaException</code> without detail
     * message.
     */
    public FachadaException() {
    }

    /**
     * Constructs an instance of <code>FachadaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FachadaException(String msg) {
        super(msg);
    }
}
