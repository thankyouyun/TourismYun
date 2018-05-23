package com.goodyun.tourismyun;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainPage1FragMiddle1ItemViewActivity extends AppCompatActivity {
    String id, img,homePage, title;


    TextView tvOver, tvOverTitle, tvMainTitle, tvTel, tvSubOver, tvSubTitle;
    ImageView mainIv;
    AutoScrollViewPager viewPager;
    MainPage1FragMiddleItemViewAdapter adapter;
    MainPage1FragMiddle1ItemVIewItems item = null;
    ArrayList<String> itemImg = new ArrayList<>();
    GoogleMap gmap;
    double lat, lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1_frag_middle1_item_view);

        Intent intent = getIntent();
        id = intent.getStringExtra("Id");
        img = intent.getStringExtra("Img");

        reedRSS();
        reedRSSImgMenu();
        reedSubInfor();


        viewPager = (AutoScrollViewPager) findViewById(R.id.item_view_auto_menu);
        adapter = new MainPage1FragMiddleItemViewAdapter(this, itemImg);
        viewPager.setAdapter(adapter);

        viewPager.setInterval(1800);
        viewPager.startAutoScroll();


        tvMainTitle = findViewById(R.id.item_view_tv_main_title);
        tvOver = findViewById(R.id.item_view_more_infor);
        tvOverTitle = findViewById(R.id.item_view_tv_more_infor_title);
        mainIv = findViewById(R.id.item_view_main_img);
        tvTel = findViewById(R.id.item_view_tv_tel);

        tvSubOver = findViewById(R.id.item_view_sub_infor);
        tvSubTitle = findViewById(R.id.item_view_tv_sub_infor_title);


    }//onCreate

    public void reedRSSImgMenu() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=14&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&contentId=" + id + "&imageYN=Y");

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
        }


    }//RssFeedTaskImg


    public void reedSubInfor() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=14&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y");

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
                                item = new MainPage1FragMiddle1ItemVIewItems();
                                item.setBaby("등록된 정보가 없습니다.");
                                item.setCard("등록된 정보가 없습니다.");
                                item.setPat("등록된 정보가 없습니다.");
                                item.setTel("등록된 정보가 없습니다.");
                                item.setParking("등록된 정보가 없습니다.");
                                item.setParkMoney("등록된 정보가 없습니다.");
                                item.setRest("등록된 정보가 없습니다.");
                                item.setTime("등록된 정보가 없습니다.");
                                item.setTime("등록된 정보가 없습니다.");
                            } else if (tagName.equals("chkbabycarriageculture")) {
                                xpp.next();
                                if (item != null) item.setBaby(xpp.getText());
                            } else if (tagName.equals("chkcreditcardculture")) {
                                xpp.next();
                                if (item != null) item.setCard(xpp.getText());
                            } else if (tagName.equals("chkpetculture")) {
                                xpp.next();
                                if (item != null) item.setPat(xpp.getText());
                            } else if (tagName.equals("infocenterculture")) {
                                xpp.next();
                                if (item != null) item.setTel(xpp.getText());
                            } else if (tagName.equals("parkingculture")) {
                                xpp.next();
                                if (item != null) item.setParking(xpp.getText());
                            } else if (tagName.equals("parkingfee")) {
                                xpp.next();
                                if (item != null) item.setParkMoney(xpp.getText());
                            } else if (tagName.equals("restdateculture")) {
                                xpp.next();
                                if (item != null) item.setRest(xpp.getText());
                            } else if (tagName.equals("usefee")) {
                                xpp.next();
                                if (item != null) item.setFee(xpp.getText());
                            } else if (tagName.equals("usetimeculture")) {
                                xpp.next();
                                if (item != null) item.setTime(xpp.getText());
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
                tvSubOver.setText("이용시간 \n" + item.getTime().replace("<br />", "").replace("<br>", "") + "\n\n쉬는 날 : " + item.getRest() +
                        "\n\n이용요금 \n" + item.getFee().replace("<br />", "") + "\n\n주차장  \n" + item.getParking().replace("<br />", "").replace("<br>", "") + "\n\n주차요금 \n" + item.getParkMoney().replace("<br />", "").replace("<br>", "") +
                        "\n\n유모차 대여 : " + item.getBaby().replace("없음", "불가").replace("있음", "가능") +
                        "\n\n애완동물 입장 여부 : " + item.getPat() + "\n\n신용카드 사용 여부 : " + item.getCard() + "\n\n" + item.getTel());
            } catch (Exception e) {
                tvSubOver.setText("기본정보가 없습니다...");

            }


        }


    }//RssFeedTask


    public void reedRSS() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=14&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=YD");

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
                                item = new MainPage1FragMiddle1ItemVIewItems();
                            } else if (tagName.equals("firstimage")) {
                                xpp.next();
                                if (item != null) item.setImg(xpp.getText());
                            } else if (tagName.equals("homepage")) {
                                xpp.next();
                                if (item != null) item.setHomePage(xpp.getText());
                            } else if (tagName.equals("mapx")) {
                                xpp.next();
                                if (item != null) item.setMapX(xpp.getText());
                            } else if (tagName.equals("mapy")) {
                                xpp.next();
                                if (item != null) item.setMapY(xpp.getText());
                            } else if (tagName.equals("overview")) {
                                xpp.next();
                                if (item != null) item.setOverView(xpp.getText());
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


            Glide.with(MainPage1FragMiddle1ItemViewActivity.this).load(item.getImg()).into(mainIv);
            tvOver.setText(item.getOverView().replace("<br>", "").replace("<br />", ""));
            tvOverTitle.setText(item.getTitle() + "에 대하여...");
            tvSubTitle.setText(item.getTitle() + "에 기본정보");
            tvMainTitle.setText(item.getTitle());
            homePage = item.getHomePage();



            title = item.getTitle();
            if(item.getMapY()!=null){
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

                    if (ActivityCompat.checkSelfPermission(MainPage1FragMiddle1ItemViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainPage1FragMiddle1ItemViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    gmap.setMyLocationEnabled(true);


                }
            });//gmap


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
