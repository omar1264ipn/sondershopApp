package com.sondershop.sondershopapp.detalleVenta.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String titulo;

    @ColumnInfo(name = "last_name")
    public String marca;

    @ColumnInfo(name = "url")
    public String urlImage;

    @ColumnInfo(name = "costo")
    public String costoProducto;

    @ColumnInfo(name = "cantidad")
    public String cantidadArticulos;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getCostoProducto() {
        return costoProducto;
    }

    public void setCostoProducto(String costoProducto) {
        this.costoProducto = costoProducto;
    }

    public String getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(String cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }
}
