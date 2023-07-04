package com.example.handioin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handioin.RetrofitClient.BaseUrlClass;
import com.example.handioin.RetrofitClient.RetrofitClientInterface;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTP_Email_Activity extends AppCompatActivity {
    private EditText evEmail;
    private Button BTNOtp;
    private TextView tvAlreadyLogin;
    int code ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        evEmail = (EditText) findViewById(R.id.ev_Email);
        BTNOtp = (Button) findViewById(R.id.BTN_otp);
        tvAlreadyLogin = (TextView) findViewById(R.id.tv_alreadyLogin);

        tvAlreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OTP_Email_Activity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        BTNOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(OTP_Email_Activity.this,OTPActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    public void sendVerifyEmail(View view) {
        Random random = new Random();
        code = random.nextInt(8999)+1000;
        evEmail = (EditText) findViewById(R.id.ev_Email);

        Call<JsonElement> call;
        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(OTP_Email_Activity.this).create(RetrofitClientInterface.class);
        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("etNo1", Edit1);
//        call = object.OTPEmail(hashMap);
//        Log.d("ss122", "Test1");
//        call.enqueue(new Callback<JsonElement>() {
//            @Override
//            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//                Log.d("ss122", "Test2" + response);
//                JsonObject json = response.body().getAsJsonObject();
//                Log.d("ss122", "Test3" + json);
//                JsonPrimitive status = json.getAsJsonPrimitive("status");
//                JsonPrimitive message = json.getAsJsonPrimitive("message");
//
//                if (status.toString().contains("200")) {
//
//                    Intent intent = new Intent(OTP_Email_Activity.this, HomeActivity.class);
//                    Toast.makeText(OTP_Email_Activity.this, "" +message, Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(OTP_Email_Activity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<JsonElement> call, Throwable t) {
//            }
//        });

    }
}