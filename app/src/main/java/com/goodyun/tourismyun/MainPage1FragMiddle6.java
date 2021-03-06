package com.goodyun.tourismyun;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainPage1FragMiddle6 extends AppCompatActivity {


    String typeId;
    RadioGroup rg;

    ArrayList<MainPage1FragMiddlesItem> items = new ArrayList<>();
    MainPage1FragMiddle6Adapter adapter;
    RecyclerView recyclerView;

//    CardView c1, c2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page1_frag_middle6);

        typeId = "32";

//        c1 = findViewById(R.id.cv1);
//        c2 = findViewById(R.id.cv2);


        reedRSS();
        recyclerView = findViewById(R.id.m6_recycler);
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {


                switch (checkedId) {
                    case R.id.rb_hotel:
                        items.clear();
                        typeId = "32";
                        adapter = new MainPage1FragMiddle6Adapter(MainPage1FragMiddle6.this, items,typeId);
                        recyclerView.setAdapter(adapter);
                        reedRSS();

                        break;
                    case R.id.rb_shopping:
                        items.clear();
                        typeId = "38";
                        adapter = new MainPage1FragMiddle6Adapter(MainPage1FragMiddle6.this, items,typeId);
                        recyclerView.setAdapter(adapter);

                        reedRSS();
                        break;
                }

            }
        });


//        c1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                c2.setCardBackgroundColor(ContextCompat.getColor(MainPage1FragMiddle6.this,R.color.white));
//                c1.setCardBackgroundColor(ContextCompat.getColor(MainPage1FragMiddle6.this,R.color.colorTabbar));
//                items.clear();
//                typeId = "32";
//                adapter = new MainPage1FragMiddle6Adapter(MainPage1FragMiddle6.this, items, typeId);
//                recyclerView.setAdapter(adapter);
//                reedRSS();
//
//
//            }
//        });
//
//        c2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                c1.setCardBackgroundColor(ContextCompat.getColor(MainPage1FragMiddle6.this,R.color.white));
//                c2.setCardBackgroundColor(ContextCompat.getColor(MainPage1FragMiddle6.this,R.color.colorTabbar));
//                items.clear();
//                typeId = "38";
//                adapter = new MainPage1FragMiddle6Adapter(MainPage1FragMiddle6.this, items, typeId);
//                recyclerView.setAdapter(adapter);
//                reedRSS();
//
//
//            }
//        });


        adapter = new MainPage1FragMiddle6Adapter(MainPage1FragMiddle6.this, items, typeId);
        recyclerView.setAdapter(adapter);


    }//onCreate


    public void reedRSS() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=" + typeId + "&areaCode=1&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=P&numOfRows=12&pageNo=1");

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

                MainPage1FragMiddlesItem item = null;
                String tagName = null;


                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_DOCUMENT:


                            break;

                        case XmlPullParser.START_TAG:

                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                item = new MainPage1FragMiddlesItem();
                            } else if (tagName.equals("addr1")) {
                                xpp.next();
                                if (item != null) item.setAddr(xpp.getText());
                            } else if (tagName.equals("contentid")) {
                                xpp.next();
                                if (item != null) item.setId(xpp.getText());
                            } else if (tagName.equals("firstimage")) {
                                xpp.next();
                                if (item != null) item.setImg(xpp.getText());
                            } else if (tagName.equals("mapx")) {
                                xpp.next();
                                if (item != null) item.setMapX(xpp.getText());
                            } else if (tagName.equals("mapy")) {
                                xpp.next();
                                if (item != null) item.setMapY(xpp.getText());
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
                                items.add(item);
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


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.notifyDataSetChanged();

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
