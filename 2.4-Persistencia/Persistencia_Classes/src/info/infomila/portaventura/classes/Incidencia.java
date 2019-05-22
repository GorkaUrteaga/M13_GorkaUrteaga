package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Gorka
 */
@Entity
public class Incidencia implements Serializable{
    
    // ATRIBUTS
    @Id
    @Column(columnDefinition="INT(3)")
    private int num;
    
    @Basic(optional=false)
    @Column(name="DATA_INICI",columnDefinition="DATE",nullable=false)
    private Date dataInici;
    
    @Basic
    @Column(name="DATA_FI",columnDefinition="DATE")
    private Date dataFi;
    
    @Basic
    @Column(name="MISSATGE_ESTAT",length=200)
    private String missatgeEstat;
    
    @Basic(optional=false)
    @Column(name="DATA_FI_PREVISTA",columnDefinition="DATE",nullable=false)
    private Date dataFiPrevista;
    
    // CONSTRUCTORS
    
    //Constructor per JPA
    protected Incidencia(){
    }
    
    public Incidencia(int num, Date dataInici, String missatgeEstat, Date dataFiPrevista) {
        this(num,dataInici,null,missatgeEstat,dataFiPrevista);
    }

    public Incidencia(int num, Date dataInici, Date dataFi, String missatgeEstat, Date dataFiPrevista) {
        setNum(num);
        setDataInici(dataInici);
        setDataFi(dataFi);
        setMissatgeEstat(missatgeEstat);
        setDataFiPrevista(dataFiPrevista);
    }

    // SETTERS
    
    public void setNum(int num) {
        if(num <= 0){
            throw new RuntimeException("El numero ha de ser positiu.");
        }
        this.num = num;
    }

    public void setDataFi(Date dataFi) {
        if(dataFi.before(dataInici)){
            throw new RuntimeException("La data fi no pot ser anterior a la data d'inici.");
        }
        this.dataFi = (Date)dataFi.clone();
    }

    public void setDataInici(Date dataInici) {
        if(dataInici == null){
            throw new RuntimeException("La data d'inici és obligatoria.");
        }
        this.dataInici = (Date) dataInici.clone();
    }

    public void setMissatgeEstat(String missatgeEstat) {
        this.missatgeEstat = missatgeEstat;
    }

    public void setDataFiPrevista(Date dataFiPrevista) {
        if(dataFiPrevista == null){
            throw new RuntimeException("La data fi prevista és obligatoria.");
        }
        if(dataFiPrevista.before(dataInici)){
            throw new RuntimeException("La data fi prevista no pot .");
        }
        this.dataFiPrevista = (Date) dataFiPrevista.clone();
    }
    
    // GETTERS
    public int getNum() {
        return num;
    }
    
    public Date getDataFiPrevista() {
        return dataFiPrevista;
    }
    
    public String getMissatgeEstat() {
        return missatgeEstat;
    }
    
    public Date getDataInici() {
        return dataInici;
    }
    
    public Date getDataFi() {
        return dataFi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.num;
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
        final Incidencia other = (Incidencia) obj;
        if (this.num != other.num) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "num=" + num + ", dataInici=" + dataInici + ", dataFi=" + dataFi + ", missatgeEstat=" + missatgeEstat + ", dataFiPrevista=" + dataFiPrevista + '}';
    }

    
    
}
