package info.infomila.portaventura;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import info.infomila.portaventura.classes.InfoUtilitzacio;
import info.infomila.portaventura.classes.PassiExpress;

public class PassisUsuari extends AppCompatActivity {

    private NavigationView navigation;
    private static PassisUsuari mPassis;
    private static PassisAdapter mAdapter;
    private static InfoUtilitzacioAdapter mAdapterInfo;
    private RecyclerView rcvPassis;
    private static RecyclerView rcvInfoUtilitzacions;
    private static FloatingActionButton fabPassis;
    private static PassiExpress passiExpressSeleccionat;
    public static final String CODI_PASSI = "CODI_PASSI";

    public static void mostrarInfoUtilitzacio(PassiExpress passiExpress) {
        passiExpressSeleccionat = passiExpress;
        if(passiExpress == null) return;
        List<InfoUtilitzacio> infoUtilitzacions = (List<InfoUtilitzacio>) passiExpress.getInfoUtilitzacions();
        mAdapterInfo = new InfoUtilitzacioAdapter(infoUtilitzacions,mPassis);
        rcvInfoUtilitzacions.setLayoutManager(new LinearLayoutManager(mPassis, LinearLayout.VERTICAL,false));
        rcvInfoUtilitzacions.setHasFixedSize(true);
        rcvInfoUtilitzacions.setAdapter(mAdapterInfo);
        fabPassis.clearAnimation();
        fabPassis.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passis_usuari);


        navigation = findViewById(R.id.navigation);
        mPassis = this;
        rcvPassis = findViewById(R.id.rcvPassis);

        rcvInfoUtilitzacions = findViewById(R.id.rcvInfoUtilitzacions);
        fabPassis = findViewById(R.id.fabPassis);
        fabPassis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fem un intent per mostrar el qr del passi.

                if(passiExpressSeleccionat == null){
                    Snackbar.make(view, "S'ha de seleccionar un passi", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Intent i = new Intent( mPassis,QrPassi.class);
                    i.putExtra(CODI_PASSI,passiExpressSeleccionat.getId());
                    startActivity(i);
                }
            }
        });


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String titol = (String) menuItem.getTitle();
                switch (titol){
                    case "Atraccions":
                        Intent i = new Intent( mPassis, MainActivity.class);
                        startActivity(i);
                        break;
                    case "Login":
                        Intent x = new Intent( mPassis,LoginUsuari.class);
                        startActivity(x);
                        break;
                }
                return true;
            }
        });

        if(LoginUsuari.clientLoginat==null){
            Toast.makeText(this, "Primer t'has de loginar", Toast.LENGTH_LONG).show();
        }else{
            mAdapter = new PassisAdapter((List<PassiExpress>)LoginUsuari.clientLoginat.getPassisExpres(),mPassis);
            rcvPassis.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false));
            rcvPassis.setHasFixedSize(true);
            rcvPassis.setAdapter(mAdapter);
        }
    }


}
