package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.Alimentacion.SCHEMA.*;

@Entity(tableName = TABLE)
public class Alimentacion {
    public interface SCHEMA {
        String TABLE = "Alimentacion";
        String ID = "id";
        String PRODUCTO = "producto";
        String DESCRIPCION = "descripcion";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = PRODUCTO)
    private String producto;
    @ColumnInfo(name = DESCRIPCION)
    private String descripcion;


    public Alimentacion() {
    }

    public Alimentacion(int id, String producto, String descripcion) {
        this.id = id;
        this.producto = producto;
        this.descripcion = descripcion;
    }

    public Alimentacion(String producto, String descripcion) {
        this.producto = producto;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Alimentacion.class)
            return false;

        Alimentacion casteo = (Alimentacion) obj;
        return casteo.id == getId() && casteo.producto.equals(getProducto()) && casteo.descripcion.equals(getDescripcion());

    }
}