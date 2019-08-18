package com.example.draw.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView myText = findViewById(R.id.textView_R1);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        myText.setTypeface(customFont);

        TextView myText4 = findViewById(R.id.textView_R2);
        Typeface customFont4 = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        myText4.setTypeface(customFont4);

        TextView myText2 = findViewById(R.id.buttonBack);
        Typeface customFont2 = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        myText2.setTypeface(customFont2);

        TextView myText3 = findViewById(R.id.textView_RPoint);
        Typeface customFont3 = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        myText3.setTypeface(customFont3);


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

