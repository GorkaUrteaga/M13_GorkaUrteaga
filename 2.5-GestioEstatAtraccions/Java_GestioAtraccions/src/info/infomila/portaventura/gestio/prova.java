/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.gestio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gorka
 */
public class prova {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //FrameGestioAtraccions.main(args);
        
        //Prova de connexió
        
        String up = "UP-MySQL";
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
            StackTraceElement [] st = ex.getStackTrace();
            /*for(int i=0;i<st.length;i++){
                System.out.println(st[i]);
            }*/
        } finally {
            if (em != null) {
                em.close();
                System.out.println("EntityManager tancat");
            }
            if (emf != null) {
                emf.close();
                System.out.println("EntityManagerFactory tancada");
            }
        }
        
    }
    
}
