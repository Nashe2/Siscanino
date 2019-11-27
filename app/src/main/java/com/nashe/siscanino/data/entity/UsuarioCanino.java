package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.UsuarioCanino.SCHEMA.*;

@Entity(tableName = UsuarioCanino.SCHEMA.TABLE,
        foreignKeys = {
                @ForeignKey(entity = Usuario.class,
                        parentColumns = Usuario.SCHEMA.ID,
                        childColumns = UsuarioCanino.SCHEMA.USUARIO,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Canino.class,
                        parentColumns = Canino.SCHEMA.ID,
                        childColumns = UsuarioCanino.SCHEMA.CANINO,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)
public class UsuarioCanino {
    public interface SCHEMA {
        String TABLE = "UsuarioCanino";
        String ID = "id";
        String USUARIO = "usuario_id";
        String CANINO = "canino_id";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = USUARIO)
    private int idUsuario;
    @ColumnInfo(name = CANINO)
    private int idCacnino;

    @Ignore
    public UsuarioCanino() {
    }

    @Ignore
    public UsuarioCanino(int id, int idUsuario, int idCacnino) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCacnino = idCacnino;
    }

    public UsuarioCanino(int idUsuario, int idCacnino) {
        this.idUsuario = idUsuario;
        this.idCacnino = idCacnino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCacnino() {
        return idCacnino;
    }

    public void setIdCacnino(int idCacnino) {
        this.idCacnino = idCacnino;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != UsuarioCanino.class) return false;
        UsuarioCanino cast = (UsuarioCanino) obj;
        return cast.idUsuario == getIdUsuario() && cast.idCacnino == getIdCacnino();
    }
}