package info.infomila.portaventura;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import info.infomila.portaventura.classes.Atraccio;

public class AtraccionsAdapter extends RecyclerView.Adapter<AtraccionsAdapter.AtraccioViewHolder> {

    //Variables
    private List<Atraccio> mAtraccions;
    private MainActivity mActivity;
    private int mSelectedPosition = -1;

    //Constructor
    public AtraccionsAdapter(List<Atraccio> pAtraccios, MainActivity activity){
        mAtraccions = pAtraccios;
        mActivity = activity;
    }

    @NonNull
    @Override
    public AtraccioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        FrameLayout fila = (FrameLayout)
                LayoutInflater.from(parent.getContext()).inflate(R.layout.atraccio_row, parent , false);

        return new AtraccioViewHolder(fila);
    }


    // El onBindViewHolder omple una fila amb valors d'un objecte que esta a la posició "position"
    @Override
    public void onBindViewHolder(@NonNull AtraccioViewHolder holder, int position) {
        final Atraccio f = mAtraccions.get(position);

        holder.txvNom.setText( f.getNom() );
        holder.txvEstat.setText( ""+f.getEstatOperatiu() );
        holder.txvTempsEspera.setText("Temps espera: 50 min");

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.displayImage(f.getUrlFoto(),holder.imgAtraccio);

        if(position == mSelectedPosition){
            //Avisem al mainActivity per a que fagi un intent i mostri la info de l'atracció.

            MainActivity.mostrarInformacioHTML(mAtraccions.get(mSelectedPosition));
        }else{

        }

    }

    @Override
    public int getItemCount() {
        return mAtraccions.size();
    }

    protected class AtraccioViewHolder extends RecyclerView.ViewHolder{

        public TextView txvNom;
        public TextView txvEstat;
        public TextView txvTempsEspera;
        public ImageView imgAtraccio;

        public FrameLayout frlFila;

        public AtraccioViewHolder(View fila) {
            super(fila);

            txvNom = fila.findViewById(R.id.txvNom);
            txvEstat = fila.findViewById(R.id.txvEstat);
            txvTempsEspera = fila.findViewById(R.id.txvTempsEspera);
            imgAtraccio = fila.findViewById(R.id.imgAtraccio);

            frlFila = (FrameLayout)fila;

            //OnClickListener (Per poder clicar una carta
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int previamentSeleccionada = AtraccionsAdapter.this.mSelectedPosition;

                    if(mSelectedPosition == getAdapterPosition()) {
                        mSelectedPosition = -1;
                    } else {
                        mSelectedPosition = getAdapterPosition();
                    }
                    notifyItemChanged(previamentSeleccionada);
                    notifyItemChanged(mSelectedPosition);
                }
            });

        }
    }
}
