package com.sondershop.sondershopapp.OperationRetrofitApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //email registro////////////////
    @GET("users/email_registration.php")
    Call<Users> performEmailRegistration(
            @Query("nombre") String user_name,
            @Query("email") String user_email,
            @Query("apellido") String user_apellido,
            @Query("telefono") String user_telefono,
            @Query("activacion") Integer estado,
            @Query("password") String user_password

    );

    //registra tabla venta////////////////
    @GET("users/registra_pagocontra.php")
    Call<Users> performRegistraPagoEntrega(
            @Query("id_cliente") String user_idCliente,
            @Query("total") String user_total,
            @Query("fecha") String user_fecha,
            @Query("metodo_pago") String metodo_pago,
            @Query("pagado") String user_pagado,
            @Query("pais") String user_pais,
            @Query("company") String user_distrito,
            @Query("direccion") String user_calle,
            @Query("estado") String user_estado,
            @Query("cp") String user_codigopostal,

            @Query("id_producto") String user_id_producto,
            @Query("id_proveedor") String user_id_proveedor,
            @Query("cantidad") String user_cantidad,
            @Query("precio") String user_precio,
            @Query("subtotal") String user_subtotal,
            @Query("logistica_sonder") String user_logistica_sonder,
            @Query("status") String user_status
    );

    //email login////////////////
    @GET("users/email_login.php")
    Call<Users> performEmailLogin(
            @Query("email") String user_email,
            @Query("password") String user_password
    );

    //phone registro////////////////
    @GET("users/phone_registration.php")
    Call<Users> performPhoneRegistration(
            @Query("user_phone") String user_phone
    );

    //phone login////////////////
    @GET("users/phone_login.php")
    Call<Users> performPhoneLogin(
            @Query("user_phone") String user_phone
    );

    //get de todas las categorias
    @GET("api/categories.php")
    Call<Users> getCategories();

    //get de los banners
    @GET("api/banners.php")
    Call<Users> getBanners();

    //get para el banner principal
    @GET("api/strip_banners.php")
    Call<Users> getStripBanners();

    //get para obtener los productos con el nombre random_shops
    @GET("api/random_shops.php")
    Call<Users> getRandomShops();

    //get para obtener los productos filtrados por categoria
    @GET("api/productosCategoria.php")
    Call<Users> getProductosCategoria();

    //get para obtener grandes ofertas en los productos
    @GET("api/great_offers_shops.php")
    Call<Users> greatOffersShop();

    //get para obtener grandes ofertas en los productos vertical
    @GET("api/great_offers_shops_vertical.php")
    Call<Users> greatOffersVerticalShop();

    //get para obtener productos por llegar
    @GET("api/new_arrivals_shops.php")
    Call<Users> newArrivalsShops();

    //get para obtener productos por llegar vertical
    @GET("api/new_arrivals_shops_vertical.php")
    Call<Users> newArrivalsVerticalShops();

    //get para obtener productos por llegar
    @GET("api/eightmm_exclusive.php")
    Call<Users> eightmmExclusive();

    //get para obtener productos por llegar vertical
    @GET("api/eightmm_exclusive_vertical.php")
    Call<Users> eightmm_exclusive_vertical();
}
