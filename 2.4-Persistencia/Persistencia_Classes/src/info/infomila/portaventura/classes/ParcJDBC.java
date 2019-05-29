/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.classes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.OneToMany;

/**
 *
 * @author Gorka
 */
public class ParcJDBC implements Serializable{
    
    private int codi;
    
    private String nom;
    
    private String urlFoto;
    
    private List<AtraccioJDBC> atraccions;

    public ParcJDBC(int codi, String nom) {
        this(codi,nom,null);
    }

    public ParcJDBC(int codi, String nom, String urlFoto) {
        setCodi(codi);
        setNom(nom);
        setUrlFoto(urlFoto);
        atraccions = new ArrayList<AtraccioJDBC>();
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
        return atraccions.size();
    }
    
    public Iterable<AtraccioJDBC> getZones(){
        return atraccions;
    }
    
    public AtraccioJDBC getZona(int indx){
        return atraccions.get(indx);
    }
    
    public boolean removeZona(AtraccioJDBC z){
        return atraccions.remove(z);
    }
    
    public boolean addZona(AtraccioJDBC z){
        return atraccions.add(z);
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
        final ParcJDBC other = (ParcJDBC) obj;
        if (this.codi != other.codi) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parc{" + "codi=" + codi + ", nom=" + nom + ", urlFoto=" + urlFoto + ", zones=" + atraccions + '}';
    }
    
}
