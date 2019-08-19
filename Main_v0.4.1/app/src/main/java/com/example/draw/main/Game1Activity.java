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
//    private String ybuff = "0,0,0,";
//    private String b[] = new String[2];
//    private String c[] = new String[2];
//    private String d[] = new String[2];
    private double suuji[][] = new double[11][6];
    private double suuji2[] = new double[6];
    //    private ImageView vi;
//    private ImageView vi2;
//    private ImageView vi3;
//    private ImageView vi4;
//    private ImageView vi6;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        GraphView graph = findViewById(R.id.graph1);

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

                    //例題
                    drawXGraphView(graph, -2, 2, 0, 0, 0, 2, Color.BLACK);
                    drawXGraphView(graph, -2, 2, 0, 0, 0, -2, Color.BLACK);
                    drawYGraphView(graph, -2, 2, 0, 0, 0, 2, Color.BLACK);
                    drawYGraphView(graph, -2, 2, 0, 0, 0, -2, Color.BLACK);
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

        //csvファイルから係数の取得
        for (int i = 1; i < 10; i++) {
            str = questions[NumberOfSection][i];
            xvalueGet(str);
            suuji2 = strChange(buff);
            for(int j=0; j< 6; j++){
                //二次元配列に係数を格納している
                suuji[i][j] = suuji2[j];
            }
            judge = null;
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

        if (Num[number] == 1) {

            drawYGraphView(graph, (int)suuji[1][4], (int)suuji[1][5], suuji[1][0], suuji[1][1], suuji[1][2], suuji[1][3], Color.BLUE);

            //正解時にカウント
            answer_count++;
        } else if (Num[number] == 2) {

            drawXGraphView(graph, (int)suuji[2][4], (int)suuji[2][5], suuji[2][0], suuji[2][1], suuji[2][2], suuji[2][3], Color.BLUE);

            //正解時にカウント
            answer_count++;
        } else if (Num[number] == 3) {

            drawYGraphView(graph, (int)suuji[3][4], (int)suuji[3][5], suuji[3][0], suuji[3][1], suuji[3][2], suuji[3][3], Color.BLUE);

            //正解時にカウント
            answer_count++;
        } else if (Num[number] == 4) {

            drawXGraphView(graph, (int)suuji[4][4], (int)suuji[4][5], suuji[4][0], suuji[4][1], suuji[4][2], suuji[4][3], Color.BLUE);
            //正解時にカウント
            answer_count++;
        } else if (Num[number] == 5) {
            drawYGraphView(graph, (int)suuji[5][4], (int)suuji[5][5], suuji[5][0], suuji[5][1], suuji[5][2], suuji[5][3], Color.RED);

            //間違い
            time = time - 10;
        } else if (Num[number] == 6) {
            drawXGraphView(graph, (int)suuji[6][4], (int)suuji[6][5], suuji[6][0], suuji[6][1], suuji[6][2], suuji[6][3], Color.RED);

            //間違い
            time = time - 10;
        } else if (Num[number] == 7) {
            drawYGraphView(graph, (int)suuji[7][4], (int)suuji[7][5], suuji[7][0], suuji[7][1], suuji[7][2], suuji[7][3], Color.RED);

            //間違い
            time = time - 10;
        } else if (Num[number] == 8) {
            drawXGraphView(graph, (int)suuji[8][4], (int)suuji[8][5], suuji[8][0], suuji[8][1], suuji[8][2], suuji[8][3], Color.RED);

            //間違い
            time = time - 10;
        } else if (Num[number] == 9) {
            drawYGraphView(graph, (int)suuji[9][4], (int)suuji[9][5], suuji[9][0], suuji[9][1], suuji[9][2], suuji[9][3], Color.RED);

            //間違い
            time = time - 10;
        } else if (Num[number] == 10) {
            drawXGraphView(graph, (int)suuji[10][4], (int)suuji[10][5], suuji[10][0], suuji[10][1], suuji[10][2], suuji[10][3], Color.RED);

            //間違い
            time = time - 10;
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
    private void drawYGraphView(GraphView graph, int gmin, int gmax, double a, double b, double c, double d, int color){
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
    private void drawXGraphView(GraphView graph, int gmin, int gmax, double a, double b, double c, double d, int color){
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

    //csvからx^3の係数を取得するメソッド(valueに数式を入れるとbuffに各係数の値が格納される)
    private void xvalueGet(String value){

        String a[] = value.split("=");
        judge = a[0];
        if(judge.equals("y")){
            judge = "x";
        }
        else{
            judge = "y";
        }

        int result = a[1].indexOf(judge + "^3+");
        if (result != -1) {

            a[1] = a[1].replace(judge + "^3+", ",");
            buff = a[1].substring(0, result + 1);
            String b[] = a[1].split(",");
            xvalueGet1(b[1]);
        } else {
            buff = "0,";
            xvalueGet1(a[1]);
        }
    }

    //csvからx^2の係数を取得するメソッド
    private void xvalueGet1(String b) {
        int result = b.indexOf(judge + "^2");
        if(result != -1){
            b = b.replace(judge + "^2+",",");
            buff = buff + b.substring(0,result+1);
            String c[] = b.split(",");
            xvalueGet2(c[1]);
        }
        else{
            buff = buff + "0,";
            xvalueGet2(b);
        }
    }

    //csvからxの係数を取得するメソッド
    private void xvalueGet2(String c) {
        int result = c.indexOf(judge + "+");
        if(result != -1){
            c = c.replace(judge + "+",",");
            buff = buff + c.substring(0,result+1);
            String d[] = c.split(",");
            xvalueGet3(d[1]);
        }
        else{
            buff = buff + "0,";
            xvalueGet3(c);
        }
    }

    //csvから切片と範囲の値を取得するメソッド、(2x+8 (2<x<4))のように空白で判断
    private void xvalueGet3(String d) {
        int result = d.indexOf("[");
        int result1 = d.length();
        String e = d.substring(0,result);
        buff = buff + e + ",";
        String e1 = d.substring(result+1,result1-1);
        e1 = e1.replace("<" + judge + "<",",");
        buff = buff + e1;
    }

    //文字列から係数を数字にするメソッド
    private double[] strChange(String buf){
        double result[] = new double[6];
        String a[] = buf.split(",",6);
        for(int i=0;i<6;i++){
            if(a[i].isEmpty()){
                result[i] = 1;
            }
            else{
                result[i] = Double.parseDouble(a[i]);
            }
        }
        return result;
    }

}
