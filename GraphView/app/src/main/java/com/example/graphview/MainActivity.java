package com.example.graphview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class MainActivity extends AppCompatActivity {

    PointsGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graph = (GraphView) findViewById(R.id.graph1);
        startGraphView(graph);
        drawYGraphView(graph, -10, 10, 0.0, 0.2, 0.0, 0.0);
        drawXGraphView(graph, -5, 3, 0.0, 0.0, 0.0, 2.0);
    }

    private void startGraphView(GraphView graph){
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
    private void drawYGraphView(GraphView graph, int gmin, int gmax, double a, double b, double c, double d){
        double x;
        double y;

        //点を細かくプロットすることで線にする
        for(x = gmin; x < gmax; x += 0.01) {
            series = new PointsGraphSeries<>();
            //線の太さ
            series.setSize(3.0f);

            //やりたい関数かいて;
            y = a * x * x * x + b * x * x + c * x + d;
            //ここまで

            series.appendData(new DataPoint(x, y), false, 2000);
            graph.addSeries(series);

        }

    }

    //関数のパラメータを渡してX=なんちゃらのグラフを描画する(とりあえず3次関数まで 例:x=ay^3+by^2+c^y+d)
    //int gminは表示させるグラフの最小 , int gmaxは表示させるグラフの最大の範囲を渡す
    private void drawXGraphView(GraphView graph, int gmin, int gmax, double a, double b, double c, double d){
        double x;
        double y;

        //点を細かくプロットすることで線にする
        for(y = gmin; y < gmax; y += 0.01) {
            series = new PointsGraphSeries<>();
            //線の太さ
            series.setSize(3.0f);

            //やりたい関数かいて;
            x = a * y * y * y + b * y * y + c * y + d;
            //ここまで

            series.appendData(new DataPoint(x, y), false, 2000);
            graph.addSeries(series);

        }

    }

}
