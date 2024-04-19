package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Question;
import com.example.quizapp.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private String userName;
    private int currentQuestion = 0;
    private int score = 0;
    private ProgressBar progressBar;
    private TextView nameText, questionTitle, questionDetail, progressText;
    private Button submitButton, nextButton,optionAButton, optionBButton, optionCButton, optionDButton, finishButton;
    private List<Question> questionList;
    private String selectedAnswer = "";
    private Button selectedButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        userName = getIntent().getStringExtra("userName");

        nameText = findViewById(R.id.nameText);
        progressBar = findViewById(R.id.progressBar);
        questionTitle = findViewById(R.id.questionTitle);
        questionDetail = findViewById(R.id.questionDetail);
        submitButton = findViewById(R.id.submitButton);
        nextButton = findViewById(R.id.nextButton);
        optionAButton = findViewById(R.id.optionAButton);
        optionBButton = findViewById(R.id.optionBButton);
        optionCButton = findViewById(R.id.optionCButton);
        optionDButton = findViewById(R.id.optionDButton);
        finishButton = findViewById(R.id.finishButton);
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);

        nameText.setText(userName);

        optionAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = optionAButton.getText().toString();
                selectAnswer(optionAButton);
            }
        });

        optionBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = optionBButton.getText().toString();
                selectAnswer(optionBButton);
            }
        });

        optionCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = optionCButton.getText().toString();
                selectAnswer(optionCButton);
            }
        });

        optionDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = optionDButton.getText().toString();
                selectAnswer(optionDButton);
            }
        });

        initializeQuestions();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion++;
                if (currentQuestion < questionList.size()) {
                    updateQuestion();
                } else {
                    displayScore();
                }
            }
        });
/*
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
*/
        updateQuestion();
    }

    private void initializeQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("1 + 1 equals to?", "2", "3", "4", "5", "2"));
        questionList.add(new Question("What is the capital of France?", "Berlin", "Madrid", "Paris", "London", "Paris"));
        questionList.add(new Question("Which planet is known as the Red Planet?", "Mars", "Jupiter", "Venus", "Mercury", "Mars"));
        questionList.add(new Question("What is the largest mammal?", "Elephant", "Blue Whale", "Giraffe", "Lion", "Blue Whale"));
        questionList.add(new Question("Who wrote 'Romeo and Juliet'?", "Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain", "William Shakespeare"));
    }

    private void updateQuestion() {
        Question question = questionList.get(currentQuestion);
        questionTitle.setText("Question " + (currentQuestion + 1));
        questionDetail.setText(question.getQuestion());
        optionAButton.setText(question.getOptionA());
        optionBButton.setText(question.getOptionB());
        optionCButton.setText(question.getOptionC());
        optionDButton.setText(question.getOptionD());
        setAnswerButtonsEnabled(true);
        submitButton.setEnabled(true);
        nextButton.setEnabled(false);

        optionAButton.setTextColor(Color.BLACK);
        optionBButton.setTextColor(Color.BLACK);
        optionCButton.setTextColor(Color.BLACK);
        optionDButton.setTextColor(Color.BLACK);

        optionAButton.setBackgroundColor(Color.WHITE);
        optionBButton.setBackgroundColor(Color.WHITE);
        optionCButton.setBackgroundColor(Color.WHITE);
        optionDButton.setBackgroundColor(Color.WHITE);


        int progress = (currentQuestion + 1) * 100 / questionList.size();
        progressBar.setProgress(progress);
        progressText.setText(String.format("%d/%d", currentQuestion + 1, questionList.size()));
    }

    private void checkAnswer() {

        if (selectedAnswer.isEmpty()) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        Question question = questionList.get(currentQuestion);
        String correctAnswer = question.getCorrectAnswer();

        if (selectedAnswer.equals(correctAnswer)) {
            Toast.makeText(this, "Answer is correct!", Toast.LENGTH_SHORT).show();
            score++;
            highlightAnswer(correctAnswer, true);
        } else {
            Toast.makeText(this, "Answer is wrong!", Toast.LENGTH_SHORT).show();
            highlightAnswer(correctAnswer, false);
        }

        setAnswerButtonsEnabled(false);
        submitButton.setEnabled(false);
        nextButton.setEnabled(true);
    }

    private void highlightAnswer(String correctAnswer, boolean isCorrect) {

        if (!isCorrect) {
            if (correctAnswer.equals(optionAButton.getText().toString())) {
                optionAButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            } else if (correctAnswer.equals(optionBButton.getText().toString())) {
                optionBButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            } else if (correctAnswer.equals(optionCButton.getText().toString())) {
                optionCButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            } else if (correctAnswer.equals(optionDButton.getText().toString())) {
                optionDButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            }
        } else {

            if (selectedAnswer.equals(correctAnswer)) {
                if (selectedAnswer.equals(optionAButton.getText().toString())) {
                    optionAButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                } else if (selectedAnswer.equals(optionBButton.getText().toString())) {
                    optionBButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                } else if (selectedAnswer.equals(optionCButton.getText().toString())) {
                    optionCButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                } else if (selectedAnswer.equals(optionDButton.getText().toString())) {
                    optionDButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                }
            }
        }
    }
    private void selectAnswer(Button button) {

        if (selectedButton != null) {
            selectedButton.setBackgroundColor(Color.WHITE);
            selectedButton.setTextColor(Color.BLACK);
        }

        button.setBackgroundColor(Color.parseColor("#00008B"));
        selectedButton = button;
    }

    private void setAnswerButtonsEnabled(boolean enabled) {
        optionAButton.setEnabled(enabled);
        optionBButton.setEnabled(enabled);
        optionCButton.setEnabled(enabled);
        optionDButton.setEnabled(enabled);
    }
    private void displayScore() {
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
}
