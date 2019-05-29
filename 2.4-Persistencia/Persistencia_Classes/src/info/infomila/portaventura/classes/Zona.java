package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Gorka
 */
@Entity
//@IdClass(ZonaParcFK.class)
public class Zona implements Serializable{

    // ATRIBUTS
    // Fa referencia a la id del parc.
    /*@Id
    @Column (name="PARC", columnDefinition="INT(6)")
    private int parcId;
    */
    @Id
    @Column (columnDefinition="INT(2)")
    private int numero;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "PARC", insertable=false, updatable=false,
            foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (PARC) REFERENCES PARC(CODI)"))
    private Parc parc;
    
    @Basic(optional = false)
    @Column(length=40, nullable = false)
    private String nom;
    
    @OneToMany(mappedBy="zona")
    //@Transient
    private List<Atraccio> atraccions; 
    
    // CONSTRUCTORS
    // Constructor per JPA
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
