/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Gorka
 */
public class ZonaParcFK implements Serializable {

    private int parc_code;
    private int zona;

    protected ZonaParcFK() {
    }

    public ZonaParcFK(int parc_code, int zona) {
        this.parc_code = parc_code;
        this.zona = zona;
    }

    public void setParc_code(int parc_code) {
        this.parc_code = parc_code;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public int getParc_code() {
        return parc_code;
    }

    public int getZona() {
        return zona;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.parc_code;
        hash = 41 * hash + this.zona;
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
        final ZonaParcFK other = (ZonaParcFK) obj;
        if (this.parc_code != other.parc_code) {
            return false;
        }
        if (this.zona != other.zona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ZonaParcFK{" + "parc_code=" + parc_code + ", zona=" + zona + '}';
    }
    
    
    
}
