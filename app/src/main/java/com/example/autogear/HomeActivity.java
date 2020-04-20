package com.example.autogear;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button generalServiceBTN;
    Button towingBTN;
    Button otherBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        generalServiceBTN = findViewById(R.id.generalServiceBTN);
        generalServiceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, GeneralServiceActivity.class));
            }
        });

        towingBTN = findViewById(R.id.towingBTN);
        towingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TowingActivity.class));
            }
        });



        otherBTN = findViewById(R.id.otherBTN);
        otherBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OtherProblemsActivity.class));
            }
        });


    }
}
