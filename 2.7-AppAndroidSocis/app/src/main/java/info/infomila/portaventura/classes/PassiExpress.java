package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PassiExpress implements Serializable{

    private int id;
    private Date data;

    private TipusPassiExpress tipusPassi;

    private List<InfoUtilitzacio> infoUtilitzacions;

    public PassiExpress(int id, Date data, TipusPassiExpress tipusPassi) {
        setId(id);
        setData(data);
        setTipusPassi(tipusPassi);
        infoUtilitzacions = new ArrayList<InfoUtilitzacio>();
    }

    //SETTERS
    public void setId(int id) {
        if(id<=0){
            throw new RuntimeException("El id ha de ser positiu");
        }
        this.id = id;
    }

    public void setData(Date data) {
        if(data == null){
            throw new RuntimeException("La data és obligatoria");
        }
        this.data = data;
    }

    public void setTipusPassi(TipusPassiExpress tipusPassi) {
        if(tipusPassi == null){
            throw new RuntimeException("El tipus de passi és obligatori");
        }
        this.tipusPassi = tipusPassi;
    }

    public int getNumInfoUtilitzacions(){
        return infoUtilitzacions.size();
    }

    public Iterable<InfoUtilitzacio> getInfoUtilitzacions(){
        return infoUtilitzacions;
    }

    public InfoUtilitzacio getEntrada(int index){
        return infoUtilitzacions.get(index);
    }

    public boolean removeInfoUtilitzacio(InfoUtilitzacio info){
        boolean esborrat = infoUtilitzacions.remove(info);
        if(esborrat){
            info.setPassi(null);
        }
        return esborrat;
    }

    public boolean addInfoUtilitzacio(InfoUtilitzacio info){
        boolean afegit = infoUtilitzacions.add(info);
        if(afegit){
            info.setPassi(this);
        }
        return afegit;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public TipusPassiExpress getTipusPassi() {
        return tipusPassi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final PassiExpress other = (PassiExpress) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PassiExpress{" + "id=" + id + ", data=" + data + ", tipusPassi=" + tipusPassi + ", infoUtilitzacions=" + infoUtilitzacions + '}';
    }

}
