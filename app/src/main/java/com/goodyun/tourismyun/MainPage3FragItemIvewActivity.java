package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainPage3FragItemIvewActivity extends AppCompatActivity {

    String id, addr, mapX, mapY;


    TextView tvOver, tvSubOver, tvOverTitle, tvSubTitle, tvTel;
    MainPage3ItemVIewItems item;
    AutoScrollViewPager viewPager;
    MainPage1FragMiddleItemViewAdapter adapter;
    ArrayList<String> itemImg = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page3_frag_item_view);

        tvOverTitle = findViewById(R.id.item_view_tv_more_infor_title);
        tvSubTitle = findViewById(R.id.item_view_tv_sub_infor_title);
        tvTel = findViewById(R.id.item_view_tv_tel);
        tvOver = findViewById(R.id.item_view_more_infor);
        tvSubOver = findViewById(R.id.item_view_sub_infor);

        Intent intent = getIntent();
        id = intent.getStringExtra("Id");

        reedRSS();
        reedInforSub();
        viewPager = findViewById(R.id.item_view_auto_tour);
        adapter = new MainPage1FragMiddleItemViewAdapter(this, itemImg);
        viewPager.setAdapter(adapter);
        viewPager.setInterval(1800);
        viewPager.startAutoScroll();


        reedRSSImgMenu();




    }//onCreate

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
                                item = new MainPage3ItemVIewItems();

                            } else if (tagName.equals("infocenter")) {
                                xpp.next();
                                if (item != null) item.setInfo(xpp.getText());
                            } else if (tagName.equals("parking")) {
                                xpp.next();
                                if (item != null) item.setParking(xpp.getText());
                            } else if (tagName.equals("restdate")) {
                                xpp.next();
                                if (item != null) item.setRest(xpp.getText());
                            } else if (tagName.equals("usetime")) {
                                xpp.next();
                                if (item != null) item.setTime(xpp.getText());
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
                tvOver.setText("\n이용시간 : "+item.getTime()+"\n\n쉬는날 : "+item.getRest()+"\n\n주차시설 : "+item.getParking()+"\n\n문의 : "+item.getInfo());

            }catch (Exception e){
                tvOver.setVisibility(View.GONE);
            }
        }

    }//infortask

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
                                item = new MainPage3ItemVIewItems();
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
                            } else if (tagName.equals("title")) {
                                xpp.next();
                                if (item != null) item.setTitle(xpp.getText());
                            }
                            break;
                        case XmlPullParser.TEXT:

                            break;
                        case XmlPullParser.END_TAG:
                            tagName = xpp.getName();


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


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);


            addr = item.getAddr();
            tvSubOver.setText(item.getOverView());
            tvOverTitle.setText(item.getTitle());


        }


    }//RssFeedTask


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
            try {
                adapter.notifyDataSetChanged();
            }catch (Exception e){

            }
        }


    }//RssFeedTaskImg


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.disappear_search, R.anim.disappear_search);
    }


    public void clickFAB(View v){
        finish();
    }


}
