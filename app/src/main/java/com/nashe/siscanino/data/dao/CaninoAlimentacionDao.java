package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.CaninoAlimentacion;
import com.nashe.siscanino.data.entity.CaninoAlimentacion.SCHEMA;

import java.util.List;

@Dao
public abstract class CaninoAlimentacionDao implements BaseDao<CaninoAlimentacion>,
        BaseDao.InnerJoinDAO<Canino, Alimentacion> {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<CaninoAlimentacion> get();

    @Override
    @Query("DELETE FROM " + CaninoAlimentacion.SCHEMA.TABLE)
    public abstract void drop();

    @Override
    @Query("SELECT * FROM Canino " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON canino.id = " + SCHEMA.TABLE + ".canino_id " +
            "WHERE " + SCHEMA.TABLE + ".canino_id = :idRight")
    public abstract List<Canino> getLeftJoinRight(int idRight);

    @Override
    @Query("SELECT * FROM Alimentacion " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON alimentacion.id = " + SCHEMA.TABLE + ".alimentacion_id " +
            "WHERE " + SCHEMA.TABLE + ".alimentacion_id = :idLeft")
    public abstract List<Alimentacion> getRightJoinLeft(int idLeft);
}
