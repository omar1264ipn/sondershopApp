package com.sondershop.sondershopapp.models;

public class CategoryModel {

    public CategoryModel() {

    }

    private String cat_title,cat_image;

    public CategoryModel(String cat_title, String cat_image) {
        this.cat_title = cat_title;
        this.cat_image = cat_image;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }

    public String getCat_image() {
        return cat_image;
    }

    public void setCat_image(String cat_image) {
        this.cat_image = cat_image;
    }
}
