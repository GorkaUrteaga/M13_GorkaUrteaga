package info.infomila.portaventura;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.infomila.portaventura.classes.Client;
import info.infomila.portaventura.classes.SocketClient;

public class LoginUsuari extends AppCompatActivity {

    public static final String CLIENT_ID = "CLIENT_ID";
    private NavigationView navigation;
    private static LoginUsuari mLogin;
    private EditText edtUser;
    private EditText edtPassword;
    private static TextView txvUsuariConnectat;
    public static Client clientLoginat;

    public static void obtenirClient(Client cli) {
        //Aquí obtenim o no el client.

        if(cli == null){
            //No funciona el toast
            Toast.makeText(mLogin, "Error en el login", Toast.LENGTH_LONG).show();
            return;
        }

        clientLoginat = cli;
        txvUsuariConnectat.setText(cli.getNom()+", "+cli.getCognom1());

        //Guardem a disc la seva id
        SharedPreferences sp = mLogin.getSharedPreferences(mLogin.getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(CLIENT_ID, cli.getId());
        editor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuari);

        navigation = findViewById(R.id.navigation);
        mLogin = this;

        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        txvUsuariConnectat = findViewById(R.id.txvUsuariConnectat);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String titol = (String) menuItem.getTitle();
                switch (titol){
                    case "Atraccions":
                        Intent i = new Intent( mLogin, MainActivity.class);
                        startActivity(i);
                        break;
                    case "Passis":
                        Intent x = new Intent( mLogin, PassisUsuari.class);
                        startActivity(x);
                        break;
                }
                return true;
            }
        });

        //Comprovem si existeix un id guardat.
        if(clientLoginat == null){
            SharedPreferences sp = this.getSharedPreferences(this.getPackageName(), MODE_PRIVATE);
            int clientId  = sp.getInt(CLIENT_ID, 0);

            if(clientId!=0){
                //Anem a buscar a aquest client i el carreguem com client actual

                SocketClient sock = new SocketClient(3,clientId);
                sock.execute();
            }
        }

    }

    public void btnLoginClick(View v)
    {
        //Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        intentarLoginar();
    }

    private void intentarLoginar() {

        SocketClient sc = new SocketClient(2, edtUser.getText().toString(), edtPassword.getText().toString());
        sc.execute();
    }
}
