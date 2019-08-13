package com.example.draw.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Game1Activity extends AppCompatActivity {
    TextView textTIme;
    ProgressBar pb;
    int time;
    private TextView questionText;
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
                if (time <= 0) return;
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
//
//        Intent intent = getIntent();
//        int nannido = intent.getIntExtra("難易度", 0);
//
//        TextView tv = findViewById(R.id.textView);
//
//
//        switch (nannido) {
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

        // ビューの初期化
        questionText  = (TextView) findViewById(R.id.text_question);
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

        // 問題をセット
        questions[0][0] = "テストの問題です";
        questions[0][1] = "解答1";
        questions[0][2] = "解答2";
        questions[0][3] = "解答3";
        questions[0][4] = "解答4";
        questions[0][5] = "解答5";
        questions[0][6] = "解答6";
        questions[0][7] = "解答7";
        questions[0][8] = "解答8";
        questions[0][9] = "解答9";
        questions[0][10] = "解答10";
        // 問題をボタンのラベルとして表示
        setQuizText();

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
        int rndNum[] = createRandomArray(10, 1);
        //問題をセット
        questionText.setText(questions[0][0]);
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
