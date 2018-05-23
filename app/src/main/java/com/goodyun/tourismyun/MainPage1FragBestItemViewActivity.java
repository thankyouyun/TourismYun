package com.goodyun.tourismyun;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainPage1FragBestItemViewActivity extends AppCompatActivity {

    String id, title, img, mapX, mapY;

    List<Address> spResultLocation;
    Geocoder geocoder;
    ListView lv;
    ArrayList<MainPage1FragBestViewItem> items = new ArrayList<>();
    MainPage1FragBestItemViewAdapter adapter;
    TextView tvTitle;
    Spinner spinner1, spinner2;
    ArrayList<String> spinnerItem = new ArrayList<>();
    ArrayAdapter<String> spAdapter;
    GoogleMap gmap;
    double lat, lon, stlat, stlon, arrlat, arrlon, stAddr, arrAddr;
    LocationManager locationManager;
    int typeSet;
    String st, arrival;

    LinearLayout dialogKmap, dialogGmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1_frag_best_item_view);


        Intent intent = getIntent();
        id = intent.getStringExtra("Id");

        title = intent.getStringExtra("Title");

        img = intent.getStringExtra("Img");

        mapX = intent.getStringExtra("MapX");
        mapY = intent.getStringExtra("MapY");
        typeSet = 25;
        spAdapter = new ArrayAdapter<String>(this, R.layout.spinner_selected, spinnerItem);
        spAdapter.add("현재위치");


        reedRSS();
        tvTitle = findViewById(R.id.item_vest_title);
        tvTitle.setText(title);
        lv = findViewById(R.id.item_vest_list_view);
        adapter = new MainPage1FragBestItemViewAdapter(this, items, getLayoutInflater());

        lv.setAdapter(adapter);

        //spinner
        spinner1 = findViewById(R.id.best_sp1);
        spinner2 = findViewById(R.id.best_sp2);

        spinnerSetMethod();


        geocoder = new Geocoder(this);
//구글맵 .........................
        if (mapY != null)

        {
            lat = Double.parseDouble(mapY);
            lon = Double.parseDouble(mapX);
        }

        setMap();


    }//onCreate

    public void clickRoad(View v) {


        //위치 찾기
        NowLocation();

        if (st.equals("현재위치")) {
            stlat = lat;
            stlon = lon;
        } else {
            try {
                spResultLocation = geocoder.getFromLocationName(st, 1);
                stlat = spResultLocation.get(0).getLatitude();
                stlon = spResultLocation.get(0).getLongitude();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (arrival.equals("현재위치")) {
            arrlat = lat;
            arrlon = lon;
        } else {
            try {
                spResultLocation = geocoder.getFromLocationName(arrival, 1);
                arrlat = spResultLocation.get(0).getLatitude();
                arrlon = spResultLocation.get(0).getLongitude();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_map_custom, null);
        builder.setView(view);

        builder.setPositiveButton("닫기", null);

        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        dialogGmap = view.findViewById(R.id.dialog_gmap);
        dialogKmap = view.findViewById(R.id.dialog_kmap);


        //구글맵 열기
        dialogGmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                String uri = "http://maps.google.com/maps?saddr=" + stlat + "," + stlon + "&daddr=" + arrlat + "," + arrlon + "&hl=ko";
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                it.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(it);


            }
        });


        //카카오맵 열기
        dialogKmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    dialog.dismiss();
                    String url = "daummaps://route?sp=" + stlat + "," + stlon + "&ep=" + arrlat + "," + arrlon + "&by=PUBLICTRANSIT";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (Exception e) {
                    dialog.dismiss();
                    String url = "market://details?id=net.daum.android.map";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }

            }
        });


    }//clickRoad

    public void spinnerSetMethod() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        spAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner1.setPrompt("출발지 선택");
        spinner2.setPrompt("도착지 선택");
        spinner1.setSelected(true);
        spinner2.setSelected(true);

        spinner1.setAdapter(spAdapter);
        spinner2.setAdapter(spAdapter);
        spinner1.setSelection(0);
        spinner2.setSelection(1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                st = spinnerItem.get(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                arrival = spinnerItem.get(position).toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }//spinner method


    public void setMap() {


        FragmentManager fragmentManager = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;

                //지도의 특정좌표로 이동 및 줌인
                LatLng seoul = new LatLng(lat, lon);
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

                if (ActivityCompat.checkSelfPermission(MainPage1FragBestItemViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainPage1FragBestItemViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                gmap.setMyLocationEnabled(true);


            }
        });//gmap

    }//gmap setup


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }//listview size method


    public void reedRSS() {
        try {


            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=25&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&listYN=Y");

            RssFeedTask task = new RssFeedTask();

            task.execute(url);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }//reed


    class RssFeedTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {

            InputStream is;
            try {

                is = urls[0].openStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(is, "utf-8");

                int eventType = xpp.next();

                MainPage1FragBestViewItem item = null;
                String tagName = null;


                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_DOCUMENT:


                            break;

                        case XmlPullParser.START_TAG:

                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                item = new MainPage1FragBestViewItem();
                            } else if (tagName.equals("subcontentid")) {
                                xpp.next();
                                if (item != null) item.setSubid(xpp.getText());
                            } else if (tagName.equals("subdetailimg")) {
                                xpp.next();
                                if (item != null) item.setImg(xpp.getText());
                            } else if (tagName.equals("subdetailoverview")) {
                                xpp.next();
                                if (item != null) item.setOverView(xpp.getText());
                            } else if (tagName.equals("subname")) {
                                xpp.next();
                                if (item != null) item.setTitle(xpp.getText());

                                String tmpTitle = item.getTitle();
                                if (tmpTitle.contains("점심식사")) {
                                    tmpTitle = tmpTitle.replace("점심식사", "").replace("(", "").replace(")", "");
                                } else if (tmpTitle.contains("식사")) {
                                    tmpTitle = tmpTitle.replace("식사", "").replace("(", "").replace(")", "");
                                }

                                spAdapter.add(tmpTitle);
                            }
                            break;
                        case XmlPullParser.TEXT:

                            break;
                        case XmlPullParser.END_TAG:
                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                items.add(item);
                                item = null;

                            }

                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;

                    }

                    eventType = xpp.next();

                }
                publishProgress();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return "파싱종료";
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            spAdapter.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
            setListViewHeightBasedOnChildren(lv);
        }


    }//RssFeedTask

    public void NowLocation() {
        //현재 위치 가져오기
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "퍼미션 체크", Toast.LENGTH_SHORT).show();
            return;
        }

        //현재 내 위치 얻어오기
        Location location = null;

        if (locationManager.isProviderEnabled("gps")) {
            location = locationManager.getLastKnownLocation("gps");
        } else if (locationManager.isProviderEnabled("network")) {
            location = locationManager.getLastKnownLocation("network");
        }

        if (location == null) {
            Toast.makeText(MainPage1FragBestItemViewActivity.this, "위치를 못찾겠습니다", Toast.LENGTH_SHORT).show();
        } else {
            //위도, 경도 얻어오기
            lat = location.getLatitude();
            lon = location.getLongitude();

        }

    }//nowLocation

    public void clickFAB(View v) {
        finish();
    }//FAB
}
