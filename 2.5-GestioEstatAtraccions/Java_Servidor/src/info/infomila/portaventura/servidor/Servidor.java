/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.net.*;
import java.io.*;
/**
 *
 * @author Gorka
 */
public class Servidor {
    
    private static Connection con = null;
    private static PreparedStatement pstm = null;
    
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + 4444 + ", " + e);
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: " + 4444 + ", " + e);
            System.exit(1);
        }

        try {
            BufferedReader br = new BufferedReader(
                                 new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(
                             new BufferedOutputStream(clientSocket.getOutputStream(), 1024), false);
            System.out.println(pw);
            //KKState kks = new KKState();
            String inputLine, outputLine;

            //outputLine = kks.processInput(null);
            //pw.println(outputLine);
            pw.flush();
            /*
            while ((inputLine = br.readLine()) != null) {
                 //outputLine = kks.processInput(inputLine);
                 //pw.println(outputLine);
                 pw.flush();
                 if (outputLine.equals("Bye."))
                    break;
            }*/
            pw.close();
            br.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
