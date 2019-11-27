package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Baño;
import com.nashe.siscanino.data.entity.Baño.SCHEMA;

import java.util.List;

@Dao
public abstract class BañoDao implements BaseDao<Baño>,
        BaseDao.UpdateDAO<Baño>,
        BaseDao.DeleteDAO<Baño>,
        BaseDao.OperationsPrimaryKeyDAO {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Baño> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Baño getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Baño> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract int deleteById(int id);
}
