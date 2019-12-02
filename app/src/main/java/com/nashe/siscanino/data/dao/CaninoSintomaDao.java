package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Canino;
import com.nashe.siscanino.data.entity.CaninoSintoma;
import com.nashe.siscanino.data.entity.CaninoSintoma.SCHEMA;
import com.nashe.siscanino.data.entity.Sintoma;

import java.util.List;

@Dao
public abstract class CaninoSintomaDao implements BaseDao<CaninoSintoma>,
        BaseDao.UpdateDAO<CaninoSintoma>,
        BaseDao.DeleteDAO<CaninoSintoma>,
        BaseDao.OperationsPrimaryKeyDAO<CaninoSintoma>,
        BaseDao.InnerJoinDAO<Canino, Sintoma> {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<CaninoSintoma> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public CaninoSintoma getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    abstract public List<CaninoSintoma> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int deleteById(int id);

    @Override
    @Query("SELECT * FROM Canino " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Canino.id = " + SCHEMA.TABLE + ".Canino_id " +
            "WHERE " + SCHEMA.TABLE + ".Canino_id = :idRight")
    public abstract List<Canino> getLeftJoinRight(int idRight);

    @Override
    @Query("SELECT * FROM Sintoma " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Sintoma_id = " + SCHEMA.TABLE + ".Sintoma_id " +
            "WHERE " + SCHEMA.TABLE + ".Sintoma_id = :idLeft")
    public abstract List<Sintoma> getRightJoinLeft(int idLeft);

    @Query("SELECT * FROM CaninoSintoma WHERE canino_id = :left")
    public abstract List<CaninoSintoma> getLeft(int left);

    @Query("SELECT * FROM CaninoSintoma WHERE sintoma_id = :right")
    public abstract List<CaninoSintoma> getRight(int right);
}
