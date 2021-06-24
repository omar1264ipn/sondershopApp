package com.sondershop.sondershopapp.models;

public class productosCategoriaModel {

    public productosCategoriaModel() {
    }

    private int id_usuario,id_producto;

    private String garantia;

    private String unidad,shop_id,shop_name, presentacion, descripcion_detallada, sku,shop_image,city,state, marca, costoEnvio, precioProducto,description,
            rating,categories,shop_added_date,pro_imagen2, pro_imagen3, pro_imagen4,modelo,estado_producto,especificaciones,pais_origen,nombre_proveedor;

    public productosCategoriaModel(int id_usuario, int id_producto, String garantia, String unidad, String shop_id, String shop_name, String presentacion, String descripcion_detallada, String sku, String shop_image, String city, String state, String marca, String costoEnvio, String precioProducto, String description, String rating, String categories, String shop_added_date, String pro_imagen2, String pro_imagen3, String pro_imagen4, String modelo, String estado_producto, String especificaciones, String pais_origen, String nombre_proveedor) {
        this.id_usuario = id_usuario;
        this.id_producto = id_producto;
        this.garantia = garantia;
        this.unidad = unidad;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.presentacion = presentacion;
        this.descripcion_detallada = descripcion_detallada;
        this.sku = sku;
        this.shop_image = shop_image;
        this.city = city;
        this.state = state;
        this.marca = marca;
        this.costoEnvio = costoEnvio;
        this.precioProducto = precioProducto;
        this.description = description;
        this.rating = rating;
        this.categories = categories;
        this.shop_added_date = shop_added_date;
        this.pro_imagen2 = pro_imagen2;
        this.pro_imagen3 = pro_imagen3;
        this.pro_imagen4 = pro_imagen4;
        this.modelo = modelo;
        this.estado_producto = estado_producto;
        this.especificaciones = especificaciones;
        this.pais_origen = pais_origen;
        this.nombre_proveedor = nombre_proveedor;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDescripcion_detallada() {
        return descripcion_detallada;
    }

    public void setDescripcion_detallada(String descripcion_detallada) {
        this.descripcion_detallada = descripcion_detallada;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getShop_image() {
        return shop_image;
    }

    public void setShop_image(String shop_image) {
        this.shop_image = shop_image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(String costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getShop_added_date() {
        return shop_added_date;
    }

    public void setShop_added_date(String shop_added_date) {
        this.shop_added_date = shop_added_date;
    }

    public String getPro_imagen2() {
        return pro_imagen2;
    }

    public void setPro_imagen2(String pro_imagen2) {
        this.pro_imagen2 = pro_imagen2;
    }

    public String getPro_imagen3() {
        return pro_imagen3;
    }

    public void setPro_imagen3(String pro_imagen3) {
        this.pro_imagen3 = pro_imagen3;
    }

    public String getPro_imagen4() {
        return pro_imagen4;
    }

    public void setPro_imagen4(String pro_imagen4) {
        this.pro_imagen4 = pro_imagen4;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getPais_origen() {
        return pais_origen;
    }

    public void setPais_origen(String pais_origen) {
        this.pais_origen = pais_origen;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }
}
