package com.goodyun.tourismyun;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MainPage1FragBestItemViewMapActivity extends AppCompatActivity {


    GoogleMap gmap;
    String title;
    Location loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1_frag_best_item_view_map);

        title = getIntent().getStringExtra("Title");

        loc = new Location("");
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        List<Address> addr  =null;

        try {
            addr = geocoder.getFromLocationName(title,1);
        }catch (Exception e){

        }
        if(addr !=null){
            Address lating = addr.get(0);
            double lat = lating.getLatitude();
            double lon = lating.getLongitude();
            loc.setLatitude(lat);
            loc.setLongitude(lon);
        }


        //구글맵 .........................
        FragmentManager fragmentManager = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;

                //지도의 특정좌표로 이동 및 줌인
                LatLng seoul = new LatLng(loc.getLatitude(),loc.getLongitude());
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15));

                //마커추가하기
                MarkerOptions marker = new MarkerOptions();
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

                if (ActivityCompat.checkSelfPermission(MainPage1FragBestItemViewMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainPage1FragBestItemViewMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                gmap.setMyLocationEnabled(true);


            }
        });


    }//onCreate


}
