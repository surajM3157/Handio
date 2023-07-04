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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFirstName;
    public static EditText etEmail;
    private EditText etPassword;
    private EditText etConfPassword, tv_Location;
    private TextView tvOtp;
    private Button BTN_Register;
    private ImageView backarrow;


    double latitude, longitude;
    private LocationRequest locationRequest;
    private final boolean isContinue = false;
    private final boolean isGPS = false;
    private final Boolean flag = false;
    private static final int REQUEST_LOCATION = 1;
    private Geocoder mGeocoder;
    private final static int ALL_PERMISSIONS_RESULT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backarrow = findViewById(R.id.iv_back_arrow);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
        getCurrentLocation();


        Dexter.withContext(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                        if (multiplePermissionsReport.areAllPermissionsGranted()) {

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                        permissionToken.cancelPermissionRequest();
                    }
                }).check();

        etFirstName = findViewById(R.id.et_FirstName);
        etEmail = findViewById(R.id.et_Email);
        etPassword = findViewById(R.id.etPassword);
        etConfPassword = findViewById(R.id.et_confPassword);
        tv_Location = findViewById(R.id.tv_Location);
        tvOtp = findViewById(R.id.tv_otp);
        BTN_Register = findViewById(R.id.BTN);


        tvOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,OTP_Email_Activity.class);
                startActivity(i);
            }
        });

        BTN_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FirstName = etFirstName.getText().toString().trim();
                String Email = etEmail.getText().toString().trim();
                String Password = etPassword.getText().toString().trim();
                String ConfPassword = etConfPassword.getText().toString().trim();
                String Address = tv_Location.getText().toString().trim();

                if (!etFirstName.getText().toString().isEmpty()) {
                    if (!etEmail.getText().toString().isEmpty()) {
                        if (!tv_Location.getText().toString().isEmpty()) {
                            if (!TextUtils.isEmpty(Password) && !TextUtils.isEmpty(ConfPassword)) {
                                if (Password.equals(ConfPassword)) {

                                    CheckRegister(FirstName, Email, Password, Address);

                                    // Toast.makeText(RegisterActivity.this, "Password match", Toast.LENGTH_SHORT).show();
                                } else {
                                    // are different
                                    Toast.makeText(RegisterActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Required Field Enter Address", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CheckRegister(String FirstName, String Email, String Password, String Address) {
        Call<JsonElement> call;
        ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);
//        dialog.setMax(100);
        dialog.setMessage(" Wait... ");
//        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();

        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(RegisterActivity.this).create(RetrofitClientInterface.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("firstname", FirstName);
        hashMap.put("email", Email);
        hashMap.put("password", Password);
        hashMap.put("fullAddress", Address);

        call = object.getUser(hashMap);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonObject userInfoModel = response.body().getAsJsonObject();
                JsonPrimitive status = userInfoModel.getAsJsonPrimitive("status");
                JsonPrimitive message = userInfoModel.getAsJsonPrimitive("message");
                if (status.toString().contains("200")) {
                    Intent intent = new Intent(RegisterActivity.this, OTPActivity.class);
                    intent.putExtra("email",Email);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, "" + message, Toast.LENGTH_SHORT).show();
//                    sharedPrefManager.setKey("firstname", FirstName);
//                    sharedPrefManager.setKey("email", Email);
//                    sharedPrefManager.setKey("fullAddress", Address);
                } else {
                    Toast.makeText(RegisterActivity.this, "user already exists", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
            }
        });
    }
    private String getCityNameByCoordinates(double lat, double lon) throws IOException {
        mGeocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);

        if (addresses != null && addresses.size() > 0) {
            String cityName = addresses.get(0).getLocality();
            String address = addresses.get(0).getAddressLine(0);
            tv_Location.setText(address);
            return addresses.get(0).getLocality();
        }
        return null;
    }

    private void getCurrentLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(RegisterActivity.this)
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(RegisterActivity.this)
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() > 0) {

                                        int index = locationResult.getLocations().size() - 1;
                                        latitude = locationResult.getLocations().get(index).getLatitude();
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
                    Toast.makeText(RegisterActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(RegisterActivity.this, 2);
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
}

