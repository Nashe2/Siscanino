package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Desparacitacion;
import com.nashe.siscanino.data.entity.Desparacitacion.SCHEMA;

import java.util.List;

@Dao
public abstract class DesparacitacionDAO implements BaseDao<Desparacitacion>, BaseDao.UpdateDAO<Desparacitacion>, BaseDao.DeleteDAO<Desparacitacion>, BaseDao.OperationsPrimaryKeyDAO {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Desparacitacion> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Desparacitacion getById(int id) ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Desparacitacion> getByIds(long[] ids) ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract int deleteById(int id) ;

    @Query("SELECT * FROM desparacitacion WHERE id = :ejemplo_parametro")
    public abstract List<Desparacitacion>  lista(int ejemplo_parametro);

}
