package com.example.handioin.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeAll_itemResponse {

    public HomeAll_itemResponse(Integer pId, Integer catId, String productName, String category, String productPrice, String location, String price, String productImage, String pDescription, String status, String secondDiscription2, String discount, String updatedAt, String createdAt) {
        this.pId = pId;
        this.catId = catId;
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
        this.location = location;
        this.price = price;
        this.productImage = productImage;
        this.pDescription = pDescription;
        this.status = status;
        this.secondDiscription2 = secondDiscription2;
        this.discount = discount;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    @SerializedName("p_id")
    @Expose
    private Integer pId;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
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
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("p_description")
    @Expose
    private String pDescription;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("secondDiscription2")
    @Expose
    private String secondDiscription2;
    @SerializedName("discount")
    @Expose
    private String discount;
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

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecondDiscription2() {
        return secondDiscription2;
    }

    public void setSecondDiscription2(String secondDiscription2) {
        this.secondDiscription2 = secondDiscription2;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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