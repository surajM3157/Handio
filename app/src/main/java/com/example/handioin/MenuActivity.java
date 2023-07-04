package com.example.handioin;

import static AdaperClass.Handio.AdaterClass.myviewholder.button;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handioin.ModelClass.HomeResponseModal;
import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.ModelClass.productRequestModal;
import com.example.handioin.Respository.ProductRespository;
import com.example.handioin.RetrofitClient.BaseUrlClass;
import com.example.handioin.RetrofitClient.RetrofitClientInterface;
import com.example.handioin.ViewModal.ProductViewModal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AdaperClass.Handio.AdaterClass;
import AdaperClass.Handio.HomeAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity implements AdaterClass.ShopClickListeners {
    private ImageView backArrow, iv_card;
    private BottomNavigationView navigation;
    private RecyclerView rvHandioProduct;
    public static TextView tvItem, tvTotalPrize;
    private ProductViewModal viewModal;

    private ProductRespository productRespository;
    private List<productRequestModal> productRequest_Item = new ArrayList<>();
    private List<ProductResponseModal> productResponse_Card = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private ArrayList<ProductResponseModal> arrayList;
    private AdaterClass adaterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        backArrow = findViewById(R.id.backArrow);
        iv_card = findViewById(R.id.iv_card);
        arrayList = new ArrayList<>();
        rvHandioProduct = findViewById(R.id.rvHandioProduct);
        rvHandioProduct.setHasFixedSize(true);
        rvHandioProduct.setLayoutManager(new LinearLayoutManager(this));
        rvHandioProduct.setItemAnimator(new DefaultItemAnimator());
        productRespository = new ProductRespository(getApplication());
        adaterClass = new AdaterClass(MenuActivity.this, arrayList);
        adaterClass.shopItemAdapter(MenuActivity.this);
        HandioMenu();

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        iv_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, Food_Detail.class);
                startActivity(i);
            }
        });


        viewModal = new ViewModelProvider(MenuActivity.this).get(ProductViewModal.class);
        viewModal.getGetAllProduct().observe(MenuActivity.this, new Observer<List<ProductResponseModal>>() {
            @Override
            public void onChanged(List<ProductResponseModal> list) {
                adaterClass.setData(list);
                rvHandioProduct.setAdapter(adaterClass);
            }
        });
    }

    private void HandioMenu() {
        Call<List<ProductResponseModal>> call;
        ProgressDialog dialog = new ProgressDialog(MenuActivity.this);
        dialog.setMessage(" Loading... ");
        dialog.show();
        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(MenuActivity.this).create(RetrofitClientInterface.class);
        call = object.getFetchhandioProduct();
        Log.d("alex", "callres" + call.toString());
        call.enqueue(new Callback<List<ProductResponseModal>>() {
            @Override
            public void onResponse(Call<List<ProductResponseModal>> call, Response<List<ProductResponseModal>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MenuActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                } else {

                    List<ProductResponseModal> productModel = response.body();
                    if (productModel != null) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MenuActivity.this, LinearLayoutManager.VERTICAL, false);
                        rvHandioProduct.setLayoutManager(linearLayoutManager);
                        adaterClass.setData(response.body());
                        rvHandioProduct.setAdapter(adaterClass);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<ProductResponseModal>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(MenuActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MenuActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAddToCartBtnClicked(ProductResponseModal requestModal) {
        button.setVisibility(View.GONE);
        ProductResponseModal respo = new ProductResponseModal();
        respo.setProductId(requestModal.getpId());
        respo.setProductName(requestModal.getProductName());
        respo.setProductPrice(requestModal.getProductPrice());
        respo.setProductImage(String.valueOf(requestModal.getProductImage()));


        int quantity = 1;

        if (!productResponse_Card.isEmpty()) {
            for (int i = 0; i < productResponse_Card.size(); i++) {
                if (respo.getProductId() == productResponse_Card.get(i).getProductId()) {
                    quantity = Integer.parseInt(productResponse_Card.get(i).getQuantity());
                    quantity++;
//                    productResponse_Card.get(i).setQuantity(String.valueOf(quantity));
                }
            }
        }

        if (quantity == 1) {
            respo.setQuantity(String.valueOf(quantity));
            respo.setTotalItemPrice(String.valueOf(quantity * respo.getProductPrice()));
            productResponse_Card.add(respo);
            viewModal.insertList(productResponse_Card);
        } else {
            viewModal.updateQuantity(respo.getProductId(), quantity);
            viewModal.updateQuantity(respo.getProductId(), quantity * respo.getProductPrice());
        }

        makeSnackBar("Add to card");

    }

    private void makeSnackBar(String M) {
        Intent i = new Intent(MenuActivity.this, cardActivity.class);
        startActivity(i);
        finish();


        // startActivity(new Intent(MenuActivity.this, cardActivity.class));
    }

}


 /*   private void makeSnackBar(String Mssg) {
        Snackbar.make(coordinatorLayout, Mssg, Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
        startActivity(new Intent(MenuActivity.this, cardActivity.class));
                    }
                }).show();
   }
} */
