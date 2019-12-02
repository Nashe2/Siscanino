package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.nashe.siscanino.data.entity.CaninoSintoma.SCHEMA.*;

@Entity(tableName = TABLE,
        foreignKeys = {
                @ForeignKey(entity = Canino.class,
                        parentColumns = Canino.SCHEMA.ID,
                        childColumns = CANINO,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Sintoma.class,
                        parentColumns = Sintoma.SCHEMA.ID,
                        childColumns = SINTOMA,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)
public class CaninoSintoma {
    public interface SCHEMA {
        String TABLE = "CaninoSintoma";
        String ID = "id";
        String CANINO = "canino_id";
        String SINTOMA = "sintoma_id";
        String DESCRIPCION = "descripcion";
        String FECHA_HORA = "fechaHora";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = CANINO)
    private int caninoId;
    @ColumnInfo(name = SINTOMA)
    private int sintomaId;
    @ColumnInfo(name = DESCRIPCION)
    private String descripcion;
    @ColumnInfo(name = FECHA_HORA)
    private Date fechaHora;

    @Ignore
    public CaninoSintoma() {
    }

    @Ignore
    public CaninoSintoma(int id, int caninoId, int sintomaId, String descripcion, Date fechaHora, Date updated) {
        this.id = id;
        this.caninoId = caninoId;
        this.sintomaId = sintomaId;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    public CaninoSintoma(int caninoId, int sintomaId, String descripcion, Date fechaHora) {
        this.caninoId = caninoId;
        this.sintomaId = sintomaId;
        this.descripcion = descripcion;
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

    public int getSintomaId() {
        return sintomaId;
    }

    public void setSintomaId(int sintomaId) {
        this.sintomaId = sintomaId;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != CaninoSintoma.class) return false;
        CaninoSintoma casteo = (CaninoSintoma) obj;
        return casteo.getId() == getId()
                && casteo.caninoId == getCaninoId()
                && casteo.sintomaId == getSintomaId()
                && casteo.descripcion.equals(getDescripcion())
                && casteo.fechaHora.equals(getFechaHora());
    }
}