package com.example.autogear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                startActivity( new Intent(HomeActivity.this, GeneralServiceActivity.class));
            }
        });
        towingBTN = findViewById(R.id.towingBTN);
        otherBTN = findViewById(R.id.otherBTN);


    }
}
