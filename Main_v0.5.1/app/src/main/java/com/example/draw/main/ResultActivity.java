package com.example.draw.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {
    int sample;
    int point;
    String name;
    int nannido;

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


//        TextView myText5 = findViewById(R.id.buttonra);
//        Typeface customFont5 = Typeface.createFromAsset(getAssets(), "TanukiMagic.ttf");
//        myText5.setTypeface(customFont3);
//
//        txtname=(EditText)findViewById(R.id.txtname);
//        buttonStart=(Button)findViewById(R.id.buttonStart);
//        member=new Member();
//        reff=FirebaseDatabase.getInstance().getReference().child("Member");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                    maxid=(dataSnapshot.getChildrenCount());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        buttonStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                member.setName(txtname.getText().toString().trim());
//                reff.child(String.valueOf(maxid+1)).setValue(member);
//                Toast.makeText(StartActivity.this,"data",Toast.LENGTH_LONG).show();
//            }
//        });


        //Game1Activity.javaから得点を受け取り、表示する
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int point = bundle.getInt("point");
            nannido = bundle.getInt("難易度");
            if(point <= 0){
                point = 0;
            }
            if(point >= 100){
                point = 100;
            }

            TextView scoreText = (TextView) findViewById(R.id.textView_RPoint);
            scoreText.setText(String.format("%d", point));
            sample=point;
        }

        Button button = findViewById(R.id.buttonBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
        Button buttonra = findViewById(R.id.buttonra);
        buttonra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText editText = new EditText(ResultActivity.this);
                editText.setHint("名前入力欄");
                new AlertDialog.Builder(ResultActivity.this)
                        .setTitle("ランキング")
                        .setMessage("名前を入力して点数を登録")
                        .setView(editText)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(ResultActivity.this, RankingActivity.class);
                                String returnValue = editText.getText().toString();
                                intent.putExtra("name", returnValue);
                                intent.putExtra("point", sample);
                                intent.putExtra("難易度", nannido);
                                startActivity(intent);

                            }
                        }).show();
            }
        });


    }
}

