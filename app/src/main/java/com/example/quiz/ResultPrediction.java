package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.TextView;

public class ResultPrediction extends AppCompatActivity {

    public static final String EXTRA_SCORE = "Score";
    public TextView finalScore;
    private TextView resultPrediction;
    private int integerScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_prediction);

        resultPrediction = findViewById(R.id.result_prediction);
        finalScore=findViewById(R.id.text_final_score);

        Intent i = getIntent();
        String score = i.getStringExtra(Intent.EXTRA_TEXT);
        finalScore.setText(score);
        integerScore = Integer.getInteger(score);

        finalResultPrediction();

    }
    public void finalResultPrediction(){
        switch (integerScore){
            case 1:
                integerScore = 1;
                resultPrediction.setText("Your covid risk is minimum ");
                break;
            case 2:
                integerScore = 2;
                resultPrediction.setText("Your covid risk is not that much ");
                break;
            case 3:
                integerScore = 3;
                resultPrediction.setText("Your covid risk is high ");
                break;
            case 4:
                integerScore = 4;
                resultPrediction.setText("Your covid risk is maximum ");
                break;
        }
    }
}