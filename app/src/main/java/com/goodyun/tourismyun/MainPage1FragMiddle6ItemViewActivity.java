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
import android.util.Log;
import android.view.View;
import android.view.Window;
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

public class MainPage1FragMiddle6ItemViewActivity extends AppCompatActivity {

    String id, img,typeId, addr,title, urlAddr;


    TextView tvOver, tvSubOver, tvOverTitle, tvSubTitle, tvTel;
    MainPage1FragMiddle6ItemVIewItems item;
    AutoScrollViewPager viewPager;
    MainPage1FragMiddleItemViewAdapter adapter;
    ArrayList<String> itemImg = new ArrayList<>();

    GoogleMap gmap;
    double lat, lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_page1_frag_middle6_item_view);

        tvOverTitle = findViewById(R.id.item_view_tv_more_infor_title);
        tvSubTitle = findViewById(R.id.item_view_tv_sub_infor_title);
        tvTel = findViewById(R.id.item_view_tv_tel);
        tvOver = findViewById(R.id.item_view_more_infor);
        tvSubOver = findViewById(R.id.item_view_sub_infor);

        Intent intent = getIntent();
        id = intent.getStringExtra("Id");
        typeId = intent.getStringExtra("TypeId");

        reedRSS();
        viewPager = findViewById(R.id.item_view_auto_tour);
        adapter = new MainPage1FragMiddleItemViewAdapter(MainPage1FragMiddle6ItemViewActivity.this, itemImg);
        viewPager.setAdapter(adapter);
        viewPager.setInterval(1800);
        viewPager.startAutoScroll();


        reedRSSImgMenu();



        reedInforSub();

    }//onCreate


    public void reedRSS() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=" + typeId + "&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y");

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
                                item = new MainPage1FragMiddle6ItemVIewItems();
                            } else if (tagName.equals("addr1")) {
                                xpp.next();
                                if (item != null) item.setAddr(xpp.getText());
                            } else if (tagName.equals("mapx")) {
                                xpp.next();
                                if (item != null) item.setMapX(xpp.getText());
                            } else if (tagName.equals("mapy")) {
                                xpp.next();
                                if (item != null) item.setMapY(xpp.getText());
                            } else if (tagName.equals("overview")) {
                                xpp.next();
                                if (item != null) item.setOverView(xpp.getText());
                            } else if (tagName.equals("tel")) {
                                xpp.next();
                                if (item != null) item.setTel(xpp.getText());
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


            addr = item.getAddr();
            tvOver.setText(item.getOverView());
            tvOverTitle.setText(item.getTitle());





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

                    if (ActivityCompat.checkSelfPermission(MainPage1FragMiddle6ItemViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainPage1FragMiddle6ItemViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    gmap.setMyLocationEnabled(true);


                }
            });//gmap

        }


    }//RssFeedTask


    public void reedInforSub() {
        try {

            if (typeId.equals("32")) {
                urlAddr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=32&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y";
            } else {
                urlAddr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=38&contentId=" + id + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y";
            }
            URL url = new URL(urlAddr);

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


                if (typeId.equals("32")) {


                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        switch (eventType) {

                            case XmlPullParser.START_DOCUMENT:


                                break;

                            case XmlPullParser.START_TAG:

                                tagName = xpp.getName();
                                if (tagName.equals("item")) {
                                    item = new MainPage1FragMiddle6ItemVIewItems();

                                } else if (tagName.equals("checkintime")) {
                                    xpp.next();
                                    if (item != null) item.setCheckin(xpp.getText());
                                } else if (tagName.equals("checkouttime")) {
                                    xpp.next();
                                    if (item != null) item.setCheckout(xpp.getText());
                                } else if (tagName.equals("chkcooking")) {
                                    xpp.next();
                                    if (item != null) item.setCook(xpp.getText());
                                } else if (tagName.equals("foodplace")) {
                                    xpp.next();
                                    if (item != null) item.setFood(xpp.getText());
                                } else if (tagName.equals("infocenterlodging")) {
                                    xpp.next();
                                    if (item != null) item.setTelNum(xpp.getText());
                                } else if (tagName.equals("subfacility")) {
                                    xpp.next();
                                    if (item != null) item.setSubFacility(xpp.getText());
                                }


                                break;
                            case XmlPullParser.TEXT:

                                break;
                            case XmlPullParser.END_TAG:


                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;

                        }

                        eventType = xpp.next();

                    }//while

                }else{

                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        switch (eventType) {

                            case XmlPullParser.START_DOCUMENT:


                                break;

                            case XmlPullParser.START_TAG:

                                tagName = xpp.getName();
                                if (tagName.equals("item")) {
                                    item = new MainPage1FragMiddle6ItemVIewItems();

                                } else if (tagName.equals("infocentershopping")) {
                                    xpp.next();
                                    if (item != null) item.setTelNum(xpp.getText());
                                } else if (tagName.equals("opentime")) {
                                    xpp.next();
                                    if (item != null) item.setShopTime(xpp.getText());
                                } else if (tagName.equals("saleitem")) {
                                    xpp.next();
                                    if (item != null) item.setShopSale(xpp.getText());
                                }


                                break;
                            case XmlPullParser.TEXT:

                                break;
                            case XmlPullParser.END_TAG:


                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;

                        }

                        eventType = xpp.next();

                    }//while


                }//if문


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

            if(typeId.equals("32")) {
                tvSubOver.setText("\n체크인 : " + item.getCheckin() + " / 체크아웃 : " + item.getCheckout() + "\n\n취사시설 : " + item.getFood() +
                        "\n\n기타시설 : " + item.getSubFacility() + "\n\n상세주소 : " + addr);
            }else{
                tvSubOver.setText("\n\n운영 시간 \n"+item.getShopTime()+"\n\n판매 아이템 \n"+item.getShopSale());

            }

            tvTel.setText(item.getTelNum());
            tvTel.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + tvTel.getText().toString()));
                    startActivity(intent);

                    return false;
                }
            });


        }

    }//infortask


    public void reedRSSImgMenu() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=38&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&contentId=" + id + "&imageYN=Y");

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

                }//while
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
            if (itemImg.size() == 0) {
                viewPager.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();

        }


    }//RssFeedTaskImg


    public void clickFAB(View v) {
        finish();
    }


}
