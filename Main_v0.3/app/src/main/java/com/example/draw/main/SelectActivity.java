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

    int nannido = 0;

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
            final String[] items = {"一年生", "二年生", "三年生"};
            final String[] items2 = {"レベル1","レベル2","レベル3","レベル4","レベル5"};

            //中学生
            if(view == tyugaku){
                new AlertDialog.Builder(SelectActivity.this).setTitle("何年生？").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nannido += 10 * (which + 1);

                        new AlertDialog.Builder(SelectActivity.this).setTitle("レベルを選択してください。").setItems(items2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                nannido += (which + 1);

                                Intent intent = new Intent(SelectActivity.this, GamestartActivity.class);
                                intent.putExtra("難易度", nannido);
                                startActivity(intent);
                            }
                        }).show();

                    }
                }).show();
            }else{ //高校生
                new AlertDialog.Builder(SelectActivity.this).setTitle("何年生？").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nannido += 100 + (10 * (which + 1));

                        new AlertDialog.Builder(SelectActivity.this).setTitle("レベルを選択してください。").setItems(items2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                nannido += (which + 1);

                                Intent intent = new Intent(SelectActivity.this, GamestartActivity.class);
                                intent.putExtra("難易度", nannido);
                                startActivity(intent);
                            }
                        }).show();

                    }
                }).show();
            }
        }
    };

}
