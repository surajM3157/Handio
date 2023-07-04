package com.example.handioin.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponseModal {
    public HomeResponseModal(Integer pId, String productName, String category, String productPrice, String location, Object productPricePerkg, String productImage, String pDescription, String secondDiscription2, String deliverycharges, String updatedAt, String createdAt) {
        this.pId = pId;
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
        this.location = location;
        this.productPricePerkg = productPricePerkg;
        this.productImage = productImage;
        this.pDescription = pDescription;
        this.secondDiscription2 = secondDiscription2;
        this.deliverycharges = deliverycharges;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    @SerializedName("p_id")
    @Expose
    private Integer pId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("product_price_perkg")
    @Expose
    private Object productPricePerkg;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("p_description")
    @Expose
    private String pDescription;
    @SerializedName("secondDiscription2")
    @Expose
    private String secondDiscription2;
    @SerializedName("deliverycharges")
    @Expose
    private String deliverycharges;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getProductPricePerkg() {
        return productPricePerkg;
    }

    public void setProductPricePerkg(Object productPricePerkg) {
        this.productPricePerkg = productPricePerkg;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getSecondDiscription2() {
        return secondDiscription2;
    }

    public void setSecondDiscription2(String secondDiscription2) {
        this.secondDiscription2 = secondDiscription2;
    }

    public String getDeliverycharges() {
        return deliverycharges;
    }

    public void setDeliverycharges(String deliverycharges) {
        this.deliverycharges = deliverycharges;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
