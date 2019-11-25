package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.Usuario;
import com.nashe.siscanino.data.entity.UsuarioCaninoJoin;
import com.nashe.siscanino.data.entity.UsuarioCaninoJoin.SCHEMA;

import java.util.List;

@Dao
public abstract class UsuarioCaninoJoinDao implements BaseDao<UsuarioCaninoJoin>,
        BaseDao.InnerJoinDAO<Usuario, Canino> {
    
    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    abstract public List<UsuarioCaninoJoin> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM Usuario " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Usuario.id = " + SCHEMA.TABLE + ".usuario_id " +
            "WHERE " + SCHEMA.TABLE + ".canino_id = :idRight")
    abstract public List<Usuario> getLeftJoinRight(int idRight) ;

    @Override
    @Query("SELECT * FROM Canino " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Canino.id = " + SCHEMA.TABLE + ".canino_id " +
            "WHERE " + SCHEMA.TABLE + ".usuario_id = :idLeft")
    abstract public List<Canino> getRightJoinLeft(int idLeft) ;
}
