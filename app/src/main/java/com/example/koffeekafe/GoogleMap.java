package com.example.koffeekafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMap extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this  );
    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        LatLng Conestoga= new LatLng(43.390117, -80.404038);
        googleMap.addMarker(new MarkerOptions().position(Conestoga).title("Conestoga"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Conestoga))  ;
    }
}