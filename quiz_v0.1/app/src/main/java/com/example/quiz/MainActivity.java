package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

import au.com.bytecode.opencsv.CSVReader;

public class MainActivity extends AppCompatActivity {
    //csvファイルの問題数
    private int NumberOfQuestions = 1;

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
        setContentView(R.layout.activity_main);

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

//        // 問題をセット
//        questions[0][0] = "テストの問題です";
//        questions[0][1] = "解答1";
//        questions[0][2] = "解答2";
//        questions[0][3] = "解答3";
//        questions[0][4] = "解答4";
//        questions[0][5] = "解答5";
//        questions[0][6] = "解答6";
//        questions[0][7] = "解答7";
//        questions[0][8] = "解答8";
//        questions[0][9] = "解答9";
//        questions[0][10] = "解答10";

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
