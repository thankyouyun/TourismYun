package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainPage1FragMiddle5ItemViewActivity extends AppCompatActivity {
    String id, addr, mapX, mapY;

    TextView tvOver, tvSubOver, tvMainTitle, tvOverTitle, tvSubTitle;
    MainPage1FragMiddle5ItemVIewItems item;
    AutoScrollViewPager viewPager;
    MainPage1FragMiddleItemViewAdapter adapter;
    ArrayList<String> itemImg = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1_frag_middle5_item_view);

        Intent intent = getIntent();
        id = intent.getStringExtra("Id");


        reedRSS();
        reedRSSImgMenu();
        reedInforSub();

        viewPager = findViewById(R.id.item_view_auto_tour);
        adapter = new MainPage1FragMiddleItemViewAdapter(this, itemImg);
        viewPager.setAdapter(adapter);

        viewPager.setInterval(1800);
        viewPager.startAutoScroll();


        tvOverTitle = findViewById(R.id.item_view_tv_more_infor_title);
        tvSubTitle = findViewById(R.id.item_view_tv_sub_infor_title);
        tvMainTitle = findViewById(R.id.item_view_tv_main_title);
        tvOver = findViewById(R.id.item_view_more_infor);
        tvSubOver = findViewById(R.id.item_view_sub_infor);


    }//onCreate


    public void reedRSS() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=12&contentId="+id+"&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y");

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
                                item = new MainPage1FragMiddle5ItemVIewItems();
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


            tvMainTitle.setText(item.getTitle());
            addr = item.getAddr();
            tvSubOver.setText(item.getOverView());
            tvOverTitle.setText(item.getTitle() + "-필수정보");
            tvSubTitle.setText(item.getTitle() + "에 기본정보");



        }


    }//RssFeedTask


    public void reedInforSub() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=12&contentId="+id+"&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y");

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
                                item = new MainPage1FragMiddle5ItemVIewItems();
                                item.setBaby("등록된 정보가 없습니다.");
                                item.setCard("등록된 정보가 없습니다.");
                                item.setPet("등록된 정보가 없습니다.");
                                item.setTel("등록된 정보가 없습니다.");
                                item.setParking("등록된 정보가 없습니다.");
                                item.setParkingfee("등록된 정보가 없습니다.");
                                item.setRest("등록된 정보가 없습니다.");
                                item.setTime("등록된 정보가 없습니다.");
                                item.setTime("등록된 정보가 없습니다.");
                            } else if (tagName.equals("chkbabycarriage")) {
                                xpp.next();
                                if (item != null) item.setBaby(xpp.getText());
                            } else if (tagName.equals("chkcreditcard")) {
                                xpp.next();
                                if (item != null) item.setCard(xpp.getText());
                            } else if (tagName.equals("chkpet")) {
                                xpp.next();
                                if (item != null) item.setPet(xpp.getText());
                            } else if (tagName.equals("infocenter")) {
                                xpp.next();
                                if (item != null) item.setRest(xpp.getText());
                            } else if (tagName.equals("parking")) {
                                xpp.next();
                                if (item != null) item.setParkingfee(xpp.getText());
                            } else if (tagName.equals("useseason")) {
                                xpp.next();
                                if (item != null) item.setTime(xpp.getText());
                            } else if (tagName.equals("usetime")) {
                                xpp.next();
                                if (item != null) item.setTel(xpp.getText());
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

            try {
                tvOver.setText("\n이용시간 \n" + item.getTime()+ "\n" + item.getTel()+"\n유모차 대여 : " + item.getBaby() + "\n애완동물 : " + item.getPet() + "\n신용카드 : " + item.getCard().replace("없음", "사용불가") + "\n주차시설 : " + item.getParking() + "\n\n문의 " + item.getRest().replace("<br />", ""));
            }catch (Exception e){
                tvOver.setText("등록된 정보가 없습니다.");
            }
        }

    }//infortask


    public void reedRSSImgMenu() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=12&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&contentId="+id+"&imageYN=Y");

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


    public void clickback(View v) {


        finish();

    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.disappear_search, R.anim.disappear_search);
    }
}
