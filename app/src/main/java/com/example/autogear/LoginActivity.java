package com.example.autogear;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //variables used to refer layout
    private EditText userName, userPwd;
    private Button loginBTN;
    private TextView forgotTV, link_signupTV;
    //Firebase authenticator
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.loginEmail);
        userPwd = findViewById(R.id.loginpassword);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                System.out.println(firebaseUser.getDisplayName());
                if (firebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "you are logged in", Toast.LENGTH_SHORT).show();
                    Intent b1 = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(b1);
                } else {
                    Toast.makeText(LoginActivity.this, "PLease login", Toast.LENGTH_SHORT).show();
                }

            }
        };

        loginBTN = findViewById(R.id.userLoginBTN);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String emailid = userName.getText().toString();
                String passwd = userPwd.getText().toString();

                if (emailid.isEmpty()) {
                    userName.setError("Enter the email");
                    userName.requestFocus();
                } else if (passwd.isEmpty()) {
                    userPwd.setError("Enter the password");
                    userPwd.requestFocus();
                } else if (emailid.isEmpty() && passwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(emailid.isEmpty() && passwd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(emailid, passwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {

                                Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                            } else {

                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }

                        }
                    });

                } else {
                    Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotTV = findViewById(R.id.forgotlink);
        forgotTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });

        link_signupTV = findViewById(R.id.link_signup);
        link_signupTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }
}