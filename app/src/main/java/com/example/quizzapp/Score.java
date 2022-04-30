package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

public class Score extends AppCompatActivity {
    TextView wrongA,correctA;

    Button logout,tryAgain;
    DonutProgress progress;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        logout = findViewById(R.id.logoutBtn);
        tryAgain = findViewById(R.id.tryAgainBtn);
        progress = findViewById(R.id.donut_progress);
        wrongA = findViewById(R.id.wrongA);
        correctA = findViewById(R.id.correctA);
        //get score sent via intent
        Intent i = getIntent();
        score = i.getIntExtra("score",0);
        wrongA.setText("Wrong Answers : "+String.valueOf(5-score));
        correctA.setText("Correct Answers : "+score);


        progress.setDonut_progress(String.valueOf(score*100/5));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Score.this, MainActivity.class));
            }
        });
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Score.this, LoadingPage.class));

            }
        });
    }
}