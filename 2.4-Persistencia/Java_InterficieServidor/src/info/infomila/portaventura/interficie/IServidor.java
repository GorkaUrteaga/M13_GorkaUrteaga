/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.interficie;

import info.infomila.portaventura.classes.AtraccioJDBC;

/**
 *
 * @author Gorka
 */
public interface IServidor {
    
    public void crearConnexio();
    public void enviarAtraccions();
    public void enviarInfoUtilitzacio();
    public void tancarConnexio();
    
}
