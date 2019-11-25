package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.Date;

import static com.nashe.siscanino.data.entity.PetSintoma.SCHEMA.*;

@Entity(tableName = TABLE,
        primaryKeys = {PET,
                SINTOMA},
        foreignKeys = {
                @ForeignKey(entity = Pet.class,
                        parentColumns = Pet.SCHEMA.ID,
                        childColumns = PET,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Sintoma.class,
                        parentColumns = Sintoma.SCHEMA.ID,
                        childColumns = SINTOMA,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)

public class PetSintoma {
    public interface SCHEMA {
        String TABLE = "PetSintoma";
        String PET = "pet_id";
        String SINTOMA = "sintoma_id";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
        String DIAGNOSTICO ="diagnostico";
    }

    @ColumnInfo(name = PET)
    private int petId;
    @ColumnInfo(name = SINTOMA)
    private int sintomaId;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;
    @ColumnInfo(name = DIAGNOSTICO)
    private String diagnostico;

    @Ignore
    public PetSintoma() {
    }

    @Ignore
    public PetSintoma(int petId, int sintomaId, Date created, Date updated, String diagnostico) {
        this.petId = petId;
        this.sintomaId = sintomaId;
        this.created = created;
        this.updated = updated;
        this.diagnostico = diagnostico;
    }

    public PetSintoma(int sintomaId, Date created, Date updated, String diagnostico) {
        this.sintomaId = sintomaId;
        this.created = created;
        this.updated = updated;
        this.diagnostico = diagnostico;
    }

    @Ignore
    public PetSintoma(Date created, Date updated, String diagnostico) {
        this.created = created;
        this.updated = updated;
        this.diagnostico = diagnostico;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getSintomaId() {
        return sintomaId;
    }

    public void setSintomaId(int sintomaId) {
        this.sintomaId = sintomaId;
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

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != PetSintoma.class)
            return false;

        PetSintoma casteo = (PetSintoma) obj;
        return casteo.petId == getPetId() && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.diagnostico.equals(getDiagnostico())&& casteo.sintomaId == getSintomaId();
    }

}
