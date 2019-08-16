package com.example.graphview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double y,x;
        double a,b,c,d;
        //いじって
        a = 4.0;
        b = 1.0;
        c = 1.0;
        d = 0.0;

        //いじらないで
        x = 0.0;

        GraphView graph = (GraphView) findViewById(R.id.graph1);
        series = new LineGraphSeries<DataPoint>();

        graph.addSeries(series);

        graph.getViewport().setMinX(-10);
        graph.getViewport().setMaxX(10);
        graph.getViewport().setMinY(-10);
        graph.getViewport().setMaxY(10);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);

        for(int i = -50; i < 50; i++) {
            x = x + 0.1;
            //やりたい関数かいて
            y = a * x * x + b * x + c;
            //ここまで

            series.appendData(new DataPoint(x, y), true, 100);
        }
        graph.addSeries(series);
    }
}
