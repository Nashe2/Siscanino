package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Vacuna;
import com.nashe.siscanino.data.entity.Vacuna.SCHEMA;

import java.util.List;

@Dao
public abstract class VacunaDao implements BaseDao<Vacuna>, BaseDao.UpdateDAO<Vacuna> , BaseDao.DeleteDAO<Vacuna>, BaseDao.OperationsPrimaryKeyDAO{

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Vacuna> get() ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Vacuna getById(int id) ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Vacuna> getByIds(long[] ids) ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract int deleteById(int id);

    @Query("SELECT * FROM Vacuna WHERE id = :ejemplo_parametro")
    public abstract List<Vacuna>  lista(int ejemplo_parametro);

}

