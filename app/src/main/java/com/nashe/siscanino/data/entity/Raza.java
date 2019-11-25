package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.Raza.SCHEMA.CARACTERISTICAS;
import static com.nashe.siscanino.data.entity.Raza.SCHEMA.ID;
import static com.nashe.siscanino.data.entity.Raza.SCHEMA.NOMBRE;
import static com.nashe.siscanino.data.entity.Raza.SCHEMA.TABLE;

@Entity(tableName = TABLE)
public class Raza {

    public interface SCHEMA {
        String TABLE = "Raza";
        String ID = "id";
        String NOMBRE = "nombre";
        String CARACTERISTICAS = "caracteristicas";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    //TODO: Hacer unico los nombres
    @ColumnInfo(name = NOMBRE)
    private String nombre;
    @ColumnInfo(name = CARACTERISTICAS)
    private String caracteristica;

    @Ignore
    public Raza() {
    }

    @Ignore
    public Raza(int id, String nombre, String caracteristica) {
        this.id = id;
        this.nombre = nombre;
        this.caracteristica = caracteristica;
    }

    public Raza(String nombre, String caracteristica) {
        this.nombre = nombre;
        this.caracteristica = caracteristica;
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

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Raza.class) return false;
        Raza cast = (Raza) obj;
        return cast.id == getId()
                && cast.nombre.equals(getNombre())
                && cast.caracteristica.equals(getCaracteristica());
    }
}
