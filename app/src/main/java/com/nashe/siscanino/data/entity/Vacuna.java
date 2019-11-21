package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.nashe.siscanino.data.entity.Vacuna.SCHEMA.*;

@Entity(tableName = TABLE,
        foreignKeys = {@ForeignKey(
                entity = Cartilla.class,
                parentColumns = Cartilla.SCHEMA.ID,
                childColumns = CARTILLA,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )}
)

public class Vacuna {
    public interface SCHEMA{
        String TABLE="Vacuna";
        String ID="id";
        String PRODUCTO="producto";
        String DOSIS="dosis";
        String FECHA="fecha";
        String CARTILLA="cartilla_id";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = PRODUCTO)
    private String producto;
    @ColumnInfo(name = DOSIS)
    private String dosis;
    @ColumnInfo(name = FECHA)
    private Date fecha;
    @ColumnInfo(name = CARTILLA)
    private  int cartillaId;

    @Ignore
    public Vacuna() {
    }

    @Ignore
    public Vacuna(int id, String producto, String dosis, Date fecha, int cartillaId) {
        this.id = id;
        this.producto = producto;
        this.dosis = dosis;
        this.fecha = fecha;
        this.cartillaId = cartillaId;
    }

    public Vacuna(String producto, String dosis, Date fecha, int cartillaId) {
        this.producto = producto;
        this.dosis = dosis;
        this.fecha = fecha;
        this.cartillaId = cartillaId;
    }

    @Ignore
    public Vacuna(String producto, String dosis, Date fecha) {
        this.producto = producto;
        this.dosis = dosis;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCartillaId() {
        return cartillaId;
    }

    public void setCartillaId(int cartillaId) {
        this.cartillaId = cartillaId;
    }

    @Override
    public  boolean equals(@Nullable Object obj){
        if (obj==null || obj.getClass() != Vacuna.class)
            return false;

        Vacuna casteo= (Vacuna) obj;
        return  casteo.id == getId() && casteo.producto.equals(getProducto()) && casteo.dosis.equals(getDosis()) && casteo.fecha.equals(getFecha()) && casteo.cartillaId == getCartillaId();
    }
}
