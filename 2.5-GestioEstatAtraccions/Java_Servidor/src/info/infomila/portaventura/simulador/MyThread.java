/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.simulador;

import info.infomila.portaventura.servidor.*;
import info.infomila.portaventura.classes.AtraccioJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gorka
 */
public class MyThread extends Thread{
    
    private static Connection con = null;
    private static PreparedStatement stm = null;
    
    private AtraccioJDBC atraccio;
    
    public MyThread(AtraccioJDBC atraccio){
        this.atraccio = atraccio;
        try {
            String url = "jdbc:mysql://92.222.27.83:3306/m2_gurteaga?serverTimezone=UTC";
            con = DriverManager.getConnection(url, "m2-gurteaga", "47129014J");
            System.out.println("Connectat");
            String cad = "update Atraccio set clients_en_cua = ? where codi = ?";
            stm = con.prepareStatement(cad);
            
        } catch (SQLException ex) {
            System.out.println("No s'estableix connexió");
            System.out.println("Info: " + ex.getMessage());
            System.exit(1);
        }
    }
    
    public void run(){
        
        System.out.println("Simulació atraccions.");
        System.out.println("---------------------------------------");
        //System.out.println("Codi: "+atraccio.getNom());
        int resultat = atraccio.getClientsEnCua();
        while(true){
        
            try {    
                
                TimeUnit.MINUTES.sleep(atraccio.getTempsPerRonda());
                //Thread.sleep(atraccio.getTempsPerRonda()*100000);
                resultat = resultat - atraccio.getCapacitatMaximaRonda();
                if(resultat < 0 ){
                    resultat = 0;
                }
                
                stm.setInt(1, resultat);
                stm.setInt(2, atraccio.getCodi());
                stm.executeUpdate();
                System.out.println(atraccio.getNom()+" , clients en cua: "+resultat);
                
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
    
}
