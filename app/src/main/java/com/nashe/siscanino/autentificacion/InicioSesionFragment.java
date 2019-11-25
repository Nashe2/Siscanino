package com.nashe.siscanino.autentificacion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.dao.UsuarioDao;
import com.nashe.siscanino.utils.SharedPreferenceHandler;
import com.nashe.siscanino.utils.ViewHandler;

@SuppressLint("ClickableViewAccessibility")
@SuppressWarnings("SpellCheckingInspection")
public class InicioSesionFragment extends BaseFragment
        implements View.OnFocusChangeListener {

    // Base de datos
    private UsuarioDao usuarioDAO;

    //Views
    private MaterialButton btnDisparador;
    private TextInputLayout inLayoutUsuario;
    private TextInputEditText txtUsuario;
    private TextInputEditText txtContrasenia;
    private MaterialTextView lblRegistro;

    public InicioSesionFragment() { /* Requiere un constructor vacio */ }

    public static InicioSesionFragment newInstance() {
        return new InicioSesionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);

        //Configuracion de la BD
        usuarioDAO = database.usuarioDAO();

        // Configuracion de los views
        btnDisparador = view.findViewById(R.id.btn_disparador);
        inLayoutUsuario = view.findViewById(R.id.in_layout_usuario);
        txtUsuario = view.findViewById(R.id.txt_userio);
        txtContrasenia = view.findViewById(R.id.txt_contrasenia);
        lblRegistro = view.findViewById(R.id.lbl_registro);

        btnDisparador.setText(getResources().getText(R.string.iniciar_sesion));
        txtUsuario.setOnFocusChangeListener(this);

        lblRegistro.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mListener.cambiarFragment(Constantes.REGISTRO_FRAGMENT);
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
                        Toast.makeText(activity.getBaseContext(), "Credenciales no validas", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(activity.getBaseContext(), "Ha iniciado sesión", Toast.LENGTH_SHORT).show();
                    SharedPreferenceHandler.set(activity.getBaseContext(), Constantes.USUARIO_ID, checarUsuario);
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
            if (v.getId() == R.id.txt_userio) {
                ViewHandler.setMensajeError(inLayoutUsuario, ViewHandler.esUsuarioValido(txtUsuario.getText().toString()), getString(R.string.corregir_usuario));
            }
        }
    }
}
