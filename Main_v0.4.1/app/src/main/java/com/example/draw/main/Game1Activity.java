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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import au.com.bytecode.opencsv.CSVReader;

public class Game1Activity extends AppCompatActivity {
    PointsGraphSeries<DataPoint> series;
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
    private String str;
    String judge;
    //関数の係数が順番に入っている（,の前が空欄の場合だけ係数が1）
    private String buff;

    //CSVから取得した関数の各係数などの値を格納する二次元配列
    private double suuji[][] = new double[11][8];
    //suuji2[0]が１の時Y =、0の時X=の関数
    private double suuji2[] = new double[8];

    Handler handler;
    Runnable runnable;
    GraphView graph;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        graph = findViewById(R.id.graph1);

        //グラフの軸表示
        startGraphView(graph);


        // ビューの初期化
        answerButton1 =  findViewById(R.id.button1);
        answerButton2 =  findViewById(R.id.button2);
        answerButton3 =  findViewById(R.id.button3);
        answerButton4 =  findViewById(R.id.button4);
        answerButton5 =  findViewById(R.id.button5);
        answerButton6 =  findViewById(R.id.button6);
        answerButton7 =  findViewById(R.id.button7);
        answerButton8 =  findViewById(R.id.button8);
        answerButton9 =  findViewById(R.id.button9);
        answerButton10 = findViewById(R.id.button10);

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
//                case 14:
//                    //中学一年生レベル4
//                    NumberOfAnswer = 3;
//                    NumberOfSection = 3;
//                    break;
//                case 15:
//                    //中学一年生レベル5
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 4;
//                    break;
                case 21:
                    //中学二年生レベル1
                    NumberOfAnswer = 3;
                    NumberOfSection = 3;
                    break;
                case 22:
                    //中学二年生レベル2
                    NumberOfAnswer = 3;
                    NumberOfSection = 4;
                    break;
                case 23:
                    //中学二年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 5;
                    break;
//                case 24:
//                    //中学二年生レベル4
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 8;
//                    break;
//                case 25:
//                    //中学二年生レベル5
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 9;
//                    break;
                case 31:
                    //中学三年生レベル1
                    NumberOfAnswer = 3;
                    NumberOfSection = 6;
                    break;
                case 32:
                    //中学三年生レベル2
                    NumberOfAnswer = 6;
                    NumberOfSection = 7;
                    break;
                case 33:
                    //中学三年生レベル3
                    NumberOfAnswer = 6;
                    NumberOfSection = 8;
                    break;
                case 34:
//                    //中学三年生レベル4
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 13;
//                    break;
//                case 35:
//                    //中学三年生レベル5
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 14;
//                    break;
                case 111:
                    //高校一年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 9;
                    break;
                case 112:
                    //高校一年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 10;
                    break;
                case 113:
                    //高校一年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 11;
                    break;
//                case 114:
//                    //高校一年生レベル4
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 18;
//                    break;
//                case 115:
//                    //高校一年生レベル5
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 19;
//                    break;
                case 121:
                    //高校二年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 12;
                    break;
                case 122:
                    //高校二年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 13;
                    break;
                case 123:
                    //高校二年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 14;
                    break;
//                case 124:
//                    //高校二年生レベル4
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 23;
//                    break;
//                case 125:
//                    //高校二年生レベル5
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 24;
//                    plotMain(NumberOfAnswer);
//                    break;
                case 131:
                    //高校三年生レベル1
                    NumberOfAnswer = 4;
                    NumberOfSection = 15;
                    break;
                case 132:
                    //高校三年生レベル2
                    NumberOfAnswer = 4;
                    NumberOfSection = 16;
                    break;
                case 133:
                    //高校三年生レベル3
                    NumberOfAnswer = 4;
                    NumberOfSection = 17;
                    break;
//                case 134:
//                    //高校三年生レベル4
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 28;
//                    break;
//                case 135:
//                    //高校三年生レベル5
//                    NumberOfAnswer = 4;
//                    NumberOfSection = 29;
//                    break;
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

//        //csvファイルから係数の取得
//        for (int i = 1; i < 11; i++) {
//            str = questions[NumberOfSection][i];
//            xvalueGet(str);
//            suuji2 = strChange(buff);
//            for(int j=0; j<7; j++){
//                //二次元配列に係数を格納している
//                suuji[i][j] = suuji2[j];
//            }
//            judge = null;
//        }
        //csvファイルから係数の取得
        for (int i = 1; i < 11; i++) {
            str = questions[NumberOfSection][i];
            xvalueGet(str);
            //格納された値からn次関数(FunctionFlag = 7)か円の式(FunctionFlag = 8)か判定
            String test[] = buff.split(",");
            if(Double.parseDouble(test[0]) > 9){
                for(int j=0; j<8; j++){
                    //二次元配列に係数を格納している
                    suuji2 = strChanger(buff);
                    suuji[i][j] = suuji2[j];
                }
            }
            else{
                suuji2 = strChange(buff);
                for(int j=0; j<7; j++){
                    //二次元配列に係数を格納している
                    suuji[i][j] = suuji2[j];
                }
            }
            judge = null;
            buff = null;
        }


