package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.Date;

import static com.nashe.siscanino.data.entity.PetActividad.SCHEMA.*;


@Entity(tableName = TABLE,
        primaryKeys = {PET,
                ACTIVIDAD},
        foreignKeys = {
                @ForeignKey(entity = Pet.class,
                        parentColumns = Pet.SCHEMA.ID,
                        childColumns = PET,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Actividad.class,
                        parentColumns = Actividad.SCHEMA.ID,
                        childColumns = ACTIVIDAD,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)

public class PetActividad {
    public interface SCHEMA {
        String TABLE = "PetActividad";
        String PET = "pet_id";
        String ACTIVIDAD = "actividad_id";
        String NOMBRE = "nombre";
        String HORA = "hora";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
    }

    @ColumnInfo(name = PET)
    private int petId;
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
    public PetActividad() {
    }

    @Ignore
    public PetActividad(int petId, int actividadId, String nombre, String hora, Date created, Date updated) {
        this.petId = petId;
        this.actividadId = actividadId;
        this.nombre = nombre;
        this.hora = hora;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
    }

    public PetActividad(int actividadId, String nombre, String hora, Date created, Date updated) {
        this.actividadId = actividadId;
        this.nombre = nombre;
        this.hora = hora;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
    }

    @Ignore
    public PetActividad(String nombre, String hora, Date created, Date updated) {
        this.nombre = nombre;
        this.hora = hora;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
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
        if (obj == null || obj.getClass() != PetActividad.class)
            return false;

        PetActividad casteo = (PetActividad) obj;
        return casteo.petId == getPetId() && casteo.nombre.equals(getNombre())  && casteo.hora.equals(getHora()) && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.actividadId == getActividadId();

    }

}
