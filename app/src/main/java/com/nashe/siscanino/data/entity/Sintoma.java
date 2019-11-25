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
        String FECHA = "fecha";
        String HORA = "hora";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
        String EST_AMNIMO = "animo";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NOMBRE)
    private String nombre;
    @ColumnInfo(name = FECHA)
    private Date fecha;
    @ColumnInfo(name = HORA)
    private String hora;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;
    @ColumnInfo(name = EST_AMNIMO)
    private String animo;

    @Ignore
    public Sintoma() {
    }

    @Ignore
    public Sintoma(int id, String nombre, Date fecha, String hora, Date created, Date updated, String animo) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
        this.animo = animo;
    }

    public Sintoma(String nombre, Date fecha, String hora, Date created, Date updated, String animo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getAnimo() {
        return animo;
    }

    public void setAnimo(String animo) {
        this.animo = animo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj==null || obj.getClass() !=Sintoma.class)
            return false;

        Sintoma casteo=(Sintoma)obj;
        return casteo.id==getId() && casteo.nombre.equals(getNombre()) && casteo.fecha.equals(getFecha()) && casteo.hora.equals(getHora()) && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.animo.equals(getAnimo());
    }
}
