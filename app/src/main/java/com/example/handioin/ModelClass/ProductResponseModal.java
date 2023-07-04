package com.example.handioin.ModelClass;


import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.handioin.DBHelper.GithubTypeConverters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "productRequest")
public class ProductResponseModal {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    private int productId;
    @ColumnInfo(name = "productName")
    @SerializedName("product_name")
    @Expose

    private String productName;
    @ColumnInfo(name = "productImage")
    @SerializedName("product_image")
    @Expose

    private String productImage;
    @ColumnInfo(name = "productPrice")
    @SerializedName("product_price")
    @Expose

    private Integer productPrice;
    @ColumnInfo(name = "quantity")
    private String quantity;
    @ColumnInfo(name = "totalItemPrice")
    private String totalItemPrice;


    @ColumnInfo(name = "p_id")
    @SerializedName("p_id")
    @Expose
    public Integer pId;


    @SerializedName("cat_id")
    @Expose
    private Integer catId;


    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("price")
    @Expose
    public String price;

    @SerializedName("p_description")
    @Expose
    public String pDescription;
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



    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(String totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
