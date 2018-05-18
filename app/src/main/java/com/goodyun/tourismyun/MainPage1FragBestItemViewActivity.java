package com.goodyun.tourismyun;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class MainPage1FragBestItemViewActivity extends AppCompatActivity {

    String id, title, img,mapX,mapY;


    ListView lv;
    ArrayList<MainPage1FragBestItemView> items = new ArrayList<>();
    MainPage1FragBestItemViewAdapter adapter;
    TextView tvTitle;
    GoogleMap gmap;
    double lat, lon;

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

        reedRSS();
        tvTitle = findViewById(R.id.item_vest_title);
        tvTitle.setText(title);
        lv = findViewById(R.id.item_vest_list_view);
        adapter = new MainPage1FragBestItemViewAdapter(this, items, getLayoutInflater());

        lv.setAdapter(adapter);






        if(mapY!=null){
            lat = Double.parseDouble(mapY);
            lon = Double.parseDouble(mapX);

        }


        //구글맵 .........................
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





    }//onCreate

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
    }


    public void reedRSS() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=25&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&listYN=Y");

            RssFeedTask task = new RssFeedTask();

            task.execute(url);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


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

                MainPage1FragBestItemView item = null;
                String tagName = null;


                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_DOCUMENT:


                            break;

                        case XmlPullParser.START_TAG:

                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                item = new MainPage1FragBestItemView();
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


            adapter.notifyDataSetChanged();
            setListViewHeightBasedOnChildren(lv);
        }


    }//RssFeedTask


    public void clickFAB(View v) {
        finish();
    }
}
