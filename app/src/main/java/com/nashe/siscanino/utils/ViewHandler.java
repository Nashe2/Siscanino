package com.nashe.siscanino.utils;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nashe.siscanino.R;

@SuppressWarnings("SpellCheckingInspection")
public class ViewHandler {
    public static void setMensajeError(View view, boolean validacion, String mensaje) {
        if (view instanceof TextInputLayout)
            ((TextInputLayout) view).setError(validacion ? null : mensaje);
    }

    public static boolean esUsuarioValido(String usuario) {
        return (!usuario.trim().equals("")) && usuario.trim().length() > 1;
    }

    public static MaterialToolbar configToolbarBotonAtras(final AppCompatActivity activity, View inflador) {
        AppBarLayout appBarLayout = inflador.findViewById(R.id.appBar);
        appBarLayout.setOutlineProvider(null); // Disable shadows

        MaterialToolbar toolbar = inflador.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });
        return toolbar;
    }

    public static String[] obtenerFecha(View view, int empieza) {
        String regex = "/";
        String auxiliar = "";
        if (view instanceof MaterialButton)
            auxiliar = ((MaterialButton) view).getText().subSequence(empieza, ((MaterialButton) view).getText().length()).toString();
        return auxiliar.split(regex);
    }

    public static String[] obtenerHora(View view, int empieza) {
        String regex = ":";
        String auxiliar = "";
        if (view instanceof MaterialButton)
            auxiliar = ((MaterialButton) view).getText().subSequence(empieza, ((MaterialButton) view).getText().length()).toString();
        return auxiliar.split(regex);
    }


    public static void mostrarBottomNavigation(AppCompatActivity activity) {
        BottomNavigationView navegacion;
        navegacion = activity.findViewById(R.id.navegacion);
        navegacion.setVisibility(View.VISIBLE);
    }

    public static void ocultarBottomNavigation(AppCompatActivity activity) {
        BottomNavigationView navegacion;
        navegacion = activity.findViewById(R.id.navegacion);
        navegacion.setVisibility(View.INVISIBLE);
    }

}
