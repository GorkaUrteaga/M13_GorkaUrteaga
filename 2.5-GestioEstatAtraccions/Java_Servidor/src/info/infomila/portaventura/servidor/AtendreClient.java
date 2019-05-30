/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.servidor;

import info.infomila.portaventura.classes.Atraccio;
import info.infomila.portaventura.classes.AtraccioJDBC;
import info.infomila.portaventura.classes.Client;
import info.infomila.portaventura.classes.InfoUtilitzacio;
import info.infomila.portaventura.classes.Parc;
import info.infomila.portaventura.classes.ParcJDBC;
import info.infomila.portaventura.classes.PassiExpress;
import info.infomila.portaventura.classes.TipusPassiExpress;
import info.infomila.portaventura.classes.Zona;
import info.infomila.portaventura.simulador.MyThread;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gorka
 */
public class AtendreClient extends Thread{
    //Socket
    private Socket client;
    //Enviar data sockets
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    //JDBC
    private static Connection con = null;
    private static Statement stm = null;
    
    public AtendreClient(Socket client){
        this.client = client;
        establirConnexio();
    }
    
    public void run(){
        
        System.out.println("Atenent a client...");

        try {
            
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            int opcio = 0;
            
            Object opcioObject = in.readObject();
            opcio = (int)opcioObject;
            //opcio = ois.readInt();
            
            System.out.println("Opcio escollida: "+opcio);
            
            switch(opcio){
                case 1:
                    enviarParcs();
                    break;
                case 2:
                    enviarClient();
                    break;
                case 3:
                    enviarClientRecuperatPerId();
                    break;
                case 4:
                    potAccedirClient();
                    break;
                case 5:
                    pujaAtraccioPassi();
                    
            }
            client.close();

        } catch (IOException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            System.out.println("Moro... i tanco connexió.");
            tancarConnexio();
        }
    }

