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
    private int NumberOfQuestions = 30;
    //正解の数
    private int NumberOfAnswer = 4;
    //難易度別に問題を選択するための変数
    private int NumberOfSection = 0;

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
    private String questions[][] = new String[30][11];
    TextView textTIme;
    ProgressBar pb;
    int time;
    int count;
    int answer_count = 0;
    private ImageView vi;
    private ImageView vi2;
    private ImageView vi3;
    private ImageView vi4;
    private ImageView vi6;
    Handler handler;
    Runnable runnable;


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
        vi = findViewById(R.id.imageView);
        vi2 = findViewById(R.id.imageView2);
        vi3 = findViewById(R.id.imageView3);
        vi4 = findViewById(R.id.imageView4);
        vi6 = findViewById(R.id.imageView6);

        //GamestartActivity.javaから受け取った変数nannidoから問題の正解数と、quiz_data.csvからの問題選択を行う配列questions[]に使用する変数NumberOfQuestionの設定を行う
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int nannido = bundle.getInt("難易度");
            switch (nannido){
                case 11:
                    //中学一年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 0;
                    break;
                case 12:
                    //中学一年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 1;
                    break;
                case 13:
                    //中学一年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 2;
                    break;
                case 14:
                    //中学一年生レベル4
                    NumberOfAnswer = 4;
                    NumberOfSection = 3;
                    break;
                case 15:
                    //中学一年生レベル5
                    NumberOfAnswer = 4;
                    NumberOfSection = 4;
                    break;
                case 21:
                    //中学二年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 5;
                    break;
                case 22:
                    //中学二年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 6;
                    break;
                case 23:
                    //中学二年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 7;
                    break;
                case 24:
                    //中学二年生レベル4
                    NumberOfAnswer = 4;
                    NumberOfSection = 8;
                    break;
                case 25:
                    //中学二年生レベル5
                    NumberOfAnswer = 4;
                    NumberOfSection = 9;
                    break;
                case 31:
                    //中学三年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 10;
                    break;
                case 32:
                    //中学三年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 11;
                    break;
                case 33:
                    //中学三年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 12;
                    break;
                case 34:
                    //中学三年生レベル4
                    NumberOfAnswer = 4;
                    NumberOfSection = 13;
                    break;
                case 35:
                    //中学三年生レベル5
                    NumberOfAnswer = 4;
                    NumberOfSection = 14;
                    break;
                case 111:
                    //高校一年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 15;
                    break;
                case 112:
                    //高校一年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 16;
                    break;
                case 113:
                    //高校一年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 17;
                    break;
                case 114:
                    //高校一年生レベル4
                    NumberOfAnswer = 4;
                    NumberOfSection = 18;
                    break;
                case 115:
                    //高校一年生レベル5
                    NumberOfAnswer = 4;
                    NumberOfSection = 19;
                    break;
                case 121:
                    //高校二年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 20;
                    break;
                case 122:
                    //高校二年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 21;
                    break;
                case 123:
                    //高校二年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 22;
                    break;
                case 124:
                    //高校二年生レベル4
                    NumberOfAnswer = 4;
                    NumberOfSection = 23;
                    break;
                case 125:
                    //高校二年生レベル5
                    NumberOfAnswer = 4;
                    NumberOfSection = 24;
                    break;
                case 131:
                    //高校三年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 25;
                    break;
                case 132:
                    //高校三年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 26;
                    break;
                case 133:
                    //高校三年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 27;
                    break;
                case 134:
                    //高校三年生レベル4
                    NumberOfAnswer = 4;
                    NumberOfSection = 28;
                    break;
                case 135:
                    //高校三年生レベル5
                    NumberOfAnswer = 4;
                    NumberOfSection = 29;
                    break;
                default:
                    //不正
                    break;
            }
        }


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
        final int Num[] = setQuizText(NumberOfSection);

        textTIme = findViewById(R.id.textTime);
        pb = findViewById(R.id.progressBar);
        time = 100;
        textTIme.setText(String.valueOf(time));
        pb.setProgress(time);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

