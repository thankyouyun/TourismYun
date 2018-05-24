package com.goodyun.tourismyun;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainPage1FragMiddle2ItemViewActivity extends AppCompatActivity {
    String id, img, telName, addr;

    TextView tvOver, tvTel, tvSubOver, tvInforName;
    MainPage1FragMiddle2ItemVIewItems item;
    AutoScrollViewPager viewPager;
    MainPage1FragMiddleItemViewAdapter adapter;
    ArrayList<String> itemImg = new ArrayList<>();
    GoogleMap gmap;
    double lat, lon;

    LoadSQLlite loadSQLlite;
    int typeSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page1_frag_middle2_item_view);

        Intent intent = getIntent();
        id = intent.getStringExtra("Id");
        img = intent.getStringExtra("Img");
        typeSet = 2;
        loadSQLlite = new LoadSQLlite(this, "test.db", null, 1);
        loadSQLlite.insert(id, img, typeSet);


        reedRSS();
        reedRSSImgMenu();
        reedSubInfor();
        reedInforSub();
        viewPager = findViewById(R.id.item_view_auto_tour);
        adapter = new MainPage1FragMiddleItemViewAdapter(this, itemImg);
        viewPager.setAdapter(adapter);

        viewPager.setInterval(1800);
        viewPager.startAutoScroll();


        tvOver = findViewById(R.id.item_view_more_infor);
        tvTel = findViewById(R.id.item_view_tv_tel);
        tvInforName = findViewById(R.id.item_view_more_infor_name);
        tvSubOver = findViewById(R.id.item_view_sub_infor);

    }//onCreate


    public void reedRSSImgMenu() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=15&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&contentId=" + id + "&imageYN=Y");

            RssFeedTaskImg task = new RssFeedTaskImg();

            task.execute(url);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


    class RssFeedTaskImg extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {

            InputStream is;
            try {

                is = urls[0].openStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(is, "utf-8");

                int eventType = xpp.next();

                String item = null;
                String tagName = null;


                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_DOCUMENT:


                            break;

                        case XmlPullParser.START_TAG:

                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                item = new String("item");
                            } else if (tagName.equals("smallimageurl")) {
                                xpp.next();
                                if (item != null) item = xpp.getText();
                            }
                            break;
                        case XmlPullParser.TEXT:

                            break;
                        case XmlPullParser.END_TAG:
                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                itemImg.add(item);
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

        //publishProgress()를 호출하면 실행되는 메소드 UI변경작업 가능
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            adapter.notifyDataSetChanged();
        }


    }//RssFeedTaskImg


    public void reedSubInfor() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=15&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&listYN=Y");

            RssFeedSub task = new RssFeedSub();

            task.execute(url);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


    class RssFeedSub extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {

            InputStream is;
            try {

                is = urls[0].openStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(is, "utf-8");

                int eventType = xpp.next();


                String tagName = null;


                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_DOCUMENT:


                            break;

                        case XmlPullParser.START_TAG:

                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                item = new MainPage1FragMiddle2ItemVIewItems();

                            } else if (tagName.equals("infoname")) {
                                xpp.next();
                                if (item != null) item.setName(xpp.getText());
                            } else if (tagName.equals("infotext")) {
                                xpp.next();
                                if (item != null) item.setIntro(xpp.getText());
                            }
                            break;
                        case XmlPullParser.TEXT:

                            break;
                        case XmlPullParser.END_TAG:
                            tagName = xpp.getName();
                            if (tagName.equals("item")) {


                                publishProgress();
                            }

                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;

                    }

                    eventType = xpp.next();

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return "파싱종료";
        }

        //publishProgress()를 호출하면 실행되는 메소드 UI변경작업 가능
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            try {

                tvOver.setText(item.getName() + "\n" + item.getIntro().replace("<br />", ""));

            } catch (Exception e) {
                tvOver.setText("기본정보가 없습니다...");

            }


        }


    }//RssFeedTask


    public void reedRSS() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=15&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y");

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


                String tagName = null;


                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_DOCUMENT:


                            break;

                        case XmlPullParser.START_TAG:

                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                item = new MainPage1FragMiddle2ItemVIewItems();
                            } else if (tagName.equals("addr1")) {
                                xpp.next();
                                if (item != null) item.setAddr(xpp.getText());
                            } else if (tagName.equals("mapx")) {
                                xpp.next();
                                if (item != null) item.setMapX(xpp.getText());
                            } else if (tagName.equals("mapy")) {
                                xpp.next();
                                if (item != null) item.setMapY(xpp.getText());
                            } else if (tagName.equals("tel")) {
                                xpp.next();
                                if (item != null) item.setTel(xpp.getText());
                            } else if (tagName.equals("telname")) {
                                xpp.next();
                                if (item != null) item.setTelName(xpp.getText());
                            } else if (tagName.equals("title")) {
                                xpp.next();
                                if (item != null) item.setTitle(xpp.getText());
                            }
                            break;
                        case XmlPullParser.TEXT:

                            break;
                        case XmlPullParser.END_TAG:
                            tagName = xpp.getName();
                            if (tagName.equals("item")) {

                                publishProgress();
                            }

                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;

                    }

                    eventType = xpp.next();

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return "파싱종료";
        }

        //publishProgress()를 호출하면 실행되는 메소드 UI변경작업 가능
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            tvInforName.setText(item.getTitle());
            addr = item.getAddr();
            telName = item.getTelName();
            tvTel.setText(item.getTel());
            tvTel.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + tvTel.getText().toString()));
                    startActivity(intent);

                    return false;
                }
            });


            if (item.getMapY() != null) {
                lat = Double.parseDouble(item.getMapY());
                lon = Double.parseDouble(item.getMapX());

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
                    marker.title(item.getTitle());
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

                    if (ActivityCompat.checkSelfPermission(MainPage1FragMiddle2ItemViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainPage1FragMiddle2ItemViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    gmap.setMyLocationEnabled(true);


                }
            });//gmap


        }


    }//RssFeedTask


    public void reedInforSub() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=15&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y");

            RssFeedTaskInforSub task = new RssFeedTaskInforSub();

            task.execute(url);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


    class RssFeedTaskInforSub extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {

            InputStream is;
            try {

                is = urls[0].openStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(is, "utf-8");

                int eventType = xpp.next();


                String tagName = null;


                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_DOCUMENT:


                            break;

                        case XmlPullParser.START_TAG:

                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                item = new MainPage1FragMiddle2ItemVIewItems();
                            } else if (tagName.equals("agelimit")) {
                                xpp.next();
                                if (item != null) item.setAge(xpp.getText());
                            } else if (tagName.equals("eventenddate")) {
                                xpp.next();
                                if (item != null) item.setEnddate(xpp.getText());
                            } else if (tagName.equals("eventplace")) {
                                xpp.next();
                                if (item != null) item.setPlase(xpp.getText());
                            } else if (tagName.equals("eventstartdate")) {
                                xpp.next();
                                if (item != null) item.setStdate(xpp.getText());
                            } else if (tagName.equals("playtime")) {
                                xpp.next();
                                if (item != null) item.setTime(xpp.getText());
                            } else if (tagName.equals("spendtimefestival")) {
                                xpp.next();
                                if (item != null) item.setUseTime(xpp.getText());
                            } else if (tagName.equals("sponsor1")) {
                                xpp.next();
                                if (item != null) item.setSpon(xpp.getText());
                            } else if (tagName.equals("subevent")) {
                                xpp.next();
                                if (item != null) item.setEvent(xpp.getText());
                            } else if (tagName.equals("usetimefestival")) {
                                xpp.next();
                                if (item != null) item.setFee(xpp.getText());
                            }

                            break;
                        case XmlPullParser.TEXT:

                            break;
                        case XmlPullParser.END_TAG:
                            tagName = xpp.getName();
                            if (tagName.equals("item")) {

                                publishProgress();
                            }

                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;

                    }

                    eventType = xpp.next();

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return "파싱종료";
        }

        //publishProgress()를 호출하면 실행되는 메소드 UI변경작업 가능
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            adapter.notifyDataSetChanged();

            if (item.getFee().contains("br")) {
                item.setFee(item.getFee().replace("<br />", ""));
            }
            if (item.getEvent() != null) {
                if (item.getEvent().contains("br")) {
                    item.setEvent(item.getEvent().replace("<br />", ""));
                }
            }

            tvSubOver.setText("주최 : " + item.getSpon() + "\n\n장소 : " + item.getPlase() + "\n\n상세주소 : " + addr + "\n\n시작일 : " + item.getStdate() + "\n\n종료일 :" + item.getEnddate() +
                    "\n\n이용요금 : " + item.getFee() + "\n\n연령 : " + item.getAge() + "\n\n관람시간 : " + item.getTime() + "\n\n소요시간 : " + item.getUseTime() +
                    "\n\n행사 : " + item.getEvent());


        }


    }//RssFeedTask

    public void clickback(View v) {


        finish();

    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.disappear_search, R.anim.disappear_search);
    }

}
