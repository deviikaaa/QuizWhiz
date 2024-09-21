package com.example.examsnow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExamEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_editor);

    }

    public static class CustomAdapter extends RecyclerView.Adapter<ExamEditor.CustomAdapter.ViewHolder>{
        private final ArrayList<Question> arr;
        public static class ViewHolder extends RecyclerView.ViewHolder{
            private final EditText question;
            private final RadioButton option1rb;
            private final RadioButton option2rb;
            private final RadioButton option3rb;
            private final RadioButton option4rb;
            private final EditText option1et;
            private final EditText option2et;
            private final EditText option3et;
            private final EditText option4et;
            private final LinearLayout new_question;

            public ViewHolder(View view){
                super(view);
                question=view.findViewById(R.id.question);
                option1rb=view.findViewById(R.id.option1rb);
                option2rb=view.findViewById(R.id.option2rb);
                option3rb=view.findViewById(R.id.option3rb);
                option4rb=view.findViewById(R.id.option4rb);

                option1et=view.findViewById(R.id.option1et);
                option2et=view.findViewById(R.id.option2et);
                option3et=view.findViewById(R.id.option3et);
                option4et=view.findViewById(R.id.option4et);
                new_question=view.findViewById(R.id.new_question);

            }

            public EditText getQuestion() {
                return question;
            }

            public RadioButton getOption1rb() {
                return option1rb;
            }

            public RadioButton getOption2rb() {
                return option2rb;
            }

            public RadioButton getOption3rb() {
                return option3rb;
            }

            public RadioButton getOption4rb() {
                return option4rb;
            }

            public EditText getOption1et() {
                return option1et;
            }

            public EditText getOption2et() {
                return option2et;
            }

            public EditText getOption3et() {
                return option3et;
            }

            public EditText getOption4et() {
                return option4et;
            }

            public LinearLayout getNew_question() {
                return new_question;
            }
        }

        public CustomAdapter (ArrayList<Question> data){
            arr=data;
        }
        @NonNull
        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.question_edit,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
            holder.setIsRecyclable(false);
            holder.getQuestion().setText(arr.get(position).getQuestion());
            holder.getOption1et().setText(arr.get(position).getOption1());
            holder.getOption2et().setText(arr.get(position).getOption2());
            holder.getOption3et().setText(arr.get(position).getOption3());
            holder.getOption4et().setText(arr.get(position).getOption4());

            switch (arr.get(position).getCorrectAnswer()){
                case 1: holder.getOption1rb().setChecked(true);
                break;

                case 2: holder.getOption2rb().setChecked(true);
                    break;

                case 3: holder.getOption3rb().setChecked(true);
                    break;
                case 4: holder.getOption4rb().setChecked(true);
                    break;
            }

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}