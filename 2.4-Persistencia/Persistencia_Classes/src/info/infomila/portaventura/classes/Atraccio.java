package info.infomila.portaventura.classes;

import info.infomila.portaventura.enums.EstatOperatiu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author Gorka
 */
@Entity
public class Atraccio implements Serializable{
    
    // ATRIBUTS
    @Id
    @Column (columnDefinition="INT(3)")
    private int codi;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ZONA", insertable=false, updatable=false,
            foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (ZONA) REFERENCES ZONA(NUMERO)"))
    private Zona zona;
    
    /*@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "PARC", insertable=false, updatable=false,
            foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (PARC) REFERENCES ZONA(PARC)"))
    private Parc parc;
    */
    @Basic(optional=false)
    @Column (name="CAPACITAT_MAXIMA_RONDA",columnDefinition="INT(3)", nullable=false)
    private int capacitatMaximaRonda;
    
    @Basic(optional=false)
    @Column (name="DESCRIPCIO_HTML",length=400, nullable=false)
    private String descripcioHTML;
    
    @Basic(optional=false)
    @Column (length=40, nullable=false)
    private String nom;
    
    @Basic(optional=false)
    @Column (name="TEMPS_PER_RONDA",columnDefinition="INT(3)", nullable=false)
    private int tempsPerRonda;
    
    @Basic(optional=false)
    @Column (name="URL_FOTO", length=500, nullable=false)
    private String urlFoto;
    
    @Basic(optional=false)
    @Column (name="CLIENTS_EN_CUA",columnDefinition="INT(3)", nullable=false)
    private int clientsEnCua;
    
    @Basic(optional=false)
    @Column (name="ALSADA_MINIMA_AMB_ACOMPANYANT",columnDefinition="INT(3)", nullable=false)
    private int alsadaMinimaAmbAcompanyant;
    
