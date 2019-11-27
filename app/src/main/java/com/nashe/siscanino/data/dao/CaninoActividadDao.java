package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Actividad;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.CaninoActividad;
import com.nashe.siscanino.data.entity.CaninoActividad.SCHEMA;

import java.util.List;

@Dao
public abstract class CaninoActividadDao implements BaseDao<CaninoActividad>,
        BaseDao.InnerJoinDAO<Canino, Actividad> {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<CaninoActividad> get();

    @Override
    @Query("DELETE FROM " + CaninoActividad.SCHEMA.TABLE)
    public abstract void drop();

    @Override
    @Query("SELECT * FROM Canino " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON canino.id = " + SCHEMA.TABLE + ".canino_id " +
            "WHERE " + SCHEMA.TABLE + ".canino_id = :idRight")
    public abstract List<Canino> getLeftJoinRight(int idRight);

    @Override
    @Query("SELECT * FROM Actividad " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON actividad_id = " + SCHEMA.TABLE + ".actividad_id " +
            "WHERE " + SCHEMA.TABLE + ".actividad_id = :idLeft")
    public abstract List<Actividad> getRightJoinLeft(int idLeft);
}
