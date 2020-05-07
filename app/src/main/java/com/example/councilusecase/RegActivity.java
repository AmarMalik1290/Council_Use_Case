package com.example.councilusecase;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.nfc.Tag;
        import android.os.Bundle;
        import android.util.Log;
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

public class RegActivity extends AppCompatActivity {

    private EditText RegName, RegPassword, RegEmail;
    private Button regbutton;
    private TextView RegLogin;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        setupUIViews();
        mAuth = FirebaseAuth.getInstance();


        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //converting the email and password to string (so then Firebase can read it)
                    String Reg_Email = RegEmail.getText().toString().trim();
                    String Reg_password = RegPassword.getText().toString().trim();
                    //Firebase email and Password
                    mAuth.createUserWithEmailAndPassword(Reg_Email, Reg_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d("Worked", "Sucessful Login");
                                startActivity(new Intent(RegActivity.this, SecondActivity.class));
                            }else{
                                //a Toast feature allows there to be a pop up like a notification
                                Toast.makeText(RegActivity.this, "UnSuccessful Login. Please try again", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });



                }
            }
        });
        RegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegActivity.this, MainActivity.class));
            }
        });
    }
    //creating a method for the variables to the id
    private void setupUIViews(){
        RegName = (EditText)findViewById(R.id.etRegUserName);
        RegPassword = (EditText)findViewById(R.id.etRegpassword);
        RegEmail = (EditText)findViewById(R.id.etRegEmail);

        regbutton = (Button)findViewById(R.id.etSignup);

        RegLogin = (TextView)findViewById(R.id.tvRegLogin);
    }
    //creating a true or false method to make sure all the information is entered
    private Boolean validate() {
        boolean result = false;

        String name = RegName.getText().toString();
        String password = RegPassword.getText().toString();
        String email = RegEmail.getText().toString();

        if (name.isEmpty() && password.isEmpty() && email.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else{
            result = true;
        }

        return result;
    }
}
