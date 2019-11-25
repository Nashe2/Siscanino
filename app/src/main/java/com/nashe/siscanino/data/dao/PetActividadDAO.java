package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Actividad;
import com.nashe.siscanino.data.entity.Pet;
import com.nashe.siscanino.data.entity.PetActividad;
import com.nashe.siscanino.data.entity.PetActividad.SCHEMA;

import java.util.List;

@Dao
public abstract class PetActividadDAO implements BaseDao<PetActividad>, BaseDao.InnerJoinDAO<Pet, Actividad> {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<PetActividad> get() ;

    @Override
    @Query("DELETE FROM " + PetActividad.SCHEMA.TABLE)
    public abstract void drop();

    @Override
    @Query("SELECT * FROM Pet " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Pet.id = " + SCHEMA.TABLE + ".Pet_id " +
            "WHERE " + SCHEMA.TABLE + ".Pet_id = :idRight")
    public abstract List<Pet> getLeftJoinRight(int idRight) ;

    @Override
    @Query("SELECT * FROM Actividad " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Actividad_id = " + SCHEMA.TABLE + ".Actividad_id " +
            "WHERE " + SCHEMA.TABLE + ".Actividad_id = :idLeft")
    public abstract List<Actividad> getRightJoinLeft(int idLeft) ;
}
