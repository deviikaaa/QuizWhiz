package com.example.examsnow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        EditText first_name = findViewById(R.id.first_name);
        EditText last_name = findViewById(R.id.last_name);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText confirm_password = findViewById(R.id.confirm_password);

        Button signup = findViewById(R.id.signup);
        TextView login = findViewById(R.id.login);

        signup.setOnClickListener(view -> {
            ProgressDialog progressDialog = new ProgressDialog(Signup.this);
            progressDialog.setMessage("Loading..");
            progressDialog.setCancelable(false);
            progressDialog.show();

            String pass = password.getText().toString();
            String confirmPass = confirm_password.getText().toString();
            String em = email.getText().toString();
            String firstName = first_name.getText().toString();
            String lastName = last_name.getText().toString();

            // Validate password
            if (!pass.equals(confirmPass)) {
                confirm_password.setError("Password doesn't match");
                progressDialog.dismiss();
                return;
            }

            // Firebase Authentication
            auth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(Signup.this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    DatabaseReference ref = database.child("Users").child(user.getUid());

                    // Save user details to Firebase Database
                    ref.child("First Name").setValue(firstName);
                    ref.child("Last Name").setValue(lastName).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent i = new Intent(Signup.this, Home.class);
                            i.putExtra("User UID", user.getUid());
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(Signup.this, "Failed to save user data", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                } else {
                    Toast.makeText(Signup.this, "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        });

        // Redirect to login screen
        login.setOnClickListener(view -> {
            Intent intent = new Intent(Signup.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
