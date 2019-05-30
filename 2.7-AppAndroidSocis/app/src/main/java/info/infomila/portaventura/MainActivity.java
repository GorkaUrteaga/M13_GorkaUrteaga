package info.infomila.portaventura;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import info.infomila.portaventura.classes.PassiExpress;
import info.infomila.portaventura.classes.Zona;
import info.infomila.portaventura.classes.Atraccio;
import info.infomila.portaventura.classes.Parc;
import info.infomila.portaventura.classes.SocketClient;

import static info.infomila.portaventura.LoginUsuari.CLIENT_ID;

public class MainActivity extends AppCompatActivity {
    public static final String NEW_ACTIVITY_INTENT_PARAM___ATRACCIO = "Atraccio";
    private static RecyclerView rcvAtraccions;
    private static AtraccionsAdapter mAdapter;
    private static Spinner sprParcs;
    private static MainActivity mActivity;
    private static List<Parc> mParcs;
    private static Parc parcSeleccionat;
    private static EditText edtFiltre;
    private static NavigationView navigation;


    public static void mostrarInformacioHTML(Atraccio atraccio) {

        if(atraccio == null) return;
        //Fem un intent per mostrar la informació d'aquesta.
        mActivity.intentPerMostrarAtraccio(atraccio);

    }

    private void intentPerMostrarAtraccio(Atraccio atraccio) {
        Intent i = new Intent( this, InfoAtraccio.class);
        i.putExtra(NEW_ACTIVITY_INTENT_PARAM___ATRACCIO, atraccio);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvAtraccions = findViewById(R.id.rcvAtraccions);
        rcvAtraccions.setLayoutManager(new GridLayoutManager(this,2));
        rcvAtraccions.setHasFixedSize(true);

        edtFiltre = findViewById(R.id.edtFiltre);
        navigation = findViewById(R.id.navigation);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String titol = (String) menuItem.getTitle();
                switch (titol){
                    case "Passis":
                        Intent i = new Intent( mActivity, PassisUsuari.class);
                        startActivity(i);
                        break;
                    case "Login":
                        Intent x = new Intent(mActivity,LoginUsuari.class);
                        startActivity(x);
                        break;
                }
                return true;
            }
        });

        sprParcs = findViewById(R.id.sprParcs);
        mActivity = this;

        //Inicialitzem el ImageLoader
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
    }

    public void btnFitlreClick(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        afegirAtraccionsLlista(true);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        obtenirParcs();

        //Fiquem l'escoltador de selectedItem al spinner.
        sprParcs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos!= -1){
                    parcSeleccionat = mParcs.get(pos);
                    afegirAtraccionsLlista(false);

                }
            }

            public void onNothingSelected(AdapterView<?> adapterView)
            {
                parcSeleccionat = null;
            }
        });

    }


    private void obtenirParcs() {
        //Opcio 1 es per obtenir els parcs amb les zones i les atraccions
        SocketClient sc = new SocketClient(1,mActivity);
        sc.execute();
    }

    public void afegirParcsSpinner(List<Parc> parcs) {

        mParcs = parcs;

        List<String> llParcs = new ArrayList<String>();
        for(Parc p : parcs){
            llParcs.add(p.getNom());
            Log.d("PARCS",p.getNom());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mActivity,
                android.R.layout.simple_spinner_item, llParcs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprParcs.setAdapter(dataAdapter);

    }


    private void afegirAtraccionsLlista(Boolean filtre) {

        if(parcSeleccionat == null) return;

        List<Atraccio> atraccions = new ArrayList<Atraccio>();
        String atraccioFiltrada = "";
        if(edtFiltre.getText()!=null){
            atraccioFiltrada = edtFiltre.getText().toString();
        }

        for(Zona z:parcSeleccionat.getZones()){
            for(Atraccio a:z.getAtraccions()){
                if(filtre){
                    if(a.getNom().toLowerCase().contains(atraccioFiltrada.toLowerCase())){
                        atraccions.add(a);
                    }
                }else{
                    atraccions.add(a);
                }

            }
        }

        mAdapter = new AtraccionsAdapter(atraccions,mActivity);
        rcvAtraccions.setAdapter(mAdapter);
    }

}
