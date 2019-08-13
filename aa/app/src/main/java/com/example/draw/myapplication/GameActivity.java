package com.example.draw.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;


public class GameActivity extends AppCompatActivity {
    TextView textTIme;
    ProgressBar pb;
    int time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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
            case 20:
                tv.setText("高校一年生");
                break;
            case 21:
                tv.setText("高校二年生");
                break;
            case 22:
                tv.setText("高校三年生");
                break;
            default:
                tv.setText("不正");
                break;
        }

    }

}
