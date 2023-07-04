package com.example.handioin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.handioin.RetrofitClient.BaseUrlClass;
import com.example.handioin.RetrofitClient.RetrofitClientInterface;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText evEmail;
    private EditText etPassword;
    private TextView tvOTP;
    private Button Login;
    private TextView tvSignUp;
    SharedPrefManagerA Sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        evEmail = findViewById(R.id.ev_email);
        etPassword = findViewById(R.id.et_password);
        tvOTP = findViewById(R.id.tv_OTP);
        Login = findViewById(R.id.BTN_Login);
        tvSignUp = findViewById(R.id.tv_SignUp);

        Sp = new SharedPrefManagerA(LoginActivity.this);

//        Dexter.withContext(this)
//                .withPermissions(Manifest.permission. ACCESS_COARSE_LOCATION ,Manifest.permission.ACCESS_FINE_LOCATION  , Manifest.permission.ACCESS_NETWORK_STATE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
//
//                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
//                            Toast.makeText(LoginActivity.this, "Yes", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(LoginActivity.this, "1st permission", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
//                        permissionToken.cancelPermissionRequest();
//                    }
//                }).check();

        tvOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, OTP_Email_Activity.class);
                startActivity(i);
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = evEmail.getText().toString().trim();
                String Password = etPassword.getText().toString().trim();
                if (!evEmail.getText().toString().isEmpty()) {
                    if (!etPassword.getText().toString().isEmpty()) {
                        CheckLogin(Email, Password);
                    } else {
                        Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CheckLogin(String Email, String Password) {
        ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage(" Checking... ");
        dialog.show();
        Call<JsonElement> call;
        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(LoginActivity.this).create(RetrofitClientInterface.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", Email);
        hashMap.put("password", Password);
        call = object.userLogin(hashMap);
        call.enqueue(new Callback<JsonElement>() {

            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonObject userInfoModel = response.body().getAsJsonObject();
                JsonPrimitive status = userInfoModel.getAsJsonPrimitive("status");
                JsonPrimitive message = userInfoModel.getAsJsonPrimitive("message");

                if (status.toString().contains("600"))
//                if(status.equals("Login Successfull"))
//                    if(status.toString().contains("600"))
//                        if(Integer.parseInt(status.toString())==600)
//                            if(status.toString().equals("600"))
//                                if(message.equals("Login Successfull"))
//                                    if(status.getAsJsonPrimitive().equals("600"))

                {

                 Sp.setEmail(Email);
                 Sp.setPasswords(Password);

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                    intent.putExtra("email",Email);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                }

//                if (response.isSuccessful()) {
//                    JsonObject object = (JsonObject) response.body();
//                    Log.d("AAAA","Test9"+object);
//                    res=userInfoModel.getAsJsonPrimitive("response");
//                    JsonPrimitive msg=userInfoModel.getAsJsonPrimitive("msg");
//                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(LoginActivity.this, "" + message, Toast.LENGTH_SHORT).show();
//                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
            }
        });
    }
}