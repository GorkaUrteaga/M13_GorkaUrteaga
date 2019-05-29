/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gorka
 */
public class ClientSocket {
   
    
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost",6666);
            PrintStream ps = new PrintStream(client.getOutputStream());
            
            //Missatge cap al server
            ps.println("Hello to SERVER!");
            
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            
            String message = br.readLine();
            System.out.println("Missatge: "+message);
        
        } catch (IOException ex) {
            System.out.println("Info: "+ex.getMessage());
        }
        
        
        
    }
}
