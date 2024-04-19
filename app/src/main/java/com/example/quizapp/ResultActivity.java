package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.R;

public class ResultActivity extends AppCompatActivity {

    private String userName;
    private int score;
    private TextView congratsText, scoreText;
    private Button newQuizButton, finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        userName = getIntent().getStringExtra("userName");
        score = getIntent().getIntExtra("score", 0);

        congratsText = findViewById(R.id.congratsText);
        scoreText = findViewById(R.id.scoreText);
        newQuizButton = findViewById(R.id.newQuizButton);
        finishButton = findViewById(R.id.finishButton);

        congratsText.setText("Congratulations " + userName+ "!");
        scoreText.setText("Your score: " + score + "/5");

        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
                finish();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
