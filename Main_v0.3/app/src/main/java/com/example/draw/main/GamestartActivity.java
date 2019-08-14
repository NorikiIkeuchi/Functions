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

        //SelectActivity.javaから変数nannidoを受け取り、表示
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            final int nannido = bundle.getInt("難易度");
            TextView tv = findViewById(R.id.textView5);

            switch (nannido){
                case 11:
                    tv.setText("中学一年生レベル1");
                    break;
                case 12:
                    tv.setText("中学一年生レベル2");
                    break;
                case 13:
                    tv.setText("中学一年生レベル3");
                    break;
                case 14:
                    tv.setText("中学一年生レベル4");
                    break;
                case 15:
                    tv.setText("中学一年生レベル5");
                    break;
                case 21:
                    tv.setText("中学二年生レベル1");
                    break;
                case 22:
                    tv.setText("中学二年生レベル2");
                    break;
                case 23:
                    tv.setText("中学二年生レベル3");
                    break;
                case 24:
                    tv.setText("中学二年生レベル4");
                    break;
                case 25:
                    tv.setText("中学二年生レベル5");
                    break;
                case 31:
                    tv.setText("中学三年生レベル1");
                    break;
                case 32:
                    tv.setText("中学三年生レベル2");
                    break;
                case 33:
                    tv.setText("中学三年生レベル3");
                    break;
                case 34:
                    tv.setText("中学三年生レベル4");
                    break;
                case 35:
                    tv.setText("中学三年生レベル5");
                    break;
                case 111:
                    tv.setText("高校一年生レベル1");
                    break;
                case 112:
                    tv.setText("高校一年生レベル2");
                    break;
                case 113:
                    tv.setText("高校一年生レベル3");
                    break;
                case 114:
                    tv.setText("高校一年生レベル4");
                    break;
                case 115:
                    tv.setText("高校一年生レベル5");
                    break;
                case 121:
                    tv.setText("高校二年生レベル1");
                    break;
                case 122:
                    tv.setText("高校二年生レベル2");
                    break;
                case 123:
                    tv.setText("高校二年生レベル3");
                    break;
                case 124:
                    tv.setText("高校二年生レベル4");
                    break;
                case 125:
                    tv.setText("高校二年生レベル5");
                    break;
                case 131:
                    tv.setText("高校三年生レベル1");
                    break;
                case 132:
                    tv.setText("高校三年生レベル2");
                    break;
                case 133:
                    tv.setText("高校三年生レベル3");
                    break;
                case 134:
                    tv.setText("高校三年生レベル4");
                    break;
                case 135:
                    tv.setText("高校三年生レベル5");
                    break;
                default:
                    tv.setText("不正");
                    break;
            }

            //変数nannidoをGame1Activityに渡し、画面遷移
            Button button = findViewById(R.id.buttonStart);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GamestartActivity.this, Game1Activity.class);
                    intent.putExtra("難易度", nannido);
                    startActivity(intent);
                }
            });

        }

    }
}
