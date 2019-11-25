package com.nashe.siscanino.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = Alimentacion.SCHEMA.TABLE)
public class Alimentacion {
    public interface SCHEMA {
        String TABLE = "Alimentacion";
        String ID = "id";
    }
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
