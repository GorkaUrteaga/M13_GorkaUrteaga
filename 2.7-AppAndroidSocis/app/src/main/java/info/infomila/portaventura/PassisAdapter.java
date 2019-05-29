package info.infomila.portaventura;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import info.infomila.portaventura.classes.PassiExpress;

public class PassisAdapter extends RecyclerView.Adapter<PassisAdapter.PassiViewHolder> {

    //Variables
    private List<PassiExpress> mPassis;
    private PassisUsuari mActivity;
    private int mSelectedPosition = -1;

    //Constructor
    public PassisAdapter(List<PassiExpress> pPassis, PassisUsuari activity){
        mPassis = pPassis;
        mActivity = activity;
    }

    @NonNull
    @Override
    public PassiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        FrameLayout fila = (FrameLayout)
        LayoutInflater.from(parent.getContext()).inflate(R.layout.passi_row, parent , false);

        return new PassiViewHolder(fila);
    }


    // El onBindViewHolder omple una fila amb valors d'un objecte que esta a la posició "position"
    @Override
    public void onBindViewHolder(@NonNull PassiViewHolder holder, int position) {
        final PassiExpress f = mPassis.get(position);

        holder.txvId.setText(""+f.getId() );
        holder.txvTipusPassi.setText(f.getTipusPassi().getNom());
        holder.txvData.setText(f.getData().toString());


        if(position == mSelectedPosition){
            //Avisem al PassisUsuari i fem un intent per mostrar el qr
            //MainActivity.mostrarInformacioHTML(mPassis.get(mSelectedPosition));
        }else{

        }

    }

    @Override
    public int getItemCount() {
        return mPassis.size();
    }

    protected class PassiViewHolder extends RecyclerView.ViewHolder{

        public TextView txvId;
        public TextView txvTipusPassi;
        public TextView txvData;

        public FrameLayout frlFila;

        public PassiViewHolder(View fila) {
            super(fila);

            //Agafem els components correctes de la fila

            txvId = fila.findViewById(R.id.txvId);
            txvTipusPassi = fila.findViewById(R.id.txvTipusPassi);
            txvData = fila.findViewById(R.id.txvData);

            frlFila = (FrameLayout)fila;

            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int previamentSeleccionada = PassisAdapter.this.mSelectedPosition;

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

