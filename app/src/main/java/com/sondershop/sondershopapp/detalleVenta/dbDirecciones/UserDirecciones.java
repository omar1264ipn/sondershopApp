package com.sondershop.sondershopapp.detalleVenta.dbDirecciones;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserDirecciones {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "apellido")
    public String apellido;

    @ColumnInfo(name = "numero")
    public String numero;

    @ColumnInfo(name = "almacena_pais")
    public String almacenaPais;

    @ColumnInfo(name = "distrito")
    public String distrito;

    @ColumnInfo(name = "calle")
    public String calle;

    @ColumnInfo(name = "estado")
    public String estado;

    @ColumnInfo(name = "codigo_postal")
    public String codigoPostal;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAlmacenaPais() {
        return almacenaPais;
    }

    public void setAlmacenaPais(String almacenaPais) {
        this.almacenaPais = almacenaPais;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
