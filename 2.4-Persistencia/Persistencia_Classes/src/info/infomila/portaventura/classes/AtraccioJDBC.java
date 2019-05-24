package info.infomila.portaventura.classes;

public class AtraccioJDBC {

    private int codi;
    private int capacitatMaximaRonda;
    private String nom;
    private int tempsPerRonda;
    private int clientsEnCua;

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
        return "AtraccioJDBC{" + "codi=" + codi + ", capacitatMaximaRonda=" + capacitatMaximaRonda + ", nom=" + nom + ", tempsPerRonda=" + tempsPerRonda + ", clientsEnCua=" + clientsEnCua + '}';
    }
    
}
