package com.goodyun.tourismyun;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainPage1FragMiddle5 extends AppCompatActivity {

    String changeList,mapX,mapY;

    Spinner spinner;
    ArrayAdapter spinnerAdapter;

    ArrayList<MainPage1FragMiddlesItem> items = new ArrayList<>();
    MainPage1FragMiddle5Adapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page1_frag_middle5);

        spinner = findViewById(R.id.spinner);

        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.datas, R.layout.spinner_selected);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        changeList = "&cat1=A01&cat2=&cat3=";

        reedRSS();
        recyclerView = findViewById(R.id.m5_recycler);
        adapter = new MainPage1FragMiddle5Adapter(this, items);
        recyclerView.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        items.clear();
                        changeList = "&cat1=A01&cat2=&cat3=";
                        reedRSS();
                        break;
                    case 1:
                        items.clear();
                        changeList = "&cat1=A01&cat2=A0101&cat3=A01010100";
                        reedRSS();
                        break;
                    case 2:
                        items.clear();
                        changeList = "&cat1=A01&cat2=A0101&cat3=A01010400";
                        reedRSS();
                        break;
                    case 3:
                        items.clear();
                        changeList = "&cat1=A01&cat2=A0101&cat3=A01010500";
                        reedRSS();
                        break;
                    case 4:
                        items.clear();
                        changeList = "&cat1=A01&cat2=A0101&cat3=A01010700";
                        reedRSS();
                        break;
                    case 5:
                        items.clear();
                        changeList = "&cat1=A01&cat2=A0101&cat3=A01010800";
                        reedRSS();
                        changeList = "&cat1=A01&cat2=A0101&cat3=A01010900";
                        reedRSS();
                        changeList = "&cat1=A01&cat2=A0101&cat3=A01011800";
                        reedRSS();
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }//onCreate


    public void reedRSS() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=12&areaCode=1&sigunguCode=" + changeList + "&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=12&pageNo=1");

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
