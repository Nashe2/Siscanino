package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Pet;
import com.nashe.siscanino.data.entity.Pet.SCHEMA;

import java.security.PublicKey;
import java.util.List;

@Dao
public abstract class PetDAO implements BaseDao<Pet> , BaseDao.UpdateDAO<Pet> , BaseDao.DeleteDAO<Pet>, BaseDao.OperationsPrimaryKeyDAO {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Pet> get() ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Pet getById(int id) ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Pet> getByIds(long[] ids) ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract int deleteById(int id);

    @Query("SELECT * FROM Pet WHERE id = :ejemplo_parametro")
    public abstract List<Pet>  lista(int ejemplo_parametro);
}
