package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Pet;
import com.nashe.siscanino.data.entity.PetSintoma;
import com.nashe.siscanino.data.entity.PetSintoma.SCHEMA;
import com.nashe.siscanino.data.entity.Sintoma;

import java.util.List;

@Dao
public abstract class PetSintomaDAO implements BaseDao<PetSintoma>, BaseDao.InnerJoinDAO<Pet, Sintoma> {
    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<PetSintoma> get() ;

    @Override
    @Query("DELETE FROM " + PetSintoma.SCHEMA.TABLE)
    public abstract void drop();



    @Override
    @Query("SELECT * FROM Pet " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Pet.id = " + SCHEMA.TABLE + ".Pet_id " +
            "WHERE " + SCHEMA.TABLE + ".Pet_id = :idRight")
    public abstract List<Pet> getLeftJoinRight(int idRight) ;

    @Override
    @Query("SELECT * FROM Sintoma " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Sintoma_id = " + SCHEMA.TABLE + ".Sintoma_id " +
            "WHERE " + SCHEMA.TABLE + ".Sintoma_id = :idLeft")
    public abstract List<Sintoma> getRightJoinLeft(int idLeft) ;
}
