package com.example.councilusecase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private int counter = 5;
    private TextView userSignup;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private TextView forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog((this));

        //assigning the ID to a variable
        Username = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.etLogin);
        userSignup = (TextView)findViewById(R.id.tvReg);
        forgotpassword = (TextView)findViewById(R.id.tvForgotPassword);

        //If the user is already log in it will go straight to the main page
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));

        }


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });
        //When the user clicks on the "Need to sign up?" they will go to the Reg class
        userSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (MainActivity.this, RegActivity.class
                ));
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });

    }

    private void validate (String userName, String userPassword){

        progressDialog.setMessage("Please wait while you validate you!");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    progressDialog.dismiss();
                    if(counter ==0) {
                        Login.setEnabled(false);
                    }
                }
            }
        });

    }
}
