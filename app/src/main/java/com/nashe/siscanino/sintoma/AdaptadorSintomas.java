package com.nashe.siscanino.sintoma;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nashe.siscanino.data.dao.SintomaDao;
import com.nashe.siscanino.data.entity.CaninoSintoma;
import com.nashe.siscanino.data.entity.Sintoma;
import com.nashe.siscanino.utils.DateOperation;
import com.nashe.siscanino.utils.OnItemClickListenerAdapter;
import com.nashe.siscanino.utils.OnItemLongClickListenerAdapter;

import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.DatabaseRoom;

import java.util.ArrayList;

@SuppressLint("SetTextI18n")
public class AdaptadorSintomas extends RecyclerView.Adapter<AdaptadorSintomas.ViewHolder> {

    private Context context;
    private ArrayList<CaninoSintoma> lista;
    private OnItemClickListenerAdapter clickListener;
    private OnItemLongClickListenerAdapter longClickListener;
    private int layout;
    private SintomaDao dao;

    protected void setOnItemClickListener(OnItemClickListenerAdapter listener) {
        this.clickListener = listener;
    }

    protected void setOnItemLongClickListener(OnItemLongClickListenerAdapter listener) {
        this.longClickListener = listener;
    }

    public AdaptadorSintomas(Context context, int layout, ArrayList<CaninoSintoma> lista) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
        dao = DatabaseRoom.getInstance(context).sintomaDao();
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
        CaninoSintoma item = lista.get(position);
        Sintoma referencia = dao.getById(item.getSintomaId());

        holder.lblId.setText(item.getId() + "");
        holder.lblNombre.setText(referencia.getNombre());
        String fechaTiempo = DateOperation.formatDateTime(item.getFechaHora().getTime());
        holder.lblOtroDato.setText(fechaTiempo);
        holder.imgIcono.setImageResource(R.drawable.ic_mood_bad_black_24dp);
        holder.imgEditar.setImageResource(R.drawable.ic_edit_24dp);
        holder.imgEliminar.setImageResource(R.drawable.ic_clear_24dp);
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        public CardView card;
        public MaterialTextView lblId;
        public MaterialTextView lblNombre;
        public MaterialTextView lblOtroDato;
        public ImageView imgIcono;
        public ImageView imgEditar;
        public ImageView imgEliminar;

        public ViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.card);
            lblId = v.findViewById(R.id.lbl_id);
            imgIcono = v.findViewById(R.id.img_icono);
            lblNombre = v.findViewById(R.id.lbl_sintoma);
            lblOtroDato = v.findViewById(R.id.lbl_fechaHora);
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

    public void delete(int posicion) {
        lista.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, lista.size());
    }

    public void setLista(ArrayList<CaninoSintoma> lista) {
        this.lista = lista;
    }
}