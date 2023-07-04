package com.example.handioin.RetrofitClient;

import android.content.Context;

import com.example.handioin.RetrofitClient.InternetClass.InternetInterseptClass;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseUrlClass {
    public static final String BaseUrl = "https://handio.in/public/api/";
    private static Retrofit retrofit = null;
    public static Retrofit getRetrofitClient(Context mContext) {
        if (retrofit == null) {
            OkHttpClient.Builder oktHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(new InternetInterseptClass(mContext));
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            oktHttpClient.addInterceptor(interceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient.build())
                    .build();
        }
        return retrofit;
    }
}
