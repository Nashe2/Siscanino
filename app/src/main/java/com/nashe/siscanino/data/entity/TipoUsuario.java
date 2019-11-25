package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.TipoUsuario.SCHEMA.DESCRIPCION;
import static com.nashe.siscanino.data.entity.TipoUsuario.SCHEMA.ID;
import static com.nashe.siscanino.data.entity.TipoUsuario.SCHEMA.NOMBRE;
import static com.nashe.siscanino.data.entity.TipoUsuario.SCHEMA.TABLE;

@Entity(tableName = TABLE)
public class TipoUsuario {
    public interface SCHEMA {
        String TABLE = "TipoUsuario";
        String ID = "id";
        String NOMBRE = "nombre";
        String DESCRIPCION = "DESCRIPCION";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NOMBRE)
    private String nombre;
    @ColumnInfo(name = DESCRIPCION)
    private String descripcion;

    @Ignore
    public TipoUsuario() {
    }

    @Ignore
    public TipoUsuario(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public TipoUsuario(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != TipoUsuario.class) return false;
        TipoUsuario cast = (TipoUsuario) obj;
        return cast.id == getId()
                && cast.nombre.equals(getNombre())
                && cast.descripcion.equals(getDescripcion());
    }
}
