package com.nashe.siscanino.perfil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.entity.Canino;

import java.util.ArrayList;

@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class AdaptadorCanino extends RecyclerView.Adapter<AdaptadorCanino.ViewHolder> {

    private Context context;
    private ArrayList<Canino> lista;
    private OnItemClickListenerAdapter listener;
    private int layout;

    public interface OnItemClickListenerAdapter {
        void onItemClickDelete(int position);

        void onItemClickUpdate(int position);
    }

    protected void setOnItemClickListener(OnItemClickListenerAdapter listener) {
        this.listener = listener;
    }

    public AdaptadorCanino(Context context, int layout, ArrayList<Canino> lista) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
    }

    @Override
    public int getItemCount() {
        return lista.size() > 0 ? lista.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Canino item = lista.get(position);
        holder.lblId.setText(item.getId() + "");
        holder.lblNombre.setText(item.getNombre());
        holder.lblOtroDato.setText(String.valueOf(item.getPeso()));
        holder.imgCanino.setImageResource(R.drawable.ic_pets_black_24dp);
        holder.imgEditar.setImageResource(R.drawable.ic_edit_24dp);
        holder.imgEliminar.setImageResource(R.drawable.ic_clear_24dp);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public MaterialTextView lblId;
        public MaterialTextView lblNombre;
        public MaterialTextView lblOtroDato;
        public ImageView imgCanino;
        public ImageView imgEditar;
        public ImageView imgEliminar;

        public ViewHolder(@NonNull View v) {
            super(v);

            lblId = v.findViewById(R.id.lbl_id);
            imgCanino = v.findViewById(R.id.img_icono);
            lblNombre = v.findViewById(R.id.lbl_titulo);
            lblOtroDato = v.findViewById(R.id.lbl_subTitulo);
            imgEditar = v.findViewById(R.id.img_editar);
            imgEliminar = v.findViewById(R.id.img_eliminar);
            imgEliminar.setOnClickListener(this);
            imgEditar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener == null) return;
            long position = getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return;

            if (imgEditar.getId() == view.getId()) {
                listener.onItemClickUpdate((int) position);
            }

            if (imgEliminar.getId() == view.getId()) {
                listener.onItemClickDelete((int) position);
            }
        }
    }

    public void delete(int posicion) {
        lista.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, lista.size());
    }
}