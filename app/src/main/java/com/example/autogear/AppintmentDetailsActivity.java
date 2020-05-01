package com.example.autogear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class AppintmentDetailsActivity extends AppCompatActivity {
    TextView detailsTV;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appintment_details);

        db = FirebaseFirestore.getInstance();

        detailsTV = findViewById(R.id.detailsTV);


        db.collection("General service")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d(TAG, document.getId() + " => " + document.getData());

                                //String year = (String) document.get("Year");
                                detailsTV.setText("Year : "+document.get("Year")+"\n"+"Model : "+document.get("Model")+"\n"+"Miles : "+document.get("Miles"));

                            }
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(AppintmentDetailsActivity.this,"Error occurred",Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
}
