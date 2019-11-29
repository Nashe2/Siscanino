package com.nashe.siscanino.alimentacion;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.AlimentacionDao;
import com.nashe.siscanino.data.dao.CaninoAlimentacionDao;
import com.nashe.siscanino.data.dao.CaninoDao;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.CaninoAlimentacion;
import com.nashe.siscanino.menu.MenuActivity;
import com.nashe.siscanino.perfil.PerfilFragment;
import com.nashe.siscanino.utils.SharedPreferencesPersonalizados;

import java.util.ArrayList;
import java.util.List;

@SuppressLint({"ClickableViewAccessibility", "BinaryOperationInTimber"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class AlimentacionFragment extends BaseFragment {

    // Base de datos
    private CaninoDao caninoDao;
    private CaninoAlimentacionDao caninoAlimentacionDao;
    private AlimentacionDao alimentacionDao;

    // Views
    private ImageView imgConfig;
    private RecyclerView recyclerViewAlimentacion;
    private FloatingActionButton btnAgregarAlimento;

    // Auxiliares
    private AdaptadorAlimentos adaptador;
    private List<CaninoAlimentacion> caninoAlimentacion;
    private int canino_id;
    private Canino canino;

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

        if (canino_id == -1) {
            Toast.makeText(activity.getBaseContext(), "Es necesario seleccionar un canino", Toast.LENGTH_SHORT).show();
            ((MenuActivity) activity).selectBottomNAvigationItem(R.id.nav_perfil);
            eliminar(activity.getSupportFragmentManager(), Constantes.ALIMENTACION_FRAGMENT);
        }

        // Configuracion de la BD
        caninoDao = database.caninoDao();
        caninoAlimentacionDao = database.caninoAlimentacionDao();
        alimentacionDao = database.alimentacionDao();
        caninoAlimentacion = caninoAlimentacionDao.getLeft(canino_id);

        //Configuracion de las views
        imgConfig = view.findViewById(R.id.imgAlimentacion_configuracion);
        btnAgregarAlimento = view.findViewById(R.id.btnAlimentos_agregarCaninoAlimento);
        recyclerViewAlimentacion = view.findViewById(R.id.recyclerAlimentos_caninoAlimentos);

        btnAgregarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cargar fragment
            }
        });

        // Configuracion RecyclerView
        recyclerViewAlimentacion.setNestedScrollingEnabled(true);
        recyclerViewAlimentacion.setLayoutManager(new GridLayoutManager(activity.getBaseContext(), 1));
        recyclerViewAlimentacion.setHasFixedSize(true);
        adaptador = new AdaptadorAlimentos(activity.getBaseContext(),R.layout.item_canino_alimento,new ArrayList<CaninoAlimentacion>(caninoAlimentacion));
        recyclerViewAlimentacion.setAdapter(adaptador);

        return view;
    }

    public void actualizarLista(int id) {
        caninoAlimentacion = caninoAlimentacionDao.getLeft(id);
        adaptador.setLista(new ArrayList<CaninoAlimentacion>(caninoAlimentacion));
        adaptador.notifyDataSetChanged();

    }

}
