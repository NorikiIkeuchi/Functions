package com.example.draw.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.widget.TextView;

import java.util.UUID;

public class RankingActivity extends AppCompatActivity {

    private TextView mname1;
    private TextView mname2;
    private TextView mname3;
    private TextView mpoint1;
    private TextView mpoint2;
    private TextView mpoint3;
    int nannido;


    String returnValuen;
    int returnValuep;

    public static class User {
        public int rankingNo;
        public String name;
        public Double time;
        public String date;
        public String userId;

        // 空のコンストラクタの宣言が必須
        public User() {
        }

        public User(String _name, Double _time,String _date, String _userId) {
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
        setContentView(R.layout.activity_ranking);

        mname1 = (TextView) findViewById(R.id.name1);
        mname2 = (TextView) findViewById(R.id.name2);
        mname3 = (TextView) findViewById(R.id.name3);
        mpoint1 = (TextView) findViewById(R.id.point1);
        mpoint2 = (TextView) findViewById(R.id.point2);
        mpoint3 = (TextView) findViewById(R.id.point3);

        // とりあえずUserIdはUUIDで作成。（その後、毎回変わらないようにプリファランスに保存するなどが必要）
        String sUserId = UUID.randomUUID().toString();
        User user = new User("UMEHARA", 5.3, "2019-10-21 09:00:27", sUserId);
        // インスタンスの取得
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // ファイルパスを指定してリファレンスを取得
        DatabaseReference ref = database.getReference("info");
        // データを登録
        ref.child(sUserId).setValue(user);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            returnValuen = bundle.getString("name");
            returnValuep = bundle.getInt("point");
            nannido = bundle.getInt("難易度");
        }
        String st = String.valueOf(nannido);

        DatabaseReference refUser = database.getReference("info").child(sUserId).child("name");
        refUser.setValue(returnValuen);

        DatabaseReference reff = database.getReference("info").child(sUserId).child("time");
        reff.setValue(returnValuep);


        final DatabaseReference rank1_name = database.getReference("info").child(st).child("rank1").child("name");
        final DatabaseReference rank2_name = database.getReference("info").child(st).child("rank2").child("name");
        final DatabaseReference rank3_name = database.getReference("info").child(st).child("rank3").child("name");
        final DatabaseReference rank1_point = database.getReference("info").child(st).child("rank1").child("point");
        final DatabaseReference rank2_point = database.getReference("info").child(st).child("rank2").child("point");
        final DatabaseReference rank3_point = database.getReference("info").child(st).child("rank3").child("point");


        rank1_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String rank1name = dataSnapshot.getValue(String.class);
                rank2_name.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String rank2name = dataSnapshot.getValue(String.class);
                        rank3_name.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String rank3name = dataSnapshot.getValue(String.class);
                                rank1_point.addValueEventListener(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        final int rank1point = dataSnapshot.getValue(int.class);
                                        rank2_point.addValueEventListener(new ValueEventListener() {

                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                final int rank2point = dataSnapshot.getValue(int.class);
                                                rank3_point.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        int rank3point = dataSnapshot.getValue(int.class);
                                                                if(returnValuep >= rank1point) {
                                                                    rank3_name.setValue(rank2name);
                                                                    mname3.setText(rank2name);

                                                                    rank3_point.setValue(rank2point);
                                                                    String srank3point = String.valueOf(rank2point);
                                                                    mpoint3.setText(srank3point);

                                                                    rank2_name.setValue(rank1name);
                                                                    mname2.setText(rank1name);

                                                                    rank2_point.setValue(rank1point);
                                                                    String srank2point = String.valueOf(rank1point);
                                                                    mpoint2.setText(srank2point);

                                                                    rank1_name.setValue(returnValuen);
                                                                    mname1.setText(returnValuen);

                                                                    rank1_point.setValue(returnValuep);
                                                                    String srank1point = String.valueOf(returnValuep);
                                                                    mpoint1.setText(srank1point);

                                                                    try {
                                                                        Thread.sleep(50000000);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }


                                                                } else if((returnValuep >= rank2point)&&(returnValuep < rank1point)) {
                                                                    rank3_name.setValue(rank2name);
                                                                    mname3.setText(rank2name);

                                                                    rank3_point.setValue(rank2point);
                                                                    String srank5point = String.valueOf(rank2point);
                                                                    mpoint3.setText(srank5point);

                                                                    rank2_name.setValue(returnValuen);
                                                                    mname2.setText(returnValuen);

                                                                    rank2_point.setValue(returnValuep);
                                                                    String srank4point = String.valueOf(returnValuep);
                                                                    mpoint2.setText(srank4point);

                                                                    try {
                                                                        Thread.sleep(50000000);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                } else if ((returnValuep >= rank3point)&&(returnValuep < rank2point)) {
                                                                    rank3_name.setValue(returnValuen);
                                                                    mname3.setText(returnValuen);

                                                                    rank3_point.setValue(returnValuep);
                                                                    String srank6point = String.valueOf(returnValuep);
                                                                    mpoint3.setText(srank6point);

                                                                    try {
                                                                        Thread.sleep(500000000);
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }
                                                                String srank3point = String.valueOf(rank3point);
                                                                mpoint3.setText(srank3point);

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                                String srank2point = String.valueOf(rank2point);
                                                mpoint2.setText(srank2point);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        String srank1point = String.valueOf(rank1point);
                                        mpoint1.setText(srank1point);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                mname3.setText(rank3name);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        mname2.setText(rank2name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mname1.setText(rank1name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }
}

