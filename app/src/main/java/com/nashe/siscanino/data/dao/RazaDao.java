package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Raza;
import com.nashe.siscanino.data.entity.Raza.SCHEMA;

import java.util.List;

@Dao
public abstract class RazaDao implements BaseDao<Raza>,
        BaseDao.UpdateDAO<Raza>,
        BaseDao.DeleteDAO<Raza>,
        BaseDao.OperationsPrimaryKeyDAO<Raza> {

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    abstract public List<Raza> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public Raza getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    abstract public List<Raza> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int deleteById(int id);
}
