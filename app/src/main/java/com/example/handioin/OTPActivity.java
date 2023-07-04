package com.example.handioin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.handioin.ModelClass.otpRequestModal;
import com.example.handioin.RetrofitClient.BaseUrlClass;
import com.example.handioin.RetrofitClient.RetrofitClientInterface;

import java.util.HashMap;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OTPActivity extends AppCompatActivity {


    private EditText etNo1;
    private EditText etNo2;
    private EditText etNo3;
    private EditText etNo4;
    private Button BTNVerifyOtp;
    private Button BTNResendOTP;
    private Timer Timer;
    int code;
    String email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optactivity);


        etNo1 = (EditText) findViewById(R.id.et_no1);
        etNo2 = (EditText) findViewById(R.id.et_no2);
        etNo3 = (EditText) findViewById(R.id.et_no3);
        etNo4 = (EditText) findViewById(R.id.et_no4);
        BTNVerifyOtp = (Button) findViewById(R.id.BTN_verify_otp);
        BTNResendOTP = (Button) findViewById(R.id.BTN_resend_OTP);

        Intent intent = getIntent();
        if (intent != null) {
            email = intent.getStringExtra("email");
        }
        etNo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() < 0) {
                    etNo2.requestFocus();
                }
//                Toast.makeText(OTPActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        });
        etNo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 0) {
                    etNo3.requestFocus();
                }
            }
        });
        etNo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 0) {
                    etNo4.requestFocus();
                }
            }
        });

        BTNVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputCode;
                inputCode = etNo1.getText().toString() + etNo2.getText() + etNo3.getText() + etNo4.getText();
                if (!inputCode.isEmpty()) {
                    checkCode(inputCode);
                } else {
                    Toast.makeText(OTPActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                }
//                String Edit1 = etNo1.getText().toString();
//                String Edit2 = etNo1.getText().toString();
//                String Edit3 = etNo1.getText().toString();
//                String Edit4 = etNo1.getText().toString();
            }
        });
    }

//        authentication1(Edit1,Edit2,Edit3,Edit4);
//        String inputCode;
//        inputCode = etNo1.getText().toString() + etNo2.getText().toString() + etNo3.getText().toString() + etNo4.getText().toString();
//        if (inputCode.equals(String.valueOf(code))) {
//            Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
//        }

//    private TimerTask timerTask =new TimerTask() {
//        @Override
//        public void run() {
//            authentication();
//            start();
//        }
//        private void start() {
//            if (Timer!=null){
//                return;
//            }
//            timer = new Timer();
//            timerTask.scheduledExecutionTime(timerTask,0,1000);
//        }
//    }

    private void checkCode(String inputCode) {
//        Random random = new Random();
//        code = random.nextInt(8999) + 1000;
//        Log.d("ss122", "Test");
        Call<otpRequestModal> call;
        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(OTPActivity.this).create(RetrofitClientInterface.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("otp", inputCode);

        call = object.OTP(hashMap);
        Log.d("ss122", "Test1");
        call.enqueue(new Callback<otpRequestModal>() {
            @Override
            public void onResponse(Call<otpRequestModal> call, Response<otpRequestModal> response) {
                Log.d("ss122", "Test2" + response);
                otpRequestModal json = response.body();
                Toast.makeText(OTPActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    Toast.makeText(OTPActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OTPActivity.this, HomeActivity.class);
//                    String data;
//                    Bundle extras = intent.getExtras();
//                    if (extras != null)
//                        data = getIntent().getExtras().getString("email");
                    startActivity(intent);
                } else {
                    Toast.makeText(OTPActivity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<otpRequestModal> call, Throwable t) {
                Toast.makeText(OTPActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
