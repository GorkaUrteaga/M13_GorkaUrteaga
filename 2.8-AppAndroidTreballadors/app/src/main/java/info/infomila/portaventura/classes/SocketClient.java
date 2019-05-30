package info.infomila.portaventura.classes;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import info.infomila.portaventura.InfoAtraccio;
import info.infomila.portaventura.MainActivity;

public class SocketClient extends AsyncTask<Void,Void,Void> {

    private int opcio;
    private static String hostName = "192.168.1.176";//"10.150.0.171";//"192.168.1.176";//"10.132.2.106";
    private static int port = 666;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private int idPassi;
    private int idAtraccio;
    private Activity mActivity;
    private List<Parc> parcs = null;

    private int potAccedir = 0;
    private String motiu = "";
    private Boolean potPrimeraFila = false;

    public SocketClient(int opcio, Activity pActivity){
        this.opcio = opcio;
        this.mActivity = pActivity;
    }


    public SocketClient(int opcio, int idPassi, int idAtraccio, Activity pActivity){
        this.opcio = opcio;
        this.idPassi = idPassi;
        this.idAtraccio = idAtraccio;
        this.mActivity = pActivity;
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
                case 4:
                    rebrePotAccedir();
                    break;
                case 5:
                    confirmarAcces();

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

    private void confirmarAcces() {

        try {

            out.writeObject(idPassi);
            out.writeObject(idAtraccio);

        } catch (Exception e) {
            Log.e("ERROR SOCKET",":",e);
        }

    }

    private void rebrePotAccedir() {

        try {

            out.writeObject(idPassi);
            out.writeObject(idAtraccio);

            potAccedir = (int) in.readObject();
            potPrimeraFila = (Boolean) in.readObject();
            motiu = (String) in.readObject();

        } catch (Exception e) {
            Log.e("ERROR SOCKET",":",e);
        }

        //InfoAtraccio.potAccedirUsuari(potAccedir,motiu, potPrimeraFila);
    }

    private void rebreLlistaParcs() {

        try {

            parcs = (List<Parc>)in.readObject();

        } catch (Exception e) {
            Log.e("ERROR SOCKET",":",e);
        }

        //MainActivity.afegirParcsLlista(parcs);
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);

        switch (opcio){
            case 1:
                ((MainActivity)mActivity).afegirParcsLlista(parcs);
                break;
            case 4:
                ((InfoAtraccio)mActivity).potAccedirUsuari(potAccedir,motiu, potPrimeraFila);
                break;

        }

    }
}