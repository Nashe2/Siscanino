package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.User;
import com.nashe.siscanino.data.entity.User.SCHEMA;

import java.util.List;

@Dao
public abstract class UserDAO implements BaseDao<User>, BaseDao.UpdateDAO<User>, BaseDao.DeleteDAO<User>, BaseDao.OperationsPrimaryKeyDAO {
//
  //  @Insert
    //public abstract void insert(User registro);

    //@Update
    //public  abstract  void update(User registro);

    //@Delete
    //public abstract  void delete(User registro);

    //@Query("select * from User")
   // public  abstract List<User> select();


    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<User> get() ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract User getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public  abstract List<User> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public  abstract int deleteById(int id);

    @Query("SELECT * FROM User WHERE id = :ejemplo_parametro")
    public abstract List<User> lista(int ejemplo_parametro);
}
