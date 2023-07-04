package com.example.handioin.ModelClass;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class productRequestModal  implements Parcelable{
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

    private String productPrice;
    @ColumnInfo(name = "quantity")
    private String quantity;
    @ColumnInfo(name = "totalItemPrice")
    private String totalItemPrice;



    public productRequestModal(Integer pId, Integer catId, String productName, String category, String productPrice, String location, String price, String productImage, String pDescription, String status, String secondDiscription2, String discount, String updatedAt, String createdAt) {

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

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("price")
    @Expose
    private String price;

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


    protected productRequestModal(Parcel in) {
        productName = in.readString();
        productImage = in.readString();
        productPrice = in.readString();
        quantity = in.readString();
        totalItemPrice = in.readString();
        if (in.readByte() == 0) {
            pId = null;
        } else {
            pId = in.readInt();
        }
        if (in.readByte() == 0) {
            catId = null;
        } else {
            catId = in.readInt();
        }
        category = in.readString();
        location = in.readString();
        price = in.readString();
        pDescription = in.readString();
        status = in.readString();
        secondDiscription2 = in.readString();
        discount = in.readString();
        updatedAt = in.readString();
        createdAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productImage);
        dest.writeString(productPrice);
        dest.writeString(quantity);
        dest.writeString(totalItemPrice);
        if (pId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(pId);
        }
        if (catId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(catId);
        }
        dest.writeString(category);
        dest.writeString(location);
        dest.writeString(price);
        dest.writeString(pDescription);
        dest.writeString(status);
        dest.writeString(secondDiscription2);
        dest.writeString(discount);
        dest.writeString(updatedAt);
        dest.writeString(createdAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<productRequestModal> CREATOR = new Creator<productRequestModal>() {
        @Override
        public productRequestModal createFromParcel(Parcel in) {
            return new productRequestModal(in);
        }

        @Override
        public productRequestModal[] newArray(int size) {
            return new productRequestModal[size];
        }
    };

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
