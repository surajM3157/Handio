package com.example.handioin;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handioin.ModelClass.HomeAll_itemRequest;
import com.example.handioin.ModelClass.HomeAll_itemResponse;
import com.example.handioin.ModelClass.HomeResponseModal;
import com.example.handioin.ModelClass.HomeproductModal;
import com.example.handioin.RetrofitClient.BaseUrlClass;
import com.example.handioin.RetrofitClient.RetrofitClientInterface;
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
import java.util.List;
import java.util.Locale;

import AdaperClass.Handio.HomeAdapter;
import AdaperClass.Handio.HomeItemAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RelativeLayout appbar;
    private TextInputEditText EvLocation;
    private ImageView ivLogo;
    private TextView tvAllItem;
    private SearchView SearchView;
    private ImageView cvImg;
    private ImageView ivImg;
    private BottomNavigationView navigation;
    private RecyclerView rvHandio,rvHandioAll;

    //----------> MAP <------------//

    double latitude, longitude;
    private LocationRequest locationRequest;
    private final boolean isContinue = false;
    private final boolean isGPS = false;
    private static final String TAG = "alex";
    private final Boolean flag = false;
    private static final int REQUEST_LOCATION = 1;
    private Geocoder mGeocoder;

    private final static int ALL_PERMISSIONS_RESULT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        appbar = findViewById(R.id.appbar);
        EvLocation = findViewById(R.id.Ev_Location);
        ivLogo = findViewById(R.id.iv_logo);
