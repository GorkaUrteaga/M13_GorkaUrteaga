package info.infomila.portaventura;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import info.infomila.portaventura.classes.PassiExpress;

public class PassisUsuari extends AppCompatActivity {

    private NavigationView navigation;
    private PassisUsuari mPassis;
    private PassisAdapter mAdapter;
    private RecyclerView rcvPassis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passis_usuari);


        navigation = findViewById(R.id.navigation);
        mPassis = this;
        rcvPassis = findViewById(R.id.rcvPassis);

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
            rcvPassis.setAdapter(mAdapter);
        }
    }
}
