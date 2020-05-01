package com.example.autogear;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.CollationElementIterator;
import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity implements
        View.OnClickListener {

    Button datePickerBTN, bookButton;
    ;
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        datePickerBTN = findViewById(R.id.datePickerBTN);
        datePickerBTN.setOnClickListener(this);
        bookButton = findViewById(R.id.bookButton);
        bookButton.setOnClickListener(this);
        txtDate = findViewById(R.id.dateTV);
    }

    @Override
    public void onClick(View v) {

        if (v == datePickerBTN) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == bookButton) {
            Intent intent = new Intent(getApplicationContext(), AppintmentDetailsActivity.class);
            startActivity(intent);
        }
    }

}