        //お題を黒でプロットするメソッド,答えの数を引数にしてある
        plotMain(NumberOfAnswer);

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
            }
        };

        handler.post(runnable);


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

    //クリックしたボタンに応じた関数をグラフにプロット
    private void numberCertain(int Num[], int number) {
        GraphView graph = findViewById(R.id.graph1);

        if (suuji[Num[number]][0] == 1) {
            if(Num[number] <= NumberOfAnswer){
                drawYGraphView(graph, suuji[Num[number]][5], suuji[Num[number]][6], suuji[Num[number]][1], suuji[Num[number]][2], suuji[Num[number]][3], suuji[Num[number]][4], Color.parseColor("#00adff"));
                answer_count++;
//                //正解すると時間を5秒増やす
//                time = time + 5;
//                if(time >= 100){
//                    time = 101;
//                }
            }
            else{
                drawYGraphView(graph, suuji[Num[number]][5], suuji[Num[number]][6], suuji[Num[number]][1], suuji[Num[number]][2], suuji[Num[number]][3], suuji[Num[number]][4], Color.RED);
                time = time - 10;
            }
        } else if(suuji[Num[number]][0] == 0){
            if(Num[number] <= NumberOfAnswer){
                drawXGraphView(graph, suuji[Num[number]][5], suuji[Num[number]][6], suuji[Num[number]][1], suuji[Num[number]][2], suuji[Num[number]][3], suuji[Num[number]][4], Color.parseColor("#00adff"));
                answer_count++;
                //正解すると時間を5秒増やす
//                time = time + 5;
//                if(time >= 100){
//                    time = 101;
//                }
            }
            else{
                drawXGraphView(graph, suuji[Num[number]][5], suuji[Num[number]][6], suuji[Num[number]][1], suuji[Num[number]][2], suuji[Num[number]][3], suuji[Num[number]][4], Color.RED);
                time = time - 10;
            }
        }
        else{
            if(Num[number] <= NumberOfAnswer){
                drawEnGraphView(graph, suuji[Num[number]][0], suuji[Num[number]][6], suuji[Num[number]][7], suuji[Num[number]][1], suuji[Num[number]][2], suuji[Num[number]][3], suuji[Num[number]][4], suuji[Num[number]][5], Color.GREEN);
                answer_count++;
            }
            else {
                drawEnGraphView(graph, suuji[Num[number]][0], suuji[Num[number]][6], suuji[Num[number]][7], suuji[Num[number]][1], suuji[Num[number]][2], suuji[Num[number]][3], suuji[Num[number]][4], suuji[Num[number]][5], Color.RED);
                time = time -10;
            }
        }

        //解答の正解数が設定した問題の正解数と一致したときに画面遷移
        if (answer_count == NumberOfAnswer) {
            Intent intent = new Intent(Game1Activity.this, ResultActivity.class);
            //画面遷移時に得点をResultActivity.javaのpointに渡す
            intent.putExtra("point", time);
            startActivity(intent);
            handler.removeCallbacks(runnable);
        }
    }

    private void startGraphView(GraphView graph){
        graph.getGridLabelRenderer().setGridColor(Color.parseColor("#ffffff"));
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.parseColor("#ffffff"));
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.parseColor("#ffffff"));
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(-10);
        graph.getViewport().setMaxX(10);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(-10);
        graph.getViewport().setMaxY(10);

        //number of labels on x nd y axis
        graph.getGridLabelRenderer().setNumHorizontalLabels(10);
        graph.getGridLabelRenderer().setNumVerticalLabels(10);
    }

    //関数のパラメータを渡してY=なんちゃらのグラフを描画する(とりあえず3次関数まで 例:y=ax^3+bx^2+c^x+d)
    //int gminは表示させるグラフの最小 , int gmaxは表示させるグラフの最大の範囲を渡す
    //int colorは線の色(例：赤=Color.RED, 青=Color.BLUE, 灰色=Color.GRAY, 黒=Color.BLACK)
    private void drawYGraphView(GraphView graph, double gmin, double gmax, double a, double b, double c, double d, int color){
        double x;
        double y;

        //点を細かくプロットすることで線にする
        for(x = gmin; x < gmax; x += 0.01) {
            series = new PointsGraphSeries<>();
            //線の太さ
            series.setSize(3.0f);
            //線の色
            series.setColor(color);

            //やりたい関数かいて;
            y = a * x * x * x + b * x * x + c * x + d;
            //ここまで

            series.appendData(new DataPoint(x, y), false, 2000);
            graph.addSeries(series);

        }

    }

    //関数のパラメータを渡してX=なんちゃらのグラフを描画する(とりあえず3次関数まで 例:x=ay^3+by^2+c^y+d)
    //int gminは表示させるグラフの最小 , int gmaxは表示させるグラフの最大の範囲を渡す
    //int colorは線の色(例：赤=Color.RED, 青=Color.BLUE, 灰色=Color.GRAY, 黒=Color.BLACK)
    private void drawXGraphView(GraphView graph, double gmin, double gmax, double a, double b, double c, double d, int color){
        double x;
        double y;

        //点を細かくプロットすることで線にする
        for(y = gmin; y < gmax; y += 0.01) {
            series = new PointsGraphSeries<>();
            //線の太さ
            series.setSize(3.0f);
            //線の色
            series.setColor(color);

            //やりたい関数かいて;
            x = a * y * y * y + b * y * y + c * y + d;
            //ここまで

            series.appendData(new DataPoint(x, y), false, 2000);
            graph.addSeries(series);

        }
    }

    //円の式のグラフを描画する(ax^2+bx+cy^2+dy=r)
    private void drawEnGraphView(GraphView graph,double XorYrange, double gmin, double gmax, double a, double b, double c, double d, double r, int color){
        double x = 0;
        double y = 0;
        //範囲がxのとき
        if(XorYrange == 10){
            for(x = gmin; x < gmax; x += 0.1){
                for(y = -10; y < 10; y += 0.1){
                    double res = (a*x*x)+(b*x)+(c*y*y)+(d*y);
                    int resi = (int)res;
                    if(resi == r){
                        series = new PointsGraphSeries<>();
                        //線の太さ
                        series.setSize(3.0f);
                        //線の色
                        series.setColor(color);

                        series.appendData(new DataPoint(x, y), false, 4000000);
                        graph.addSeries(series);
                    }

                }
            }
        }
        //範囲がyのとき
        else{
            for(x = -10; x < 10; x += 0.1){
                for(y = gmin; y < gmax; y += 0.1){
                    double res = (a*x*x)+(b*x)+(c*y*y)+(d*y);
                    int resi = (int)res;
                    if(resi == r){
                        series = new PointsGraphSeries<>();
                        //線の太さ
                        series.setSize(3.0f);
                        //線の色
                        series.setColor(color);

                        series.appendData(new DataPoint(x, y), false, 4000000);
                        graph.addSeries(series);
                    }

                }
            }
        }

    }

    //CSVからお題をプロット
    private void plotMain(int NumberOfAnswer){
        graph = findViewById(R.id.graph1);

        for(int i=0;i<NumberOfAnswer+1;i++){
            if (suuji[i][0] == 1) {
                drawYGraphView(graph, suuji[i][5], suuji[i][6], suuji[i][1], suuji[i][2], suuji[i][3], suuji[i][4], Color.parseColor("#bec500"));
            }
            else if(suuji[i][0] == 0){
                drawXGraphView(graph, suuji[i][5], suuji[i][6], suuji[i][1], suuji[i][2], suuji[i][3], suuji[i][4], Color.parseColor("#bec500"));

            }
            else{
                drawEnGraphView(graph, suuji[i][0], suuji[i][6], suuji[i][7], suuji[i][1], suuji[i][2], suuji[i][3], suuji[i][4], suuji[i][5], Color.parseColor("#bec500"));
            }
        }
    }


