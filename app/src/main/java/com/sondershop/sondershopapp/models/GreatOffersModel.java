package com.sondershop.sondershopapp.models;

public class GreatOffersModel {

    private String shop_id,shop_name,shop_owner_name,addhar_or_pan,pincode,shop_image,city,state,landmark,discount,coupen,description,
            rating,categories,shop_added_date;

    public GreatOffersModel() {
    }

    public GreatOffersModel(String shop_id, String shop_name, String shop_owner_name, String addhar_or_pan, String pincode, String shop_image, String city, String state, String landmark, String discount, String coupen, String description, String rating, String categories, String shop_added_date) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_owner_name = shop_owner_name;
        this.addhar_or_pan = addhar_or_pan;
        this.pincode = pincode;
        this.shop_image = shop_image;
        this.city = city;
        this.state = state;
        this.landmark = landmark;
        this.discount = discount;
        this.coupen = coupen;
        this.description = description;
        this.rating = rating;
        this.categories = categories;
        this.shop_added_date = shop_added_date;
    }

    public String getShop_id() {
        return shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getShop_owner_name() {
        return shop_owner_name;
    }

    public String getAddhar_or_pan() {
        return addhar_or_pan;
    }

    public String getPincode() {
        return pincode;
    }

    public String getShop_image() {
        return shop_image;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getDiscount() {
        return discount;
    }

    public String getCoupen() {
        return coupen;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getCategories() {
        return categories;
    }

    public String getShop_added_date() {
        return shop_added_date;
    }
}
