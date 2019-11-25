package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Actividad;
import com.nashe.siscanino.data.entity.Actividad.SCHEMA;

import java.util.List;

@Dao
public abstract class ActividadDAO implements BaseDao<Actividad>, BaseDao.UpdateDAO<Actividad>, BaseDao.DeleteDAO<Actividad>, BaseDao.OperationsPrimaryKeyDAO {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Actividad> get() ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Actividad getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public  abstract List<Actividad> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public  abstract int deleteById(int id);

    @Query("SELECT * FROM Actividad WHERE id = :ejemplo_parametro")
    public abstract List<Actividad> lista(int ejemplo_parametro);
}
