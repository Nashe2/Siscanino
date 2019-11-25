package com.nashe.siscanino.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nashe.siscanino.data.converter.DateConverter;


import com.nashe.siscanino.data.dao.ActividadDAO;
import com.nashe.siscanino.data.dao.AlimentacionDAO;
import com.nashe.siscanino.data.dao.BañoDAO;
import com.nashe.siscanino.data.dao.CartillaDAO;
import com.nashe.siscanino.data.dao.DesparacitacionDAO;
import com.nashe.siscanino.data.dao.MedicamentoDAO;
import com.nashe.siscanino.data.dao.PetActividadDAO;
import com.nashe.siscanino.data.dao.PetAlimentacionDAO;
import com.nashe.siscanino.data.dao.PetDAO;
import com.nashe.siscanino.data.dao.PetSintomaDAO;
import com.nashe.siscanino.data.dao.SintomaDAO;
import com.nashe.siscanino.data.dao.UserDAO;
import com.nashe.siscanino.data.dao.VacunaDAO;
import com.nashe.siscanino.data.entity.Actividad;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.Baño;
import com.nashe.siscanino.data.entity.Cartilla;
import com.nashe.siscanino.data.dao.UserPetDAO;
import com.nashe.siscanino.data.entity.Desparacitacion;
import com.nashe.siscanino.data.entity.Medicamento;
import com.nashe.siscanino.data.entity.Pet;
import com.nashe.siscanino.data.entity.PetActividad;
import com.nashe.siscanino.data.entity.PetAlimentacion;
import com.nashe.siscanino.data.entity.PetSintoma;
import com.nashe.siscanino.data.entity.Sintoma;
import com.nashe.siscanino.data.entity.User;
import com.nashe.siscanino.data.entity.UserPet;
import com.nashe.siscanino.data.entity.Vacuna;

@Database(entities = {

        User.class,
        Pet.class,
        UserPet.class,
        Cartilla.class,
        Vacuna.class,
        Desparacitacion.class,
        Alimentacion.class,
        PetAlimentacion.class,
        Actividad.class,
        Baño.class,
        Sintoma.class,
        PetActividad.class,
        PetSintoma.class,
        Medicamento.class

        },
        version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class DatabaseRoom extends RoomDatabase {

    private static final String NAME = "SimpleDatabase.db";
    private static volatile DatabaseRoom INSTANCE;

    public abstract UserDAO userDAO();
    public abstract PetDAO petDAO();
    public abstract UserPetDAO userPetDAO();
    public abstract CartillaDAO cartillaDAO();
    public abstract VacunaDAO vacunaDAO();
    public abstract DesparacitacionDAO desparacitacionDAO();
    public abstract AlimentacionDAO alimentacionDAO();
    public abstract PetAlimentacionDAO petAlimentacionDAO();
    public abstract ActividadDAO actividadDAO();
    public abstract BañoDAO bañoDAO();
    public abstract SintomaDAO sintomaDAO();
    public abstract PetActividadDAO petActividadDAO();
    public abstract PetSintomaDAO petSintomaDAO();
    public abstract MedicamentoDAO medicamentoDAO();

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
                .allowMainThreadQueries() // Uncomment if threads available in MainThread
                .build();
    }

    public void close() {
        if (INSTANCE == null) return;
        if (INSTANCE.isOpen()) INSTANCE.close();
        INSTANCE = null;
    }
}