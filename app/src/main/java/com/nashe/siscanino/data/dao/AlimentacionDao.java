package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.Alimentacion.SCHEMA;

import java.util.List;

@Dao
public abstract class AlimentacionDao implements BaseDao<Alimentacion>,
        BaseDao.UpdateDAO<Alimentacion>,
        BaseDao.DeleteDAO<Alimentacion>,
        BaseDao.OperationsPrimaryKeyDAO {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Alimentacion> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Alimentacion getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Alimentacion> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract int deleteById(int id);
}
