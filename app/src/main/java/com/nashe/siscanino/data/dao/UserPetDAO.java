package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Pet;
import com.nashe.siscanino.data.entity.User;
import com.nashe.siscanino.data.entity.UserPet;
import com.nashe.siscanino.data.entity.UserPet.SCHEMA;

import java.util.List;

@Dao
public abstract class UserPetDAO implements BaseDao<UserPet> , BaseDao.InnerJoinDAO<User, Pet>{

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<UserPet> get() ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop();

    /**
    @Query("SELECT * FROM User " +
            "INNER JOIN UserPet " +
            "ON User.id =  UserPet.user_id " +
            "WHERE UserPet.pet_id = :id")
    public abstract List<User> getUserPet(int id) ;

    @Query("SELECT * FROM pet " +
            "INNER JOIN  UserPet " +
            "ON Pet.id =  UserPet.pet_id " +
            "WHERE UserPet.user_id = :id")
    public abstract List<Pet> getPetUser(int id) ;
**/
    @Override
    @Query("SELECT * FROM User " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON User.id = " + SCHEMA.TABLE + ".User_id " +
            "WHERE " + SCHEMA.TABLE + ".Pet_id = :idRight")
    public abstract List<User> getLeftJoinRight(int idRight) ;

    @Override
    @Query("SELECT * FROM Pet " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Pet.id = " + SCHEMA.TABLE + ".Pet_id " +
            "WHERE " + SCHEMA.TABLE + ".User_id = :idLeft")
    public abstract List<Pet> getRightJoinLeft(int idLeft) ;
}

