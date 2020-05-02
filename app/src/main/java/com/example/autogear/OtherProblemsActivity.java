package com.example.autogear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OtherProblemsActivity extends AppCompatActivity {
    Button sendBTN;
    EditText problemsET,othermodelET,otherYearET,otherMilesET;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_problems);

        db = FirebaseFirestore.getInstance();

        problemsET = (EditText) findViewById(R.id.problemET);
        othermodelET = (EditText) findViewById(R.id.othermodelET);
        otherYearET = (EditText) findViewById(R.id.otherYearET);
        otherMilesET = (EditText) findViewById(R.id.otherMilesET);



        sendBTN = findViewById(R.id.otherSendBTN);
        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String problem = problemsET.getText().toString();
                String model = othermodelET.getText().toString();
                String year = otherYearET.getText().toString();
                String miles = otherMilesET.getText().toString();


                Map<String, Object> user = new HashMap<>();
                user.put("Year", year);
                user.put("Model", model);
                user.put("Miles", miles);
                user.put("Problem",problem);

// Add a new document with a generated ID
                db.collection("Other Problems")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(OtherProblemsActivity.this, "Data added ", Toast.LENGTH_SHORT).show();
                                //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Log.w(TAG, "Error adding document", e);
                                Toast.makeText(OtherProblemsActivity.this,"Error occured",Toast.LENGTH_SHORT).show();
                            }
                        });


                finish();
                startActivity(new Intent(OtherProblemsActivity.this, ScheduleAppointment.class));
            }
        });
        }
    }






