package com.nashe.siscanino.autentificacion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.nashe.siscanino.BaseActivity;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.data.DatabaseRoom;
import com.nashe.siscanino.data.dao.TipoUsuarioDao;
import com.nashe.siscanino.data.entity.TipoUsuario;
import com.nashe.siscanino.menu.MenuActivity;
import com.nashe.siscanino.utils.SharedPreferenceHandler;

import java.util.List;

import timber.log.Timber;

@SuppressLint("BinaryOperationInTimber")
@SuppressWarnings("SpellCheckingInspection")
public class AutentificacionActivity extends BaseActivity
        implements BaseFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentificacion);

        checarSiExisteUsuario();
        cambiarFragment(Constantes.INICIO_SESION_FRAGMENT);
    }

    @Override
    public void cambiarFragment(String fragment) {
        switch (fragment) {
            case Constantes.INICIO_SESION_FRAGMENT:
                BaseFragment.cargar(this.getSupportFragmentManager(), InicioSesionFragment.newInstance(), Constantes.INICIO_SESION_FRAGMENT);
                break;
            case Constantes.REGISTRO_FRAGMENT:
                BaseFragment.cargar(this.getSupportFragmentManager(), RegistroFragment.newInstance(), Constantes.REGISTRO_FRAGMENT);
                break;
        }
    }

    @Override
    public void cambiarActivity(String activity) {
        Intent intent;

        if (Constantes.MENU_ACTIVITY.equals(activity)) {
            intent = new Intent(this, MenuActivity.class);
        } else {
            Timber.e("No se encontro el Activity");
            return;
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) finish();
        super.onBackPressed();
    }

    private void checarSiExisteUsuario() {
        Integer valor = (Integer) SharedPreferenceHandler
                .get(this, Constantes.USUARIO_ID, SharedPreferenceHandler.Type.INT);
        if (valor != null) cambiarActivity(Constantes.MENU_ACTIVITY);
    }

    private void chequeoTiposUsuario() {
        TipoUsuarioDao dao = DatabaseRoom.getInstance(this).tipoUsuarioDAO();

        StringBuilder test = new StringBuilder();
        List<TipoUsuario> tipoUsuarios = dao.get();
        for (TipoUsuario row : tipoUsuarios) {
            test.append(row.getNombre()).append("\n");
            Timber.i("row : " + row.getNombre());
        }
    }
}
