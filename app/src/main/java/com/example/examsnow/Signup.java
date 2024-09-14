package com.example.examsnow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText first_name = findViewById(R.id.first_name);
        EditText last_name = findViewById(R.id.last_name);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText confirm_password = findViewById(R.id.confirm_password);

        Button signup = findViewById(R.id.signup);
        TextView login = findViewById(R.id.login);

        login.setOnClickListener(view -> {
            Intent intent = new Intent(Signup.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
