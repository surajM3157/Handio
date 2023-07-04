package com.example.handioin.RetrofitClient.InternetClass;

import java.io.IOException;

public class NointernetClass extends IOException {

    @Override
    public String getMessage() {
        return "No Internet Connection";
        // You can send any message whatever you want from here.
    }
}