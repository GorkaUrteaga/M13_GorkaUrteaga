package info.infomila.portaventura;

import java.util.Date;

/**
 *
 * @author Gorka
 */
public class Incidencia {
    
    // ATRIBUTS
    private int num;
    private Date dataInici;
    private Date dataFi;
    private String missatgeEstat;
    private Date dataFiPrevista;
    private Atraccio atraccio;
    
    // CONSTRUCTORS
    
    //Constructor per JPA
    protected Incidencia(){
    }
    
    public Incidencia(int num, Date dataInici, String missatgeEstat, Date dataFiPrevista, Atraccio atraccio) {
        this(num,dataInici,null,missatgeEstat,dataFiPrevista,atraccio);
    }

    public Incidencia(int num, Date dataInici, Date dataFi, String missatgeEstat, Date dataFiPrevista, Atraccio atraccio) {
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
    
    public void setAtraccio(Atraccio atraccio) {
        if(atraccio == null){
            throw new RuntimeException("L'atraccio és obligatoria.");
        }
        this.atraccio = atraccio;
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
}
