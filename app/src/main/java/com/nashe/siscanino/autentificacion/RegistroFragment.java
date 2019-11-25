package com.nashe.siscanino.autentificacion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.UsuarioDao;
import com.nashe.siscanino.data.entity.Usuario;
import com.nashe.siscanino.utils.SharedPreferenceHandler;
import com.nashe.siscanino.utils.ViewHandler;

@SuppressLint("ClickableViewAccessibility")
@SuppressWarnings({"SpellCheckingInspection","FieldCanBeLocal"})
public class RegistroFragment extends BaseFragment implements View.OnFocusChangeListener {

    // Base de datos
    private UsuarioDao usuarioDAO;

    // Views
    private MaterialButton btnDisparador;
    private TextInputLayout inLayoutUsuario;
    private TextInputEditText txtUsuario;
    private TextInputLayout inLayoutContrasenia;
    private TextInputEditText txtContrasenia;
    private MaterialTextView lblIniciarSesion;
    private Toolbar toolbar;

    public RegistroFragment() { /* Requiere un constructor vacio */ }

    public static RegistroFragment newInstance() {
        return new RegistroFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        // Configuracion de la BD
        usuarioDAO = database.usuarioDAO();

        // Configuracion del menu
        toolbar = ViewHandler.configToolbarBotonAtras(activity, view);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

        // Configuracion de los otros views
        btnDisparador = view.findViewById(R.id.btn_disparador);
        inLayoutUsuario = view.findViewById(R.id.in_layout_usuario);
        txtUsuario = view.findViewById(R.id.txt_userio);
        inLayoutContrasenia = view.findViewById(R.id.in_layout_contrasenia);
        txtContrasenia = view.findViewById(R.id.txt_contrasenia);
        lblIniciarSesion = view.findViewById(R.id.lbl_inicio_sesion);

        btnDisparador.setText(getResources().getText(R.string.registrarse));
        txtUsuario.setOnFocusChangeListener(this);
        txtContrasenia.setOnFocusChangeListener(this);

        lblIniciarSesion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mListener.cambiarFragment(Constantes.INICIO_SESION_FRAGMENT);
                }
                return true;
            }
        });

        btnDisparador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtUsuario.getText().toString().trim().equals("") && !txtContrasenia.getText().toString().trim().equals("")) {
                    int checarUsuario = usuarioDAO.existe(txtUsuario.getText().toString());
                    if (checarUsuario == 0) {
                        Toast.makeText(activity.getBaseContext(), "Intente con otro usuario", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Usuario preRegistro = new Usuario(txtUsuario.getText().toString(), txtContrasenia.getText().toString(), 1);
                    long confirmacion = usuarioDAO.insert(preRegistro);

                    Toast.makeText(activity.getBaseContext(), "Se ha registrado satisfactoriamente", Toast.LENGTH_SHORT).show();
                    SharedPreferenceHandler.set(activity.getBaseContext(), Constantes.USUARIO_ID, (int) confirmacion);
                    mListener.cambiarActivity(Constantes.MENU_ACTIVITY);
                    return;
                }

                if (txtUsuario.getText().toString().trim().equals("") && !txtContrasenia.getText().toString().trim().equals("")) {
                    txtUsuario.requestFocus();
                    Toast.makeText(activity.getBaseContext(), getString(R.string.checar_usuario), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!txtUsuario.getText().toString().trim().equals("") && txtContrasenia.getText().toString().trim().equals("")) {
                    txtContrasenia.requestFocus();
                    Toast.makeText(activity.getBaseContext(), getString(R.string.checar_contrasenia), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        return view;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus && v != null) {
            switch (v.getId()) {
                case R.id.txt_userio:
                    ViewHandler.setMensajeError(inLayoutUsuario, ViewHandler.esUsuarioValido(txtUsuario.getText().toString()), getString(R.string.corregir_usuario));
                    break;
                case R.id.txt_contrasenia:
                    ViewHandler.setMensajeError(inLayoutContrasenia, ViewHandler.esUsuarioValido(txtContrasenia.getText().toString()), getString(R.string.corregir_contrasenia));
                    break;
            }
        }
    }
}
