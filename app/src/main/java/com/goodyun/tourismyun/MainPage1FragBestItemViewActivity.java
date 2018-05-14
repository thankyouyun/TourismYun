package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainPage1FragBestItemViewActivity extends AppCompatActivity {

    String id,title;


    ListView lv;
    ArrayList<MainPage1FragBestItemView> items = new ArrayList<>();
    MainPage1FragBestItemViewAdapter adapter;
TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1_frag_best_item_view);


        Intent intent = getIntent();
        id = intent.getStringExtra("Id");
        title = intent.getStringExtra("Title");
        tvTitle = findViewById(R.id.item_vest_title);
        tvTitle.setText(title);
        lv = findViewById(R.id.item_vest_list_view);
        adapter = new MainPage1FragBestItemViewAdapter(this,items,getLayoutInflater());
        adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);


        reedRSS();

    }//onCreate


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
                            }  else if (tagName.equals("subdetailimg")) {
                                xpp.next();
                                if (item != null) item.setImg(xpp.getText());
                            } else if (tagName.equals("subdetailoverview")) {
                                xpp.next();
                                if (item != null) item.setOverView(xpp.getText());
                            }else if (tagName.equals("subname")) {
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


    public void clickFAB(View v){
        finish();
    }
}
