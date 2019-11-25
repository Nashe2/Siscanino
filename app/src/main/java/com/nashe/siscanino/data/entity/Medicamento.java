package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.nashe.siscanino.data.entity.Medicamento.SCHEMA.*;

@Entity(tableName = TABLE,
        foreignKeys = {@ForeignKey(
                entity = Pet.class,
                parentColumns = Pet.SCHEMA.ID,
                childColumns = PET,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )}
)

public class Medicamento {
    public interface SCHEMA {
        String TABLE = "Medicamento";
        String ID = "id";
        String NOM_MEDICAMENTO = "medicamento";
        String DESCRIPCION = "descripcion";
        String DOSIS = "dosis";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
        String PET = "pet_id";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NOM_MEDICAMENTO)
    private String medicamento;
    @ColumnInfo(name = DESCRIPCION)
    private String descripcion;
    @ColumnInfo(name = DOSIS)
    private String dosis;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;
    @ColumnInfo(name = PET)
    private int petId;

    @Ignore
    public Medicamento() {
    }

    @Ignore
    public Medicamento(int id, String medicamento, String descripcion, String dosis, Date created, Date updated, int petId) {
        this.id = id;
        this.medicamento = medicamento;
        this.descripcion = descripcion;
        this.dosis = dosis;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
        this.petId = petId;
    }

    public Medicamento(String medicamento, String descripcion, String dosis, Date created, Date updated, int petId) {
        this.medicamento = medicamento;
        this.descripcion = descripcion;
        this.dosis = dosis;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
        this.petId = petId;
    }

    @Ignore
    public Medicamento(String medicamento, String descripcion, String dosis, Date created, Date updated) {
        this.medicamento = medicamento;
        this.descripcion = descripcion;
        this.dosis = dosis;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
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

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Medicamento.class)
            return false;

        Medicamento casteo = (Medicamento) obj;
        return casteo.id == getId() && casteo.medicamento.equals(getMedicamento()) && casteo.descripcion.equals(getDescripcion()) && casteo.dosis.equals(getDosis()) && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.petId == getPetId();
    }

}

