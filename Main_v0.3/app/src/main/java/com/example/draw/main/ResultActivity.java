package com.example.draw.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Game1Activity.javaから得点を受け取り、表示する
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int point = bundle.getInt("point");
            TextView scoreText = (TextView) findViewById(R.id.textView_RPoint);
            scoreText.setText(String.format("%d", point));
        }

        Button button = findViewById(R.id.buttonBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
