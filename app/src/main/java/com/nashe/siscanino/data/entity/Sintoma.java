package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.nashe.siscanino.data.entity.Sintoma.SCHEMA.*;

@Entity(tableName = TABLE)
public class Sintoma {
    public interface SCHEMA {
        String TABLE = "Sintoma";
        String ID = "id";
        String NOMBRE = "nombre";
        String EST_AMNIMO = "animo";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NOMBRE)
    private String nombre;
    @ColumnInfo(name = EST_AMNIMO)
    private String animo;

    @Ignore
    public Sintoma() {
    }

    @Ignore
    public Sintoma(int id, String nombre, String animo) {
        this.id = id;
        this.nombre = nombre;
        this.animo = animo;
    }

    public Sintoma(String nombre, String animo) {
        this.nombre = nombre;
        this.animo = animo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnimo() {
        return animo;
    }

    public void setAnimo(String animo) {
        this.animo = animo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Sintoma.class) return false;
        Sintoma casteo = (Sintoma) obj;
        return casteo.id == getId()
                && casteo.nombre.equals(getNombre())
                && casteo.animo.equals(getAnimo());
    }
}