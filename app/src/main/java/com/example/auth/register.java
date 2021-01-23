package com.example.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    EditText mfullname , memail , mpassword , mphone;
    Button mregisterbtn;
    TextView mloginbtn;
    FirebaseAuth fauth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mfullname = findViewById(R.id.fullname);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mphone = findViewById(R.id.phone);
        mregisterbtn = findViewById(R.id.registerbtn);
        mloginbtn = findViewById(R.id.mloginbtn);
        fauth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fauth.getCurrentUser()!=null){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
finish();
        }

        mregisterbtn.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    memail.setError("email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("password is requried");
                 return;
                }
                if(password.length()<6){
                    mpassword.setError("password must be >=6 character");
                    return;

                }
                progressBar.setVisibility(View.VISIBLE);
                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this,"user created",Toast.LENGTH_SHORT).show(); ;
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{
                            Toast.makeText(register.this,"some error occured"+task.getException().getMessage(),Toast.LENGTH_SHORT).show(); ;

                        }
                    }
                });



            }
        });

    }
}
