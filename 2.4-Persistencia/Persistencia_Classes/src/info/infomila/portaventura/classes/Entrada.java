package info.infomila.portaventura.classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gorka
 */
public class Entrada {
    
    //Atributs
    private int id;
    private Date data;
    private int diesValidesa;
    private BigDecimal preu;
    private Client client;
    
    private List<Parc> parcs;

    // CONSTRUCTORS
    // Constructor per JPA
    protected Entrada(){
    }

    public Entrada(int id, Date data, int diesValidesa, BigDecimal preu, Client client) {
        setId(id);
        setData(data);
        setDiesValidesa(diesValidesa);
        setPreu(preu);
        setClient(client);
        parcs = new ArrayList<Parc>();
    }
    
    // SETTERS
    public void setId(int id) {
        if(id <= 0){
            throw new RuntimeException("La id ha de ser positiva.");
        }
        this.id = id;
    }

    public void setData(Date data) {
        this.data = (Date) data.clone();
    }

    public void setDiesValidesa(int diesValidesa) {
        if(diesValidesa <= 0){
            throw new RuntimeException("Els dies de validesa han de ser positius.");
        }
        this.diesValidesa = diesValidesa;
    }

    public void setPreu(BigDecimal preu) {
        if(preu.compareTo(BigDecimal.ZERO) == -1){
            throw new RuntimeException("El preu ha de ser 0 o positiu.");
        }
        this.preu = preu;
    }

    public void setClient(Client client) {
        if(this.client == null && client == null){
            throw new RuntimeException("El client no pot ser null.");
        }
        this.client = client;
    }
    
    // METODES LIST
    
    
    // GETTERS
    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public int getDiesValidesa() {
        return diesValidesa;
    }

    public BigDecimal getPreu() {
        return preu;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Entrada other = (Entrada) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", data=" + data + ", diesValidesa=" + diesValidesa + ", preu=" + preu + ", client=" + client + ", parcs=" + parcs + '}';
    }
}
