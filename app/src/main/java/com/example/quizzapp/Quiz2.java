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

public class Quiz2 extends AppCompatActivity {
    TextView time ;
    Button next;
    RadioGroup rg;
    RadioButton rb;
    int score ;

    String rightAnswer = "A droite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
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
                Intent test = new Intent(Quiz2.this, Quiz3.class);
                test.putExtra("score",score );
                startActivity(test);
                finish();
            }
        }.start();

        next = findViewById(R.id.nextBtn2);
        rg = findViewById(R.id.rgquiz2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            //comment
            public void onClick(View v) {
                if (Counter  !=null) {
                    Counter.cancel();
              }

                Intent intent = getIntent();
               score = intent.getIntExtra("score",0);

               if(rg.getCheckedRadioButtonId() == -1){
                   Toast.makeText(Quiz2.this, "You should select one answer",Toast.LENGTH_SHORT).show();
               }else{
                   rb = findViewById(rg.getCheckedRadioButtonId());
                   if(rb.getText().toString().equals(rightAnswer)){
                       score++;
                   }
                   Intent i = new Intent(Quiz2.this, Quiz3.class);

                   i.putExtra("score",score);
                   startActivity(i);
                   finish();
               }



            }
        });
    }
}