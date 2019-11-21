package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.Cartilla.SCHEMA.*;

@Entity(tableName = TABLE,
        foreignKeys = {@ForeignKey(
                entity = Pet.class,
                parentColumns = Pet.SCHEMA.ID,
                childColumns = PET,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )}
)

public class Cartilla {
    public interface SCHEMA {
        String TABLE = "Cartilla";
        String ID = "id";
        String MVZ = "mvz";
        String DIRECCION = "direccion";
        String CLINICA = "clinica";
        String PREVIENE = "previene";
        String PET = "pet_id";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = MVZ)
    private String mvz;
    @ColumnInfo(name = DIRECCION)
    private String direccion;
    @ColumnInfo(name = CLINICA)
    private String clinica;
    @ColumnInfo(name = PREVIENE)
    private String previene;
    @ColumnInfo(name = PET)
    private int petId;

    @Ignore
    public Cartilla() {
    }

    @Ignore
    public Cartilla(int id, String mvz, String direccion, String clinica, String previene, int petId) {
        this.id = id;
        this.mvz = mvz;
        this.direccion = direccion;
        this.clinica = clinica;
        this.previene = previene;
        this.petId = petId;
    }

    @Ignore
    public Cartilla(String mvz, String direccion, String clinica, String previene) {
        this.mvz = mvz;
        this.direccion = direccion;
        this.clinica = clinica;
        this.previene = previene;
    }

    public Cartilla(String mvz, String direccion, String clinica, String previene, int petId) {
        this.mvz = mvz;
        this.direccion = direccion;
        this.clinica = clinica;
        this.previene = previene;
        this.petId = petId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMvz() {
        return mvz;
    }

    public void setMvz(String mvz) {
        this.mvz = mvz;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public String getPreviene() {
        return previene;
    }

    public void setPreviene(String previene) {
        this.previene = previene;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Cartilla.class)
            return false;

        Cartilla casteo=(Cartilla)obj;//permite convertir el objeto global a un objeto cartilla
        return casteo.id==getId() && casteo.mvz.equals(getMvz()) && casteo.direccion.equals(getDireccion()) && casteo.clinica.equals(getClinica()) && casteo.previene.equals(getPreviene()) && casteo.petId == getPetId();
    }
}
