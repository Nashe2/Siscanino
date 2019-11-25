package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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
    }

    @ColumnInfo(name = PET)
    private int petId;
    @ColumnInfo(name = ALIMENTACION)
    private int alimentacionId;
    @ColumnInfo(name = PORCION)
    private String porcion;
    @ColumnInfo(name = HORA)
    private String hora;

    @Ignore
    public PetAlimentacion() {
    }

    public PetAlimentacion(int petId, int alimentacionId, String porcion, String hora) {
        this.petId = petId;
        this.alimentacionId = alimentacionId;
        this.porcion = porcion;
        this.hora = hora;
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


    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != PetAlimentacion.class)
            return false;

        PetAlimentacion casteo = (PetAlimentacion) obj;
        return casteo.petId == getPetId() && casteo.porcion.equals(getPorcion()) && casteo.hora.equals(getHora()) && casteo.alimentacionId == getAlimentacionId();

    }
}
