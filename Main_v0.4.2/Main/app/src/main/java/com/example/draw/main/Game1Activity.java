package com.example.draw.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import au.com.bytecode.opencsv.CSVReader;

public class Game1Activity extends AppCompatActivity {
    //csvファイルの問題数
    private int NumberOfQuestions = 1;

    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button answerButton4;
    private Button answerButton5;
    private Button answerButton6;
    private Button answerButton7;
    private Button answerButton8;
    private Button answerButton9;
    private Button answerButton10;
    private String questions[][] = new String[10][11];
    TextView textTIme;
    ProgressBar pb;
    int time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        // ビューの初期化
        answerButton1 = (Button) findViewById(R.id.button1);
        answerButton2 = (Button) findViewById(R.id.button2);
        answerButton3 = (Button) findViewById(R.id.button3);
        answerButton4 = (Button) findViewById(R.id.button4);
        answerButton5 = (Button) findViewById(R.id.button5);
        answerButton6 = (Button) findViewById(R.id.button6);
        answerButton7 = (Button) findViewById(R.id.button7);
        answerButton8 = (Button) findViewById(R.id.button8);
        answerButton9 = (Button) findViewById(R.id.button9);
        answerButton10 = (Button) findViewById(R.id.button10);

        //csvファイルの読み込み
        try {
            AssetManager as = getResources().getAssets();
            InputStream is = as.open("quiz_data.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(is), ',');

            for (int i = 0; i < NumberOfQuestions; i++) {
                questions[i] = reader.readNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 問題をボタンのラベルとして表示
        setQuizText();

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

//        Intent intent = getIntent();
//        int nannido = intent.getIntExtra("難易度", 0);
//
//        TextView tv = findViewById(R.id.textView);
//
//
//        switch (nannido){
//            case 10:
//                tv.setText("中学一年生");
//
//                break;
//            case 11:
//                tv.setText("中学二年生");
//                break;
//            case 12:
//                tv.setText("中学三年生");
//                break;
//            case 13:
//                tv.setText("高校一年生");
//                break;
//            case 14:
//                tv.setText("高校二年生");
//                break;
//            case 15:
//                tv.setText("高校三年生");
//                break;
//            default:
//                tv.setText("不正");
//                break;
//        }

        //画像の定義
        final ImageView vi = findViewById(R.id.imageView);
        final ImageView vi2 = findViewById(R.id.imageView2);

        //ボタンクリックで画像を最前面に移動
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vi.bringToFront();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vi2.bringToFront();
            }
        });

    }

    //ランダムに配列を生成するメソッド
    private int[] createRandomArray(int n, int offset){
        int data[] = new int[n];
        Random random1 = new Random();
        Random random2 = new Random();
        int buf, i, rnd1, rnd2;
        for (i = 0; i < n; i++) {
            data[i] = i + offset;
        }
        for (i = 0; i < n * 10; i++) {
            rnd1 = random1.nextInt(n);
            rnd2 = random2.nextInt(n);
            buf = data[rnd1];
            data[rnd1] = data[rnd2];
            data[rnd2] = buf;
        }
        return data;
    }

    //ボタンに問題をセットするメソッド
    private void setQuizText(){
        int rndNum[] = createRandomArray(10, 0);
        //選択肢をセット
        answerButton1.setText(questions[0][rndNum[0]]);
        answerButton2.setText(questions[0][rndNum[1]]);
        answerButton3.setText(questions[0][rndNum[2]]);
        answerButton4.setText(questions[0][rndNum[3]]);
        answerButton5.setText(questions[0][rndNum[4]]);
        answerButton6.setText(questions[0][rndNum[5]]);
        answerButton7.setText(questions[0][rndNum[6]]);
        answerButton8.setText(questions[0][rndNum[7]]);
        answerButton9.setText(questions[0][rndNum[8]]);
        answerButton10.setText(questions[0][rndNum[9]]);

    }

}
