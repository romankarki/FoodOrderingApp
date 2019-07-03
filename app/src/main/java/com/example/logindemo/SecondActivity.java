package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {
    private Button ordernow;
    private TextView logout;
    private TextView order;
    private TextView welcome;
    private ImageView food;
    DatabaseReference reff;
    Member member;
   FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toast.makeText(SecondActivity.this, "Welcome To Menu", Toast.LENGTH_LONG).show();
        ordernow = (Button) findViewById(R.id.ordernow);
        logout = (TextView) findViewById(R.id.logout);
        order = (TextView) findViewById(R.id.custom);
        welcome = (TextView)findViewById(R.id.welcome);
        food = (ImageView) findViewById(R.id.imageView3);
        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        firebaseAuth = FirebaseAuth.getInstance();

        ordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setFoodname("item 1");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                member.setUsername(user.getEmail());
                reff.push().setValue(member);
                Toast.makeText(SecondActivity.this, "order placed sucessfully",Toast.LENGTH_LONG).show();

            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, Main2Activity.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                Toast.makeText(SecondActivity.this, "logged out", Toast.LENGTH_LONG).show();
            }
        });

    }
}
