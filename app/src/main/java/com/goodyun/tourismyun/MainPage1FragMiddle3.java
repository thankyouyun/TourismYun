package com.goodyun.tourismyun;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainPage1FragMiddle3 extends AppCompatActivity {

    ArrayList<MainPage3FragItem> items = new ArrayList<>();
    MainPage1FragMiddle3Adapter adapter;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page1_frag_middle3);

        reedRSS();

        recyclerView = findViewById(R.id.m3_recycler);
        adapter = new MainPage1FragMiddle3Adapter(this,items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


    }//onCreate



    public void reedRSS() {
        try {
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=4obyapiUvJpvzT21LABYnbbPsaP4U0r0FRjGE%2FqJU3AkIiV4A0OtVejbos05oDZ8M7MOJxL2G9IS%2BnpuSNgeog%3D%3D&contentTypeId=28&areaCode=1&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=P&numOfRows=12&pageNo=1");

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

                MainPage3FragItem item = null;
                String tagName = null;


                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_DOCUMENT:


                            break;

                        case XmlPullParser.START_TAG:

                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                item = new MainPage3FragItem();
                            } else if (tagName.equals("addr1")) {
                                xpp.next();
                                if (item != null) item.setAddr(xpp.getText());
                            } else if (tagName.equals("contentid")) {
                                xpp.next();
                                if (item != null) item.setId(xpp.getText());
                            } else if (tagName.equals("firstimage")) {
                                xpp.next();
                                if (item != null) item.setImg(xpp.getText());
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

        //publishProgress()를 호출하면 실행되는 메소드 UI변경작업 가능
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
