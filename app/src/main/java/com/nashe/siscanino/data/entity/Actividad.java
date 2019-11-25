package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.Actividad.SCHEMA.*;

@Entity(tableName = TABLE)
public class Actividad {
    public interface SCHEMA {
        String TABLE = "Actividad";
        String ID = "id";
        String TIPO_ACTIVIDAD = "tipo";
        String TIEMPO = "tiempo";
        String DESCRIPCION = "descripcion";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = TIPO_ACTIVIDAD)
    private String tipo;
    @ColumnInfo(name = TIEMPO)
    private String tiempo;
    @ColumnInfo(name = DESCRIPCION)
    private String descripcion;

    @Ignore
    public Actividad() {
    }

    @Ignore
    public Actividad(int id, String tipo, String tiempo, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
    }


    public Actividad(String tipo, String tiempo, String descripcion) {
        this.tipo = tipo;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Actividad.class)
            return false;

        Actividad casteo = (Actividad) obj;
        return casteo.id == getId() && casteo.tipo.equals(getTipo()) && casteo.tiempo.equals(getTiempo()) && casteo.descripcion.equals(getDescripcion());
    }
}
