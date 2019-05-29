/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.servidor;

import info.infomila.portaventura.classes.Atraccio;
import info.infomila.portaventura.classes.Parc;
import info.infomila.portaventura.classes.ParcJDBC;
import info.infomila.portaventura.classes.Zona;
import info.infomila.portaventura.enums.EstatOperatiu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Gorka
 */
public class Servidor extends Thread{
    
    private static Connection con = null;
    private static PreparedStatement pstm = null;
    private static int port = 666;
    
    public static void main(String[] args) {
        
        try {
            ServerSocket server = new ServerSocket(port);
            
            while(true){
                
                Socket client = server.accept();
                Thread thread = new AtendreClient(client);
                thread.start();
            }
            
        } catch (IOException ex) {
            System.out.println("Info: "+ex.getMessage());
        }
        
    }
}
