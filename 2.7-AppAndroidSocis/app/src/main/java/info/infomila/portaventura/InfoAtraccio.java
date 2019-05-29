package info.infomila.portaventura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import info.infomila.portaventura.classes.Atraccio;

import static info.infomila.portaventura.MainActivity.NEW_ACTIVITY_INTENT_PARAM___ATRACCIO;


public class InfoAtraccio extends AppCompatActivity {

    private Atraccio mAtraccio = null;

    private TextView txvCodi;
    private TextView txvNom;
    private TextView txvCapacitatMaximaRonda;
    private TextView txvTempsPerRonda;
    private TextView txvClientsEnCua;
    private TextView txvAlsadaMinimaAcompanyant;
    private TextView txvAlsadaMinima;
    private ImageView imgAtraccio;

    private WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_atraccio);

        //Obtenim l'intent.
        Intent i = getIntent();
        mAtraccio = (Atraccio) i.getSerializableExtra(NEW_ACTIVITY_INTENT_PARAM___ATRACCIO);//ES NULL PER QUE?

        //Obtenim tots els camps a afegir.
        txvCodi = findViewById(R.id.txvCodi);
        txvNom = findViewById(R.id.txvNom);
        txvCapacitatMaximaRonda = findViewById(R.id.txvCapacitatMaximaRonda);
        txvTempsPerRonda = findViewById(R.id.txvTempsPerRonda);
        txvClientsEnCua = findViewById(R.id.txvClientsEnCua);
        txvAlsadaMinimaAcompanyant = findViewById(R.id.txvAlsadaMinimaAcompanyant);
        txvAlsadaMinima = findViewById(R.id.txvAlsadaMinima);
        imgAtraccio = findViewById(R.id.imgAtraccio);
        webView = findViewById(R.id.webView);

        if(mAtraccio!= null){
            carregarDadesAtraccio();
        }
    }

    private void carregarDadesAtraccio() {

        txvCodi.setText(""+mAtraccio.getCodi());
        txvNom.setText(mAtraccio.getNom());
        txvCapacitatMaximaRonda.setText("Capacitat maxima ronda: "+mAtraccio.getCapacitatMaximaRonda());

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.displayImage(mAtraccio.getUrlFoto(),imgAtraccio);

        //Carreguem el webView
        webView.loadData(mAtraccio.getDescripcioHTML(),"text/html", "UTF8");
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
