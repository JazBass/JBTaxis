package com.jazbass.jbtaxis;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindTaxiActivity extends AppCompatActivity implements OnMapReadyCallback,
GoogleMap.OnInfoWindowClickListener{

    LatLng ubicationBCN = new LatLng(41.392842, 2.158265);
    String[] Permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_taxi);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnInfoWindowClickListener(this);
        CameraPosition position = new CameraPosition.Builder()
                .target(ubicationBCN)
                .zoom(16)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(FindTaxiActivity.this,
                        Permissions, 1);
                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icono_taxi_mini);
        for (Taxi taxi : PrincipalActivity.availableTaxis) {
            if (taxi.isAvailable()) {
                MarkerOptions taxiMarker = new MarkerOptions()
                        .position(new LatLng(taxi.getLat(), taxi.getLng()))
                        .title(taxi.getCarRegistration())
                        .icon(icon)
                        .snippet("Click here for choose this taxi");
                googleMap.addMarker(taxiMarker);
            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent i = new Intent(FindTaxiActivity.this, RequestActivity.class);
        i.putExtra("carRegistration",marker.getTitle());
        startActivity(i);
    }
}
