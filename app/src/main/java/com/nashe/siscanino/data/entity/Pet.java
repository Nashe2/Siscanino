package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.Pet.SCHEMA.*;

@Entity(tableName = TABLE)
public class Pet {
    public interface SCHEMA{
        String TABLE = "Pet";
        String ID = "id";
        String NAME = "name";
        String BORN_DAY = "born";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NAME)
    private String name;
    @ColumnInfo(name = BORN_DAY)
    private String born;

    @Ignore
    public Pet() {
    }

    public Pet(int id, String name, String born) {
        this.id = id;
        this.name = name;
        this.born = born;
    }

    @Ignore
    public Pet(String name, String born) {
        this.name = name;
        this.born = born;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    @Override
    public  boolean equals(@Nullable Object obj){
        if (obj==null || obj.getClass() != Pet.class)
            return false;

        Pet casteo= (Pet) obj;
        return  casteo.id == getId() && casteo.name.equals(getName()) && casteo.born.equals(getBorn());
    }
}
