package com.goodyun.tourismyun;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    CalendarView cl;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);



        cl = findViewById(R.id.write_calendar);
        cl.setMinDate(System.currentTimeMillis());

        cl.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                intent = getIntent();

                intent.putExtra("Year",year);
                intent.putExtra("Month",month);
                intent.putExtra("Day",dayOfMonth);
                setResult(RESULT_OK,intent);

            }
        });



    }//onCreate

    public void clickCho(View v){

        finish();
    }


}
