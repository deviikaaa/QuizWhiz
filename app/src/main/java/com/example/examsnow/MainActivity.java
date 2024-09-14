package com.example.examsnow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        TextView signup = findViewById(R.id.signup);

        // Initialize Firebase Auth
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        // If the user is already logged in, navigate to Home
        if (user != null) {
            Intent i = new Intent(MainActivity.this, Home.class);
            i.putExtra("User UID",user.getUid());
            startActivity(i);
            finish();
        }

        // Handle login button click
        login.setOnClickListener(view -> {
            ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    String em=email.getText().toString();
                    String pass=password.getText().toString();
                    auth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(MainActivity.this,
                            (OnCompleteListener<AuthResult>) task->{
                        if(task.isSuccessful()){
                            FirebaseUser user=auth.getCurrentUser();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Intent i = new Intent(MainActivity.this, Home.class);
                                    i.putExtra("User UID",user.getUid());
                                    startActivity(i);
                                    finish();
                                }
                            });
                        } else{
                            Toast.makeText(MainActivity.this, "Operation Failed.", Toast.LENGTH_SHORT).show();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                }
                            });
                        }
                            });
                }
            });
//            String userEmail = email.getText().toString().trim();
//            String userPassword = password.getText().toString().trim();

            // Firebase authentication logic here (e.g., auth.signInWithEmailAndPassword)
            // You can handle errors and validation inside this block
            thread.start();
        });

        // Handle signup button click
        signup.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, Signup.class);
            startActivity(i);
            finish();
        });
    }

}