package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gorka
 */
public class Client implements Serializable{

     // ATRIBUTS
    private int id;
    private String nif;
    private String nom;
    private String cognom1;
    private String cognom2;
    private String password;

    private List<Entrada> entrades;
    private List<PassiExpress> passis;

    // CONSTRUCTORS
    // Constructor per JPA
    protected Client() {
    }

    public Client(int id, String nif, String nom) {
        this(id,nif,nom,null,null,null);
    }

    public Client(int id, String nif, String nom, String cognom1, String cognom2, String password) {
        setId(id);
        setNif(nif);
        setNom(nom);
        setCognom1(cognom1);
        setCognom2(cognom2);
        setPassword(password);
        entrades = new ArrayList<Entrada>();
        passis = new ArrayList<PassiExpress>();
    }

    // SETTERS
    public void setId(int id) {
        if(id <= 0){
            throw new RuntimeException("La id ha de ser positiva.");
        }
        this.id = id;
    }

    public void setNif(String nif) {
        if(nif == null || nif.trim().length()==0){
            throw new RuntimeException("El nif és obligatori.");
        }
        this.nif = nif;
    }

    public void setNom(String nom) {
        if(nom == null || nom.trim().equals("")){
            throw new RuntimeException("El nom és obligatori.");
        }
        this.nom = nom;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    protected void setPassword(String password) {

        this.password = password;
    }

    // METODES LIST
    public int getNumEntrades(){
        return entrades.size();
    }

    public Iterable<Entrada> getEntrades(){
        return entrades;
    }

    public Entrada getEntrada(int index){
        return entrades.get(index);
    }

    public boolean removeEntrada(Entrada entr){
        boolean esborrat = entrades.remove(entr);
        if(esborrat){
            entr.setClient(null);
        }
        return esborrat;
    }

    public boolean addEntrada(Entrada entr){
        boolean afegit = entrades.add(entr);
        if(afegit){
            entr.setClient(this);
        }
        return afegit;
    }

    public int getNumPassis(){
        return passis.size();
    }

    public Iterable<PassiExpress> getPassisExpres(){
        return passis;
    }

    public PassiExpress getPassi(int index){
        return passis.get(index);
    }

    public boolean removePassi(PassiExpress pass){
        return passis.remove(pass);
    }

    public boolean addPassi(PassiExpress pass){
        return passis.add(pass);
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
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
        final Client other = (Client) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nif=" + nif + ", nom=" + nom + ", cognom1=" + cognom1 + ", cognom2=" + cognom2 + ", entrades=" + entrades + ", passis=" + passis + '}';
    }
    
}
