package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public String EXTRA_SCORE = "SCORE";
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button nextButton;

    private List<Questions> questionsList;

    private int questionCounter;
    private int questionCountTotal;
    private Questions currentQuestion;

    private ColorStateList textColorDefaultRadioBtn;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_score);
        textViewQuestionCount = findViewById(R.id.text_question_number);
        rg = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        rb3 = findViewById(R.id.option3);
        rb4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.Next_btn);

        textColorDefaultRadioBtn = rb1.getTextColors();
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionsList = dbHelper.getAllQuestions();
        questionCountTotal = questionsList.size();
        Collections.shuffle(questionsList);

        showNextQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_LONG).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRadioBtn);
        rb2.setTextColor(textColorDefaultRadioBtn);
        rb3.setTextColor(textColorDefaultRadioBtn);
        rb4.setTextColor(textColorDefaultRadioBtn);
        rg.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionsList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestions());

            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            nextButton.setText("Confirm");
        } else {
            finishQuiz();
        }
    }


    private void checkAnswer() {
        answered = true;
        RadioButton rbselected = findViewById(rg.getCheckedRadioButtonId());
        int answerNumber = rg.indexOfChild(rbselected) + 1;

        if (answerNumber == currentQuestion.getAnswerNumber()) {
            score++;
            textViewScore.setText("Score: " + score);
        }
        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNumber()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 4 is correct");
                break;
        }
        if (questionCounter < questionCountTotal) {
            nextButton.setText("Next");
        } else {
            nextButton.setText("Finish");
        }
    }

    private void finishQuiz() {
        String score = textViewScore.getText().toString();
        Intent i = new Intent(QuizActivity.this, ResultPrediction.class);
        i.putExtra(Intent.EXTRA_TEXT, score);
        startActivity(i);
//        finish();
    }
}