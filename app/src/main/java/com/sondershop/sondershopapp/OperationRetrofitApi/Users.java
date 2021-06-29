package com.sondershop.sondershopapp.OperationRetrofitApi;

import com.google.gson.annotations.SerializedName;
import com.sondershop.sondershopapp.models.BannerModel;
import com.sondershop.sondershopapp.models.CategoryModel;
import com.sondershop.sondershopapp.models.GreatOffersModel;
import com.sondershop.sondershopapp.models.SimpleVerticalModel;
import com.sondershop.sondershopapp.models.filtroProducModel;
import com.sondershop.sondershopapp.models.productosCategoriaModel;


import java.util.List;

public class Users {

    @SerializedName("response")
    private String Response;

    @SerializedName("id_cliente")
    private String UserId;

    @SerializedName("id_venta")
    private String UserIdventa;

    @SerializedName("nombre")
    private String UserNombre;

    @SerializedName("apellido")
    private String UserApellido;

    @SerializedName("email")
    private String UserCorreo;

    @SerializedName("parametro")
    private String UserFiltro;

    @SerializedName("strip_banner_image")
    private String strip_banner_image;

    @SerializedName("categories")
    private List<CategoryModel> category;

    @SerializedName("banners")
    private List<BannerModel> banners;

    @SerializedName("random_shops") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<SimpleVerticalModel> random_shops; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    @SerializedName("productosFiltrados") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<filtroProducModel> productosFiltrados; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    @SerializedName("productos_categoria") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<productosCategoriaModel> productos_categoria; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    @SerializedName("great_offers_shops") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<GreatOffersModel> great_offers_shops; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    @SerializedName("great_offers_shops_vertical") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<SimpleVerticalModel> great_offers_shops_vertical; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    @SerializedName("new_arrivals_shops") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<GreatOffersModel> new_arrivals_shops; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    @SerializedName("new_arrivals_shops_vertical") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<SimpleVerticalModel> new_arrivals_shops_vertical; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    @SerializedName("8mm_exclusive") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<GreatOffersModel> eightmm_exclusive; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    @SerializedName("8mm_exclusive_vertical") //aqui va el nombre del nodo dque viene de la informacion de la apirest
    private List<SimpleVerticalModel> eightmm_exclusive_vertical; //en la variable instanciada va el nombre del nodo que viene de la apirest para reconcerla mas facilmente

    public String getUserFiltro() {
        return UserFiltro;
    }

    public String getResponse() {
        return Response;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserNombre() {
        return UserNombre;
    }

    public String getUserApellido() {
        return UserApellido;
    }

    public String getUserCorreo() {
        return UserCorreo;
    }

    public String getUserIdventa() {
        return UserIdventa;
    }

    public String getStrip_banner_image() {
        return strip_banner_image;
    }

    public List<CategoryModel> getCategory() {
        return category;
    }

    public List<BannerModel> getBanners() {
        return banners;
    }

    public List<productosCategoriaModel> getProductos_categoria() {
        return productos_categoria;
    }

    public List<SimpleVerticalModel> getRandom_shops() {
        return random_shops;
    }

    public List<filtroProducModel> getProductosFiltrados() {
        return productosFiltrados;
    }


    public List<GreatOffersModel> getGreat_offers_shops() {
        return great_offers_shops;
    }

    public List<SimpleVerticalModel> getGreat_offers_shops_vertical() {
        return great_offers_shops_vertical;
    }

    public List<GreatOffersModel> getNew_arrivals_shops() {
        return new_arrivals_shops;
    }

    public List<SimpleVerticalModel> getNew_arrivals_shops_vertical() {
        return new_arrivals_shops_vertical;
    }

    public List<GreatOffersModel> getEightmm_exclusive() {
        return eightmm_exclusive;
    }

    public List<SimpleVerticalModel> getEightmm_exclusive_vertical() {
        return eightmm_exclusive_vertical;
    }
}
