package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static com.nashe.siscanino.data.entity.Usuario.SCHEMA.CONTRASENIA;
import static com.nashe.siscanino.data.entity.Usuario.SCHEMA.ID;
import static com.nashe.siscanino.data.entity.Usuario.SCHEMA.NOMBRE;
import static com.nashe.siscanino.data.entity.Usuario.SCHEMA.TABLE;
import static com.nashe.siscanino.data.entity.Usuario.SCHEMA.TIPO_USUARIO;

@Entity(tableName = TABLE,
        foreignKeys = {@ForeignKey(
                entity = TipoUsuario.class,
                parentColumns = TipoUsuario.SCHEMA.ID,
                childColumns = TIPO_USUARIO,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )},
        indices = {@Index(value = {NOMBRE}, unique = true)})
public class Usuario {
    public interface SCHEMA {
        String TABLE = "Usuario";
        String ID = "id";
        String NOMBRE = "nombre";
        String CONTRASENIA = "contrasenia";
        String TIPO_USUARIO = "id_tipousuario";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NOMBRE)
    private String nombre;
    @ColumnInfo(name = CONTRASENIA)
    private String contrasenia;
    @ColumnInfo(name = TIPO_USUARIO)
    private int tipoUsuario;

    @Ignore
    public Usuario() {
    }

    @Ignore
    public Usuario(int id, String nombre, String contrasenia, int tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String nombre, String contrasenia, int tipoUsuario) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Usuario.class) return false;
        Usuario cast = (Usuario) obj;
        return cast.id == getId() && cast.nombre.equals(getNombre()) && cast.contrasenia.equals(getContrasenia()) && cast.tipoUsuario == getTipoUsuario();
    }
}