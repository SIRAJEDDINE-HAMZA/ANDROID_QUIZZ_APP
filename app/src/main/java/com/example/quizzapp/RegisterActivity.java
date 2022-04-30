package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {



    EditText REmail;
    EditText RPassword;
    EditText RConfirmPassword;
    Button register;
    FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        REmail = findViewById(R.id.r_email);
        RPassword = findViewById(R.id.rpassword);
        RConfirmPassword = findViewById(R.id.rconfirmPassword);
        register = findViewById(R.id.registerBtn);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = REmail.getText().toString();
                String password = RPassword.getText().toString();
                String confirmPassword = RConfirmPassword.getText().toString();

                if ( email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "UsernameoEmail or password or User cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(confirmPassword)){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"\"User registered successfully", Toast.LENGTH_SHORT).show();
                                Intent test = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(test);
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Registration Error" + Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this,"\"Password and Confirm Password are not matching", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}