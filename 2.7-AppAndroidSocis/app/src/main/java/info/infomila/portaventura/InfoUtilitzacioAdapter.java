package info.infomila.portaventura;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import info.infomila.portaventura.classes.InfoUtilitzacio;

public class InfoUtilitzacioAdapter extends RecyclerView.Adapter<InfoUtilitzacioAdapter.InfoUtilitzacioViewHolder> {

    //Variables
    private List<InfoUtilitzacio> mInfoUtilitzacions;
    private PassisUsuari mActivity;
    private int mSelectedPosition = -1;

    //Constructor
    public InfoUtilitzacioAdapter(List<InfoUtilitzacio> pInfoUtilitzacions, PassisUsuari activity){
        mInfoUtilitzacions = pInfoUtilitzacions;
        mActivity = activity;
    }

    @NonNull
    @Override
    public InfoUtilitzacioAdapter.InfoUtilitzacioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        FrameLayout fila = (FrameLayout)
                LayoutInflater.from(parent.getContext()).inflate(R.layout.info_utilitzacio_row, parent , false);

        return new InfoUtilitzacioAdapter.InfoUtilitzacioViewHolder(fila);
    }


    // El onBindViewHolder omple una fila amb valors d'un objecte que esta a la posició "position"
    @Override
    public void onBindViewHolder(@NonNull InfoUtilitzacioAdapter.InfoUtilitzacioViewHolder holder, int position) {
        final InfoUtilitzacio f = mInfoUtilitzacions.get(position);

        if(f==null)return;

        holder.txvTipusAcces.setText(f.getTipusAcces());
        holder.txvNomAtraccio.setText(f.getAtraccio().getNom());

        if(f.getNumUsos()>0 && f.getTipusAcces() != "ILIMITAT"){
            holder.llyInnerRow.setBackgroundColor(Color.RED);
        }else{
            holder.llyInnerRow.setBackgroundColor(Color.TRANSPARENT);
        }
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.displayImage(f.getAtraccio().getUrlFoto(),holder.imgAtraccio);


        if(position == mSelectedPosition){
            //Avisem al PassisUsuari i fem un intent per mostrar el qr
            //MainActivity.mostrarInfoUtilitzacio(mInfoUtilitzacions.get(mSelectedPosition));
        }else{

        }

    }

    @Override
    public int getItemCount() {
        return mInfoUtilitzacions.size();
    }

    protected class InfoUtilitzacioViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgAtraccio;
        public TextView txvNomAtraccio;
        public TextView txvTipusAcces;
        public ConstraintLayout llyInnerRow;

        public FrameLayout frlFila;

        public InfoUtilitzacioViewHolder(View fila) {
            super(fila);

            //Agafem els components correctes de la fila

            imgAtraccio = fila.findViewById(R.id.imgAtraccio);
            txvTipusAcces = fila.findViewById(R.id.txvTipusAcces);
            txvNomAtraccio = fila.findViewById(R.id.txvNomAtraccio);
            llyInnerRow = fila.findViewById(R.id.llyInnerRow);

            frlFila = (FrameLayout)fila;

            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int previamentSeleccionada = InfoUtilitzacioAdapter.this.mSelectedPosition;

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

