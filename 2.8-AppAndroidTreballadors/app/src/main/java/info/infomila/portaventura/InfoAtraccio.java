package info.infomila.portaventura;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nostra13.universalimageloader.core.ImageLoader;

import info.infomila.portaventura.classes.Atraccio;
import info.infomila.portaventura.classes.SocketClient;
import portaventura.infomila.info.apptreballadors.R;

import static info.infomila.portaventura.MainActivity.NEW_ACTIVITY_INTENT_PARAM___ATRACCIO;

public class InfoAtraccio extends AppCompatActivity {

    private Atraccio atraccio;
    private TextView txvNom;
    private static TextView txvAccesConcedit;
    private static Button btnConfirmarPujat;
    private ImageView imgAtraccio;
    private static int idPassi = 0;

    public void potAccedirUsuari(int potAccedir, String motiu, Boolean potPrimeraFila) {

        if(potAccedir==1){

            motiu += potPrimeraFila?" POT ACCEDIR PRIMERA FILA":" NO POT ACCEDIR PRIMERA FILA";
            txvAccesConcedit.setText("POT ACCEDIR "+motiu);
            txvAccesConcedit.setBackgroundColor(Color.GREEN);
            btnConfirmarPujat.setVisibility(View.VISIBLE);

        }else{
            String str = "NO POT ACCEDIR: "+motiu;
            txvAccesConcedit.setText(str);
            txvAccesConcedit.setBackgroundColor(Color.RED);
            btnConfirmarPujat.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_atraccio);

        Intent i = getIntent();
        atraccio = (Atraccio) i.getSerializableExtra(NEW_ACTIVITY_INTENT_PARAM___ATRACCIO);

        txvNom = findViewById(R.id.txvNom);
        txvAccesConcedit = findViewById(R.id.txvAccesConcedit);
        btnConfirmarPujat = findViewById(R.id.btnConfirmarPujat);
        imgAtraccio = findViewById(R.id.imgAtraccio);

        txvNom.setText(atraccio.getNom());
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(atraccio.getUrlFoto(),imgAtraccio);


    }

    public void btnConfirmarPujatClick(View v)
    {
        Toast.makeText(this, "Confirmat", Toast.LENGTH_LONG).show();
        //Enviam via sockets que ha pujat i el programa s'encarrega de aumentar el comptador.
        SocketClient sk = new SocketClient(5,idPassi,atraccio.getCodi(),this);
        sk.execute();
    }

    public void btnScanQrClick(View v)
    {
        //Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        //Escannejar el codi qr
        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);

        intent.setPrompt("Scan");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Cancelat l'escaneig", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                try{
                     idPassi = Integer.parseInt(result.getContents());
                }catch (Exception ex){
                    txvAccesConcedit.setText("NO S'HA LLEGIT CORRECTAMENT EL CODI QR");
                }

                if(idPassi > 0){
                    SocketClient sk = new SocketClient(4,idPassi,atraccio.getCodi(),this);
                    sk.execute();
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
