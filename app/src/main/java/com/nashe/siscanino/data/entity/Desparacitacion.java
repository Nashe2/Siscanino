package com.nashe.siscanino.data.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.nashe.siscanino.data.entity.Desparacitacion.SCHEMA.*;

@Entity(tableName = TABLE,
        foreignKeys = {@ForeignKey(
                entity = Cartilla.class,
                parentColumns = Cartilla.SCHEMA.ID,
                childColumns = CARTILLA,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )}
)
public class Desparacitacion {
    public interface SCHEMA {
        String TABLE = "Desparacitacion";
        String ID = "id";
        String NAME_PRODUCT = "producto";
        String DOSIS = "dosis";
        //String FECHA = "fecha";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
        String CARTILLA = "cartilla_id";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NAME_PRODUCT)
    private String producto;
    @ColumnInfo(name = DOSIS)
    private String dosis;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;
   // @ColumnInfo(name = FECHA)
    //private Date fecha;
    @ColumnInfo(name = CARTILLA)
    private int cartillaId;

    @Ignore
    public Desparacitacion() {
    }

    @Ignore
    public Desparacitacion(int id, String producto, String dosis, Date created, Date updated, int cartillaId) {
        this.id = id;
        this.producto = producto;
        this.dosis = dosis;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
        this.cartillaId = cartillaId;
    }

    public Desparacitacion(String producto, String dosis, Date created, Date updated, int cartillaId) {
        this.producto = producto;
        this.dosis = dosis;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
        this.cartillaId = cartillaId;
    }

    @Ignore
    public Desparacitacion(String producto, String dosis, Date created, Date updated) {
        this.producto = producto;
        this.dosis = dosis;
        long time = System.currentTimeMillis();
        this.created = new Date(time);
        this.updated = new Date(time);
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

    public int getCartillaId() {
        return cartillaId;
    }

    public void setCartillaId(int cartillaId) {
        this.cartillaId = cartillaId;
    }

    @Override
    public  boolean equals(@Nullable Object obj){
        if (obj==null || obj.getClass() != Desparacitacion.class) return false;
        Desparacitacion casteo= (Desparacitacion) obj;
        return  casteo.id == getId() && casteo.producto.equals(getProducto()) && casteo.dosis.equals(getDosis()) && casteo.created.equals(getCreated()) && casteo.updated.equals(getUpdated()) && casteo.cartillaId == getCartillaId();
    }
}
