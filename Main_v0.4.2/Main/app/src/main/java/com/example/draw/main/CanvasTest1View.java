package com.example.draw.main;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class CanvasTest1View extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawView view = new DrawView(getApplication());
        setContentView(view);
    }
}

class DrawView extends View {

    public DrawView(Context context) {
        super(context);
    }

    public void onDraw(Canvas c){
        // TODO: ここに描画処理を追加
    }

}