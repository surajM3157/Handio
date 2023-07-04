package com.example.handioin;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManagerA {
    SharedPreferences sharedPreferences;
    Context context;
    private String usename, passwords, otp, Logout;

    public SharedPrefManagerA(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences("login_details", Context.MODE_PRIVATE);
    }

    public String getEmail() {
        usename = sharedPreferences.getString("usename", "");
        return usename;
    }


    public void setEmail(String usename) {
        this.usename = usename;
        sharedPreferences.edit().putString("usename", usename).commit();
    }


    public String getPasswords() {
        passwords = sharedPreferences.getString("passwords", "");
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
        sharedPreferences.edit().putString("passwords", passwords).commit();
    }

    public String getLogout() {
        Logout = sharedPreferences.getString("usename", "");
        return Logout;
    }

    public String setLogout(String logout) {
        this.usename = usename;
        sharedPreferences.edit().putString("usename", usename).clear().commit();
        return logout;
//        Logout.clear();
//        Logout.commit();


    }
}

