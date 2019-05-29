package info.infomila.portaventura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import info.infomila.portaventura.classes.Atraccio;
import info.infomila.portaventura.classes.Parc;
import info.infomila.portaventura.classes.SocketClient;
import info.infomila.portaventura.classes.Zona;
import portaventura.infomila.info.apptreballadors.R;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView rcvAtraccions;
    private static AtraccionsAdapter mAdapter;
    private static MainActivity mActivity;

    public static void mostrarInfoEscanejar(Atraccio atraccio) {

    }

    public static void afegirParcsLlista(List<Parc> parcs) {

        List<Atraccio> atraccions = new ArrayList<Atraccio>();
        String atraccioFiltrada = "";

        for(Parc parc:parcs){
            for(Zona z:parc.getZones()){
                for(Atraccio a:z.getAtraccions()){
                    atraccions.add(a);
                }
            }
        }

        mAdapter = new AtraccionsAdapter(atraccions,mActivity);
        rcvAtraccions.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvAtraccions = findViewById(R.id.rcvAtraccions);

        //Inicialitzem el ImageLoader
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        rcvAtraccions.setLayoutManager(new GridLayoutManager(this,1));
        rcvAtraccions.setHasFixedSize(true);

        mActivity = this;

    }

    @Override
    protected void onStart()
    {
        // TODO Auto-generated method stub
        super.onStart();
        obtenirParcs();
    }

    private void obtenirParcs() {
        //Opcio 1 es per obtenir els parcs amb les zones i les atraccions
        SocketClient sc = new SocketClient(1);
        sc.execute();
    }

}
