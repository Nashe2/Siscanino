package com.nashe.siscanino.data.entity;


import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.User.SCHEMA.*;

@Entity(tableName = TABLE)
public class User {
    public  interface SCHEMA{
    String TABLE="User";
    String ID="id";
    String NOMBRE="nombre";
    String CONTRASENIA="contrasenia";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NOMBRE)
    private String nombre;
    @ColumnInfo(name = CONTRASENIA)
    private String contrasenia;

    @Ignore
    public User() {
    }

    @Ignore
    public User(int id, String nombre, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public User(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
    if(obj==null || obj.getClass() !=User.class)
        return false;

    User casteo=(User)obj;//permite convertir el objeto global a un objeto usuario
        return casteo.id==getId() && casteo.nombre.equals(getNombre()) && casteo.contrasenia.equals(getContrasenia());

    }
}
