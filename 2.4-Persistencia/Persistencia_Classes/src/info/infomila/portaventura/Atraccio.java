package info.infomila.portaventura;

import java.util.List;

/**
 *
 * @author Gorka
 */
public class Atraccio {
    
    // ATRIBUTS
    private Zona zona;
    private int codi;
    private int capacitatMaximaRonda;
    private String descripcioHTML;
    private String nom;
    private int tempsPerRonda;
    private String urlFoto;
    private int clientsEnCua;
    private int alsadaMinimaAmbAcompanyant;
    private int alsadaMinima;
    
    private EstatOperatiu estatOperatiu;
    private List<Incidencia> incidencies;
    
    //Falta potser algun atribut derivat d'una altre classe
    
    // CONSTRUCTORS
    // Constructor per JPA
    protected Atraccio(){
    }
    
    // M'espero per si falta alguna classe
    
    
    // SETTERS
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
        if(descripcioHTML == null || descripcioHTML.trim().equals("")){
            throw new RuntimeException("La descripció és obligatoria.");
        }
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

    public void setEstatOperatiu(EstatOperatiu estatOperatiu) {
        if(estatOperatiu == null){
            throw new RuntimeException("L'estat operatiu és obligatori.");
        }
        this.estatOperatiu = estatOperatiu;
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

    public EstatOperatiu getEstatOperatiu() {
        return estatOperatiu;
    }
    
    
    
}
