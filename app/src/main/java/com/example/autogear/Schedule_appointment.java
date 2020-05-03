package com.example.autogear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

public class Schedule_appointment extends AppCompatActivity {

    CalendarView calender;
    TextView date_view;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);

        // By ID we can use each component
        // which id is assign in xml file
        // use findViewById() to get the
        // CalendarView and TextView
        calender = (CalendarView)
                findViewById(R.id.calender);
        date_view = (TextView)
                findViewById(R.id.date_view);
        confirm=findViewById(R.id.confirm);

        // Add Listener in calendar
        calender
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            // In this Listener have one method
                            // and in this method we will
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {

                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0
                                String Date
                                        = dayOfMonth + "-"
                                        + (month + 1) + "-" + year;

                                // set this date in TextView for Display
                                date_view.setText(Date);
                            }
                        });
    }

    public void onconfirm(View view) {
        Intent in=new Intent(this,OtherProblemDetailsActivity.class);
        startActivity(in);
    }
}