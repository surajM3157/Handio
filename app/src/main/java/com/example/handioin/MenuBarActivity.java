package com.example.handioin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import AdaperClass.Handio.ExpendebleAdapter;
import AdaperClass.Handio.Expendebleclass;


public class MenuBarActivity extends AppCompatActivity {
    private ImageView ivBackArrow;
    private TextView tvEdit;
    private ExpandableListView expandableListView;
    private LinearLayout llAddresses;
    private LinearLayout llHelp;
    private AppCompatButton tvLogOutBTN;
    AlertDialog.Builder builder;
    //    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
    SharedPrefManagerA sp;


    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);


        ivBackArrow = findViewById(R.id.ivBackArrow);
        tvEdit = findViewById(R.id.tv_Edit);
        expandableListView = findViewById(R.id.expandableListView);
        llAddresses = findViewById(R.id.ll_Addresses);
        llHelp = findViewById(R.id.ll_Help);
        tvLogOutBTN = findViewById(R.id.tvLogOutBTN);

//        sharedPreferences = getSharedPreferences("mysharedpref", MODE_PRIVATE);
//        editor = sharedPreferences.edit();
        sp = new SharedPrefManagerA(MenuBarActivity.this);
        /////----------------------------> ExpandableListView start<------------------------////////

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = Expendebleclass.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpendebleAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        /////----------------------------> ExpandableListView End<------------------------////////

        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuBarActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        ////----------------------> Log Out BTN <--------------------////

        builder = new AlertDialog.Builder(this);

        tvLogOutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Do you want to logout? ")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(MenuBarActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Log out");
                alertDialog.show();
                sp.setLogout("");
            }
        });

        /////--------------->  Logout END <---------------///

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuBarActivity.this, UserNameEditActitvty.class);
                startActivity(i);
            }
        });
    }
}