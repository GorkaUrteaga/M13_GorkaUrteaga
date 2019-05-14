package info.infomila.portaventura.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gorka
 */
public class Zona {

    // ATRIBUTS
    private int numero;
    private String nom;
    
    private List<Atraccio> atraccions;    
    
    // CONSTRUCTORS
    // Constructor per JPA
    protected Zona(){
    }

    public Zona(int numero, String nom) {
        setNumero(numero);
        setNom(nom);
        atraccions = new ArrayList<Atraccio>();
    }
    
    // SETTERS
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
    
    public int getNumAtraccions(){
        return atraccions.size();
    }
    
    public Iterable<Atraccio> getAtraccions(){
        return atraccions;
    }
    
    public Atraccio getAtraccio(int index){
        return atraccions.get(index);
    }
    
    public boolean removeAtraccio(Atraccio atr){
        return atraccions.remove(atr);
    }
    
    public boolean addAtraccio(Atraccio atr){
        return atraccions.add(atr);
    }
    
    // GETTERS

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.numero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zona other = (Zona) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zona{" + "numero=" + numero + ", nom=" + nom + ", atraccions=" + atraccions + '}';
    }
    
}
