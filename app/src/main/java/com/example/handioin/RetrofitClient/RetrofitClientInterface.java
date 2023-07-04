package com.example.handioin.RetrofitClient;


import androidx.room.Dao;

import com.example.handioin.ModelClass.BlogResponseModal;
import com.example.handioin.ModelClass.HomeAll_itemResponse;
import com.example.handioin.ModelClass.HomeResponseModal;
import com.example.handioin.ModelClass.HomeproductModal;
import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.ModelClass.otpRequestModal;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


@Dao
public interface RetrofitClientInterface {

    // MenuActivity
    @GET("handioProduct")
    Call<List<ProductResponseModal>> getFetchhandioProduct();

    // HomeActivity
    @GET("handioProduct")
    Call<List<HomeResponseModal>> getDataFetchhandioProduct();

    // HomeActivityALL_Item
    @GET("handioProduct")
    Call <List<HomeAll_itemResponse>> getHome_item();

    @GET("Blog")
    Call<BlogResponseModal> getDataBlog();

    @POST("UserRegister")
    Call<JsonElement> getUser(@Body HashMap body);

    @POST("login")
    Call<JsonElement> userLogin(@Body HashMap<String, String> body);

    @FormUrlEncoded
    @POST("Verify")
    Call<otpRequestModal> OTP(@FieldMap HashMap<String, String> body);

//    @GET("handioProduct")
//    Call<ProductResponseModal> getProduct();

//    @POST("Verify")
//    Call<JsonElement> OTP(@Body HashMap<String, String> body);
//    @POST("Verify")
//    Call<JsonElement> OTPEmail(@Body HashMap<String, String> body);
//    @POST("Verify")
//    @FormUrlEncoded
//    Call<otpResponseModal> OTP(@FieldMap Map<String,String> params);

}
