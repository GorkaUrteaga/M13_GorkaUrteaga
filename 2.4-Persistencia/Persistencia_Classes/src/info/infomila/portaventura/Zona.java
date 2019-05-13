package info.infomila.portaventura;

import java.util.List;

/**
 *
 * @author Gorka
 */
public class Zona {

    // ATRIBUTS
    private Parc parc;
    private int numero;
    private String nom;
    
    private List<Atraccio> atraccions;    
    
    // CONSTRUCTORS
    // Constructor per JPA
    protected Zona(){
    }
    
    
    
    // SETTERS
    
    
    
    // GETTERS

    public void setParc(Parc parc) {
        if(parc == null){
            throw new RuntimeException("El parc és obligatori.");
        }
        this.parc = parc;
    }

    public void setNumero(int numero) {
        if(numero <= 0){
            throw new RuntimeException("El número ha de ser positiu.");
        }
        this.numero = numero;
    }

    public void setNom(String nom) {
        if(nom == null || nom.trim().equals("")){
            throw new RuntimeException("El nom és obligatori.");
        }
        this.nom = nom;
    }
    
    // METODES LIST
    public Iterable<Atraccio> getAtraccions(){
        return atraccions;
    }
    
    public Atraccio getAtraccio(int index){
        return atraccions.get(index);
    }
    
    // GETTERS
    public Parc getParc() {
        return parc;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }
    
    
}
