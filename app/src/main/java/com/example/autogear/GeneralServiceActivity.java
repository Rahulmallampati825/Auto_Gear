package com.example.autogear;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GeneralServiceActivity extends AppCompatActivity {


    Button sendBTN;
    private EditText yearET;
    private EditText modelET;
    private EditText milesET;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_service);



        sendBTN = findViewById(R.id.sendBTN);
        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String year = yearET.getText().toString().trim();
                String model = modelET.getText().toString().trim();
                String miles = milesET.getText().toString().trim();

                Map<String, Object> user = new HashMap<>();
                user.put("Year", year);
                user.put("Model", model);
                user.put("Miles", miles);

// Add a new document with a generated ID
                db.collection("General service")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(GeneralServiceActivity.this, "Data added ", Toast.LENGTH_SHORT).show();
                                //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               // Log.w(TAG, "Error adding document", e);
                                Toast.makeText(GeneralServiceActivity.this,"Error occured",Toast.LENGTH_SHORT).show();
                            }
                        });

                finish();
                startActivity(new Intent(GeneralServiceActivity.this, AppointmentActivity.class));
            }
        });

    }
}
