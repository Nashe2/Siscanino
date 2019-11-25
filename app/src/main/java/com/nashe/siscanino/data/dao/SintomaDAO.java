package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Sintoma;
import com.nashe.siscanino.data.entity.Sintoma.SCHEMA;

import java.util.List;

@Dao
public abstract class SintomaDAO implements BaseDao<Sintoma>, BaseDao.UpdateDAO<Sintoma>, BaseDao.DeleteDAO<Sintoma>, BaseDao.OperationsPrimaryKeyDAO {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Sintoma> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Sintoma getById(int id) ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Sintoma> getByIds(long[] ids) ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract int deleteById(int id) ;

    @Query("SELECT * FROM Sintoma WHERE id = :ejemplo_parametro")
    public abstract List<Sintoma> lista(int ejemplo_parametro);

}
