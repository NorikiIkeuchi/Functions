package com.example.draw.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game1Activity extends AppCompatActivity {
    TextView textTIme;
    ProgressBar pb;
    int time;


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
            case 13:
                tv.setText("高校一年生");
                break;
            case 14:
                tv.setText("高校二年生");
                break;
            case 15:
                tv.setText("高校三年生");
                break;
            default:
                tv.setText("不正");
                break;
        }

    }



    public static class MyView extends View {

        Paint paint;

        public MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 背景、半透明
            canvas.drawColor(Color.argb(127, 0, 127, 63));

            // 円
            paint.setColor(Color.argb(255, 68, 255, 255));
            paint.setStrokeWidth(30);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径
            canvas.drawCircle(450, 450, 100, paint);

            // 矩形
            paint.setColor(Color.argb(255, 255, 190, 0));
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
            canvas.drawRect(480, 480, 850, 880, paint);

            // 線
            paint.setStrokeWidth(15);
            paint.setColor(Color.argb(255, 0, 255, 120));
            // (x1,y1,x2,y2,paint) 始点の座標(x1,y1), 終点の座標(x2,y2)
            canvas.drawLine(350, 850, 750, 630, paint);
        }
    }
}
