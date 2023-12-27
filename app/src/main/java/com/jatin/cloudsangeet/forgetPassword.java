package com.jatin.cloudsangeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetPassword extends AppCompatActivity {
    EditText forgetPassEmail;
    Button forgetPassButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        forgetPassEmail = findViewById(R.id.forgetPassEmail);
        forgetPassButton = findViewById(R.id.forgetPassButton);
        mAuth = FirebaseAuth.getInstance();

        forgetPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                email = String.valueOf(forgetPassEmail.getText());
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(forgetPassword.this, "email is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(forgetPassword.this, "Reset Link Sent", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(forgetPassword.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();Toast.makeText(forgetPassword.this, "Unknown Error",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}