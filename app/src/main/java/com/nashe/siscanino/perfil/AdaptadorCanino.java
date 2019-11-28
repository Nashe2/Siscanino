package com.nashe.siscanino.perfil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.DatabaseRoom;
import com.nashe.siscanino.data.dao.RazaDao;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.utils.SharedPreferenceHandler;

import java.util.ArrayList;

@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class AdaptadorCanino extends RecyclerView.Adapter<AdaptadorCanino.ViewHolder> {

    private Context context;
    private ArrayList<Canino> lista;
    private OnItemClickListenerAdapter clickListener;
    private OnItemLongClickListenerAdapter longClickListener;
    private int layout;
    private RazaDao razaDao;

    public interface OnItemClickListenerAdapter {
        void onItemClickDelete(int position, int id);

        void onItemClickUpdate(int position, int id);
    }

    public interface OnItemLongClickListenerAdapter {
        void onItemLongClick(int position, int id);
    }

    protected void setOnItemClickListener(OnItemClickListenerAdapter listener) {
        this.clickListener = listener;
    }

    protected void setOnItemLongClickListener(OnItemLongClickListenerAdapter listener) {
        this.longClickListener = listener;
    }

    public AdaptadorCanino(Context context, int layout, ArrayList<Canino> lista) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
        razaDao = DatabaseRoom.getInstance(context).razaDao();
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
        Integer canino = (Integer) SharedPreferenceHandler.get(context, Constantes.CANINO_ID, SharedPreferenceHandler.Type.INT);
        if (canino != null && canino == item.getId())
            holder.card.setBackgroundResource(R.color.purple_gradient);
        holder.lblId.setText(item.getId() + "");
        holder.lblNombre.setText(item.getNombre());
        StringBuilder raza = new StringBuilder(); // -> Por si hay mas de una raza, ponerlo en un forEach
        raza.append(razaDao.getById(item.getRazaId()).getNombre());
        holder.lblOtroDato.setText(raza);
        holder.imgCanino.setImageResource(R.drawable.ic_pets_black_24dp);
        holder.imgEditar.setImageResource(R.drawable.ic_edit_24dp);
        holder.imgEliminar.setImageResource(R.drawable.ic_clear_24dp);
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        public CardView card;
        public MaterialTextView lblId;
        public MaterialTextView lblNombre;
        public MaterialTextView lblOtroDato;
        public ImageView imgCanino;
        public ImageView imgEditar;
        public ImageView imgEliminar;

        public ViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.card);
            lblId = v.findViewById(R.id.lbl_id);
            imgCanino = v.findViewById(R.id.img_icono);
            lblNombre = v.findViewById(R.id.lbl_titulo);
            lblOtroDato = v.findViewById(R.id.lbl_subTitulo);
            imgEditar = v.findViewById(R.id.img_editar);
            imgEliminar = v.findViewById(R.id.img_eliminar);
            imgEliminar.setOnClickListener(this);
            imgEditar.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener == null) return;
            long position = getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return;

            if (imgEditar.getId() == view.getId()) {
                clickListener.onItemClickUpdate((int) position, Integer.parseInt(lblId.getText().toString()));
            }

            if (imgEliminar.getId() == view.getId()) {
                clickListener.onItemClickDelete((int) position, Integer.parseInt(lblId.getText().toString()));
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (longClickListener == null) return false;
            long position = getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return false;
            longClickListener.onItemLongClick((int) position, Integer.parseInt(lblId.getText().toString()));
            return true;
        }
    }

    public void delete(int posicion, int id) {
        Integer canino = (Integer) SharedPreferenceHandler.get(context, Constantes.CANINO_ID, SharedPreferenceHandler.Type.INT);
        if (canino != null && canino == id) SharedPreferenceHandler.delete(context, Constantes.CANINO_ID);
        lista.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, lista.size());
    }

    public void setLista(ArrayList<Canino> lista) {
        this.lista = lista;
    }
}
