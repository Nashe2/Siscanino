package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.Date;

import static com.nashe.siscanino.data.entity.CaninoSintoma.SCHEMA.*;

@Entity(tableName = TABLE,
        primaryKeys = {CANINO,
                SINTOMA},
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
        String CANINO = "canino_id";
        String SINTOMA = "sintoma_id";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
        String DIAGNOSTICO ="diagnostico";
    }

    @ColumnInfo(name = CANINO)
    private int caninoId;
    @ColumnInfo(name = SINTOMA)
    private int sintomaId;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;
    @ColumnInfo(name = DIAGNOSTICO)
    private String diagnostico;

    @Ignore
    public CaninoSintoma() {
    }

    @Ignore
    public CaninoSintoma(int caninoId, int sintomaId, Date created, Date updated, String diagnostico) {
        this.caninoId = caninoId;
        this.sintomaId = sintomaId;
        this.created = created;
        this.updated = updated;
        this.diagnostico = diagnostico;
    }

    public CaninoSintoma(int sintomaId, Date created, Date updated, String diagnostico) {
        this.sintomaId = sintomaId;
        this.created = created;
        this.updated = updated;
        this.diagnostico = diagnostico;
    }

    @Ignore
    public CaninoSintoma(Date created, Date updated, String diagnostico) {
        this.created = created;
        this.updated = updated;
        this.diagnostico = diagnostico;
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
        if (obj == null || obj.getClass() != CaninoSintoma.class) return false;
        CaninoSintoma casteo = (CaninoSintoma) obj;
        return casteo.caninoId == getCaninoId() && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.diagnostico.equals(getDiagnostico())&& casteo.sintomaId == getSintomaId();
    }
}