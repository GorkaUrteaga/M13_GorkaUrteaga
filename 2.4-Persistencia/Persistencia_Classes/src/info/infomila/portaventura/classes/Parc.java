package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Gorka
 */
@Entity
public class Parc implements Serializable{
    
    // ATRIBUTS
    @Id
    @Column(columnDefinition="INT(6)")
    private int codi;
    
    @Basic(optional = false)
    @Column(length=40, nullable = false)
    private String nom;
    
    @Basic(optional = false)
    @Column(name="URL_FOTO", length=500, nullable = false)
    private String urlFoto;
    
    @OneToMany(mappedBy="parc")
    private List<Zona> zones;
    
    // CONSTRUCTORS
    // Constructor per JPA
    protected Parc(){
    }

    public Parc(int codi, String nom) {
        this(codi,nom,null);
    }

    public Parc(int codi, String nom, String urlFoto) {
        setCodi(codi);
        setNom(nom);
        setUrlFoto(urlFoto);
        zones = new ArrayList<Zona>();
    }
    
    // SETTERS
    public void setCodi(int codi) {
        if(codi <= 0){
            throw new RuntimeException("El codi ha de ser positiu.");
        }
        this.codi = codi;
    }

    public void setNom(String nom) {
        if(nom == null || nom.trim().equals("")){
            throw new RuntimeException("El nom és obligatori.");
        }
        this.nom = nom;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    
    // METODES LIST
    
    public int getNumZones(){
        return zones.size();
    }
    
    public Iterable<Zona> getZones(){
        return zones;
    }
    
    public Zona getZona(int indx){
        return zones.get(indx);
    }
    
    public boolean removeZona(Zona z){
        return zones.remove(z);
    }
    
    public boolean addZona(Zona z){
        return zones.add(z);
    }
    
    // GETTERS
    public int getCodi() {
        return codi;
    }

    public String getNom() {
        return nom;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.codi;
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
        final Parc other = (Parc) obj;
        if (this.codi != other.codi) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parc{" + "codi=" + codi + ", nom=" + nom + ", urlFoto=" + urlFoto + ", zones=" + zones + '}';
    }
    
}
