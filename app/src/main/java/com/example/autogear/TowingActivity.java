package com.example.autogear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TowingActivity extends AppCompatActivity {

    Button button;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_towing);
        button=findViewById(R.id.sendLocationBTN);
        autoCompleteTextView = findViewById(R.id.checkLocation);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(TowingActivity.this, android.R.layout.simple_list_item_1));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startpt = autoCompleteTextView.getText().toString();
                if (startpt.isEmpty()) {
                    Toast.makeText(TowingActivity.this, "Enter Location!!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent mapIntent = new Intent(TowingActivity.this, MapsActivity.class);
                    mapIntent.putExtra("start", startpt);
                    startActivity(mapIntent);
                } }
        });
    }
}
