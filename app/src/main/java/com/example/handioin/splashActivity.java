package com.example.handioin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    private ImageView ivLogo;
    SharedPrefManagerA sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivLogo = findViewById(R.id.iv_logo);
        sharedPrefManager = new SharedPrefManagerA(splashActivity.this);
        String id = sharedPrefManager.getEmail();
//        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(splashActivity.this, StartLocationActivity.class));
//                finish();
//            }
//        }, 3500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id.equals("")) {
                    Intent intent = new Intent(splashActivity.this, StartLocationActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Intent mainIntent = new Intent(splashActivity.this, HomeActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, 1500);
    }
}