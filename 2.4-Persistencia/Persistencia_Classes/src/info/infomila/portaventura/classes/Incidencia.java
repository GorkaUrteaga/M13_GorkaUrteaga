package info.infomila.portaventura.classes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ATRACCIO", updatable=false,
            foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (ATRACCIO) REFERENCES ATRACCIO(CODI)"))
    private Atraccio atraccio;
    
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
    
    //@Transient
    //private static int seguentNumero;
    
    // CONSTRUCTORS
    
    //Constructor per JPA
    protected Incidencia(){
    }
    
    public Incidencia(int num, Atraccio atraccio, Date dataInici, String missatgeEstat, Date dataFiPrevista) {
        this(num,atraccio,dataInici,null,missatgeEstat,dataFiPrevista);
    }

    public Incidencia(int num, Atraccio atraccio, Date dataInici, Date dataFi, String missatgeEstat, Date dataFiPrevista) {
        setNum(num);
        setDataInici(dataInici);
        setDataFi(dataFi);
        setMissatgeEstat(missatgeEstat);
        setDataFiPrevista(dataFiPrevista);
        setAtraccio(atraccio);
    }

    // SETTERS
    
    public void setNum(int num) {
        if(num <= 0){
            throw new RuntimeException("El numero ha de ser positiu.");
        }
        this.num = num;
    }

    public void setAtraccio(Atraccio atraccio) {
        if(atraccio == null){
            throw new RuntimeException("La atraccio és obligatoria.");
        }

        this.atraccio = atraccio;
    }
    
    public void setDataFi(Date dataFi) {
        if(dataFi != null && dataFi.before(dataInici)){
            throw new RuntimeException("La data fi no pot ser anterior a la data d'inici.");
        }
        if(dataFi!=null){
            this.dataFi = (Date)dataFi.clone();
        }else{
            this.dataFi = null;
        }
        
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

    public Atraccio getAtraccio() {
        return atraccio;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = num + "-Data inici:" + dataInici +", Data Fi Prevista:" + sdf.format(dataFiPrevista);
        if(dataFi!=null){
            str+=" (TANCADA)";
        }else{
            str+=" (OBERTA)";
        }
        return str; 
    }
    
}
