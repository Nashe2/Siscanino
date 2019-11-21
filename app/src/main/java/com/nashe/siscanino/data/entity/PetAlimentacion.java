package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.Date;

import static com.nashe.siscanino.data.entity.PetAlimentacion.SCHEMA.*;

@Entity(tableName = TABLE,
        primaryKeys = {PET,
                ALIMENTACION},
        foreignKeys = {
                @ForeignKey(entity = Pet.class,
                        parentColumns = Pet.SCHEMA.ID,
                        childColumns = PET,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Alimentacion.class,
                        parentColumns = Alimentacion.SCHEMA.ID,
                        childColumns = ALIMENTACION,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)

public class PetAlimentacion {
    public interface SCHEMA {
        String TABLE = "PetAlimentacion";
        String PET = "pet_id";
        String ALIMENTACION = "alimentacion_id";
        String PORCION = "porcion";
        String HORA = "hora";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
    }

    @ColumnInfo(name = PET)
    private int petId;
    @ColumnInfo(name = ALIMENTACION)
    private int alimentacionId;
    @ColumnInfo(name = PORCION)
    private String porcion;
    @ColumnInfo(name = HORA)
    private String hora;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;

    @Ignore
    public PetAlimentacion() {
    }

    public PetAlimentacion(int petId, int alimentacionId, String porcion, String hora, Date created, Date updated) {
        this.petId = petId;
        this.alimentacionId = alimentacionId;
        this.porcion = porcion;
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

    public int getAlimentacionId() {
        return alimentacionId;
    }

    public void setAlimentacionId(int alimentacionId) {
        this.alimentacionId = alimentacionId;
    }

    public String getPorcion() {
        return porcion;
    }

    public void setPorcion(String porcion) {
        this.porcion = porcion;
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
        if (obj == null || obj.getClass() != PetAlimentacion.class)
            return false;

        PetAlimentacion casteo = (PetAlimentacion) obj;
        return casteo.petId == getPetId() && casteo.porcion.equals(getPorcion()) && casteo.hora.equals(getHora()) && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.alimentacionId == getAlimentacionId();

    }
}
