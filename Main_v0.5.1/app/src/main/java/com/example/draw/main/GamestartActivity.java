package com.example.draw.main;

import android.content.Intent;
import android.graphics.Typeface;
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

        TextView myText = findViewById(R.id.buttonStart);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        myText.setTypeface(customFont);

        TextView myText2 = findViewById(R.id.textView5);
        Typeface customFont2 = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        myText2.setTypeface(customFont2);

        TextView myText3 = findViewById(R.id.textView3);
        Typeface customFont3 = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        myText3.setTypeface(customFont3);

        //SelectActivity.javaから変数nannidoを受け取り、表示
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int nannido1 = bundle.getInt("難易度");
            final int trans = nannido1;
            TextView tv = findViewById(R.id.textView5);
            TextView tv1 = findViewById(R.id.textView3);

            switch (nannido1){
                case 11:
                    tv.setText("中学一年生レベル1");
                    tv1.setText("正解の選択数は1つ");
                    break;
                case 12:
                    tv.setText("中学一年生レベル2");
                    tv1.setText("正解の選択数は4つ");
                    break;
                case 13:
                    tv.setText("中学一年生レベル3");
                    tv1.setText("正解の選択数は4つ");
                    break;
//                case 14:
//                    tv.setText("中学一年生レベル4");
//                    break;
//                case 15:
//                    tv.setText("中学一年生レベル5");
//                    break;
                case 21:
                    tv.setText("中学二年生レベル1");
                    tv1.setText("正解の選択数は1つ");
                    break;
                case 22:
                    tv.setText("中学二年生レベル2");
                    tv1.setText("正解の選択数は3つ");
                    break;
                case 23:
                    tv.setText("中学二年生レベル3");
                    tv1.setText("正解の選択数は4つ");
                    break;
//                case 24:
//                    tv.setText("中学二年生レベル4");
//                    break;
//                case 25:
//                    tv.setText("中学二年生レベル5");
//                    break;
                case 31:
                    tv.setText("中学三年生レベル1");
                    tv1.setText("正解の選択数は1つ");
                    break;
                case 32:
                    tv.setText("中学三年生レベル2");
                    tv1.setText("正解の選択数は3つ");
                    break;
                case 33:
                    tv.setText("中学三年生レベル3");
                    tv1.setText("正解の選択数は6つ");
                    break;
                case 34:
//                    tv.setText("中学三年生レベル4");
//                    break;
//                case 35:
//                    tv.setText("中学三年生レベル5");
//                    break;
                case 111:
                    tv.setText("高校一年生レベル1");
                    tv1.setText("正解の選択数は5つ");
                    break;
                case 112:
                    tv.setText("高校一年生レベル2");
                    tv1.setText("正解の選択数は5つ");
                    break;
                case 113:
                    tv.setText("高校一年生レベル3");
                    tv1.setText("正解の選択数は6つ");
                    break;
                case 114:
//                    tv.setText("高校一年生レベル4");
//                    break;
//                case 115:
//                    tv.setText("高校一年生レベル5");
                    break;
                case 121:
                    tv.setText("高校二年生レベル1");
                    tv1.setText("正解の選択数は5つ");
                    break;
                case 122:
                    tv.setText("高校二年生レベル2");
                    tv1.setText("正解の選択数は5つ");
                    break;
                case 123:
                    tv.setText("高校二年生レベル3");
                    tv1.setText("正解の選択数は5つ");
                    break;
                case 124:
//                    tv.setText("高校二年生レベル4");
//                    break;
//                case 125:
//                    tv.setText("高校二年生レベル5");
//                    break;
                case 131:
                    tv.setText("高校三年生レベル1");
                    tv1.setText("正解の選択数は5つ");
                    break;
                case 132:
                    tv.setText("高校三年生レベル2");
                    tv1.setText("正解の選択数は7つ");
                    break;
                case 133:
                    tv.setText("高校三年生レベル3");
                    tv1.setText("正解の選択数は5つ");
                    break;
//                case 134:
//                    tv.setText("高校三年生レベル4");
//                    break;
//                case 135:
//                    tv.setText("高校三年生レベル5");
//                    break;
                default:
                    tv.setText("不正");
                    break;
            }

            //tyuugakusei
            if(nannido1 < 100){
                //変数nannidoをGame1Activityに渡し、画面遷移
                Button button = findViewById(R.id.buttonStart);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GamestartActivity.this, Game1Activity.class);
                        intent.putExtra("難易度", trans);
                        startActivity(intent);
                    }
                });
            }
            //koukousei
            else{
                //変数nannidoをGame1Activityに渡し、画面遷移
                Button button1 = findViewById(R.id.buttonStart);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GamestartActivity.this, Game2Activity.class);
                        intent.putExtra("難易度", trans);
                        startActivity(intent);
                    }
                });
            }


        }

    }
}
