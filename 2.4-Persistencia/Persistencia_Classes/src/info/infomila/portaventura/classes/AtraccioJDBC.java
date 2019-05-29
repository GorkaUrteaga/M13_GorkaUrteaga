package info.infomila.portaventura.classes;

import info.infomila.portaventura.enums.EstatOperatiu;
import java.io.Serializable;

public class AtraccioJDBC implements Serializable{

    private int codi;
    private int capacitatMaximaRonda;
    private String nom;
    private String urlImatge;
    private String estatOperatiu;
    private int tempsPerRonda;
    private int clientsEnCua;

    public AtraccioJDBC(int codi, int capacitatMaximaRonda, String nom, String urlImatge, String estatOperatiu, int tempsPerRonda, int clientsEnCua) {
        setCodi(codi);
        setCapacitatMaximaRonda(capacitatMaximaRonda);
        setNom(nom);
        setTempsPerRonda(tempsPerRonda);
        setClientsEnCua(clientsEnCua);
        setUrlImatge(urlImatge);
        setEstatOperatiu(estatOperatiu);

    }

    
    
    public AtraccioJDBC(int codi, int capacitatMaximaRonda, String nom, int tempsPerRonda, int clientsEnCua) {
        setCodi(codi);
        setCapacitatMaximaRonda(capacitatMaximaRonda);
        setNom(nom);
        setTempsPerRonda(tempsPerRonda);
        setClientsEnCua(clientsEnCua);
    }
    
    public void setCodi(int codi) {
        this.codi = codi;
    }

    public void setCapacitatMaximaRonda(int capacitatMaximaRonda) {
        this.capacitatMaximaRonda = capacitatMaximaRonda;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTempsPerRonda(int tempsPerRonda) {
        this.tempsPerRonda = tempsPerRonda;
    }

    public void setClientsEnCua(int clientsEnCua) {
        this.clientsEnCua = clientsEnCua;
    }

    public void setUrlImatge(String urlImatge) {
        this.urlImatge = urlImatge;
    }

    public void setEstatOperatiu(String estatOperatiu) {
        this.estatOperatiu = estatOperatiu;
    }
    
    public String getUrlImatge() {
        return urlImatge;
    }

    public String getEstatOperatiu() {
        return estatOperatiu;
    }
    
    public int getCodi() {
        return codi;
    }

    public int getCapacitatMaximaRonda() {
        return capacitatMaximaRonda;
    }

    public String getNom() {
        return nom;
    }

    public int getTempsPerRonda() {
        return tempsPerRonda;
    }

    public int getClientsEnCua() {
        return clientsEnCua;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.codi;
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
        final AtraccioJDBC other = (AtraccioJDBC) obj;
        if (this.codi != other.codi) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Atraccio{" + "codi=" + codi + ", capacitatMaximaRonda=" + capacitatMaximaRonda + ", nom=" + nom + ", tempsPerRonda=" + tempsPerRonda + ", clientsEnCua=" + clientsEnCua + '}';
    }
    
}
