package com.nashe.siscanino.perfil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.CaninoDao;
import com.nashe.siscanino.data.dao.UsuarioCaninoDao;
import com.nashe.siscanino.data.dao.UsuarioDao;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.utils.SharedPreferenceHandler;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

@SuppressLint({"ClickableViewAccessibility", "BinaryOperationInTimber"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class PerfilFragment extends BaseFragment {

    // Base de datos
    private UsuarioDao usuarioDAO;
    private UsuarioCaninoDao usuarioCaninoDAO;
    private CaninoDao caninoDao;

    // Views
    private ImageView imgConfig;
    private ImageView imgUsuario;
    private MaterialTextView lblUsuario;
    private MaterialTextView lblTipoUsuario;
    private ImageView imgEditar;
    private ExtendedFloatingActionButton btnAgregarCaninoNuevo;
    private RecyclerView recyclerViewCaninos;
    private FloatingActionButton btnAgregarCanino;

    // Auxiliares
    private AdaptadorCanino adaptador;
    private List<Canino> caninos;
    private int usuario_id;

    public PerfilFragment() { /* Requiere un constructor vacio */ }

    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Configuracion de la BD
        usuarioDAO = database.usuarioDao();
        usuarioCaninoDAO = database.usuarioCaninoDao();
        caninoDao = database.caninoDao();
        usuario_id = (int) SharedPreferenceHandler.get(activity.getBaseContext(), Constantes.USUARIO_ID, SharedPreferenceHandler.Type.INT);
        caninos = usuarioCaninoDAO.getRightJoinLeft(usuario_id);

        // Configuracion de las views
        imgConfig = view.findViewById(R.id.imgPerfil_configuracion);
        imgUsuario = view.findViewById(R.id.imgPerfil_usuario);
        lblUsuario = view.findViewById(R.id.lblPerfil_usuario);
        lblTipoUsuario = view.findViewById(R.id.lblPerfil_tipousuario);
        imgEditar = view.findViewById(R.id.imgPerfil_editar);
        btnAgregarCaninoNuevo = view.findViewById(R.id.btnPerfil_agregarCaninoNuevo);
        btnAgregarCanino = view.findViewById(R.id.btnPerfil_agregarCanino);
        recyclerViewCaninos = view.findViewById(R.id.recyclerPerfil_canino);

        btnAgregarCaninoNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.cargar(activity.getSupportFragmentManager(),
                        CaninoFormFragment.newInstance(-1,usuario_id),
                        Constantes.CANINO_FORMULARIO);
            }
        });

        recyclerViewCaninos.setNestedScrollingEnabled(true);
        recyclerViewCaninos.setLayoutManager(new GridLayoutManager(activity.getBaseContext(), 1));
        recyclerViewCaninos.setHasFixedSize(true);
        adaptador = new AdaptadorCanino(activity.getBaseContext(), R.layout.item_canino, new ArrayList<Canino>(caninos));
        adaptador.setOnItemClickListener(new AdaptadorCanino.OnItemClickListenerAdapter() {
            @Override
            public void onItemClickDelete(int position, int id) {
                Timber.i("Eliminar: position ->" + position + " id -> " + id);
                adaptador.delete(position);
                caninoDao.deleteById(id);
                if (caninos.size() == 0) {
                    btnAgregarCaninoNuevo.setVisibility(View.VISIBLE);
                    btnAgregarCanino.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onItemClickUpdate(int position, int id) {
                Timber.i("Actualizar: position ->" + position + " id -> " + id);
                BaseFragment.cargar(activity.getSupportFragmentManager(),
                        CaninoFormFragment.newInstance(id,usuario_id),
                        Constantes.CANINO_FORMULARIO);
            }
        });

        recyclerViewCaninos.setAdapter(adaptador);

        btnAgregarCanino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.cargar(activity.getSupportFragmentManager(),
                        CaninoFormFragment.newInstance(-1,usuario_id),
                        Constantes.CANINO_FORMULARIO);
            }
        });

        if (caninos.size() == 0) {
            btnAgregarCaninoNuevo.setVisibility(View.VISIBLE);
        } else {
            recyclerViewCaninos.setVisibility(View.VISIBLE);
            btnAgregarCanino.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public void actualizarLista() {
        btnAgregarCaninoNuevo.setVisibility(View.GONE);
        recyclerViewCaninos.setVisibility(View.VISIBLE);
        btnAgregarCanino.setVisibility(View.VISIBLE);
        caninos = usuarioCaninoDAO.getRightJoinLeft(usuario_id);
        adaptador.setLista(new ArrayList<Canino>(caninos));
        adaptador.notifyDataSetChanged();
    }
}
