package com.example.examsnow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {
    private String userUID;
    private String firstName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseReference database= FirebaseDatabase.getInstance().getReference();
        ProgressDialog progressDialog=new ProgressDialog(Home.this);
        progressDialog.setCancelMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Bundle b=getIntent().getExtras();
        userUID=b.getString("User UID");

        TextView name=findViewById(R.id.name);
        TextView total_questions=findViewById(R.id.total_questions);
        TextView total_points=findViewById(R.id.total_points);
        Button startQuiz=findViewById(R.id.startQuiz);
        Button createQuiz=findViewById(R.id.createtQuiz);
        RelativeLayout solvedQuizzes=findViewById(R.id.solvedQuizzes);
        RelativeLayout your_quizzes=findViewById(R.id.your_quizzes);
        EditText quiz_title=findViewById(R.id.quiz_title);
        EditText start_quiz_id=findViewById(R.id.start_quiz_id);
        ImageView signout=findViewById(R.id.signout);

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot usersRef=snapshot.child("Users").child(userUID);
                firstName=usersRef.child("First Name").getValue().toString();
                if(usersRef.hasChild("Total Points")){
                    String totalPoints=usersRef.child("Total Points").getValue().toString();
                    int points=Integer.parseInt(totalPoints);
                    total_points.setText(String.format("%03d",points));

                }

                if(usersRef.hasChild("Total Questions")){
                    String totalQuestions=usersRef.child("Total Questions").getValue().toString();
                    int questions=Integer.parseInt(totalQuestions);
                    total_questions.setText(String.format("%03d",questions));
                }
                name.setText("Welcome" +firstName+"!");
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Can't connect", Toast.LENGTH_SHORT).show();

            }
        };
        database.addValueEventListener(listener);
        signout.setOnClickListener(view->{
            FirebaseAuth.getInstance().signOut();
            Intent i=new Intent(Home.this,MainActivity.class);
            startActivity(i);
            finish();

        });


    }
}