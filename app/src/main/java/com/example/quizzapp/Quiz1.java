package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Quiz1 extends AppCompatActivity {
    // INITIALIZE VARIABLES
    int test  = 1;
    TextView time ;
    Button next;
    RadioGroup rg;
    RadioButton rb;
    int score=0;
    String rightAnswer = "Oui";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        next = findViewById(R.id.nextBtn1);
        rg = findViewById(R.id.rgquiz1);
        // Assign Variable
        time = findViewById(R.id.timer);
        // Initialize timer duration
        long duration = TimeUnit.SECONDS.toMillis(30);
        // Initialize countdown
       CountDownTimer Counter = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                // on tick convert milliseconds seconds
                String sDuration = String.format(Locale.FRANCE ,"%02d : %02d", TimeUnit.MILLISECONDS.toMinutes(l),TimeUnit.MILLISECONDS.toSeconds(l)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                time.setText(sDuration);
            }

            @Override
            public void onFinish() {
                //when finished
                // Hide text
                time.setVisibility(View.GONE);
                // Display a toast
                Toast.makeText(getApplicationContext(),"you took too long to Answer",Toast.LENGTH_LONG).show();
                Intent test = new Intent(Quiz1.this, Quiz2.class);
                test.putExtra("score",score );
                startActivity(test);
                finish();
            }
        }.start();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Counter.cancel();
                if(rg.getCheckedRadioButtonId()== -1 )
                {
                    Toast.makeText(Quiz1.this, "You should select one answer",Toast.LENGTH_SHORT).show();
                }
                else {

                    rb = findViewById(rg.getCheckedRadioButtonId());
                    if(rb.getText().toString().equals(rightAnswer)){
                        score++;
                    }
                    Intent i = new Intent(Quiz1.this, Quiz2.class);

                    i.putExtra("score",score);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}