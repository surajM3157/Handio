package com.example.handioin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.ModelClass.productRequestModal;
import com.example.handioin.ViewModal.ProductViewModal;

import java.util.List;

import AdaperClass.Handio.OrderProductAdapter;

public class Food_Detail extends AppCompatActivity {

    private CardView CardContainer1;
    private LinearLayout llContainer;
    private TextView tvBestseller;
    private ImageView ivImg;
    private TextView tvHandiMushuroom;
    private TextView tvPrize;
    private TextView tvDiscription;
    private LinearLayout llBTN;
    private AppCompatButton increment;
    private TextView etNumber;
    private AppCompatButton decrement;
    private AppCompatButton CardButton;
    private ProductViewModal viewModal;
    private List<ProductResponseModal> responseModal;
    private productRequestModal RequestModal;
    private OrderProductAdapter adapter;

    int count_No;
    int count;
    int TotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        RequestModal = getIntent().getParcelableExtra("ProductItem");
//        initializeVariables();

//        viewModal.getGetAllProduct().observe(this, new Observer<List<ProductResponseModal>>() {
//            @Override
//            public void onChanged(List<ProductResponseModal> productResponseModals) {
//                responseModal.addAll(productResponseModals);
//            }
//        });
//
//        if (RequestModal != null) {
//           setDataToWidgets();
//        }
//
//        increment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count++;
//            }
//        });
//        decrement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count--;
//            }
//        });
//        CardButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                insertToRoom();
//            }
//
//            private void insertToRoom() {
//                ProductResponseModal responseM = new ProductResponseModal();
//                responseM.setProductName(RequestModal.getProductName());
//                responseM.setProductPrice(Integer.parseInt(RequestModal.getPrice()));
//                responseM.setPrice(RequestModal.getPrice());
//                responseM.setProductImage(String.valueOf(RequestModal.getProductImage()));
//
//                final int[] quantity = {1};
//                final int[] productId = new int[1];
//
//                if (!responseModal.isEmpty()) {
//                    for (int i = 0; i < responseModal.size(); i++) {
//                        if (responseM.getProductName().equals(responseModal.get(i).getProductName())) {
//                            quantity[0] = Integer.parseInt(responseModal.get(i).getQuantity());
//                            quantity[0]++;
//                            productId[0] = responseModal.get(i).getProductId();
//                        }
//                    }
//                }
//                if (quantity[0] == 1) {
//                    responseM.setQuantity(String.valueOf(quantity[0]));
//                    responseM.setTotalItemPrice(String.valueOf(responseM.getProductPrice()));
//                    viewModal.insertList(responseModal);
//                } else {
//                    viewModal.updateQuantity(productId[0], quantity[0]);
//                    viewModal.updateQuantity(productId[0], quantity[0] * Integer.parseInt(responseM.getProductPrice().toString()));
//                }
//                startActivity(new Intent(Food_Detail.this, cardActivity.class));
//            }
//        });
//    }
//
//
//    private void setDataToWidgets() {
//        tvHandiMushuroom.setText(RequestModal.getProductName());
//        tvPrize.setText(String.valueOf(RequestModal.getPrice()));
//        tvDiscription.setText(RequestModal.getpDescription());
//        Glide.with(this).load(getIntent().getData()).placeholder(R.drawable.image1).into(ivImg);
//    }
//    private void initializeVariables() {
//        CardContainer1 = (CardView) findViewById(R.id.CardContainer1);
//        llContainer = (LinearLayout) findViewById(R.id.llContainer);
//        tvBestseller = (TextView) findViewById(R.id.tv_Bestseller);
//        ivImg = (ImageView) findViewById(R.id.iv_img);
//        tvHandiMushuroom = (TextView) findViewById(R.id.tvHandiMushuroom);
//        tvPrize = (TextView) findViewById(R.id.tvPrize);
//        tvDiscription = (TextView) findViewById(R.id.tvDiscription);
//        llBTN = (LinearLayout) findViewById(R.id.llBTN);
//        increment = (AppCompatButton) findViewById(R.id.increment);
//        etNumber = (TextView) findViewById(R.id.et_Number);
//        decrement = (AppCompatButton) findViewById(R.id.decrement);
//        CardButton = (AppCompatButton) findViewById(R.id.Card_button);
//        viewModal = new ViewModelProvider(this).get(ProductViewModal.class);
   }
}