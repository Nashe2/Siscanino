package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.nashe.siscanino.data.entity.Canino.SCHEMA.COLOR;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.ID;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.NACIMIENTO;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.NOMBRE;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.PESO;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.RAZA;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.SENIAS;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.SEXO;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.TABLE;
import static com.nashe.siscanino.data.entity.Canino.SCHEMA.TAMANIO;

@Entity(tableName = TABLE)
public class Canino {

    public interface SCHEMA {
        String TABLE = "Canino";
        String ID = "id";
        String NOMBRE = "nombre";
        String NACIMIENTO = "nacimiento";
        String COLOR = "color";
        String SEXO = "sexo";
        String SENIAS = "senias";
        String PESO = "peso";
        String TAMANIO = "tamanio";
        String RAZA = "raza_id";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NOMBRE)
    private String nombre;
    @ColumnInfo(name = NACIMIENTO)
    private Date nacimiento;
    @ColumnInfo(name = COLOR)
    private String color;
    @ColumnInfo(name = SEXO)
    private int sexo;
    @ColumnInfo(name = SENIAS)
    private String senias;
    @ColumnInfo(name = PESO)
    private double peso;
    @ColumnInfo(name = TAMANIO)
    private String tamanio;
    @ColumnInfo(name = RAZA)
    private int razaId;

    @Ignore
    public Canino() {
    }

    @Ignore
    public Canino(int id, String nombre, Date nacimiento, String color, int sexo, String senias, double peso, String tamanio, int razaId) {
        this.id = id;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.color = color;
        this.sexo = sexo;
        this.senias = senias;
        this.peso = peso;
        this.tamanio = tamanio;
        this.razaId = razaId;
    }

    public Canino(String nombre, Date nacimiento, String color, int sexo, String senias, double peso, String tamanio, int razaId) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.color = color;
        this.sexo = sexo;
        this.senias = senias;
        this.peso = peso;
        this.tamanio = tamanio;
        this.razaId = razaId;
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

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getSenias() {
        return senias;
    }

    public void setSenias(String senias) {
        this.senias = senias;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public int getRazaId() {
        return razaId;
    }

    public void setRazaId(int razaId) {
        this.razaId = razaId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Canino.class) return false;
        Canino cast = (Canino) obj;
        return cast.id == getId()
                && cast.nombre.equals(getNombre())
                && cast.nacimiento.equals(getNacimiento())
                && cast.color.equals(getColor())
                && cast.sexo == getSexo()
                && cast.senias.equals(getSenias())
                && cast.peso == getPeso()
                && cast.tamanio.equals(getTamanio())
                && cast.razaId == getRazaId();
    }
}
