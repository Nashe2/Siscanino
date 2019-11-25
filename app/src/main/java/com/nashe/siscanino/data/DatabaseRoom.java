package com.nashe.siscanino.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nashe.siscanino.data.converter.DateConverter;


import com.nashe.siscanino.data.dao.AlimentacionDAO;
import com.nashe.siscanino.data.dao.CaninoDao;
import com.nashe.siscanino.data.dao.CartillaDao;
import com.nashe.siscanino.data.dao.DesparacitacionDao;
import com.nashe.siscanino.data.dao.PetDao;
import com.nashe.siscanino.data.dao.RazaDao;
import com.nashe.siscanino.data.dao.TipoUsuarioDao;
import com.nashe.siscanino.data.dao.UserDao;
import com.nashe.siscanino.data.dao.UserPetDao;
import com.nashe.siscanino.data.dao.UsuarioCaninoJoinDao;
import com.nashe.siscanino.data.dao.UsuarioDao;
import com.nashe.siscanino.data.dao.VacunaDao;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.Cartilla;
import com.nashe.siscanino.data.entity.Desparacitacion;
import com.nashe.siscanino.data.entity.Pet;
import com.nashe.siscanino.data.entity.PetAlimentacion;
import com.nashe.siscanino.data.entity.Raza;
import com.nashe.siscanino.data.entity.TipoUsuario;
import com.nashe.siscanino.data.entity.User;
import com.nashe.siscanino.data.entity.UserPet;
import com.nashe.siscanino.data.entity.Usuario;
import com.nashe.siscanino.data.entity.UsuarioCaninoJoin;
import com.nashe.siscanino.data.entity.Vacuna;

@SuppressWarnings("SpellCheckingInspection")
@Database(entities = {

        User.class,
        Pet.class,
        UserPet.class,
        Cartilla.class,
        Vacuna.class,
        Desparacitacion.class,
        Alimentacion.class,
        PetAlimentacion.class,
        // Nuevas tablas
        TipoUsuario.class,
        Usuario.class,
        UsuarioCaninoJoin.class,
        Canino.class,
        Raza.class
},
        version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class DatabaseRoom extends RoomDatabase {

    private static final String NAME = "Siscanino.db";
    private static volatile DatabaseRoom INSTANCE;

    public abstract UserDao userDAO();

    public abstract PetDao petDAO();

    public abstract UserPetDao userPetDAO();

    public abstract CartillaDao cartillaDAO();

    public abstract VacunaDao vacunaDAO();

    public abstract DesparacitacionDao desparacitacionDAO();

    public abstract AlimentacionDAO alimentacionDAO();

    public abstract TipoUsuarioDao tipoUsuarioDAO();

    public abstract UsuarioDao usuarioDAO();

    public abstract UsuarioCaninoJoinDao usuarioCaninoJoinDAO();

    public abstract CaninoDao caninoDAO();

    public abstract RazaDao razaDAO();

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