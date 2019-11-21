package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.Date;

import static com.nashe.siscanino.data.entity.UserPet.SCHEMA.*;

@Entity(tableName = TABLE,
        primaryKeys = {USER,
                PET},
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = User.SCHEMA.ID,
                        childColumns = USER,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Pet.class,
                        parentColumns = Pet.SCHEMA.ID,
                        childColumns = PET,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)

public class UserPet {
    public interface SCHEMA {
        String TABLE = "UserPet";
        String USER = "user_id";
        String PET = "pet_id";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
    }

    @ColumnInfo(name = USER)
    private int userId;
    @ColumnInfo(name = PET)
    private int petId;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;

    @Ignore
    public UserPet() {
    }

    public UserPet(int userId, int petId, Date created, Date updated) {
        this.userId = userId;
        this.petId = petId;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != UserPet.class)
            return false;

        UserPet casteo = (UserPet) obj;
        return casteo.petId == getPetId() && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.userId == getUserId();

    }

}