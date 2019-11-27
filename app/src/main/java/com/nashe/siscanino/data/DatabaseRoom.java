package com.nashe.siscanino.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nashe.siscanino.data.converter.DateConverter;


import com.nashe.siscanino.data.dao.ActividadDao;
import com.nashe.siscanino.data.dao.AlimentacionDao;
import com.nashe.siscanino.data.dao.BañoDao;
import com.nashe.siscanino.data.dao.CaninoActividadDao;
import com.nashe.siscanino.data.dao.CaninoDao;
import com.nashe.siscanino.data.dao.CartillaDao;
import com.nashe.siscanino.data.dao.DesparacitacionDao;
import com.nashe.siscanino.data.dao.MedicamentoDao;
import com.nashe.siscanino.data.dao.CaninoAlimentacionDao;
import com.nashe.siscanino.data.dao.CaninoSintomaDao;
import com.nashe.siscanino.data.dao.RazaDao;
import com.nashe.siscanino.data.dao.SintomaDao;
import com.nashe.siscanino.data.dao.TipoUsuarioDao;
import com.nashe.siscanino.data.dao.UsuarioCaninoDao;
import com.nashe.siscanino.data.dao.UsuarioDao;
import com.nashe.siscanino.data.dao.VacunaDao;
import com.nashe.siscanino.data.entity.Actividad;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.Baño;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.CaninoAlimentacion;
import com.nashe.siscanino.data.entity.Cartilla;
import com.nashe.siscanino.data.entity.Desparacitacion;
import com.nashe.siscanino.data.entity.Medicamento;
import com.nashe.siscanino.data.entity.CaninoActividad;
import com.nashe.siscanino.data.entity.CaninoSintoma;
import com.nashe.siscanino.data.entity.Raza;
import com.nashe.siscanino.data.entity.Sintoma;
import com.nashe.siscanino.data.entity.TipoUsuario;
import com.nashe.siscanino.data.entity.Usuario;
import com.nashe.siscanino.data.entity.UsuarioCanino;
import com.nashe.siscanino.data.entity.Vacuna;

@SuppressWarnings("SpellCheckingInspection")
@Database(entities = {
        Actividad.class,
        Alimentacion.class,
        Baño.class,
        Canino.class,
        CaninoActividad.class,
        CaninoAlimentacion.class,
        CaninoSintoma.class,
        Cartilla.class,
        Desparacitacion.class,
        Medicamento.class,
        Raza.class,
        Sintoma.class,
        TipoUsuario.class,
        Usuario.class,
        UsuarioCanino.class,
        Vacuna.class
},
        version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class DatabaseRoom extends RoomDatabase {

    private static final String NAME = "Siscanino.db";
    private static volatile DatabaseRoom INSTANCE;

    public abstract ActividadDao actividadDao();

    public abstract AlimentacionDao alimentacionDao();

    public abstract BañoDao bañoDao();

    public abstract CaninoDao caninoDao();

    public abstract CaninoActividadDao caninoActividadDao();

    public abstract CaninoAlimentacionDao caninoAlimentacionDao();

    public abstract CaninoSintomaDao caninoSintomaDao();

    public abstract CartillaDao cartillaDao();

    public abstract DesparacitacionDao desparacitacionDao();

    public abstract MedicamentoDao medicamentoDao();

    public abstract RazaDao razaDao();

    public abstract SintomaDao sintomaDao();

    public abstract TipoUsuarioDao tipoUsuarioDao();

    public abstract UsuarioDao usuarioDao();

    public abstract UsuarioCaninoDao usuarioCaninoDao();

    public abstract VacunaDao vacunaDao();

    public static DatabaseRoom getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                if (INSTANCE == null) INSTANCE = build(context);
            }
        }

        return INSTANCE;
    }

    private static DatabaseRoom build(Context context) {
        return Room.databaseBuilder(context, DatabaseRoom.class, NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                    }
                })
                .allowMainThreadQueries() // Comentar si el manejo de la BD estarán en hilos aparte del principal
                .build();
    }

    public void close() {
        if (INSTANCE == null) return;
        if (INSTANCE.isOpen()) INSTANCE.close();
        INSTANCE = null;
    }
}