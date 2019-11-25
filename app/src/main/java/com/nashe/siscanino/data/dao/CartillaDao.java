package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Cartilla;
import com.nashe.siscanino.data.entity.Cartilla.SCHEMA;

import java.util.List;

@Dao
public abstract class CartillaDao implements BaseDao<Cartilla>, BaseDao.UpdateDAO<Cartilla>, BaseDao.DeleteDAO<Cartilla>, BaseDao.OperationsPrimaryKeyDAO {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Cartilla> get() ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Cartilla getById(int id) ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Cartilla> getByIds(long[] ids) ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract int deleteById(int id);

    @Query("SELECT * FROM Cartilla WHERE id = :ejemplo_parametro")
    public abstract List<Cartilla>  lista(int ejemplo_parametro);
}
