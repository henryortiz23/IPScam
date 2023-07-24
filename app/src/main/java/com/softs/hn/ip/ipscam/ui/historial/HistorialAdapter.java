package com.softs.hn.ip.ipscam.ui.historial;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softs.hn.ip.ipscam.database.Historial;
import com.softs.hn.ip.ipscam.databinding.HistorialItemBinding;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ViewHolder> {

    private List<Historial> dataset;
    private OnItemClickListenerHistotial<Historial> manejadorEventoClick;

    public HistorialAdapter(List<Historial> dataset, OnItemClickListenerHistotial<Historial> manejadorEventoClick) {
        this.dataset = dataset;
        this.manejadorEventoClick = manejadorEventoClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        HistorialItemBinding binding = HistorialItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Historial historial = dataset.get(position);


        holder.binding.txtPlaca.setText(historial.getPlaca());

        holder.setOnClickListener(historial, manejadorEventoClick);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setItems(List<Historial> historial){
        this.dataset = historial;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        HistorialItemBinding binding;
        public ViewHolder(@NonNull HistorialItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void setOnClickListener(Historial datosHistorial, OnItemClickListenerHistotial<Historial> listener) {
            ////this.binding.bView.setOnClickListener(v -> listener.onItemClick(datosPedido));
            this.binding.bDelete.setOnClickListener(v -> listener.onItemClickDelete(datosHistorial));
        }
    }
}
