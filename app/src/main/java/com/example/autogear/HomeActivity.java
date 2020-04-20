package com.example.autogear;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.othermenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.logout:
                signOut();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void signOut() {
        Intent b1 = new Intent(this, LoginActivity.class);
        startActivity(b1);
    }
}
