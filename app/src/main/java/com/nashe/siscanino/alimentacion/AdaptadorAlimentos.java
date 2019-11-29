package com.nashe.siscanino.alimentacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.DatabaseRoom;
import com.nashe.siscanino.data.dao.AlimentacionDao;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.CaninoAlimentacion;
import com.nashe.siscanino.utils.DateOperation;
import com.nashe.siscanino.utils.OnItemClickListenerAdapter;
import com.nashe.siscanino.utils.OnItemLongClickListenerAdapter;
import com.nashe.siscanino.utils.SharedPreferencesPersonalizados;

import java.util.ArrayList;

@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class AdaptadorAlimentos extends RecyclerView.Adapter<AdaptadorAlimentos.ViewHolder> {

    private Context context;
    private ArrayList<CaninoAlimentacion> lista;
    private OnItemClickListenerAdapter clickListener;
    private OnItemLongClickListenerAdapter longClickListener;
    private int layout;
    private AlimentacionDao alimentacionDao;

    protected void setOnItemClickListener(OnItemClickListenerAdapter listener) {
        this.clickListener = listener;
    }

    protected void setOnItemLongClickListener(OnItemLongClickListenerAdapter listener) {
        this.longClickListener = listener;
    }

    public AdaptadorAlimentos(Context context, int layout, ArrayList<CaninoAlimentacion> lista) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
        alimentacionDao = DatabaseRoom.getInstance(context).alimentacionDao();
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
        CaninoAlimentacion item = lista.get(position);
        Alimentacion alimento = alimentacionDao.getById(item.getAlimentacionId());

        holder.lblId.setText(item.getId() + "");
        holder.lblNombre.setText(alimento.getProducto());
        String fechaTiempo = DateOperation.formatDateTime(item.getFechaHora().getTime());
        holder.lblOtroDato.setText(fechaTiempo);
        holder.imgIcono.setImageResource(R.drawable.ic_food_black);
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
            lblNombre = v.findViewById(R.id.lbl_producto);
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

    public void delete(int posicion, int id) {
        /*int canino = SharedPreferencesPersonalizados.obtenerCaninoSeleccionado(context);
        if (canino != -1 && canino == id)
            SharedPreferenceHandler.delete(context, Constantes.CANINO_ID);
        lista.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, lista.size());*/
    }

    public void setLista(ArrayList<CaninoAlimentacion> lista) {
        this.lista = lista;
    }
}