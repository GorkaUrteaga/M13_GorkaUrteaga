/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.interficie;

/**
 *
 * @author Gorka
 */
public class ServidorException extends Exception {
    
    public ServidorException(String message) {
        super(message);
    }
    public ServidorException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
