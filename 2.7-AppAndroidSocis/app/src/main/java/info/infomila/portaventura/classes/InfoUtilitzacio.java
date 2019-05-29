package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.util.Objects;

public class InfoUtilitzacio implements Serializable {

    private PassiExpress passi;
    private Atraccio atraccio;
    private int numUsos;
    private String TipusAcces;

    //CONSTRUCTORS
    public InfoUtilitzacio(PassiExpress passi, Atraccio atraccio, int numUsos, String TipusAcces) {
        this.passi = passi;
        this.atraccio = atraccio;
        this.numUsos = numUsos;
        this.TipusAcces = TipusAcces;
    }

    //SETTERS
    public void setPassi(PassiExpress passi) {
        this.passi = passi;
    }

    public void setAtraccio(Atraccio atraccio) {
        this.atraccio = atraccio;
    }

    public void setNumUsos(int numUsos) {
        this.numUsos = numUsos;
    }

    public void setTipusAcces(String TipusAcces) {
        this.TipusAcces = TipusAcces;
    }


    //GETTERS
    public PassiExpress getPassi() {
        return passi;
    }

    public Atraccio getAtraccio() {
        return atraccio;
    }

    public int getNumUsos() {
        return numUsos;
    }

    public String getTipusAcces() {
        return TipusAcces;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.passi);
        hash = 59 * hash + Objects.hashCode(this.atraccio);
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
        final InfoUtilitzacio other = (InfoUtilitzacio) obj;
        if (!Objects.equals(this.passi, other.passi)) {
            return false;
        }
        if (!Objects.equals(this.atraccio, other.atraccio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InfoUtilitzacio{" + "atraccio=" + atraccio + ", numUsos=" + numUsos + ", TipusAcces=" + TipusAcces + '}';
    }

}
