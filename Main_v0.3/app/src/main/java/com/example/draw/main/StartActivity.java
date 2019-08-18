package com.example.draw.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class StartActivity extends AppCompatActivity {


    public static class User {
        public int rankingNo;
        public String name;
        public Double time;
        public String date;
        public String userId;

        // 空のコンストラクタの宣言が必須
        public User() {
        }

        public User(String _name, Double _time,String _date,     String _userId) {
            name = _name;
            time = _time;
            date = _date;
            userId = _userId;
        }
        public void setRankingNo( int _rankingNo ){
            rankingNo = _rankingNo;
        }

        public Integer getRankingNo(){
            return rankingNo;
        }
        public String getName(){
            return name;
        }
        public Double getTime(){
            return time;
        }
        public String getDate(){
            return date;
        }
        public String getUserId(){
            return userId;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        String sUserId = null;
        User user = new User("まこto", 5.3, "2019-10-21 09:00:27", sUserId);
        // とりあえずUserIdはUUIDで作成。（その後、毎回変わらないようにプリファランスに保存するなどが必要）
        sUserId = UUID.randomUUID().toString();
        // インスタンスの取得
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // ファイルパスを指定してリファレンスを取得
        DatabaseReference ref = database.getReference("info");
        // データを登録
        ref.child(sUserId).setValue(user);

        Button button = findViewById(R.id.buttonStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }



}
