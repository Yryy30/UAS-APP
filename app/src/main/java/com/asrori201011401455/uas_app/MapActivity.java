package com.asrori201011401455.uas_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    FrameLayout map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map = findViewById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        SharedPreferences sp = getSharedPreferences("MySp", MODE_PRIVATE);
        Double latitude = Double.parseDouble(sp.getString("lat", "-6.402698035626719"));
        Double longitude = Double.parseDouble(sp.getString("long", "106.79132727226958"));

        this.gMap = googleMap;
        LatLng wisata = new LatLng(latitude, longitude);
        this.gMap.addMarker(new MarkerOptions().position(wisata).title("Lokasi"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(wisata));
    }
}