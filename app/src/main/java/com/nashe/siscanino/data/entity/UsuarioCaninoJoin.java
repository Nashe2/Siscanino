package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;


@Entity(tableName = UsuarioCaninoJoin.SCHEMA.TABLE,
        primaryKeys = {UsuarioCaninoJoin.SCHEMA.USUARIO,
                UsuarioCaninoJoin.SCHEMA.CANINO},
        foreignKeys = {
                @ForeignKey(entity = Usuario.class,
                        parentColumns = Usuario.SCHEMA.ID,
                        childColumns = UsuarioCaninoJoin.SCHEMA.USUARIO,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Canino.class,
                        parentColumns = Canino.SCHEMA.ID,
                        childColumns = UsuarioCaninoJoin.SCHEMA.CANINO,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)
public class UsuarioCaninoJoin {
    public interface SCHEMA {
        String TABLE = "UsuarioCanino";
        String USUARIO = "usuario_id";
        String CANINO = "canino_id";
    }

    @ColumnInfo(name = SCHEMA.USUARIO)
    private int idUsuario;
    @ColumnInfo(name = SCHEMA.CANINO)
    private int idCacnino;

    @Ignore
    public UsuarioCaninoJoin() {
    }

    public UsuarioCaninoJoin(int idUsuario, int idCacnino) {
        this.idUsuario = idUsuario;
        this.idCacnino = idCacnino;
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
        if (obj == null || obj.getClass() != UsuarioCaninoJoin.class) return false;
        UsuarioCaninoJoin cast = (UsuarioCaninoJoin) obj;
        return cast.idUsuario == getIdUsuario()
                && cast.idCacnino == getIdCacnino();
    }
}