//                if(time <= 0){
//                    //
//                    Intent intent = new Intent(Game1Activity.this, ResultActivity.class);
//                    startActivity(intent);
//                    //intentしつづけないようにする処理
//                    time = 10000;
//                }
                time--;
                textTIme.setText(String.valueOf(time));
                pb.setProgress(time);
                handler.postDelayed(this, 1000);

                //時間切れ時に画面遷移
                if(time <= 0) {
                    Intent intent = new Intent(Game1Activity.this, ResultActivity.class);
                    //画面遷移時に得点をResultActivity.javaのpointに渡す
                    intent.putExtra("point", time);
                    startActivity(intent);
                    handler.removeCallbacks(runnable);

                }
//                else{
//                    time--;
//                    textTIme.setText(String.valueOf(time));
//                    pb.setProgress(time);
//                    handler.postDelayed(this, 1000);
//                }


            }
        };

        Button button = findViewById(R.id.buttonResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //終了ボタンを押した時にtimeを0に設定
                time = 0;
                Intent intent = new Intent(Game1Activity.this, ResultActivity.class);
                //画面遷移時に得点をResultActivity.javaのpointに渡す
                intent.putExtra("point", time);
                startActivity(intent);
                handler.removeCallbacks(runnable);
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


        answerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                numberCertain(Num, count);

                //ボタン無効化
                answerButton1.setEnabled(false);
            }
        });

        answerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 1;
                numberCertain(Num, count);

                //ボタン無効化
                answerButton2.setEnabled(false);
            }
        });

        answerButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 2;
                numberCertain(Num,count);

                //ボタン無効化
                answerButton3.setEnabled(false);
            }
        });

        answerButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 3;
                numberCertain(Num, count);

                //ボタン無効化
                answerButton4.setEnabled(false);
            }
        });

        answerButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 4;
                numberCertain(Num, count);

                //ボタン無効化
                answerButton5.setEnabled(false);
            }
        });

        answerButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 5;
                numberCertain(Num,count);

                //ボタン無効化
                answerButton6.setEnabled(false);
            }
        });

        answerButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 6;
                numberCertain(Num, count);

                //ボタン無効化
                answerButton7.setEnabled(false);
            }
        });

        answerButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 7;
                numberCertain(Num, count);

                //ボタン無効化
                answerButton8.setEnabled(false);
            }
        });

        answerButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 8;
                numberCertain(Num, count);

                //ボタン無効化
                answerButton9.setEnabled(false);
            }
        });

        answerButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 9;
                numberCertain(Num, count);

                //ボタン無効化
                answerButton10.setEnabled(false);
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
    private int[] setQuizText(int NumberOfSection){
        int rndNum[] = createRandomArray(10, 1);
        //選択肢をセット
        answerButton1.setText(questions[NumberOfSection][rndNum[0]]);
        answerButton2.setText(questions[NumberOfSection][rndNum[1]]);
        answerButton3.setText(questions[NumberOfSection][rndNum[2]]);
        answerButton4.setText(questions[NumberOfSection][rndNum[3]]);
        answerButton5.setText(questions[NumberOfSection][rndNum[4]]);
        answerButton6.setText(questions[NumberOfSection][rndNum[5]]);
        answerButton7.setText(questions[NumberOfSection][rndNum[6]]);
        answerButton8.setText(questions[NumberOfSection][rndNum[7]]);
        answerButton9.setText(questions[NumberOfSection][rndNum[8]]);
        answerButton10.setText(questions[NumberOfSection][rndNum[9]]);

        return rndNum;
    }

    //ボタンクリックで画像を最前面に移動

//    private int[] numberCertain(int Num[], int number) {
//        int array[];
//        array = new int[4];

    private void numberCertain(int Num[], int number) {

        if (Num[number] == 1) {
            vi2.bringToFront();

            //正解時にカウント
            answer_count++;
        } else if (Num[number] == 2) {
            vi3.bringToFront();

            //正解時にカウント
            answer_count++;
        } else if (Num[number] == 3) {
            vi4.bringToFront();

            //正解時にカウント
            answer_count++;
        } else if (Num[number] == 4) {
            vi6.bringToFront();

            //正解時にカウント
            answer_count++;
        } else if (true) {
            time = time - 10;
        }


//        return array;
        //解答の正解数が設定した問題の正解数と一致したときに画面遷移
        if (answer_count == NumberOfAnswer) {
            Intent intent = new Intent(Game1Activity.this, ResultActivity.class);
            //画面遷移時に得点をResultActivity.javaのpointに渡す
            intent.putExtra("point", time);
            startActivity(intent);
            handler.removeCallbacks(runnable);
        }
    }
}
