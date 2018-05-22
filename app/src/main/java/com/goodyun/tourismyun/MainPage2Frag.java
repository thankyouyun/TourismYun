package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainPage2Frag extends Fragment {
    ArrayList<MainPage2Frag2DBItems> dbitems = new ArrayList<>();
    ListView listView;
    MainPage2FragAdapter adapter;
    ScrollView scrollView;
    FloatingActionButton fab;
    SwipeRefreshLayout refreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page2_fragment, container, false);

        fab = view.findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IntroActivity.loginOnOff) {
                    startActivity(new Intent(getActivity(), WriteBoardActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LogInActivity.class));
                }
            }
        });

        loadDB();

        listView = view.findViewById(R.id.list_view);
        adapter = new MainPage2FragAdapter(dbitems, getLayoutInflater());
        listView.setAdapter(adapter);

        refreshLayout = view.findViewById(R.id.frag2_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dbitems.clear();
                loadDB();
                refreshLayout.setRefreshing(false);
            }
        });


        return view;
    }//onCreate


    @Override
    public void onResume() {
        super.onResume();
    }

    void loadDB() {

        //Volley library 사용...가능..

        new Thread() {
            @Override
            public void run() {

                String serverUrl = "http://toutt.dothome.co.kr/tour/boardloadDb.php";

                try {
                    URL url = new URL(serverUrl);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "utf-8");
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    String line = reader.readLine();

                    while (true) {
                        buffer.append(line);

                        line = reader.readLine();
                        if (line == null) break;

                        buffer.append("\n");
                    }

                    //읽어온 데이터 문자열에서 db의 row(레코드)별로 배열로 분리하기.
                    String[] rows = buffer.toString().split(";");

                    dbitems.clear();
                    for (String row : rows) {
                        String[] datas = row.split("&");
                        if (datas.length != 8) continue;

                        String no = datas[0];
                        String id = datas[1];
                        String title = datas[2];
                        String datecr = datas[3];
                        String name = datas[4];
                        String date = datas[5];
                        String place = datas[6];
                        String text = datas[7];


                        dbitems.add(0, new MainPage2Frag2DBItems(no, id, title, datecr, name, date, place, text));

                        //리스트뷰 갱신!!
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                adapter.notifyDataSetChanged();
                                setListViewHeightBasedOnChildren(listView);

                            }
                        });

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

        }.start();


    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 240;
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
    }//ls


}
