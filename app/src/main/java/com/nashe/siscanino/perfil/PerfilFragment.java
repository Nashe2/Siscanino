package com.nashe.siscanino.perfil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.UsuarioCaninoJoinDao;
import com.nashe.siscanino.data.dao.UsuarioDao;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.utils.SharedPreferenceHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

@SuppressLint({"ClickableViewAccessibility", "BinaryOperationInTimber"})
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal"})
public class PerfilFragment extends BaseFragment {

    // Base de datos
    private UsuarioDao usuarioDAO;
    private UsuarioCaninoJoinDao usuarioCaninoJoinDAO;

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
        usuarioDAO = database.usuarioDAO();
        usuarioCaninoJoinDAO = database.usuarioCaninoJoinDAO();
        final int usuario_id = (int) SharedPreferenceHandler.get(activity.getBaseContext(), Constantes.USUARIO_ID, SharedPreferenceHandler.Type.INT);
        caninos = usuarioCaninoJoinDAO.getRightJoinLeft(usuario_id);

        // Configuracion de las views
        imgConfig = view.findViewById(R.id.imgPerfil_configuracion);
        imgUsuario = view.findViewById(R.id.imgPerfil_usuario);
        lblUsuario = view.findViewById(R.id.lblPerfil_usuario);
        lblTipoUsuario = view.findViewById(R.id.lblPerfil_tipousuario);
        imgEditar = view.findViewById(R.id.imgPerfil_editar);

        if (false/*caninos.size() == 0*/) {
            btnAgregarCaninoNuevo = view.findViewById(R.id.btnPerfil_agregarCaninoNuevo);
            btnAgregarCaninoNuevo.setVisibility(View.VISIBLE);
            btnAgregarCaninoNuevo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseFragment.cargar(activity.getSupportFragmentManager(),
                            CaninoFormFragment.newInstance(usuario_id),
                            Constantes.CANINO_FORMULARIO);
                }
            });
        } else {
            Date fecha_test = new Date(System.currentTimeMillis());
            ArrayList test = new ArrayList<Canino>(3);
            test.add(new Canino(10, "Test", fecha_test, "test", 1, "test", 10.0, "Grande", 1));
            test.add(new Canino(20, "Test2", fecha_test, "test2", 1, "test2", 20.0, "Grande2", 1));
            test.add(new Canino(30, "Test3", fecha_test, "test3", 1, "test3", 30.0, "Grande3", 1));

            recyclerViewCaninos = view.findViewById(R.id.recyclerPerfil_canino);
            recyclerViewCaninos.setVisibility(View.VISIBLE);
            btnAgregarCanino = view.findViewById(R.id.btnPerfil_agregarCanino);
            btnAgregarCanino.setVisibility(View.VISIBLE);

            recyclerViewCaninos.setLayoutManager(new GridLayoutManager(activity.getBaseContext(), 1));
            recyclerViewCaninos.setHasFixedSize(true);
            adaptador = new AdaptadorCanino(activity.getBaseContext(), R.layout.item_canino, test);
            adaptador.setOnItemClickListener(new AdaptadorCanino.OnItemClickListenerAdapter() {
                @Override
                public void onItemClickDelete(int position) {
                    Timber.i("Eliminar: " + position);
                    adaptador.delete(position);
                }

                @Override
                public void onItemClickUpdate(int position) {
                    Timber.i("Actualizar: " + position);
                }
            });

            recyclerViewCaninos.setAdapter(adaptador);

            btnAgregarCanino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseFragment.cargar(activity.getSupportFragmentManager(),
                            CaninoFormFragment.newInstance(usuario_id),
                            Constantes.CANINO_FORMULARIO);
                }
            });
        }

        return view;
    }
}
