package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zona implements Serializable {

    private int numero;

    private Parc parc;

    private String nom;

    //@Transient
    private List<Atraccio> atraccions;

    protected Zona(){
    }

    public Zona(int numero, Parc parc, String nom) {
        this.numero = numero;
        this.parc = parc;
        this.nom = nom;
        atraccions = new ArrayList<Atraccio>();
    }

    // SETTERS

    public void setParc(Parc parc) {
        if(parc == null){
            throw new RuntimeException("El parc no pot ser null.");
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

    public Parc getParc() {
        return parc;
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
