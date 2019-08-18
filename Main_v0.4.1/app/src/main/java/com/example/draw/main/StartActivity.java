package com.example.draw.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        TextView myText2 = findViewById(R.id.myText);
        Typeface customFont2 = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        myText2.setTypeface(customFont2);

        Button button = findViewById(R.id.buttonStart);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
        button.setTypeface(customFont);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
