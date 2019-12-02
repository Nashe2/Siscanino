package com.nashe.siscanino;

import android.app.Application;

import com.nashe.siscanino.data.DatabaseRoom;
import com.nashe.siscanino.data.dao.CaninoActividadDao;
import com.nashe.siscanino.data.entity.Actividad;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.Baño;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.CaninoActividad;
import com.nashe.siscanino.data.entity.CaninoAlimentacion;
import com.nashe.siscanino.data.entity.CaninoSintoma;
import com.nashe.siscanino.data.entity.Cartilla;
import com.nashe.siscanino.data.entity.Medicamento;
import com.nashe.siscanino.data.entity.Raza;
import com.nashe.siscanino.data.entity.Sintoma;
import com.nashe.siscanino.data.entity.TipoUsuario;
import com.nashe.siscanino.data.entity.Usuario;
import com.nashe.siscanino.data.entity.UsuarioCanino;
import com.nashe.siscanino.utils.SharedPreferenceHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

        long timestamp = System.currentTimeMillis();

        baseDatos.tipoUsuarioDao().inserts(
                new TipoUsuario("Dueño", "El usuario es el encargado del canino"),
                new TipoUsuario("Familiar", "El usuario es algun familiar del dueño"),
                new TipoUsuario("Cuidador", "El usuario tendrá acceso restringido ")
        );

        baseDatos.usuarioDao().inserts(
                new Usuario("root", "root", 1),
                new Usuario("nashe", "nashe", 1),
                new Usuario("luis", "luis", 2)
        );

        baseDatos.razaDao().inserts(
                new Raza("Schnauzer", "Es un perro muy carismatico"),
                new Raza("Chihuahua", "Es un perro muy chiquito"),
                new Raza("Corgi", "Es un perro inteligente"),
                new Raza("Akita", "Es un perro muy fiel"),
                new Raza("Doberman", "Es un perro feroz")
        );

        baseDatos.caninoDao().inserts(
                new Canino("Adonis", new Date(timestamp), "Gris", 0, "Es muy bonito", 15.0, "Mediano", 1),
                new Canino("Luna", new Date(timestamp), "Cafe oscuro", 1, "Es muy brava", 7.0, "Pequeño", 2),
                new Canino("Ein", new Date(timestamp), "Cafe claro", 0, "Sale en un anime", 11.0, "Pequeño", 3),
                new Canino("Hachiko", new Date(timestamp), "Cafe claro", 0, "Sale en una pelicula", 16.0, "Mediano", 4)
        );

        baseDatos.usuarioCaninoDao().inserts(
                new UsuarioCanino(1, 1),
                new UsuarioCanino(1, 2),
                new UsuarioCanino(1, 3),
                new UsuarioCanino(1, 4),
                new UsuarioCanino(3, 3),
                new UsuarioCanino(3, 4)
        );

        baseDatos.sintomaDao().inserts(
                new Sintoma("Fatiga", "Cansado"),
                new Sintoma("Falta de apetito", "Triste")
        );

        baseDatos.caninoSintomaDao().inserts(
                new CaninoSintoma(1, 1, "Posible dolor estomacal", new Date(timestamp)),
                new CaninoSintoma(1, 2, "Lombrices en el estomago", new Date(timestamp)),
                new CaninoSintoma(1, 2, "Intoxicación", new Date(timestamp))
        );

        baseDatos.alimentacionDao().inserts(
                new Alimentacion("Croquetas DogChow", "Son altas en vitaminas"),
                new Alimentacion("Tortilla", "Les gusta mucho a los perros")
        );

        baseDatos.caninoAlimentacionDao().inserts(
                new CaninoAlimentacion(1, 1, "250 mg", new Date(timestamp)),
                new CaninoAlimentacion(1, 1, "250 mg", new Date(timestamp)),
                new CaninoAlimentacion(1, 2, "2 c/u", new Date(timestamp))
        );

        baseDatos.actividadDao().inserts(
                new Actividad("Correr", "25 min", "Es una actividad para oxigenar sus pulmone"),
                new Actividad("Atrapar la pelota", "30 min", "Sirve para elevar sus reflejos")
        );

        baseDatos.caninoActividadDao().inserts(
                new CaninoActividad(1, 1, ":)", "10:00", new Date(timestamp), new Date(timestamp)),
                new CaninoActividad(1, 1, ":(", "12:00", new Date(timestamp), new Date(timestamp)),
                new CaninoActividad(1, 2, ":)", "23:00", new Date(timestamp), new Date(timestamp))
        );

        baseDatos.bañoDao().inserts(
                new Baño(new Date(timestamp), new Date(timestamp), 1),
                new Baño(new Date(timestamp), new Date(timestamp), 1),
                new Baño(new Date(timestamp), new Date(timestamp), 2)
        );

        baseDatos.medicamentoDao().inserts(
                new Medicamento("Paracetamol", "Sirvio para eliviar el dolor", "250 mg", new Date(timestamp), new Date(timestamp), 1),
                new Medicamento("Alivianax", "Sirvio para eliviar el dolor muscular", "250 mg", new Date(timestamp), new Date(timestamp), 1)
        );

        baseDatos.cartillaDao().inserts(
                new Cartilla("QWE", "Guadalupe Victoria #534", "Salud mejor", "Parvovirus", 1),
                new Cartilla("ASD", "Iturbide #123", "Vida saludable", "Parvovirus", 2),
                new Cartilla("ZXC", "Vicente Guerrero #75", "Salud mejor", "Parvovirus", 3),
                new Cartilla("RTY", "Guadalupe victoria #534", "Vida Saludable", "Parvovirus", 4)
        );

        SharedPreferenceHandler.set(this, Constantes.PRIMERA_EJECUCION, true);
        Timber.d("Inicializado los datos en la base de datos");
    }
}
