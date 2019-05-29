package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.math.BigDecimal;

public class TipusPassiExpress implements Serializable {

    private int id;
    private String nom;
    private BigDecimal preuPerDia;

    protected TipusPassiExpress(){
    }

    public TipusPassiExpress(int id, String nom, BigDecimal preuPerDia) {
        setId(id);
        setNom(nom);
        setPreuPerDia(preuPerDia);
    }

    // SETTERS
    public void setId(int id) {
        if(id <= 0){
            throw new RuntimeException("El id ha de ser superior a 0.");
        }
        this.id = id;
    }

    public void setNom(String nom) {
        if(nom == null || nom.trim().equals("")){
            throw new RuntimeException("El nom és obligatori.");
        }
        this.nom = nom;
    }

    public void setPreuPerDia(BigDecimal preuPerDia) {
        if(preuPerDia.compareTo(BigDecimal.ZERO) == -1){
            throw new RuntimeException("El preu no pot ser  negatiu.");
        }
        this.preuPerDia = preuPerDia;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public BigDecimal getPreuPerDia() {
        return preuPerDia;
    }

    @Override
    public String toString() {
        return "TipusPassiExpress{" + "id=" + id + ", nom=" + nom + ", preuPerDia=" + preuPerDia + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
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
        final TipusPassiExpress other = (TipusPassiExpress) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
