package info.infomila.portaventura.simulador;

import info.infomila.portaventura.servidor.*;
import info.infomila.portaventura.classes.Atraccio;
import info.infomila.portaventura.classes.AtraccioJDBC;
import info.infomila.portaventura.enums.EstatOperatiu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gorka
 */
public class SimulacioAtraccions {    
    
    private static Connection con = null;
    private static Statement stm = null;
    
    public static void main(String[] args) {
        
        //Anem a simular el fluxe de gent a les atraccions
        
        //Connexió JDBC per agafar les atraccions de la BD.

        try {
            String url = "jdbc:mysql://92.222.27.83:3306/m2_gurteaga?serverTimezone=UTC";
            con = DriverManager.getConnection(url, "m2-gurteaga", "47129014J");
            System.out.println("Connectat");
            
            stm = con.createStatement();
            
        } catch (SQLException ex) {
            System.out.println("No s'estableix connexió");
            System.out.println("Info: " + ex.getMessage());
            System.exit(1);
        }
        
        //Agafem totes les atraccions.
        String cad ="select * from Atraccio";
        ResultSet rs = null;
        try {
            rs = stm.executeQuery(cad);
        } catch (SQLException ex) {
            System.out.println("Info: "+ex.getMessage());
            tancarConnexio();
            System.exit(1);
        }
        
        if(rs == null){
            System.out.println("No ha retornat cap atracció!");
            System.exit(1);
        }
        
        try {
            while(rs.next()){
                
                //System.out.println("CODI: "+rs.getInt("CODI")+" nom: "+rs.getString("NOM"));
                int codi = rs.getInt("CODI");
                //int codiZona = rs.getInt("ZONA");
                int capacitatMaximaRonda = rs.getInt("CAPACITAT_MAXIMA_RONDA");
                //String descripcioHTML = null;//rs.getString("DESCRIPCIO_HTML");
                String nom = rs.getString("NOM");
                int tempsPerRonda = rs.getInt("TEMPS_PER_RONDA");
                //String urlFoto = rs.getString("URL_FOTO");
                int clientsEnCua = rs.getInt("CLIENTS_EN_CUA");
                //int alsadaMinimaAmbAcompanyant = rs.getInt("ALSADA_MINIMA_AMB_ACOMPANYANT");
                //int alsadaMinima = rs.getInt("ALSADA_MINIMA");
                //EstatOperatiu estatOperatiu = rs.getString("ESTAT_OPERATIU");
                AtraccioJDBC atr = new AtraccioJDBC(codi,capacitatMaximaRonda,nom,tempsPerRonda,clientsEnCua);
                
                MyThread fil = new MyThread(atr);
                fil.start();
            
            }
        }
        catch (SQLException ex) {
            System.out.println("Error mentres es llegia el result set "+ex.getMessage());
        }

    }
    
    private static void tancarConnexio(){
        try {
            if(stm != null && !stm.isClosed()){
                stm.close();
            }
            if(con!= null && !con.isClosed()){
                con.close();
            }
        } catch (SQLException ex1) {
            System.out.println(ex1.getMessage());
        }
    }
    
}
