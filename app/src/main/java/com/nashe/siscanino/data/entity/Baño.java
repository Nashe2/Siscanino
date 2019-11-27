package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.nashe.siscanino.data.entity.Baño.SCHEMA.*;

@Entity(tableName = TABLE,
        foreignKeys = {@ForeignKey(
                entity = Canino.class,
                parentColumns = Canino.SCHEMA.ID,
                childColumns = CANINO,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )}
)
public class Baño {
    public interface SCHEMA {
        String TABLE = "Baño";
        String ID = "id";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
        String CANINO = "canino_id";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;
    @ColumnInfo(name = CANINO)
    private int caninoId;

    @Ignore
    public Baño() {
    }

    @Ignore
    public Baño(int id, Date created, Date updated, int caninoId) {
        this.id = id;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
        this.caninoId = caninoId;
    }

    @Ignore
    public Baño(Date created, Date updated) {
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
    }

    public Baño(Date created, Date updated, int caninoId) {
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
        this.caninoId = caninoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCaninoId() {
        return caninoId;
    }

    public void setCaninoId(int caninoId) {
        this.caninoId = caninoId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Baño.class) return false;
        Baño casteo = (Baño) obj;
        return casteo.id == getId() && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.caninoId == getCaninoId();
    }
}