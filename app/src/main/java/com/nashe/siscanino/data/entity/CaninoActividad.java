package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.nashe.siscanino.data.entity.CaninoActividad.SCHEMA.*;

@Entity(tableName = TABLE,
        foreignKeys = {
                @ForeignKey(entity = Canino.class,
                        parentColumns = Canino.SCHEMA.ID,
                        childColumns = CANINO,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Actividad.class,
                        parentColumns = Actividad.SCHEMA.ID,
                        childColumns = ACTIVIDAD,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)
public class CaninoActividad {
    public interface SCHEMA {
        String TABLE = "CaninoActividad";
        String ID = "ID";
        String CANINO = "canino_id";
        String ACTIVIDAD = "actividad_id";
        String NOMBRE = "nombre";
        String FECHA_HORA = "fecha_hora";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = CANINO)
    private int caninoId;
    @ColumnInfo(name = ACTIVIDAD)
    private int actividadId;
    @ColumnInfo(name = NOMBRE)
    private String nombre;
    @ColumnInfo(name = FECHA_HORA)
    private Date fechaHora;

    @Ignore
    public CaninoActividad() {
    }

    @Ignore
    public CaninoActividad(int id, int caninoId, int actividadId, String nombre, Date fechaHora) {
        this.id = id;
        this.caninoId = caninoId;
        this.actividadId = actividadId;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
    }

    public CaninoActividad(int caninoId, int actividadId, String nombre, Date fechaHora) {
        this.caninoId = caninoId;
        this.actividadId = actividadId;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaninoId() {
        return caninoId;
    }

    public void setCaninoId(int caninoId) {
        this.caninoId = caninoId;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != CaninoActividad.class) return false;
        CaninoActividad casteo = (CaninoActividad) obj;
        return casteo.id == getId()
                && casteo.caninoId == getCaninoId()
                && casteo.actividadId == getActividadId()
                && casteo.nombre.equals(getNombre())
                && casteo.fechaHora.equals(getFechaHora());
    }
}