    private void enviarParcs() {
        //Consulta JDBC
        String cad ="select * from Parc";
        ResultSet rs = null;
        try {
            rs = stm.executeQuery(cad);
        } catch (SQLException ex) {
            System.out.println("Info: "+ex.getMessage());
            tancarConnexio();
            System.exit(1);
        }
        
        if(rs == null){
            System.out.println("No ha retornat cap parc!");
            System.exit(1);
        }
        List<Parc> llParcs = new ArrayList<Parc>();
        try {
            while(rs.next()){
                
                int codi = rs.getInt("CODI");
                
                String nom = rs.getString("NOM");
                String urlFoto = rs.getString("URL_FOTO");
    
                Parc parc = new Parc(codi,nom,urlFoto);
                llParcs.add(parc);
            
            }
        }
        catch (SQLException ex) {
            System.out.println("Error mentres es llegia el result set "+ex.getMessage());
        }
        //Per cada parc obtindrem les seves zones
        try {
            for(Parc p : llParcs){
                System.out.println("PARC: "+p.getNom());
                cad = "select * from Zona where parc ="+p.getCodi();
                rs = stm.executeQuery(cad);
                if(rs == null){
                    System.out.println("No ha retornat cap zona!");
                    System.exit(1);
                }
                
                while(rs.next()){
                    
                    int numero = rs.getInt("NUMERO");
                    String nom = rs.getString("NOM");
                    Zona z = new Zona(numero,p,nom);
                    p.addZona(z);
                    System.out.println("\tZONA: "+z.getNom());
                }
                //Un cop afegides les zones
                
                for(Zona z : p.getZones()){
                    
                    cad = "select * from Atraccio where zona ="+z.getNumero();
                    rs = stm.executeQuery(cad);
                    if(rs == null){
                        System.out.println("No ha retornat cap zona!");
                        System.exit(1);
                    }

                    for(Atraccio a :buscarAtraccions(rs)){
                        z.addAtraccio(a);
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Error mentres es llegia el result set "+ex.getMessage());
        }
        
        try {
            out.writeObject(llParcs);
        } catch (IOException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void establirConnexio() {
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
    }

    private void tancarConnexio() {
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

    private void enviarClient() {
        //Si el login es correcte enviarem el client amb els seus passis, infoUtilitzacio, etc
        List<String> paramsLogin = new ArrayList<String>();
        try {
            paramsLogin = (List<String>) in.readObject();
            
            if(paramsLogin.size()!= 2){
                throw new RuntimeException("No s'han rebut correctament l'usuari i la contrassenya");
            }
            //Establim connexió amb la bd i confirmem login.
            
            System.out.println("Params: "+paramsLogin.get(0)+" pass:"+paramsLogin.get(1));
            
            String cad ="select * from Client where nif= '"+paramsLogin.get(0).trim()+"' and password= '"+paramsLogin.get(1).trim()+"'";
            //System.out.println(cad);
            ResultSet rs = null;
            
            rs = stm.executeQuery(cad);
            System.out.println("RS: "+rs);
            if(rs == null){
                System.out.println("No ha trobat el client indicat");
            }
            
            Client cli = null;
            
            while(rs.next()){
                
                int id = rs.getInt("ID");
                String nom = rs.getString("NOM");
                String cognom1 = rs.getString("COGNOM1");
                String cognom2 = rs.getString("COGNOM2");
                
                cli = new Client(id,paramsLogin.get(0),nom,cognom1,cognom2,paramsLogin.get(1));
                
            }
            
            if(cli == null){
                //Retornem al servidor el resultat per saber si ha de rebre un client o no.
                System.out.println("Cli no trobat!");
                out.writeObject(0);
                return;
            }
            
            //Anem a buscar els passis
            cli = introduirPassisClient(cli);
            
            
            //Hem trobat el client i l'enviem
            out.writeObject(1);
            
            
            System.out.println("Client: "+cli.toString());
            
            out.writeObject(cli);
            
        } catch (IOException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
            System.out.println("Info: "+ex.getMessage());
            tancarConnexio();
            System.exit(1);
        }
        
    }

    private void enviarClientRecuperatPerId() {
     
        Client cli = null;
        try{
            
            int idClient = (int) in.readObject();
            
            String cad ="select * from Client where id="+idClient;
            //System.out.println(cad);
            ResultSet rs = null;
            
            rs = stm.executeQuery(cad);
            System.out.println("RS: "+rs);
            if(rs == null){
                System.out.println("No ha trobat el client indicat");
            }
            
            while(rs.next()){
                
                int id = rs.getInt("ID");
                String nif = rs.getString("NIF");
                String nom = rs.getString("NOM");
                String cognom1 = rs.getString("COGNOM1");
                String cognom2 = rs.getString("COGNOM2");
                String password = rs.getString("PASSWORD");
                
                cli = new Client(id,nif,nom,cognom1,cognom2,password);
                
            }
            
            if(cli == null){
                //Retornem al servidor el resultat per saber si ha de rebre un client o no.
                System.out.println("Cli no trobat!");
                out.writeObject(0);
                return;
            }
            
            cli = introduirPassisClient(cli);
            
            out.writeObject(1);
            out.writeObject(cli);
            
        } catch (IOException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
            System.out.println("Info: "+ex.getMessage());
            tancarConnexio();
            System.exit(1);
        }
        
    }
    
    private Client introduirPassisClient(Client cli) throws SQLException{
        ResultSet rs = null;
        
        String cad = "select * from passi_express where client="+cli.getId();
        rs = stm.executeQuery(cad);
        System.out.println(cad);
        while(rs.next()){
            int id = rs.getInt("ID");
            int tipusPassi = rs.getInt("TIPUS");
            Date data = rs.getDate("DATA");

            establirConnexio();
            cad = "select * from tipus_passi_express where id ="+tipusPassi;
            ResultSet result = stm.executeQuery(cad);

            System.out.println(cad);
            TipusPassiExpress tp = null;
            while(result.next()){
                //System.out.println("Result set");
                String nomTipus  = result.getString("NOM");
                BigDecimal preuTipus = result.getBigDecimal("PREU_PER_DIA");

                tp = new TipusPassiExpress(tipusPassi,nomTipus,preuTipus);    
            }
            
            PassiExpress pe = new PassiExpress(id,data,tp);
            
            //establirConnexio();
            cad = "select inf.atraccio atraccio, inf.numero_usos numero_usos,tip.tipus tipus_acces from info_utilitzacio inf join tipus_acces tip on inf.tipus_acces = tip.id where passi="+id;
            ResultSet resultat = stm.executeQuery(cad);
            InfoUtilitzacio inf;
            while(resultat.next()){
                
                int idAtraccio = resultat.getInt("ATRACCIO");
                int numUsos = resultat.getInt("NUMERO_USOS");
                String tipusAcces = resultat.getString("TIPUS_ACCES");
                
                Atraccio a = null;
                //Anem a buscar la atracció.
                establirConnexio();
                cad = "select * from atraccio where codi ="+idAtraccio;
                ResultSet res = stm.executeQuery(cad);
                List<Atraccio> atraccions = buscarAtraccions(res);
                Atraccio atraccio = atraccions.get(0);
                
                inf = new InfoUtilitzacio(pe,atraccio,numUsos,tipusAcces);
                pe.addInfoUtilitzacio(inf);
            }
            
            cli.addPassi(pe);
        }
        
        return cli;
    }
    
    private List<Atraccio> buscarAtraccions(ResultSet rs) throws SQLException{
        List<Atraccio> atraccions = new ArrayList<Atraccio>();
        
        while(rs.next()){

            int numero = rs.getInt("CODI");
            int capacitatMaxRonda = rs.getInt("CAPACITAT_MAXIMA_RONDA");
            String descripcioHTML = rs.getString("DESCRIPCIO_HTML");
            String nom = rs.getString("NOM");
            int tempsPerRonda = rs.getInt("TEMPS_PER_RONDA");
            String urlFoto = rs.getString("URL_FOTO");
            int clientsEnCua = rs.getInt("CLIENTS_EN_CUA");
            int alsadaMinimaAmbAcompanyant = rs.getInt("ALSADA_MINIMA_AMB_ACOMPANYANT");
            int alsadaMinima = rs.getInt("ALSADA_MINIMA");
            String estatOperatiu = rs.getString("ESTAT_OPERATIU");

            Atraccio a = new Atraccio(numero,null,capacitatMaxRonda,descripcioHTML,nom,tempsPerRonda,urlFoto,clientsEnCua,alsadaMinimaAmbAcompanyant,alsadaMinima,estatOperatiu);
            atraccions.add(a);
            System.out.println("\t\tATRACCIO: "+a.getNom());
        }
        
        return atraccions;
    }

    private void potAccedirClient() {
        System.out.println("POT ACCEDIR?");
        try {
            int idPassi = (int) in.readObject();
            int idAtraccio = (int) in.readObject();
            
            String cad = "select numero_usos,tip.tipus tipus_acces from info_utilitzacio info left join tipus_acces tip on info.tipus_acces = tip.id where passi ="+idPassi+ " and atraccio = "+idAtraccio;
            System.out.println(cad);
            
            ResultSet resultat = stm.executeQuery(cad);
            int numUsos = 0;
            String tipusAcces = "";
            Boolean potPrimeraFila = true;
            while(resultat.next()){
                numUsos = resultat.getInt("NUMERO_USOS");
                tipusAcces = resultat.getString("TIPUS_ACCES");
            }
            String motiu = tipusAcces;
            int entra = 0;
            
            if(numUsos == 0){
                entra = 1;
            }else if(tipusAcces.equals("ILIMITAT_UN_SOL_US_1aFila")){
                entra = 1;
                potPrimeraFila = false;
            }else{
                motiu = "Atraccio consumida";
            }
            
            out.writeObject(entra);
            out.writeObject(potPrimeraFila);
            System.out.println(motiu);
            out.writeObject(motiu);
            
        } catch (IOException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Info: "+ex.getMessage());
            tancarConnexio();
            System.exit(1);
        }
        
    }

    private void pujaAtraccioPassi() {
        
        System.out.println("HA PUJAT");
        try {
            int idPassi = (int) in.readObject();
            int idAtraccio = (int) in.readObject();
            
            String cad = "update info_utilitzacio set numero_usos = 1 where passi ="+idPassi+ " and atraccio = "+idAtraccio+ " and tipus_acces != 2";
            System.out.println(cad);
            
            stm.executeUpdate(cad);
            
        } catch (IOException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtendreClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Info: "+ex.getMessage());
            tancarConnexio();
            System.exit(1);
        }
        
    }
    
}