    @Basic(optional=false)
    @Column (name="ALSADA_MINIMA",columnDefinition="INT(3)", nullable=false)
    private int alsadaMinima;
    
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name="ESTAT_OPERATIU")
    //private EstatOperatiu estatOperatiu;
    private String estatOperatiu;
            
    @OneToMany(mappedBy="atraccio",cascade = {CascadeType.PERSIST})
    private List<Incidencia> incidencies = new ArrayList<Incidencia>();
    
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "INCIDENCIA",
            foreignKey = @ForeignKey(name="FK_Atraccio_Incidencia",foreignKeyDefinition = "FOREIGN KEY (INCIDENCIA) REFERENCES INCIDENCIA(NUM)"))
    private Incidencia incidencia;
    
    //Falta potser algun atribut derivat d'una altre classe
    
    // CONSTRUCTORS
    // Constructor per JPA
    protected Atraccio(){
    }
    
    // M'espero per si falta alguna classe
    
    
    // SETTERS

    public Atraccio(int codi, Zona zona, int capacitatMaximaRonda, String descripcioHTML, String nom, int tempsPerRonda, String urlFoto, int clientsEnCua, int alsadaMinimaAmbAcompanyant, int alsadaMinima, String estatOperatiu) {
        setCodi(codi);
        setZona(zona);
        setCapacitatMaximaRonda(capacitatMaximaRonda);
        setDescripcioHTML(descripcioHTML);
        setNom(nom);
        setTempsPerRonda(tempsPerRonda);
        setUrlFoto(urlFoto);
        setClientsEnCua(clientsEnCua);
        setAlsadaMinimaAmbAcompanyant(alsadaMinimaAmbAcompanyant);
        setAlsadaMinima(alsadaMinima);
        setEstatOperatiu(estatOperatiu);
        incidencies = new ArrayList<Incidencia>();
    }
    
    //incidencia actual
    public void setIncidencia(Incidencia incidencia) {
        if(incidencia != null && !incidencia.getAtraccio().equals(this)){
            incidencia.setAtraccio(this);
        }
        this.incidencia = incidencia;
    }
    
    public void setZona(Zona zona) {
        if(zona == null){
            throw new RuntimeException("La zona és obligatoria.");
        }
        
        this.zona = zona;
    }

    public void setCodi(int codi) {
        if(codi <= 0){
            throw new RuntimeException("El codi ha de ser positiu.");
        }
        this.codi = codi;
    }

    public void setCapacitatMaximaRonda(int capacitatMaximaRonda) {
        if(capacitatMaximaRonda <= 0){
            throw new RuntimeException("La capacitat màxima per ronda ha de ser positiva.");
        }
        this.capacitatMaximaRonda = capacitatMaximaRonda;
    }

    public void setDescripcioHTML(String descripcioHTML) {
        this.descripcioHTML = descripcioHTML;
    }

    public void setNom(String nom) {
        if(nom == null || nom.trim().equals("")){
            throw new RuntimeException("El nom és obligatori.");
        }
        this.nom = nom;
    }

    public void setTempsPerRonda(int tempsPerRonda) {
        if(tempsPerRonda <= 0){
            throw new RuntimeException("El temps per ronda ha de ser positiu.");
        }
        this.tempsPerRonda = tempsPerRonda;
    }

    public void setUrlFoto(String urlFoto) {
        if(urlFoto == null || urlFoto.trim().equals("")){
            throw new RuntimeException("La url de la foto és obligatoria.");
        }
        this.urlFoto = urlFoto;
    }

    public void setClientsEnCua(int clientsEnCua) {
        if(clientsEnCua < 0){
            throw new RuntimeException("Els clients en cua han de ser positius o 0.");
        }
        this.clientsEnCua = clientsEnCua;
    }

    public void setAlsadaMinimaAmbAcompanyant(int alsadaMinimaAmbAcompanyant) {
        if(alsadaMinimaAmbAcompanyant <= 0){
            throw new RuntimeException("L'alçada minima amb acompanyant ha de ser superior a 0.");
        }
        this.alsadaMinimaAmbAcompanyant = alsadaMinimaAmbAcompanyant;
    }

    public void setAlsadaMinima(int alsadaMinima) {
        if(alsadaMinima <= 0){
            throw new RuntimeException("L'alçada minima ha de ser superior a 0.");
        }
        this.alsadaMinima = alsadaMinima;
    }

    public void setEstatOperatiu(String estatOperatiu) {
        if(estatOperatiu == null){
            throw new RuntimeException("L'estat operatiu és obligatori.");
        }
        this.estatOperatiu = estatOperatiu;
    }
    
    // METODES LLISTA
    public int getNumIncidencies(){
        return incidencies.size();
    }
    
    public Iterable<Incidencia> iteIncidencies(){
        return incidencies;
    }
    
    public Incidencia getIncidencia(int index){
        return incidencies.get(index);
    }
    
    public boolean removeIncidencia(Incidencia inc){
        return incidencies.remove(inc);
    }
    
    public boolean addIncidencia(Incidencia inc){
        return incidencies.add(inc);
    }
    
    // GETTERS
    public Zona getZona() {
        return zona;
    }

    public int getCodi() {
        return codi;
    }

    public int getCapacitatMaximaRonda() {
        return capacitatMaximaRonda;
    }

    public String getDescripcioHTML() {
        return descripcioHTML;
    }

    public String getNom() {
        return nom;
    }

    public int getTempsPerRonda() {
        return tempsPerRonda;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public int getClientsEnCua() {
        return clientsEnCua;
    }

    public int getAlsadaMinimaAmbAcompanyant() {
        return alsadaMinimaAmbAcompanyant;
    }

    public int getAlsadaMinima() {
        return alsadaMinima;
    }

    public String getEstatOperatiu() {
        return estatOperatiu;
    }
    
    public Incidencia getIncidencia() {
        return incidencia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.zona);
        hash = 67 * hash + this.codi;
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
        final Atraccio other = (Atraccio) obj;
        if (this.codi != other.codi) {
            return false;
        }
        if (!Objects.equals(this.zona, other.zona)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atraccio{ codi=" + codi + ", capacitatMaximaRonda=" + capacitatMaximaRonda + ", descripcioHTML=" + descripcioHTML + ", nom=" + nom + ", tempsPerRonda=" + tempsPerRonda + ", urlFoto=" + urlFoto + ", clientsEnCua=" + clientsEnCua + ", alsadaMinimaAmbAcompanyant=" + alsadaMinimaAmbAcompanyant + ", alsadaMinima=" + alsadaMinima + ", estatOperatiu=" + estatOperatiu + ", incidencies=" + incidencies + '}';
    }
    
}
