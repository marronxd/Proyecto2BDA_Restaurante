/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encriptador;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
/**
 * Para encriptar y desencriptar datos
 * @author aaron
 */
public class Encriptador {
    private static final String ALGORITMO = "AES";
    private static final String LLAVE_SECRETA = "MiClaveSuperSecr"; // Debe tener 16 caracteres

    /**
     * Método para encriptar datos
     * @param datos
     * @return
     * @throws Exception 
     */
    public static String encriptar(String datos) throws Exception {
        SecretKeySpec key = new SecretKeySpec(LLAVE_SECRETA.getBytes(), ALGORITMO); // genera una llave secreta
        Cipher cipher = Cipher.getInstance(ALGORITMO);// obtiene el algoritmo matematico que usara
        cipher.init(Cipher.ENCRYPT_MODE, key); // encripta la llave
        byte[] encriptado = cipher.doFinal(datos.getBytes()); //genera el encript
        return Base64.getEncoder().encodeToString(encriptado); 
    }

    /**
     * desecripta datps
     * @param datosEncriptados
     * @return
     * @throws Exception 
     */
    public static String desencriptar(String datosEncriptados) throws Exception {
        SecretKeySpec key = new SecretKeySpec(LLAVE_SECRETA.getBytes(), ALGORITMO);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] desencriptado = cipher.doFinal(Base64.getDecoder().decode(datosEncriptados));
        return new String(desencriptado);
    }
}
