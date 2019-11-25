package com.nashe.siscanino.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nashe.siscanino.data.BaseDao;
import com.nashe.siscanino.data.entity.Medicamento;
import com.nashe.siscanino.data.entity.Medicamento.SCHEMA;

import java.util.List;

@Dao
public abstract class MedicamentoDAO implements BaseDao<Medicamento>, BaseDao.UpdateDAO<Medicamento>, BaseDao.DeleteDAO<Medicamento>, BaseDao.OperationsPrimaryKeyDAO {

    @Override
    @Query("SELECT COUNT(*)FROM " + SCHEMA.TABLE)
    public abstract int count() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    public abstract List<Medicamento> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    public abstract void drop() ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract Medicamento getById(int id) ;

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Medicamento> getByIds(long[] ids) ;

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    public abstract int deleteById(int id) ;

    @Query("SELECT * FROM Medicamento WHERE id = :ejemplo_parametro")
    public abstract List<Medicamento>  lista(int ejemplo_parametro);

}
