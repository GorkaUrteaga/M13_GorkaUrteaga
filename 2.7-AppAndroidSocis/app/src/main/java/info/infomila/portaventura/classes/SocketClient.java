package info.infomila.portaventura.classes;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import info.infomila.portaventura.LoginUsuari;
import info.infomila.portaventura.MainActivity;

public class SocketClient extends AsyncTask<Void,Void,Void> {

    private int opcio;
    private static String hostName = "10.150.0.171";//"192.168.1.176";//"10.150.0.171";//"10.132.2.106";
    private static int port = 666;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private String login;
    private String password;
    private int clientId;

    public SocketClient(int opcio){
        this.opcio = opcio;
    }

    public SocketClient(int opcio,String login, String password){
        this.opcio = opcio;
        this.login = login;
        this.password = password;
    }

    public SocketClient(int opcio,int clientId){
        this.opcio = opcio;
        this.clientId = clientId;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Socket socket = new Socket(hostName, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            //out.writeInt(opcio);
            out.writeObject(opcio);

            switch (opcio){
                case 1:
                    rebreLlistaParcs();
                    break;
                case 2:
                    loginClient();
                    break;
                case 3:
                    recuperarClientViaId();
                    break;
            }

        }catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            Log.e("ERROR DE SOCKET","POS ESO",e);
            //System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            Log.e("ERROR DE SOCKET","POS ESO",e);
            //System.exit(1);
        }
        finally {
            return null;
        }
    }

    private void recuperarClientViaId() {
        Client cli = null;
        try {
            out.writeObject(clientId);

            int totOk = (int) in.readObject();

            if(totOk == 1){
                cli = (Client) in.readObject();
            }

        } catch (Exception e) {
            Log.e("ERROR SOCKET",":",e);
        }

        LoginUsuari.obtenirClient(cli);

    }

    private void loginClient() {

        List<String> parametresLogin = new ArrayList<String>();
        parametresLogin.add(login);
        parametresLogin.add(password);
        Client cli = null;
        try {
            out.writeObject(parametresLogin);

            int resultat = (int) in.readObject();

            if(resultat == 1){
                cli = (Client) in.readObject();
            }

        } catch (Exception e) {
            Log.e("ERROR SOCKET",":",e);
        }

        LoginUsuari.obtenirClient(cli);

    }

    private void rebreLlistaParcs() {
        List<Parc> parcs = null;
        try {

            parcs = (List<Parc>)in.readObject();

        } catch (Exception e) {
            Log.e("ERROR SOCKET",":",e);
        }

        MainActivity.afegirParcsSpinner(parcs);
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);

    }
}