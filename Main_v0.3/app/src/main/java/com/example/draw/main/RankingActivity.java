package com.example.draw.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //start画面への遷移
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RankingActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SaveTerminal() {
        String userId = "TARMINAL";
        String userName = "ターミナル";
        Double time = 99999.9;
        String date = "1999-01-01 00:00:00";

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("info");

        StartActivity.User user = new StartActivity.User(userName, time, date, userId);
        ref.child(userId).setValue(user);
    }
}
