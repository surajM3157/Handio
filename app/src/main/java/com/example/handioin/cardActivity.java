package com.example.handioin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.ModelClass.productRequestModal;
import com.example.handioin.ViewModal.ProductViewModal;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import AdaperClass.Handio.OrderProductAdapter;

public class cardActivity extends AppCompatActivity implements OrderProductAdapter.CardClickListener {
    private LinearLayout llRedBg;
    private ImageView backArrow;
    private TextInputEditText EvLocation;
    private RecyclerView cartRecyclerView;
    private BottomNavigationView navigation;
    private TextView tvTotalPrize;
    private TextView tvProcessPay, tvRS;
    private ProductViewModal viewModal;
    private ArrayList array;
    private OrderProductAdapter adapter;
    private List<ProductResponseModal> productresponse;
    private List<ProductResponseModal> productrespons;


    double latitude, longitude;
    private LocationRequest locationRequest;
    private final boolean isContinue = false;
    private final boolean isGPS = false;
    private static final String TAG = "alex";
    private final Boolean flag = false;
    private static final int REQUEST_LOCATION = 1;
    private Geocoder mGeocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        llRedBg = (LinearLayout) findViewById(R.id.llRedBg);
        backArrow = (ImageView) findViewById(R.id.backArrow);
        EvLocation = (TextInputEditText) findViewById(R.id.Ev_Location);
        cartRecyclerView = (RecyclerView) findViewById(R.id.cartRecyclerView);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        tvTotalPrize = (TextView) findViewById(R.id.tv_TotalPrize);
        tvProcessPay = (TextView) findViewById(R.id.tvProcessPay);
        tvRS = (TextView) findViewById(R.id.tv_RS);
        productrespons = new ArrayList<>();

        adapter = new OrderProductAdapter(cardActivity.this, productrespons);

//        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview, mobileArray);
//        ListView listView = (ListView) findViewById(R.id.mobile_list);
//        listView.setAdapter(adapter);

        viewModal = new ViewModelProvider(this).get(ProductViewModal.class);
        viewModal.getGetAllProduct().observe(this, new Observer<List<ProductResponseModal>>() {
            @Override
            public void onChanged(List<ProductResponseModal> productResponseModals) {
                double price = 0;
                adapter.notifyDataSetChanged();
                adapter.OrderAdapter(productResponseModals);
         //       Toast.makeText(cardActivity.this, "gfdgfdsf" + productResponseModals.get(0).getProductName(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i <= productResponseModals.size(); i++) {
                }
                //          tvTotalPrize.setText(String.valueOf(price));
            }
        });

//        viewModal.getGetAllProduct().observe(cardActivity.this, new Observer<List<ProductResponseModal>>() {
//            @Override
//            public void onChanged(List<ProductResponseModal> list) {
//                for (int i = 0; i < list.size(); i++) {
//                    List<productRequestModal> data = list.get(i).getArray();
//                }
//                adapter.setAdapter(data);
//            }
//        });

        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(adapter);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
        getCurrentLocation();

        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cardActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


//    private void deleteItem(int position) {
//        mDataSet.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, mDataSet.size());
//        holder.itemView.setVisibility(View.GONE);
//    }

    //------------------>  Adapter Class Used Start <-----------------//
    @Override
    public void noDeleteClick(ProductResponseModal responseModal) {
        viewModal.deleteList(productresponse);
    }

    @Override
    public void onPlus_BTN(ProductResponseModal responseModal) {

        int quantity = Integer.parseInt(responseModal.getQuantity()) + 1;
     //   Toast.makeText(this, "" + quantity, Toast.LENGTH_SHORT).show();
        viewModal.updateQuantity(responseModal.getProductId(), quantity);
        viewModal.updatePrice(responseModal.getProductId(), quantity * Integer.parseInt(responseModal.getProductPrice().toString()));
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onMine_BTN(ProductResponseModal responseModal) {

        int quantity = Integer.parseInt(responseModal.getQuantity()) - 1;
        if (quantity != 0) {
            viewModal.updateQuantity(responseModal.getProductId(), quantity);
            //     Toast.makeText(this, "DoneThere"+responseModal.getProductName(), Toast.LENGTH_SHORT).show();
            viewModal.updatePrice(responseModal.getProductId(), quantity * Integer.parseInt(responseModal.getProductPrice().toString()));
            adapter.notifyDataSetChanged();
        } else {
            viewModal.deleteList(productrespons);
        }
        //------------------>  Adapter Class Used End <-----------------//
//        tvProcessPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvRS.setVisibility(View.VISIBLE);
//                tvProcessPay.setVisibility(View.VISIBLE);
//                tvTotalPrize.setVisibility(View.VISIBLE);
//            }
//        });
    }


    //--------------------> Map Start <-------------------//
    private String getCityNameByCoordinates(double lat, double lon) throws IOException {
        mGeocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);
        if (addresses != null && addresses.size() > 0) {
            String cityName = addresses.get(0).getLocality();
            String address = addresses.get(0).getAddressLine(0);
            EvLocation.setText(address);
            return addresses.get(0).getLocality();
        }
        return null;
    }

    private void getCurrentLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(cardActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(cardActivity.this)
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(cardActivity.this)
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() > 0) {

                                        int index = locationResult.getLocations().size() - 1;
                                        latitude = locationResult.getLocations().get(index).getLatitude();
//                                        Toast.makeText(cardActivity.this, "" + latitude, Toast.LENGTH_SHORT).show();
//                                        Log.d("alex", ";lat" + latitude);
                                        longitude = locationResult.getLocations().get(index).getLongitude();
                                        Log.d("alex", ";lat" + latitude);
                                        try {
                                            getCityNameByCoordinates(latitude, longitude);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }, Looper.getMainLooper());
                } else {
                    turnOnGPS();
                }
            } else {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
        }
    }

    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());
        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(cardActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(cardActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });
    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOCATION) {
            if (resultCode == Activity.RESULT_OK) {
                getCurrentLocation();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {
                    getCurrentLocation();
                } else {
                    turnOnGPS();
                }
            }
        }
    }
    //--------------------> Map End <-------------------//
}