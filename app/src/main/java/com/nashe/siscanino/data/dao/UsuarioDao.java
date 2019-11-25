package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;


import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Usuario;
import com.nashe.siscanino.data.entity.Usuario.SCHEMA;

import java.util.List;

@Dao
public abstract class UsuarioDao implements BaseDao<Usuario>,
        BaseDao.UpdateDAO<Usuario>,
        BaseDao.DeleteDAO<Usuario>,
        BaseDao.OperationsPrimaryKeyDAO<Usuario> {

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    abstract public List<Usuario> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public Usuario getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    abstract public List<Usuario> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int deleteById(int id);

    @Query("SELECT id FROM " + SCHEMA.TABLE + " WHERE nombre = :usuario")
    abstract public int existe(String usuario);
}
