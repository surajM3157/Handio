package com.example.handioin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UserNameEditActitvty extends AppCompatActivity {

    private LinearLayout llAccount;
    private ImageView ivBackArrow;
    private EditText etName;
    private EditText etNumber;
    private EditText etEmailAddress;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_edit_actitvty);

        llAccount = findViewById(R.id.llAccount);
        ivBackArrow = findViewById(R.id.iv_back_arrow);
        etName = findViewById(R.id.et_Name);
        etNumber = findViewById(R.id.etNumber);
        etEmailAddress = findViewById(R.id.et_emailAddress);

        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MenuBarActivity.class);
                startActivity(i);
            }
        });



    }
}