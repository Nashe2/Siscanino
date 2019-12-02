package com.nashe.siscanino.sintoma;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.CaninoSintomaDao;
import com.nashe.siscanino.data.entity.CaninoSintoma;
import com.nashe.siscanino.menu.MenuActivity;
import com.nashe.siscanino.utils.OnItemClickListenerAdapter;
import com.nashe.siscanino.utils.SharedPreferencesPersonalizados;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

@SuppressLint({"ClickableViewAccessibility", "BinaryOperationInTimber"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class SintomaFragment extends BaseFragment {

    // Base de datos
    private CaninoSintomaDao dao;

    // Views
    private ImageView imgConfig;
    private RecyclerView recyclerView;
    private FloatingActionButton btnAgregar;

    // Auxiliares
    private AdaptadorSintomas adaptador;
    private List<CaninoSintoma> lista;
    private int canino_id;

    public SintomaFragment() { /* Requiere un constructor vacio */ }

    public static SintomaFragment newInstance() {
        SintomaFragment fragment = new SintomaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sintoma, container, false);

        canino_id = SharedPreferencesPersonalizados.obtenerCaninoSeleccionado(activity.getBaseContext());

        // Configuracion de la BD
        dao = database.caninoSintomaDao();
        lista = dao.getLeft(canino_id);

        //Configuracion de las views
        imgConfig = view.findViewById(R.id.imgSintomas_configuracion);
        btnAgregar = view.findViewById(R.id.btnSintomas_agregarCaninoSintomas);
        recyclerView = view.findViewById(R.id.recyclerSintomas_caninoSintomas);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseFragment.cargar(activity.getSupportFragmentManager(),
                        SintomaFormFragment.newInstance(-1),
                        Constantes.SINTOMA_FORMULARIO_FRAGMENT);
            }
        });

        // Configuracion RecyclerView
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(activity.getBaseContext(), 1));
        recyclerView.setHasFixedSize(true);
        adaptador = new AdaptadorSintomas(activity.getBaseContext(), R.layout.item_canino_sintoma, new ArrayList<CaninoSintoma>(lista));
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
                        SintomaFormFragment.newInstance(id),
                        Constantes.SINTOMA_FORMULARIO_FRAGMENT);
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
        adaptador.setLista(new ArrayList<CaninoSintoma>(lista));
        adaptador.notifyDataSetChanged();
    }
}
