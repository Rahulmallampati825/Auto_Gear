package com.example.autogear;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class OtherProblemDetailsActivity extends AppCompatActivity {

    TextView otherTV ;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_problem_details);

        db = FirebaseFirestore.getInstance();

        otherTV = findViewById(R.id.OtherTextView);

        db.collection("Other Problems")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG, document.getId() + " => " + document.getData());
                            //String year = (String) document.get("Year");
                            for (QueryDocumentSnapshot document : task.getResult())
                                otherTV.setText("Year : " + document.get("Year") + "\n" + "Model : " + document.get("Model") + "\n" + "Miles : " + document.get("Miles"));
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(OtherProblemDetailsActivity.this,"Error occurred",Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }
}
