package com.nashe.siscanino.actividad;

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
import com.nashe.siscanino.data.dao.CaninoActividadDao;
import com.nashe.siscanino.data.entity.CaninoActividad;
import com.nashe.siscanino.menu.MenuActivity;
import com.nashe.siscanino.utils.OnItemClickListenerAdapter;
import com.nashe.siscanino.utils.SharedPreferencesPersonalizados;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


@SuppressLint({"ClickableViewAccessibility", "BinaryOperationInTimber"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class ActividadFragment extends BaseFragment {

    // Base de datos
    private CaninoActividadDao dao;

    // Views
    private ImageView imgConfig;
    private RecyclerView recyclerView;
    private FloatingActionButton btnAgregar;

    // Auxiliares
    private AdaptadorActividad adaptador;
    private List<CaninoActividad> lista;
    private int canino_id;

    public ActividadFragment() { /* Requiere un constructor vacio */ }


    public static ActividadFragment newInstance() {
        ActividadFragment fragment = new ActividadFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actividad, container, false);

        canino_id = SharedPreferencesPersonalizados.obtenerCaninoSeleccionado(activity.getBaseContext());

        // Configuracion de la BD
        dao = database.caninoActividadDao();
        lista = dao.getLeft(canino_id);

        //Configuracion de las views
        imgConfig = view.findViewById(R.id.imgActividades_configuracion);
        btnAgregar = view.findViewById(R.id.btnActividades_agregarCaninoActividad);
        recyclerView = view.findViewById(R.id.recyclerActividades_caninoActividades);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseFragment.cargar(activity.getSupportFragmentManager(),
                        ActividadFormFragment.newInstance(-1),
                        Constantes.ACTIVIDAD_FORMULARIO_FRAGMENT);
            }
        });

        // Configuracion RecyclerView
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(activity.getBaseContext(), 1));
        recyclerView.setHasFixedSize(true);
        adaptador = new AdaptadorActividad(activity.getBaseContext(), R.layout.item_canino_actividad, new ArrayList<CaninoActividad>(lista));
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
                        ActividadFormFragment.newInstance(id),
                        Constantes.ACTIVIDAD_FORMULARIO_FRAGMENT);
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
        adaptador.setLista(new ArrayList<CaninoActividad>(lista));
        adaptador.notifyDataSetChanged();
    }
}
