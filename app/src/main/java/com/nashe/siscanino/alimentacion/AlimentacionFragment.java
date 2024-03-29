package com.nashe.siscanino.alimentacion;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.CaninoAlimentacionDao;
import com.nashe.siscanino.data.entity.CaninoAlimentacion;
import com.nashe.siscanino.menu.MenuActivity;
import com.nashe.siscanino.utils.OnItemClickListenerAdapter;
import com.nashe.siscanino.utils.SharedPreferencesPersonalizados;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

@SuppressLint({"ClickableViewAccessibility", "BinaryOperationInTimber"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class AlimentacionFragment extends BaseFragment {

    // Base de datos
    private CaninoAlimentacionDao dao;

    // Views
    private ImageView imgConfig;
    private RecyclerView recyclerView;
    private FloatingActionButton btnAgregar;

    // Auxiliares
    private AdaptadorAlimentos adaptador;
    private List<CaninoAlimentacion> lista;
    private int canino_id;

    public AlimentacionFragment() { /* Requiere un constructor vacio */ }

    public static AlimentacionFragment newInstance() {
        AlimentacionFragment fragment = new AlimentacionFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alimentacion, container, false);

        canino_id = SharedPreferencesPersonalizados.obtenerCaninoSeleccionado(activity.getBaseContext());

        // Configuracion de la BD
        dao = database.caninoAlimentacionDao();
        lista = dao.getLeft(canino_id);

        //Configuracion de las views
        imgConfig = view.findViewById(R.id.imgAlimentacion_configuracion);
        btnAgregar = view.findViewById(R.id.btnAlimentos_agregarCaninoAlimento);
        recyclerView = view.findViewById(R.id.recyclerAlimentos_caninoAlimentos);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseFragment.cargar(activity.getSupportFragmentManager(),
                        AlimentosFormFragment.newInstance(-1),
                        Constantes.ALIMENTACION_FORMULARIO_FRAGMENT);
            }
        });

        // Configuracion RecyclerView
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(activity.getBaseContext(), 1));
        recyclerView.setHasFixedSize(true);
        adaptador = new AdaptadorAlimentos(activity.getBaseContext(), R.layout.item_canino_alimento, new ArrayList<CaninoAlimentacion>(lista));
        adaptador.setOnItemClickListener(new OnItemClickListenerAdapter() {
            @Override
            public void onItemClickDelete(int position, int id) {
                Timber.d("Eliminar: position: " + position + " id: " + id);
                adaptador.delete(position);
                dao.deleteById(id);
            }

            @Override
            public void onItemClickUpdate(int position, int id) {
                Timber.d("Actualizar -> position: " + position + " id: " + id);
                BaseFragment.cargar(activity.getSupportFragmentManager(),
                        AlimentosFormFragment.newInstance(id),
                        Constantes.ALIMENTACION_FORMULARIO_FRAGMENT);
            }
        });

        recyclerView.setAdapter(adaptador);

        if (canino_id == -1) {
            Toast.makeText(activity.getBaseContext(), "Es necesario seleccionar un canino", Toast.LENGTH_SHORT).show();
            ((MenuActivity) activity).selectBottomNAvigationItem(R.id.nav_perfil);
            //eliminar(activity.getSupportFragmentManager(), Constantes.ALIMENTACION_FRAGMENT);
        }

        return view;
    }

    public void actualizarLista(int id) {
        lista = dao.getLeft(id);
        adaptador.setLista(new ArrayList<CaninoAlimentacion>(lista));
        adaptador.notifyDataSetChanged();
    }

}
