package com.example.autogear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OtherProblemsActivity extends AppCompatActivity {
    Button sendBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_problems);


        sendBTN = findViewById(R.id.sendBTN);
        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherProblemsActivity.this, Otherproblem_appointments.class));
            }
        });
        }
    }






