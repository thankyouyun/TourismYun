package com.goodyun.tourismyun;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainPage1FragUnderItemViewActivity extends AppCompatActivity {

    String title;
    GoogleMap gmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1_frag_under_item_view);

        title = getIntent().getStringExtra("Addr");



        final double x = Double.parseDouble(getIntent().getStringExtra("MapX"));
        final double y = Double.parseDouble(getIntent().getStringExtra("MapY"));


        FragmentManager fragmentManager = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;

                //지도의 특정좌표로 이동 및 줌인
                LatLng seoul= new LatLng(y,x);
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15));

                //마커추가하기
                MarkerOptions marker= new MarkerOptions();
                marker.title(title);
//                marker.snippet("Seoul is the capital of Korea");
//                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ch_chopa));
                marker.anchor(0.5f, 1.0f);

                marker.position(seoul);

                gmap.addMarker(marker);


                //지도의 대표적인 설정들.
                UiSettings settings = gmap.getUiSettings();
                settings.setZoomControlsEnabled(true);
                settings.setMyLocationButtonEnabled(true);
                settings.setCompassEnabled(true);

                if (ActivityCompat.checkSelfPermission(MainPage1FragUnderItemViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainPage1FragUnderItemViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                gmap.setMyLocationEnabled(true);


            }
        });


    }//onCreate





}
