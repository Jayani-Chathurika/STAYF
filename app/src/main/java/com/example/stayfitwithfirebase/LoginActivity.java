package com.example.stayfitwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.stayfitwithfirebase.databinding.ActivityLoginBinding;
import com.example.stayfitwithfirebase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
FirebaseAuth firebaseAuth;
ProgressDialog progressDialog;
          @Override
          protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          binding=ActivityLoginBinding.inflate(getLayoutInflater());
          setContentView(binding.getRoot());

    firebaseAuth=FirebaseAuth.getInstance();
    progressDialog=new ProgressDialog(this);

    binding.login.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
        String email=binding.emailAddress.getText().toString().trim();
        String password=binding.password.getText().toString().trim();
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                progressDialog.cancel();
                Toast.makeText(LoginActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        }
    });
    binding.resetPassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view){
            String email=binding.emailAddress.getText().toString();
           progressDialog.setTitle("Sending Mail");
            progressDialog.show();
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
    }
    });
    binding.goToSignUpActivity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    });
    }
}