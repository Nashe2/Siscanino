package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.CaninoAlimentacion.SCHEMA.*;

@Entity(tableName = TABLE,
        foreignKeys = {
                @ForeignKey(entity = Canino.class,
                        parentColumns = Canino.SCHEMA.ID,
                        childColumns = CANINO,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Alimentacion.class,
                        parentColumns = Alimentacion.SCHEMA.ID,
                        childColumns = ALIMENTACION,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)
public class CaninoAlimentacion {
    public interface SCHEMA {
        String TABLE = "CaninoAlimentacion";
        String ID = "id";
        String CANINO = "canino_id";
        String ALIMENTACION = "alimentacion_id";
        String PORCION = "porcion";
        String HORA = "hora";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    // TODO: Poner Fecha y hora
    @ColumnInfo(name = CANINO)
    private int caninoId;
    @ColumnInfo(name = ALIMENTACION)
    private int alimentacionId;
    @ColumnInfo(name = PORCION)
    private String porcion;
    @ColumnInfo(name = HORA)
    private String hora;

    @Ignore
    public CaninoAlimentacion() {
    }

    @Ignore
    public CaninoAlimentacion(int id, int caninoId, int alimentacionId, String porcion, String hora) {
        this.id =id;
        this.caninoId = caninoId;
        this.alimentacionId = alimentacionId;
        this.porcion = porcion;
        this.hora = hora;
    }

    public CaninoAlimentacion(int caninoId, int alimentacionId, String porcion, String hora) {
        this.caninoId = caninoId;
        this.alimentacionId = alimentacionId;
        this.porcion = porcion;
        this.hora = hora;
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
        if (obj == null || obj.getClass() != CaninoAlimentacion.class) return false;
        CaninoAlimentacion casteo = (CaninoAlimentacion) obj;
        return casteo.caninoId == getCaninoId() && casteo.porcion.equals(getPorcion()) && casteo.hora.equals(getHora()) && casteo.alimentacionId == getAlimentacionId();
    }
}