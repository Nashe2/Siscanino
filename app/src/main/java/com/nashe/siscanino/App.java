package com.nashe.siscanino;

import android.app.Application;

import com.nashe.siscanino.data.DatabaseRoom;
import com.nashe.siscanino.data.entity.Raza;
import com.nashe.siscanino.data.entity.TipoUsuario;
import com.nashe.siscanino.data.entity.Usuario;
import com.nashe.siscanino.utils.SharedPreferenceHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

@SuppressWarnings("SpellCheckingInspection")
public class App extends Application {
    private DatabaseRoom baseDatos;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());

        baseDatos = DatabaseRoom.getInstance(this);
        inicioDatosDB();
    }

    private void inicioDatosDB() {
        Boolean bandera = (Boolean) SharedPreferenceHandler.get(this, Constantes.PRIMERA_EJECUCION, SharedPreferenceHandler.Type.BOOLEAN);
        if (bandera != null && bandera) return;

        inicioTablaTipoUsuario();
        inicioTablaUsuario();
        inicioTablaRaza();

        SharedPreferenceHandler.set(this, Constantes.PRIMERA_EJECUCION, true);
        Timber.d("Inicializado los datos en la base de datos");
    }


    private void inicioTablaTipoUsuario() {
        List<TipoUsuario> INIT_TIPOUSUARIO = new ArrayList<>(Arrays.asList(
                new TipoUsuario("Dueño", "El usuario es el encargado del canino"),
                new TipoUsuario("Familiar", "El usuario es algun familiar del dueño"),
                new TipoUsuario("Cuidador", "El usuario tendrá acceso restringido ")
        ));

        baseDatos.tipoUsuarioDao().inserts(INIT_TIPOUSUARIO);
    }

    private void inicioTablaUsuario() {
        List<Usuario> INIT_USUARIO = new ArrayList<>(Arrays.asList(
                new Usuario("root", "root", 1),
                new Usuario("luis", "luis", 1)
        ));

        baseDatos.usuarioDao().inserts(INIT_USUARIO);
    }

    private void inicioTablaRaza() {
        List<Raza> INIT_RAZA = new ArrayList<>(Arrays.asList(
                new Raza("Snouscher", "Es un perro bonito"),
                new Raza("Doberman", "Es un perro feroz"),
                new Raza("Chihuahua", "Es un perro muy chiquito")
        ));

        baseDatos.razaDao().inserts(INIT_RAZA);
    }
}
