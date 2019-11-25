package com.nashe.siscanino.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nashe.siscanino.BaseActivity;
import com.nashe.siscanino.BaseFragment;
import com.nashe.siscanino.Constantes;
import com.nashe.siscanino.R;
import com.nashe.siscanino.autentificacion.AutentificacionActivity;
import com.nashe.siscanino.data.DatabaseRoom;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.UsuarioCaninoJoin;
import com.nashe.siscanino.perfil.PerfilFragment;
import com.nashe.siscanino.utils.SharedPreferenceHandler;
import com.nashe.siscanino.utils.ViewHandler;

import java.util.List;
import java.util.Objects;

import timber.log.Timber;

@SuppressLint("BinaryOperationInTimber")
public class MenuActivity extends BaseActivity
        implements BaseFragment.OnFragmentInteractionListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private DatabaseRoom database;

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        database = DatabaseRoom.getInstance(this);

        configBottomNavigation();

        chequeoUsuario();
    }

    private void configBottomNavigation() {
        bottomNav = findViewById(R.id.navegacion);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.nav_principal);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_actividad:
                if (bottomNav.getSelectedItemId() != R.id.nav_actividad)
                    cambiarFragment(Constantes.ACTIVIDAD_FRAGMENT);
                break;
            case R.id.nav_salud:
                if (bottomNav.getSelectedItemId() != R.id.nav_salud)
                    cambiarFragment(Constantes.SALUD_FRAGMENT);
                break;
            case R.id.nav_principal:
                if (bottomNav.getSelectedItemId() != R.id.nav_principal)
                    cambiarFragment(Constantes.PRINCIPAL_FRAGMENT);
                break;
            case R.id.nav_alimentacion:
                if (bottomNav.getSelectedItemId() != R.id.nav_alimentacion)
                    cambiarFragment(Constantes.ALIMENTACION_FRAGMENT);
                break;
            case R.id.nav_perfil:
                if (bottomNav.getSelectedItemId() != R.id.nav_perfil)
                    cambiarFragment(Constantes.PERFIL_FRAGMENT);
                break;
        }
        return true;
    }

    @Override
    public void cambiarFragment(String fragment) {
        switch (fragment) {
            case Constantes.ACTIVIDAD_FRAGMENT:
                Timber.i("Actividad");
                //BaseFragment.cargar(this.getSupportFragmentManager(), CaninoFragment.newInstance(), Constantes.ACTIVIDAD_FRAGMENT);
                break;
            case Constantes.SALUD_FRAGMENT:
                Timber.i("Salud");
                //BaseFragment.cargar(this.getSupportFragmentManager(), PerfilFragment.newInstance(), Constantes.SALUD_FRAGMENT);
                break;
            case Constantes.PRINCIPAL_FRAGMENT:
                Timber.i("Principal");
                //BaseFragment.cargar(this.getSupportFragmentManager(), RegistroFragment.newInstance(), Constantes.PRINCIPAL_FRAGMENT);
                break;
            case Constantes.ALIMENTACION_FRAGMENT:
                Timber.i("Alimentacion");
                //BaseFragment.cargar(this.getSupportFragmentManager(), RegistroFragment.newInstance(), Constantes.ALIMENTACION_FRAGMENT);
                break;
            case Constantes.PERFIL_FRAGMENT:
                Timber.i("Perfil");
                BaseFragment.cargar(this.getSupportFragmentManager(), PerfilFragment.newInstance(), Constantes.PERFIL_FRAGMENT);
                break;
        }
    }

    @Override
    public void cambiarActivity(String activity) {
        Intent intent;

        if (Constantes.AUTENTIFICACION_ACTIVITY.equals(activity)) {
            intent = new Intent(this, AutentificacionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            Timber.e("No se encontro el Activity");
            return;
        }

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //FIXME: Mejorar esta parte
        try {
            Boolean banderaCaninoFormulario = Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag(Constantes.CANINO_FORMULARIO)).isVisible();

            if(banderaCaninoFormulario!= null && banderaCaninoFormulario){
                ViewHandler.mostrarBottomNavigation(this);
                //FIXME: Borrar despues de terminado el modulo ya que son para test
                List<Canino> caninos = database.caninoDAO().get();
                List<UsuarioCaninoJoin> join = database.usuarioCaninoJoinDAO().get();

                for (Canino item: caninos){
                    Timber.i("Canino-> id: " + item.getNombre() + " nombre: "+item.getNombre());
                }

                for (UsuarioCaninoJoin item: join){
                    Timber.i("Usuario" +item.getIdUsuario() + " Canino" + item.getIdCacnino());
                }

                super.onBackPressed();
                return;
            }

        }catch (NullPointerException e){
            Timber.e(e);
        }

        if (bottomNav.getSelectedItemId() != R.id.nav_principal) {
            getSupportFragmentManager().popBackStack(
                    getSupportFragmentManager().getBackStackEntryAt(1).getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
            );
            bottomNav.getMenu().getItem(2).setChecked(true);
        } else {
            finish();
        }
    }

    private void chequeoUsuario() {
        //SharedPreferenceHandler.delete(this, Constantes.USUARIO_ID);
        int valor = (int) SharedPreferenceHandler.get(this, Constantes.USUARIO_ID, SharedPreferenceHandler.Type.INT);
        Timber.d("ID Usuario:" + valor);
    }
}