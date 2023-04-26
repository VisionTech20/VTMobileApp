package com.example.beta2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Survey extends AppCompatActivity {

    private TextView surveyQuestion1, surveyQuestion2, surveyQuestion3, surveyQuestion4;
    private RadioGroup colorOptions1, colorOptions2, colorOptions3, colorOptions4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        surveyQuestion1 = findViewById(R.id.survey_question1);
        colorOptions1 = findViewById(R.id.color_options1);
        surveyQuestion2 = findViewById(R.id.survey_question2);
        colorOptions2 = findViewById(R.id.color_options2);
        surveyQuestion3 = findViewById(R.id.survey_question3);
        colorOptions3 = findViewById(R.id.color_options3);
        surveyQuestion4 = findViewById(R.id.survey_question4);
        colorOptions4 = findViewById(R.id.color_options4);

        TextView textView = findViewById(R.id.textView2);
        textView.setText("If so what is it?");

        TextInputEditText textInputEditText = findViewById(R.id.textInputEditText);
        String text = textInputEditText.getText().toString();


        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOptionId = colorOptions1.getCheckedRadioButtonId();
                RadioButton selectedOption = findViewById(selectedOptionId);
                Toast.makeText(Survey.this, "Submission Successful", Toast.LENGTH_SHORT).show();

                Button button7 = (Button) findViewById(R.id.button7);
                Button button8 = (Button) findViewById(R.id.button8);
                Button button9 = (Button) findViewById(R.id.button9);

                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Survey.this,loginActrivity.class);
                        startActivity(intent);
                    }
                });

                button8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Survey.this,MainActivity2.class);
                        startActivity(intent);
                    }
                });

                button9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Survey.this,HomeActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}

