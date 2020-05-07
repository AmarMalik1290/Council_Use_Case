package com.example.councilusecase;

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
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetpassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordEmail = (EditText)findViewById(R.id.etEmailForgot);
        resetpassword = (Button)findViewById(R.id.PasswordReset);
        mAuth = FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when a user is entering its not actually a string so we need to convert it to a string
                String useremail = passwordEmail.getText().toString().trim();
                //seeing if the user has entered anything
                if (useremail.equals("")){
                    Toast.makeText(PasswordActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                }else{
                    //checking if the email is already on the Firebase database
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this, "Password email sent", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(PasswordActivity.this, "Error sending Password reset", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }
}