//    //csvからx^3の係数を取得するメソッド(valueに数式を入れるとbuffに各係数の値が格納される)
////    private void xvalueGet(String value){
////
////        //"="の前の文字がxかyかの判定
////        String a[] = value.split("=");
////        judge = a[0];
////        //Yの関数の場合judgeにXを格納、また１の時にYの関数と判定
////        if(judge.equals("y")){
////            judge = "x";
////            buff = "1,";
////        }
////        //Xの関数の場合judgeにXを格納、また０の時にXの関数と判定
////        else{
////            judge = "y";
////            buff = "0,";
////        }
////
////        //"x^3+"が文字列内に存在しない場合resultに-1を格納
////        int result = a[1].indexOf(judge + "^3");
////        int result2 = a[1].indexOf(judge + "^3(");
////        if (result != -1) {
////            if (result2 != -1) {
////                a[1] = a[1].replace(judge + "^3(", ",");
////                buff = buff + a[1].substring(0, result2 + 1);
////                String b[] = a[1].split(",");
////                b[1] = "(" + b[1];
////                buff = buff + b[0] + ",";
////                xvalueGet3(b[1]);
////            } else {
////                //judgeがXの場合、文字列"x^3+"を文字列","に変換
////                a[1] = a[1].replace(judge + "^3+", ",");
////                a[1] = a[1].replace(judge + "^3-", ",");
////
////                //a[1]の文字列のインデックスが1からresult + 1までの文字列をbuffに格納
////                buff = a[1].substring(0, result + 1);
////
////                //a[1]の文字列を","の文字で分割、b[0]に","より前半の文字列を格納、b[1]に","より後半の文字列を格納
////                String b[] = a[1].split(",");
////                xvalueGet1(b[1]);
////            }
////        }
////        else {
////            buff = buff + "0,";
////            xvalueGet1(a[1]);
////        }
////    }
////
////    //csvからx^2の係数を取得するメソッド
////    private void xvalueGet1(String b) {
////        int result = b.indexOf(judge + "^2");
////        int result2 = b.indexOf(judge + "^2(");
////        if(result != -1) {
////            if (result2 != -1) {
////                b = b.replace(judge + "^2(", ",");
////                buff = buff + b.substring(0, result2 + 1);
////                String c[] = b.split(",");
////                c[1] = "(" + c[1];
////                buff = buff + c[0] + ",";
////                xvalueGet3(c[1]);
////            } else {
////                b = b.replace(judge + "^2+", ",");
////                b = b.replace(judge + "^2-", ",");
////                buff = buff + b.substring(0, result + 1);
////                String c[] = b.split(",");
////                xvalueGet2(c[1]);
////            }
////        }
////        else{
////            buff = buff + "0,";
////            xvalueGet2(b);
////        }
////    }
////
////    //csvからxの係数を取得するメソッド
////    private void xvalueGet2(String c) {
////        int result = c.indexOf(judge + "+");
////        int result1 = c.indexOf(judge + "-");
////        int result2 = c.indexOf(judge + "(");
////        if(result != -1){
////            c = c.replace(judge + "+",",");
////            buff = buff + c.substring(0,result+1);
////            String d[] = c.split(",");
////            xvalueGet3(d[1]);
////        }
////        else if(result1 != -1){
////            c = c.replace(judge + "-",",");
////            buff = buff + c.substring(0,result1+1);
////            String d[] = c.split(",");
////            d[1] = "-" + d[1];
////            xvalueGet3(d[1]);
////        }
////        else if(result2 != -1){
////            c = c.replace(judge + "(",",");
////            buff = buff + c.substring(0,result1+1);
////            String d[] = c.split(",");
////            d[1] = "(" + d[1];
////            buff = buff + d[0] + ",";
////            xvalueGet3(d[1]);
////        }
////        else if(true){
////            buff = buff + "0,";
////            xvalueGet3(c);
////        }
////    }
////
////    //csvから切片と範囲の値を取得するメソッド
////    private void xvalueGet3(String d) {
////        int result = d.indexOf("(");
////        int result1 = d.length();
////        String e = d.substring(0,result);
////        if(e.isEmpty()){
////            buff = buff + "0,";
////        }
////        else{
////            buff = buff + e + ",";
////        }
////
////        String e1 = d.substring(result+1,result1-1);
////        e1 = e1.replace("<" + judge + "<",",");
////        buff = buff + e1;
////    }
////
////    //文字列から係数を数字にするメソッド、返り値は各係数の値を格納した配列
////    //result[０]はxの関数かyの関数かを判定する値
////    //result[１]はx^3の係数
////    //result[２]はx^２の係数
////    //result[３]はxの係数
////    //result[４]は切片
////    //result[５]は関数の範囲の値が小さい方の値
////    //result[６]は関数の範囲の値が大きい方の値
////    private double[] strChange(String buf){
////        double result[] = new double[7];
////        String a[] = buf.split(",",7);
////        for(int i=0;i<7;i++){
////            if(a[i].isEmpty()){
////                result[i] = 1;
////            }
////            else if(a[i].equals("-")){
////                //String型からDouble型へ変換
////                result[i] = -1;
////            }
////            else if(true){
////                result[i] = Double.parseDouble(a[i]);
////            }
////        }
////        return result;
////    }

    private void xvalueGet(String value){

        //"="の前の文字がxかyかの判定
        String a[] = value.split("=");
        judge = a[0];
        //"="の前の文字数を数えることで円の式かn次関数かを判定
        //n次関数のとき
        if(a[0].length() == 1){
            //Yの関数の場合judgeにXを格納、また１の時にYの関数と判定
            if(judge.equals("y")){
                judge = "x";
                buff = "1,";
            }
            //Xの関数の場合judgeにXを格納、また０の時にXの関数と判定
            else{
                judge = "y";
                buff = "0,";
            }

            //"x^3+"が文字列内に存在しない場合resultに-1を格納
            int result = a[1].indexOf(judge + "^3");
            int result2 = a[1].indexOf(judge + "^3(");
            if (result != -1) {
                if (result2 != -1) {
                    a[1] = a[1].replace(judge + "^3(", ",");
                    buff = buff + a[1].substring(0, result2 + 1);
                    String b[] = a[1].split(",");
                    b[1] = "(" + b[1];
                    buff = buff + "0,";
                    xvalueGet3(b[1]);
                } else {
                    //judgeがXの場合、文字列"x^3+"を文字列","に変換
                    a[1] = a[1].replace(judge + "^3+", ",");
                    a[1] = a[1].replace(judge + "^3-", ",");

                    //a[1]の文字列のインデックスが1からresult + 1までの文字列をbuffに格納
                    buff = a[1].substring(0, result + 1);

                    //a[1]の文字列を","の文字で分割、b[0]に","より前半の文字列を格納、b[1]に","より後半の文字列を格納
                    String b[] = a[1].split(",");
                    xvalueGet1(b[1]);
                }
            }
            else {
                buff = buff + "0,";
                xvalueGet1(a[1]);
            }
        }
        //円の式のとき
        else{
            //変数ごとに存在しなければ-1を格納
            int checkx2 = a[0].indexOf("x^2");
            if(checkx2 != -1){
                a[0] = a[0].replace("x^2+",",");
//                a[0] = a[0].replace("x^2-",",");
                a[0] = a[0].replace("x^2",",");
                //a[1]の文字列のインデックスが1からresult + 1までの文字列をbuffに格納
//                buff = buff + a[0].substring(0, checkx2 + 1);

                //a[1]の文字列を","の文字で分割、b[0]に","より前半の文字列を格納、b[1]に","より後半の文字列を格納
                String b[] = a[0].split(",", 0);
                if(b[1].length() != 0){
                    buff = a[0].substring(0, checkx2 + 1);
                    EnvalueX(b[1], a[1]);
                }


            }
            else{
                buff = "0,";
                EnvalueX(a[0], a[1]);
            }

        }

    }

    //ennox
    private void EnvalueX(String b, String r){
        //変数ごとに存在しなければ-1を格納
        int checkx = b.indexOf("x");
        if(checkx != -1){

            b = b.replace("x+",",");
//            b = b.replace("x-",",");
            b = b.replace("x",",");
            //a[1]の文字列のインデックスが1からresult + 1までの文字列をbuffに格納
            buff = buff + b.substring(0, checkx + 1);

            //a[1]の文字列を","の文字で分割、b[0]に","より前半の文字列を格納、b[1]に","より後半の文字列を格納
            String c[] = b.split(",");
            EnvalueY2(c[1], r);

        }
        else{
            buff = buff + "0,";
            EnvalueY2(b, r);
        }
    }

    //ennnoy^2
    private void EnvalueY2(String c, String r){
        //変数ごとに存在しなければ-1を格納
        int checky2 = c.indexOf("y^2");
        if(checky2 != -1){

            c = c.replace("y^2+",",");
//            c = c.replace("y^2-",",");
            c = c.replace("y^2",",");
            //a[1]の文字列のインデックスが1からresult + 1までの文字列をbuffに格納
            buff = buff + c.substring(0, checky2 + 1);

            //a[1]の文字列を","の文字で分割、b[0]に","より前半の文字列を格納、b[1]に","より後半の文字列を格納
            String d[] = c.split(",");
            if(c.equals(",")){
                EnvalueY(c,r);
            }
            else{
                EnvalueY(d[1], r);
            }

        }
        else{
            buff = buff + "0,";
            EnvalueY(c, r);
        }
    }

    //ennnoy
    private void EnvalueY(String d, String r){
        //変数ごとに存在しなければ-1を格納
        int checky = d.indexOf("y");
        if(checky != -1){

            d =d.replace("y+",",");
//            d = d.replace("y-",",");
            d = d.replace("y",",");
            //a[1]の文字列のインデックスが1からresult + 1までの文字列をbuffに格納
            buff = buff + d.substring(0, checky + 1);

            //a[1]の文字列を","の文字で分割、b[0]に","より前半の文字列を格納、b[1]に","より後半の文字列を格納
            String c[] = d.split(",");
            if(d.equals(",")){
                EnvalueR(r);
            }
            else{
                EnvalueR(r);
            }

        }
        else{
            buff = buff + "0,";
            EnvalueR(r);
        }
    }

    //ennnouhen
    private void EnvalueR(String r){
        //変数ごとに存在しなければ-1を格納
        int checkr = r.indexOf("(");
        if(checkr != -1){
            int RLength = r.length();
//            buff = buff + r.substring(0, RLength-1);
            r = r.replace("(",",");
            int checkRx = r.indexOf("x");
            int checkRy = r.indexOf("y");
            //範囲がx
            if(checkRx != -1){
                r = r.replace("<x<", ",");
                //buffの先頭に10を格納→x範囲の円の式
                buff = "10," + buff + r.substring(0,r.length()-1);
            }
            else{
                //範囲がy
                if(checkRy != -1){
                    r = r.replace("<y<", ",");
                    //buffの先頭に11を格納→y範囲の円の式
                    buff = "11," +  buff + r.substring(0,r.length()-1);
                }
                else{
                    //範囲なし
                    buff = "10," + buff + r + ",-10,10";
                }
            }

        }
        else{
            //範囲なし
            buff = "10," +  buff + r + ",-10,10";
        }
    }


    //csvからx^2の係数を取得するメソッド
    private void xvalueGet1(String b) {
        int result = b.indexOf(judge + "^2");
        int result2 = b.indexOf(judge + "^2(");
        if(result != -1) {
            if (result2 != -1) {
                b = b.replace(judge + "^2(", ",");
                buff = buff + b.substring(0, result2 + 1);
                String c[] = b.split(",");
                c[1] = "(" + c[1];
                buff = buff + "0,";
                xvalueGet3(c[1]);
            } else {
                b = b.replace(judge + "^2+", ",");
                b = b.replace(judge + "^2-", ",");
                buff = buff + b.substring(0, result + 1);
                String c[] = b.split(",");
                xvalueGet2(c[1]);
            }
        }
        else{
            buff = buff + "0,";
            xvalueGet2(b);
        }
    }

    //csvからxの係数を取得するメソッド
    private void xvalueGet2(String c) {
        int result = c.indexOf(judge + "+");
        int result1 = c.indexOf(judge + "-");
        int result2 = c.indexOf(judge + "(");
        if(result != -1){
            c = c.replace(judge + "+",",");
            buff = buff + c.substring(0,result+1);
            String d[] = c.split(",");
            xvalueGet3(d[1]);
        }
        else if(result1 != -1){
            c = c.replace(judge + "-",",");
            buff = buff + c.substring(0,result1+1);
            String d[] = c.split(",");
            d[1] = "-" + d[1];
            xvalueGet3(d[1]);
        }
        else if(result2 != -1){
            c = c.replace(judge + "(",",");
            buff = buff + c.substring(0,result1+1);
            String d[] = c.split(",");
            d[1] = "(" + d[1];
            buff = buff + "0,";
            xvalueGet3(d[1]);
        }
        else if(true){
            buff = buff + "0,";
            xvalueGet3(c);
        }
    }

    //csvから切片と範囲の値を取得するメソッド
    private void xvalueGet3(String d) {
        int result = d.indexOf("(");
        int result1 = d.length();
        String e = d.substring(0,result);
        if(e.isEmpty()){
            buff = buff + "0,";
        }
        else{
            buff = buff + e + ",";
        }

        String e1 = d.substring(result+1,result1-1);
        e1 = e1.replace("<" + judge + "<",",");
        buff = buff + e1;
    }

    //文字列から係数を数字にするメソッド、返り値は各係数の値を格納した配列
    //result[０]はxの関数かyの関数かを判定する値
    //result[１]はx^3の係数
    //result[２]はx^２の係数
    //result[３]はxの係数
    //result[４]は切片
    //result[５]は関数の範囲の値が小さい方の値
    //result[６]は関数の範囲の値が大きい方の値
    //または
    //result[0]から範囲がxかyか，result[1] x^2の係数，result[2]xの係数，result[3] y^２の係数，result[4] yの係数
    //result[5] r^２の値，result[6] 範囲の最小値，result[7] 範囲の最大値
    private double[] strChange(String buf){
        double result[] = new double[7];
        String a[] = buf.split(",",7);
        for(int i=0;i<7;i++){
            if(a[i].isEmpty()){
                result[i] = 1;
            }
            else if(a[i].equals("-")){
                //String型からDouble型へ変換
                result[i] = -1;
            }
            else if(true){
                result[i] = Double.parseDouble(a[i]);
            }
        }
        return result;
    }

    private double[] strChanger(String buf){ ;
        double result[] = new double[8];
        String a[] = buf.split(",",8);
        for(int i=0;i<8;i++){
            if(a[i].isEmpty()){
                result[i] = 1;
            }
            else if(a[i].equals("-")){
                //String型からDouble型へ変換
                result[i] = -1;
            }
            else if(true){
                result[i] = Double.parseDouble(a[i]);
            }
        }
        return result;
    }


}
