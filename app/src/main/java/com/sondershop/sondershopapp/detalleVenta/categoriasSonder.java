package com.sondershop.sondershopapp.detalleVenta;

public class categoriasSonder {

    private String nombreCategoria;
    private int imgCategoria;

    public categoriasSonder() {
    }

    public categoriasSonder(String nombreCategoria, int imgCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.imgCategoria = imgCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getImgCategoria() {
        return imgCategoria;
    }

    public void setImgCategoria(int imgCategoria) {
        this.imgCategoria = imgCategoria;
    }
}
