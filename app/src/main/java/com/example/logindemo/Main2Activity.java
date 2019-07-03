package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {
    private TextView placeorder;
    private Button ordered;
    private EditText foodname;
    Member member;
    DatabaseReference reff;
   // Member member;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        placeorder = (TextView) findViewById(R.id.place);
        ordered = (Button) findViewById(R.id.placeorder);
        foodname = (EditText) findViewById(R.id.foodname);
        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        firebaseAuth = FirebaseAuth.getInstance();
        ordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameoffood = foodname.getText().toString().trim();
                member.setFoodname(nameoffood);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                member.setUsername(user.getEmail());
                reff.push().setValue(member);
                Toast.makeText(Main2Activity.this,"your order has been placed.",Toast.LENGTH_LONG).show();

            }
        });
    }
}