//     \\\\   cvImg = findViewById(R.id.cv_img);
        rvHandio = findViewById(R.id.rvHandio);
        rvHandioAll = findViewById(R.id.rvHandioAll);

        ivImg = findViewById(R.id.iv_img);
        navigation = findViewById(R.id.navigation);
        tvAllItem = findViewById(R.id.tvAllItem);





        Home_Handio();
        Home_HandioAllitem();

        //----------> MAP S <------------//

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
        getCurrentLocation();

        //----------> MAP E <------------//

        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MenuBarActivity.class);
                startActivity(i);
            }
        });

        tvAllItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.blog:
                        startActivity(new Intent(getApplicationContext(), BlogActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), cardActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.res:
                        //  startActivity(new Intent(getApplicationContext(), BlogActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });

        ////-------------------------------------> BottomSheet <-----------------------------------////


//        View bottomSheetView = getLayoutInflater().inflate(R.layout.activity_map_driver_details, null);
//        bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(HomeActivity.this, R.style.AppBottomdialogTheme);
//        bottomSheetView.setBackgroundColor(Color.TRANSPARENT);
//        bottomSheetDialog.setContentView(bottomSheetView);
//        ImageView driver_pic1 = bottomSheetView.findViewById(R.id.driver_pic1);
//        TextView driver_name = bottomSheetView.findViewById(R.id.driver_name);
//        TextView vehile_name = bottomSheetView.findViewById(R.id.vehile_name);
//        TextView vehile_no = bottomSheetView.findViewById(R.id.vehile_no);
//        TextView driver_rating = bottomSheetView.findViewById(R.id.driver_rating);
//        TextView get_otp = bottomSheetView.findViewById(R.id.get_otp);
//        TextView tv_rating = bottomSheetView.findViewById(R.id.tv_rating);
//        TextView tv_total_trip = bottomSheetView.findViewById(R.id.tv_total_trip);
//        TextView tv_year = bottomSheetView.findViewById(R.id.tv_year);
//        TextView feed_back = bottomSheetView.findViewById(R.id.drop_back_add);
//        TextView tv_moredata = bottomSheetView.findViewById(R.id.tv_moredata);
//        TextView callDriver_end_booking = bottomSheetView.findViewById(R.id.callDriver_end_booking);
//        TextView callDriver_truck = bottomSheetView.findViewById(R.id.callDriver_truck);

    }


    private void Home_Handio() {
        Call<List<HomeResponseModal>> call;
        ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
        dialog.setMessage(" Loading... ");
        dialog.show();
        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(HomeActivity.this).create(RetrofitClientInterface.class);
        call = object.getDataFetchhandioProduct();
        Log.d("alex", "callres" + call.toString());
        call.enqueue(new Callback<List<HomeResponseModal>>() {
            @Override
            public void onResponse(Call<List<HomeResponseModal>> call, Response<List<HomeResponseModal>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(HomeActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                } else {

                        List<HomeResponseModal> productModel = response.body();
                    if (productModel != null) {

                        HomeAdapter homeAdapter = new HomeAdapter(HomeActivity.this, productModel);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);

                        rvHandio.setLayoutManager(linearLayoutManager);
                        rvHandio.setAdapter(homeAdapter);

                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<HomeResponseModal>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(HomeActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Home_HandioAllitem() {
        Call<List<HomeAll_itemResponse>> call;
        Log.d("suraj", "Text");
        ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
        dialog.setMessage(" Loading... ");
        dialog.show();
        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(HomeActivity.this).create(RetrofitClientInterface.class);
        call = object.getHome_item();
        Log.d("suraj", "Text2"+call.toString());
        call.enqueue(new Callback<List<HomeAll_itemResponse>>() {
            @Override
            public void onResponse(Call<List<HomeAll_itemResponse>> call, Response<List<HomeAll_itemResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.d("retrofit","ok Done2==>>  "+response.isSuccessful());
                } else {

                    List<HomeAll_itemResponse> restData = response.body();;

                    if (restData != null) {
                        HomeItemAll adapter = new HomeItemAll(HomeActivity.this, restData);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rvHandioAll.setLayoutManager(linearLayoutManager);
                        rvHandioAll.setAdapter(adapter);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<HomeAll_itemResponse>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(HomeActivity.this, " "+call.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

/*   private void Home_HandioAllitem() {
//        Call<HomeAll_itemResponse> call;
//        Log.d("suraj", "Text");
//        ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
//        dialog.setMessage(" Loading... ");
//        dialog.show();
//        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(HomeActivity.this).create(RetrofitClientInterface.class);
//        call = object.getHome_item();
//        Log.d("suraj", "Text2" + call.toString());
//        call.enqueue(new Callback<HomeAll_itemResponse>() {
//            @Override
//            public void onResponse(Call<HomeAll_itemResponse> call, Response<HomeAll_itemResponse> response) {
//                if (!response.isSuccessful()) {
//                    Log.d("suraj", "Text3" + response.toString());
//                    Toast.makeText(HomeActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
//                } else {
//                    HomeAll_itemResponse productModel = response.body();
//                    if (productModel != null) {
//                        Log.d("suraj", "Text4" + productModel);
//                        List<HomeAll_itemRequest> restData = productModel.getArray();
//                        Log.d("suraj", "Text5" + restData);
//                        HomeItemAll adapter = new HomeItemAll(HomeActivity.this, restData);
//                        Log.d("suraj", "Text6" + adapter);
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
//                        rvHandioAll.setLayoutManager(linearLayoutManager);
//                        rvHandioAll.setAdapter(adapter);
//
////                        for (int i = 0; i < restData.size(); i++) {
////                           Toast.makeText(HomeActivity.this, "For Condition work", Toast.LENGTH_SHORT).show();
////                        }
//                    }
//                }
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<HomeAll_itemResponse> call, Throwable t) {
//                if (t instanceof IOException) {
//                    Toast.makeText(HomeActivity.this, " " + call.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    } */

    //----------> MAP Start<------------//

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
            if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(HomeActivity.this)
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(HomeActivity.this)
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() > 0) {

                                        int index = locationResult.getLocations().size() - 1;
                                        latitude = locationResult.getLocations().get(index).getLatitude();
//                                        Toast.makeText(HomeActivity.this, "" + latitude, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(HomeActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(HomeActivity.this, 2);
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
    //----------> MAP End <------------//
}