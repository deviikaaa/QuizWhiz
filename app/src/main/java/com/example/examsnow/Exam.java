package com.example.examsnow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.cert.PKIXRevocationChecker;

public class Exam extends AppCompatActivity {

        private Question[] data;
        private String quizID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        ListView listView=findViewById(R.id.listview);
        Button submit=findViewById(R.id.submit);
        TextView title=findViewById(R.id.title);

    }
    public class ListAdapter extends BaseAdapter{
        Question[] arr;
        ListAdapter(Question[] arr2){
            arr=arr2;
        }

        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            View v=inflater.inflate(R.layout.question,null);
            TextView question=v.findViewById(R.id.question);
            RadioButton option1=v.findViewById(R.id.option1);
            RadioButton option2=v.findViewById(R.id.option2);
            RadioButton option3=v.findViewById(R.id.option3);
            RadioButton option4=v.findViewById(R.id.option4);

            question.setText(data[i].getQuestion());
            option1.setText(data[i].getOption1());
            option2.setText(data[i].getOption2());
            option3.setText(data[i].getOption3());
            option4.setText(data[i].getOption4());

            option1.setOnCheckedChangeListener((compoundButton,b)->{
                if (b) data[i].setSelectedAnswer(1);
            });

            option2.setOnCheckedChangeListener((compoundButton,b)->{
                if (b) data[i].setSelectedAnswer(2);
            });
            option3.setOnCheckedChangeListener((compoundButton,b)->{
                if (b) data[i].setSelectedAnswer(3);
            });
            option4.setOnCheckedChangeListener((compoundButton,b)->{
                if (b) data[i].setSelectedAnswer(4);
            });

            switch (data[i].getSelectedAnswer()){
                case1:option1.setChecked(true);
                break;

                case2:option2.setChecked(true);
                break;
                case3:option4.setChecked(true);
                break;
                case4:option4.setChecked(true);
                break;
            }


            return v;
        }
    }
}