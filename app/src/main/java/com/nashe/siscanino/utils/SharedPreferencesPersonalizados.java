package com.nashe.siscanino.utils;

import android.content.Context;

import com.nashe.siscanino.Constantes;

public class SharedPreferencesPersonalizados {
    public static int obtenerUsuarioActivo(Context context) throws NullPointerException {
        Integer valor = (Integer) SharedPreferenceHandler.get(context, Constantes.USUARIO_ID, SharedPreferenceHandler.Type.INT);
        return (valor != null) ? valor : -1;
    }

    public static int obtenerCaninoSeleccionado(Context context) throws NullPointerException {
        Integer valor = (Integer) SharedPreferenceHandler.get(context, Constantes.CANINO_ID, SharedPreferenceHandler.Type.INT);
        return (valor != null) ? valor : -1;
    }
}
