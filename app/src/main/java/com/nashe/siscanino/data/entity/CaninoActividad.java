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
        String HORA = "hora";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
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
    @ColumnInfo(name = HORA)
    private String hora;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;

    @Ignore
    public CaninoActividad() {
    }

    @Ignore
    public CaninoActividad(int id, int caninoId, int actividadId, String nombre, String hora, Date created, Date updated) {
        this.id = id;
        this.caninoId = caninoId;
        this.actividadId = actividadId;
        this.nombre = nombre;
        this.hora = hora;
        this.created = created;
        this.updated = updated;
    }

    public CaninoActividad(int caninoId, int actividadId, String nombre, String hora, Date created, Date updated) {
        this.caninoId = caninoId;
        this.actividadId = actividadId;
        this.nombre = nombre;
        this.hora = hora;
        this.created = created;
        this.updated = updated;
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != CaninoActividad.class) return false;
        CaninoActividad casteo = (CaninoActividad) obj;
        return casteo.caninoId == getCaninoId() && casteo.nombre.equals(getNombre()) && casteo.hora.equals(getHora()) && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.actividadId == getActividadId();
    }
}