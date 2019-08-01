package com.example.draw.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Game1Activity extends AppCompatActivity {
    TextView textTIme;
    ProgressBar pb;
    int time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        textTIme = findViewById(R.id.textTime);
        pb = findViewById(R.id.progressBar);
        time = 100;
        textTIme.setText(String.valueOf(time));
        pb.setProgress(time);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(time <= 0) return;
                time--;
                textTIme.setText(String.valueOf(time));
                pb.setProgress(time);
                handler.postDelayed(this, 1000);
            }
        };

        Button button = findViewById(R.id.buttonResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game1Activity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        handler.post(runnable);

        Intent intent = getIntent();
        int nannido = intent.getIntExtra("難易度", 0);

        TextView tv = findViewById(R.id.textView);


        switch (nannido){
            case 10:
                tv.setText("中学一年生");

                break;
            case 11:
                tv.setText("中学二年生");
                break;
            case 12:
                tv.setText("中学三年生");
                break;
            case 13:
                tv.setText("高校一年生");
                break;
            case 14:
                tv.setText("高校二年生");
                break;
            case 15:
                tv.setText("高校三年生");
                break;
            default:
                tv.setText("不正");
                break;
        }

    }

}
