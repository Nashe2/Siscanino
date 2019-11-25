package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Pet;
import com.nashe.siscanino.data.entity.Alimentacion;
import com.nashe.siscanino.data.entity.PetAlimentacion;
import com.nashe.siscanino.data.entity.PetAlimentacion.SCHEMA;

import java.util.List;

@Dao
public abstract class PetAlimentacionDAO implements BaseDao<PetAlimentacion>, BaseDao.InnerJoinDAO<Pet, Alimentacion>{

@Override
@Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
public abstract int count() ;

@Override
@Query("SELECT * FROM " + SCHEMA.TABLE)
public abstract List<PetAlimentacion> get() ;

@Override
@Query("DELETE FROM " + PetAlimentacion.SCHEMA.TABLE)
public abstract void drop();

@Override
@Query("SELECT * FROM Pet " +
        "INNER JOIN " + SCHEMA.TABLE + " " +
        "ON Pet.id = " + SCHEMA.TABLE + ".Pet_id " +
        "WHERE " + SCHEMA.TABLE + ".Pet_id = :idRight")
public abstract List<Pet> getLeftJoinRight(int idRight) ;

@Override
@Query("SELECT * FROM Alimentacion " +
        "INNER JOIN " + SCHEMA.TABLE + " " +
        "ON Alimentacion.id = " + SCHEMA.TABLE + ".Alimentacion_id " +
        "WHERE " + SCHEMA.TABLE + ".Alimentacion_id = :idLeft")
public abstract List<Alimentacion> getRightJoinLeft(int idLeft) ;
}
