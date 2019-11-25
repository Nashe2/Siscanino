package com.nashe.siscanino.utils;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

    public static MaterialToolbar configToolbarBotonAtras(AppCompatActivity activity, View inflador) {
        AppBarLayout appBarLayout = inflador.findViewById(R.id.appBar);
        appBarLayout.setOutlineProvider(null); // Disable shadows

        MaterialToolbar toolbar = inflador.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        return toolbar;
    }

    public static void mostrarBottomNavigation(AppCompatActivity activity){
        BottomNavigationView navegacion;
        navegacion = activity.findViewById(R.id.navegacion);
        navegacion.setVisibility(View.VISIBLE);
    }

    public static void ocultarBottomNavigation(AppCompatActivity activity){
        BottomNavigationView navegacion;
        navegacion = activity.findViewById(R.id.navegacion);
        navegacion.setVisibility(View.INVISIBLE);
    }

}
