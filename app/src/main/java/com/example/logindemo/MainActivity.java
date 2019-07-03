package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
   // private TextView Info;
    private Button Login;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name =(EditText) findViewById(R.id.name);
        Password =(EditText)findViewById(R.id.password);
      //  Info =(TextView)findViewById(R.id.info);
        Login =(Button) findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this , SecondActivity.class));
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  member.setUser(Name.getText().toString());
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });


        userRegistration= (TextView) findViewById(R.id.info);
        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

    }
    private void validate(String userName , String userPassword){
        progressDialog.setMessage("please wait verifying");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){
                 progressDialog.dismiss();
                 Toast.makeText(MainActivity.this,"login sucessful", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(MainActivity.this , SecondActivity.class));

             }else{
                 progressDialog.dismiss();
                 Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();
             }
            }
        });

    }
}
