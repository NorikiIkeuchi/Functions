package com.example.draw.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GamestartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);

        Button button = findViewById(R.id.buttonStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamestartActivity.this, Game1Activity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int nannido = intent.getIntExtra("難易度", 0);

        TextView tv = findViewById(R.id.textView5);


        switch (nannido){
            case 10:
                tv.setText("レベル1");

                break;
            case 11:
                tv.setText("レベル2");
                break;
            case 12:
                tv.setText("レベル3");
                break;
            case 13:
                tv.setText("レベル4");
                break;
            case 14:
                tv.setText("レベル5");
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
