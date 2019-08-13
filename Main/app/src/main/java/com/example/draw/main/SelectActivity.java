package com.example.draw.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SelectActivity extends AppCompatActivity {
    Button tyugaku;
    Button koukou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        tyugaku = findViewById(R.id.buttonTyugaku);
        koukou = findViewById(R.id.buttonKoukou);

        tyugaku.setOnClickListener(listener);
        koukou.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String[] items = {"一年生", "二年生", "三年生"};

            if(view == tyugaku){
                new AlertDialog.Builder(SelectActivity.this).setTitle("何年生？").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int nannido = 10 + which;
                        Intent intent = new Intent(SelectActivity.this, GamestartActivity.class);
                        intent.putExtra("難易度", nannido);
                        startActivity(intent);
                    }
                }).show();
            }else{
                new AlertDialog.Builder(SelectActivity.this).setTitle("何年生？").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int nannido = 13 + which;
                        Intent intent = new Intent(SelectActivity.this, GamestartActivity.class);
                        intent.putExtra("難易度", nannido);
                        startActivity(intent);
                    }
                }).show();
            }
        }
    };

}
